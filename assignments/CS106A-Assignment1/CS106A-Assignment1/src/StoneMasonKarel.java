/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The purpose of this program is to,
 * 		assuming some infinite number of beepers in Karel's bag
 * 		assuming Karel starts at (1, 1) facing east
 * 		assuming columns at 4 corners apart
 * 		assuming the top of columns is a wall
 * 		assuming the end of columns is a wall
 * 		assuming some columns have a beeper at (1, columnNumber)
 * fill columns at each column position such that each column is full of beepers
 * from (1, columnNumber) to a wall.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {
	
	public void run() {
		while (!frontIsBlocked()) {
			fillColumn();
			moveToColumn();
		}
		// OBOB Mitigation
		fillColumn();
	}
	
	/*
	 * Purpose: Fill a column of (1, columnPosition) from (1, columnPosition) to wall and return
	 * 	to initial position
	 * Conditions of use:
	 * Precondition: Starts at some (1, columnPosition) facing east
	 * Assumption: Fills column with beepers
	 * Postcondition: Ends at (1, columnPosition) facing east
	 */
	private void fillColumn() {
		turnLeft();
		while (!frontIsBlocked()){
			if (beepersPresent()) {
				move();
			} else {
				putBeeper();
				move();
			}
		}
		// OBOB Mitigation
		if (!beepersPresent()) {
			putBeeper();
		}
		// Go back
		turnAround();
		while (!frontIsBlocked()) {
			move();
		}
		turnLeft();
	}
	
	/*
	 * Purpose: Move to a column such that columnPosition is (position + 4, 1)
	 * Conditions of use:
	 * Precondition: Starts at position such that there is a column at (position + 4, 1)
	 * Assumption: Column to be filled at position
	 * Postcondition: Ends at (position + 4, 1)
	 */
	private void moveToColumn() {
		for (int i = 0; i < 4; i++) {
			move();
		}
	}

}
