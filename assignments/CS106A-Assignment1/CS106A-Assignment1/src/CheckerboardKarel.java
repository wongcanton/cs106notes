/*
 * File: CeckerBoardKarel.java
 * ---------------------------
 * The purpose of this program is to,
 * 		assuming Karel starts at (1, 1) in a world,
 * fill a world with beepers on every other corner.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	
	public void run() {
		fillColumnA();
		while (!frontIsBlocked()) {
			move();
			fillColumnB();
			if (!frontIsBlocked()) {
				move();
				fillColumnA();
			}
		}
	}
	/*
	 * Purpose: Ascend a column and fill with beepers on every other corner
	 * Conditions of use:
	 * Precondition: Starts at (columnPosition, 1) facing north and no beepers are present in column
	 * Assumption: Fills column with beepers on every other corner
	 * Postcondition: Ends at (columnPosition, columnMax)
	 */
	private void ascendColumn() {
		putBeeper();
		while (!frontIsBlocked()) {
			move();
			if (!frontIsBlocked()) {
				move();
				putBeeper();
			}
			
		}
	}
	
	/*
	 * Purpose: Go back down a column from (columnPosition, columnMax) to (columnPosition, 1)
	 * Conditions of use:
	 * Preconditon: Starts at (columnPosition, columnMax) facing north
	 * Assumption: Goes back down to (columnPosition, 1)
	 * Postcondition: Ends at (columnPosition, 1)
	 */
	private void descendColumn() {
		turnAround();
		while (!frontIsBlocked()) {
			move();
		}
		turnLeft();
	}
	
	/*
	 * Purpose: Fill a column of (columnPosition, 1) with beepers on every other corner,
	 * 		starting with corner (columnPosition. 1)
	 * Conditions of use:
	 * Precondition: Starts at (columnPosition, 1) facing east and no beepers are present in column
	 * Assumption: Fills column with beepers on every other corner
	 * Postcondition: Ends at (columnPosition, 1) facing east
	 */
	private void fillColumnA() {
		turnLeft();
		ascendColumn();
		descendColumn();
		
	}
	
	/*
	 * Purpose: Fill a column of (columnPosition, 1) with beepers on every other corner,
	 * 	starting with corner (1, columnPosition + 1)
	 * Conditions of use:
	 * Precondition: Starts at (columnPosition, 1) facing east and no beepers are present in column
	 * Assumption: Fills with beepers on every other corner
	 * Postcondition: Ends at (columnPosition, 1) facing east
	 */
	private void fillColumnB() {
		turnLeft();
		move();
		ascendColumn();
		descendColumn();
	}

}
