package crapsSimulation;

import java.util.Random;
import java.util.Scanner;

import state.Loss;
import state.Win;

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

			// COMPUTER'S ROUND
			wager = randomly_generate(MAX_WAGER);
			System.out.println("I wager $" + wager + " (Computer)");
			dice = new Dice();
			playRound(dice);

			if (dice.getState() instanceof Win)
				computer_winnings = computer_winnings + wager;
			else
				computer_winnings = computer_winnings - wager;
		}
	}

	public static int promptForNumGames() {
		Scanner scanner = new Scanner(System.in);
		String readLine = "";
		System.out.println("How many games would you like to play?");
		readLine = scanner.nextLine();
		return Integer.parseInt(readLine);
	}

	// prompts for and returns wager between 1 and max_wager dollars
	public static int getHumansWager(double max_wager) {
		Scanner scanner = new Scanner(System.in);
		String readLine = "";
		System.out.println("How much would you like to wager this round?");
		readLine = scanner.nextLine();
		int wager = Integer.parseInt(readLine);
		if (1 <= wager || wager <= 100)
			return wager;
		else
			return 1;

	}

	// generates random integer between 1 and max
	public static int randomly_generate(int max) {
		final int MIN_WAGER = 1;
		Random rand = new Random();
		return rand.nextInt(max - MIN_WAGER + 1) + MIN_WAGER;
	}

	public static void playRound(Dice dice) {
		// play until win or loss occurs
		while (!(dice.getState() instanceof Win) && !(dice.getState() instanceof Loss))
			dice.rollDice();
	}
}
