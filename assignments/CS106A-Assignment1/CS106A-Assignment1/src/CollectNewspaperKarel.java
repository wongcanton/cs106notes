/*
 * File: CollectNewspaperKarel.java
 * ---------------------------------
 * The purpose of this program is to,
 * 		assuming Karel starting in a predefined house:
 * Move to a newspaper (beeper)
 * Pick up the newspaper (beeper)
 * Move back to the initial position
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
	
	public void run() {
		moveToNewspaper();
		pickBeeper();
		goBackToInitialPosition();
	}
	
	/*
	 * Purpose:
	 * Move to a pre-defined position to pick up a newspaper (beeper)
	 * Conditions of use:
	 * Precondition: Start facing east at (3, 4)
	 * Assumption: Newspaper is a beeper at (6, 3)
	 * Postcondition: End facing east at (6, 3)
	 */
	private void moveToNewspaper() {
		move();
		move();
		turnRight();
		move();
		turnLeft();
		move();
	}
	
	/*
	 * Purpose:
	 * Move from a pre-defined position back to a pre-defined initial position
	 * Conditions of use:
	 * Precondition: Start facing east at (6, 3)
	 * Assumption: Initial position is facing east at (3, 4)
	 * Postcondition: End facing east at (3, 4)
	 */
	private void goBackToInitialPosition() {
		turnAround();
		move();
		turnRight();
		move();
		turnLeft();
		move();
		move();
		turnAround();
	}
}
