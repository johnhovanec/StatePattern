package crapsSimulation;

import java.util.Random;
import states.State;

public class Dice {
	// Context class - current roll of the dice
	private int current_roll;
	private State objState;

	public Dice() {
		objState = State.InitialState(this);
	}

	public void setState(State newState) {
		objState = newState;
	}

	public State getState() {
		return objState;
	}

	public int getCurrentRoll() {
		return current_roll;
	}

	public void rollDice() {
		current_roll = generateRoll();
		System.out.println("rolled a " + current_roll);
		objState.roll_dice();
	}

	// randomly generated number between 2-12
	private int generateRoll() {
		final int MIN = 2;
		final int MAX = 12;
		Random rand = new Random();
		return rand.nextInt(MAX - MIN + 1) + MIN;
	}
}
