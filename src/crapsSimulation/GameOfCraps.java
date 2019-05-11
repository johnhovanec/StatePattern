package crapsSimulation;

import java.util.Random;
import java.util.Scanner;
import states.ComingOutState;
import states.Loss;
import states.Win;

public class GameOfCraps {

	public static void main(String[] args) {

		int wager;
		int human_winnings = 0;
		int computer_winnings = 0;
		int num_games_to_play;
		final int MAX_WAGER = 100;
		Dice dice;

		num_games_to_play = promptForNumGames();
		for (int i = 1; i <= num_games_to_play; i++) {

			// HUMAN'S ROUND
			wager = getHumansWager(MAX_WAGER);
			dice = new Dice();
			playRound(dice);

			if (dice.getState() instanceof Win)
				human_winnings = human_winnings + wager;
			else
				human_winnings = human_winnings - wager;

			dice.setState(new ComingOutState(dice));

			// COMPUTER'S ROUND
			wager = randomly_generate(MAX_WAGER);
			System.out.println("\nI wager $" + wager + " (Computer)");
			dice = new Dice();
			playRound(dice);

			if (dice.getState() instanceof Win)
				computer_winnings = computer_winnings + wager;
			else
				computer_winnings = computer_winnings - wager;

			System.out.println("Your current winnings: $" + human_winnings);
			System.out.println("My current winnings: $" + computer_winnings);
			dice.setState(new ComingOutState(dice));
		}

		if (human_winnings > computer_winnings)
			System.out.println("GAME OVER -- Humans Rule!");
		else if (computer_winnings > human_winnings)
			System.out.println("GAME OVER -- Computers Rule!");
		else
			System.out.println("GAME OVER -- Humans and Computers rule equally!");
	}

	private static int promptForNumGames() {
		Scanner scanner = new Scanner(System.in);
		String readLine = "";
		System.out.println("How many games would you like to play?");
		readLine = scanner.nextLine();
		return Integer.parseInt(readLine);
	}

	private static int getHumansWager(double max_wager) {
		Scanner scanner = new Scanner(System.in);
		String readLine = "";
		System.out.println("How much would you like to wager this round?");
		readLine = scanner.nextLine();
		int wager = Integer.parseInt(readLine);
		if (1 <= wager && wager <= max_wager)
			return wager;
		else
			return 1;
	}

	// generates random integer between 1 and max
	private static int randomly_generate(int max) {
		final int MIN_WAGER = 1;
		Random rand = new Random();
		return rand.nextInt(max - MIN_WAGER + 1) + MIN_WAGER;
	}

	private static void playRound(Dice dice) {
		// play until win or loss occurs
		while (!(dice.getState() instanceof Win) && !(dice.getState() instanceof Loss))
			dice.rollDice();
	}
}
