/*
 * File: MidpointFinderKarel.java
 * ------------------------------
 * The purpose of this program is to,
 * 		assuming Karel starts at (1, 1) in a world,
 * find the midpoint in a world (if even, pick a point on either side closest to the midpoint)
 * and place a beeper, then stop.
 */

import stanford.karel.*;

public class MidpointFinderKarel extends SuperKarel {
	
	public void run() {
		goToMidpoint();
		putBeeper();

	}
	/*
	 * Purpose: Move to the midpoint of a world
	 * Conditions of use:
	 * Precondition: Start facing east at (1, 1)
	 * Assumption: Moves to midpoint of world by moving backward one corner for every 2 corners forward
	 * Postcondition: Ends facing east at midpoint 
	 */
	private void goToMidpoint() {
		if (!frontIsBlocked()) {
			move();
			if (!frontIsBlocked()) {
				move();
			}
			goToMidpoint();
			turnAround();
			move();
			turnAround();
		}
// Not sure why this works though.
	}

}
