package states;

import crapsSimulation.Dice;

public class PointState extends State {
// ------------------------------------------------------------------------
// Second roll of dice:
//    7 Loss ("seven out")
//    match of previous roll Win ("hits the points")
//    any other, roll again
// ------------------------------------------------------------------------    
	private int points_to_match;

	public PointState(State source) {
		super(source);
		points_to_match = source.getContext().getCurrentRoll();
	}

	public PointState(Dice dice) {
		super(dice);
		System.out.println("** Invalid State Transition Exception **");
		System.exit(0);
	}

	public void transitionState() {
		// transitions to either a Win or Loss state
		int roll = this.getContext().getCurrentRoll();

		if (roll ==  points_to_match)
			getContext().setState(new Win(this));
		else if (roll == 7)
			getContext().setState(new Loss(this));
	}
}
