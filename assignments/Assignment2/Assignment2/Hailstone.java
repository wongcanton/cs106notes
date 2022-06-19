/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * The purpose of this program is to read a positive integer from
 * a user and output the Hailstone sequence for that number.
 */


import acm.program.*;

public class Hailstone extends ConsoleProgram {
	/**
	 * Method: isEven
	 * @param n int n
	 * @return boolean true is n is even, boolean false if n is odd
	 * isEven is adapted from Eric Roberts' text, 
	 * The Art and Science of Java, p. 113
	 */
	private boolean isEven(int n) {
		return (n % 2 == 0);
	}
	
	/**
	 * Method: hailstone
	 * Processes argument n and returns (n / 2) if n is even,
	 * (3n + 1) if n is odd. Prints operation to console as side
	 * effect.
	 * @param n int n
	 * @return (n / 2) if n is even, (3n + 1) if n is odd
	 */
	private int hailstone(int n) {
		if (isEven(n)) {
			println(n + " is even so I take half: " + (n / 2));
			return (n / 2);
		} else {
			println(n + " is odd so I make 3n + 1: " + ((3 * n) + 1));
			return ((3 * n) + 1);
		}
	}
	
	public void run() {
		int n = readInt("Enter a number: ");
		int steps = 0;
		while (n != HAILSTONE) {
			n = hailstone(n);
			steps++;
		}
		println("The process took " + steps + " to reach 1.");
	}
	
	/** Ending value for Hailstone */
	private static final int HAILSTONE = 1;
}

