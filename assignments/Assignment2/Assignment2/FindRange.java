/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * The purpose of this program is to read input from a user until a sentinel
 * is reached, then print the smallest and largest numbers entered.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	/**
	 * Method: outputValues
	 * Prints the smallest and largest numbers.
	 * @param smallest int
	 * @param largest int
	 * @returns void
	 */
	private void outputValues(int smallest, int largest) {
		println("smallest: " + smallest);
		println("largest: " + largest);
	}
	
	
	public void run() {
		println("This program finds the largest and smallest numbers.");
		int smallest = SENTINEL;
		int largest = SENTINEL;
		int input = SENTINEL;
		while (true) {
			input = readInt("Enter a number: ");
			if (input == SENTINEL) {
				if (smallest == SENTINEL && largest == SENTINEL) {
					println("Congradulations, you guessed the sentinel " +
							"on the first try!");
					break;
				} else {
					outputValues(smallest, largest);
					break;
				}
			}
			if (input < smallest || smallest == SENTINEL) {
				smallest = input;
			} 
			if (input > largest || largest == SENTINEL) {
				largest = input;
			}
		}
	}
	
	
	private static final int SENTINEL = 0;
}

