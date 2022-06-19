/*
 * File: TestYahtzee.java
 * ----------------------
 * This file contains a class definition of a class used for testing the
 * Yahtzee game and related classes.
 */

import acm.program.*;
import java.util.*;

public class TestYahtzee extends ConsoleProgram implements YahtzeeConstants {
	
	/**
	 * Method: run
	 * Runs the tester.
	 */
	public void run() {
		println("This is a test for the Yahtzee game.");
		int category = 0;
		int score = 0;
		int[] rollArray;
		while (true) {
			roll = new ArrayList<Integer>();
			rollArray = new int[N_DICE];
			for (int i = 0; i < 5; i++) {
				roll.add(readInt("Enter a value for roll value " + (i + 1) + ": "));
				if (roll.contains(0)) break;
			}
			while (true) {
				while (true) {
					category  = readInt("Enter a category to test: ");
					if (category > 17) {
						println("Invalid category, try again.");
					} else break;
				}
				
				if (category == 0) break;
				for (int i = 0; i < roll.size(); i++) {
					rollArray[i] = roll.get(i);
				}
				score = scorer.checkCategory(rollArray, category);
				println(
					"Your score for category " + 
					category + 
					" is: " + 
					score
				);

			}
			if (roll.contains(0)) break;
		}
		
	}
	
	
	/* Instance variables. */
	private ArrayList<Integer> roll;
	private YahtzeeScorer scorer = new YahtzeeScorer();
}
