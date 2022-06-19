/*
 * File: YahtzeeScorer.java
 * ------------------------
 * This file contains the class definition for YahtzeeScorer used in a game
 * 	of Yahtzee.
 */

import java.util.*;

public class YahtzeeScorer implements YahtzeeConstants {
	public YahtzeeScorer() {
		this.score = 0;
	}
	
	
	/**
	 * Method: checkCategory
	 * Takes a case to test the roll against, assesses the condition of whether
	 * 	the roll passes the case, and then returns a score of 0 if the roll
	 * 	fails the case, or a value greater than 0. The value greater than zero
	 * 	is computed based on the category's rule.
	 * @param int[] roll: int array representing a roll.
	 * @param int category: int representing a category to test.
	 * @return int score: int with value of corresponding to pass in category,
	 * 	and computed value based on case success.
	 */
	public int checkCategory(int[] roll, int category) {
		// Convert the roll into an ArrayList for ease of use.
		rollList = new ArrayList<Integer>();
		for (int i = 0; i < roll.length; i++) {
			rollList.add(roll[i]);
		}
		
		switch (category) {
			case ONES:
				/* Number of ones. */
				score = sumValueOnDice(rollList, roll, ONES);
				break;
				
			case TWOS:
				/* Number of twos. */
				score = sumValueOnDice(rollList, roll, TWOS);
				break;
				
			case THREES:
				/* Number of threes. */
				score = sumValueOnDice(rollList, roll, THREES);
				break;
				
			case FOURS:
				/* Number of fours. */
				score = sumValueOnDice(rollList, roll, FOURS);
				break;
				
			case FIVES:
				/* Number of fives. */
				score = sumValueOnDice(rollList, roll, FIVES);
				break;
				
			case SIXES:
				/* Number of sixes. */
				score = sumValueOnDice(rollList, roll, SIXES);
				break;
			
			case THREE_OF_A_KIND:
				/* Contains three of a kind. */
				if (isContainingMultiple(rollList, 3)) {
					score = sumDice(roll);
				} else score = 0;
				break;
			
			case FOUR_OF_A_KIND:
				/* Contains four of a kind. */
				if (isContainingMultiple(rollList, 4)) {
					score = sumDice(roll);
				} else score = 0;
				break;
			
			case FULL_HOUSE:
				/* Contains three of a kind and two of a kind. */
				if (
					isContainingMultiple(rollList, 3) && 
					isContainingMultiple(rollList, 2)
				) {
					score = 25;
				} else score = 0;
				break;
			
			case SMALL_STRAIGHT:
				/* Contains 4 consecutive values. */
				if (countConsecutive(rollList) >= 4) {
					score = 30;
				} else score = 0;
				break;
			
			case LARGE_STRAIGHT:
				/* Contains 5 consecutive values. */
				if (countConsecutive(rollList) >= 5) {
					score = 40;
				} else score = 0;
				break;
			
			case YAHTZEE:
				/* Contains 5 of a kind. */
				if (isContainingMultiple(rollList, 5)) {
					score = 50;
				} else score = 0;
				break;
			
			case CHANCE:
				/* Sum of dice values. */
				score = sumDice(roll);
				break;

			default:
				score = 0;
				break;
		}
		return score;
	}
	
	
	/**
	 * Method: sumDice
	 * Takes a roll and sums the values of the dice in the roll.
	 * @param int[] roll: int array representing a roll.
	 * @return int total: int representing the sum of values on the dice.
	 */
	private int sumDice(int[] roll) {
		int total = 0;
		for (int i = 0; i < roll.length; i++) {
			total += roll[i];
		}
		
		return total;
	}
	
	
	/**
	 * Method: sumValueOnDice
	 * Takes a roll and a number to count, and returns how many of that number
	 * 	are in the roll.
	 * @param ArrayList<Integer> roll: ArrayList containing 5 Integers
	 * 	from 1 to 6.
	 * 
	 * @param int counter: int to count.
	 * @return int total: int representing total number of counter in roll.
	 */
	private int sumValueOnDice(
			ArrayList<Integer> rollList, 
			int[] roll, 
			int counter
		) {
		int total = 0;
		if (rollList.contains(counter)) {
			for (int i = 0; i < roll.length; i++) {
				if (roll[i] == counter) {
					total += roll[i];
				}
			}
		} else {
			total = 0;
		}
		
		return total;
	}
	

	
	/**
	 * Method: isContainingMultiple
	 * Takes a roll, a number of times to find value, and returns a boolean
	 * of whether one value is found the given number of times in the roll.
	 * @param ArrayList<Integer> roll: ArrayList containing 5 Integers with
	 * 	values from 1 to 6.
	 * @param count: int of number of times to count the value in the roll.
	 * @return boolean isContaining: boolean value of whether an integer is
	 * 	found count number of times in the roll.
	 */
	private boolean isContainingMultiple(
			ArrayList<Integer> rollList, 
			int count
	) {
		boolean isContaining= false;
		int toRemove = 0;
		// Cycle through 1-6 to serve as value.
		for (int i = 1; i < 7; i++) {
			/*
			 * Assign isContaining to be true if value in roll, until
			 * it is not. Remove values from ArrayList after testing.
			 */
			for (int j = 0; j < count; j++) {
				if (rollList.contains(i)) {
					isContaining = true;
					toRemove = rollList.indexOf(i);
					rollList.remove(toRemove);
					if (j == count - 1) return isContaining;
				} else {
					isContaining = false;
					break;
				}
			}
		}
		
		return isContaining;
	}
	
	
	/**
	 * Method: countConsecutive
	 * Takes a roll and counts how many consecutive values are in the roll.
	 * @param ArrayList<Integer> rollList: ArrayList having 5 Integers with values
	 * 	from 1 to 6.
	 * @return int counter: int representing number of consecutive values in
	 * 	the roll.
	 */
	private int countConsecutive(ArrayList<Integer> rollList) {
		int count = 0;
		for (int i = 1; i < 7; i++) {
			if (rollList.contains(i)) {
				count++;
				if (!rollList.contains(i + 1)) {
					break;
				}
			}
		}
		
		return count;
	}
		
	
	/* Instance Variables */
	private int score;
	private ArrayList<Integer> rollList;
}
