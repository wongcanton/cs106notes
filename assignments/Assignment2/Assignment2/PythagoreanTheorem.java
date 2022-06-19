/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This purpose of this program is to take input from a user of values A and B
 * and return a double precision value to the console that is the square
 * root of the addition of A squared and B squared.
 */

/*
 * Decomposition
 * readInt
 * readInt
 * pythag(int, int)
 * println(pythag)
 */

import acm.program.*;
import java.lang.Math.*;

public class PythagoreanTheorem extends ConsoleProgram {
	/**
	 * Method: pythagoreanTheorem
	 * Computes and returns the c value for c^2 = sqrt(a^2 + b^2)
	 * @param a int
	 * @param b int
	 * @return double that is Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2))
	 */
	private double pythagoreanTheorem(int a, int b) {
		return (Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2)));
	}
	
	public void run() {
		// Introduce the user to the program
		println("Enter values to compute Pythagorean theorem.");
		int a = readInt("a: ");
		int b = readInt("b: ");
		println("c = " + pythagoreanTheorem(a, b));
	}
}
