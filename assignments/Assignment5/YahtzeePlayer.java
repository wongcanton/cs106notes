/*
 * File: YahtzeePlayer.java
 * ------------------------
 * This file contains the class definition for a YahtzeePlayer used in a game
 * 	of Yahtzee.
 */

import java.util.*;

public class YahtzeePlayer {
	public YahtzeePlayer(String playerName) {
		this.name = playerName;
		this.upperScore = 0;
		this.upperBonus = 0;
		this.lowerScore = 0;
		this.total = 0;
	}
	
	
	/**
	 * Method: addSelectedCategory
	 * Takes a category to test and stores the category in ArrayList
	 * 	selectedCategories.
	 * @param int category: int representing a category selected by the
	 * 	user.
	 * @return void The selectedCategories is modified in place!
	 */
	public void addSelectedCategory(int category) {
		this.selectedCategories.add(category);
	}
	
	
	/**
	 * Method: isInSelectedCategories
	 * Takes a category, and returns a boolean isAlreadySelected with a
	 * 	value of true if the category is in selectedCategories. Otherwise
	 *	returns false.
	 * @param int category: int representing a category selected by the
	 * 	user.
	 * @return boolean: boolean true if category is in selectedCategories.
	 */
	public boolean isInSelectedCategories(int category) {
		return selectedCategories.contains(category);
	}
	
	
	/**
	 * Method: getTotal
	 * Returns the total score for the player.
	 * @return int total: int representing the total score of the player.
	 */
	public int getTotal() {
		return this.total;
	}
	
	
	/**
	 * Method: updateTotal
	 * Takes a score to add to the player's total, and adds the score to
	 * 	the total.
	 * @param int score: int to add to the total score of the player.
	 * @return void The total is modified in place!
	 */
	public void updateTotal(int score) {
		this.total += score;
	}
	
	
	/**
	 * Method: updateUpperScore
	 * Takes a score to add to the player's upper score, and adds the score to
	 * 	the upper score. If the upper score is greater than 63, the
	 * 	upperBonus is assigned a value of 35.
	 * @param int score: int to add to the upper score of the player.
	 * @return void The upper score is modified in place!
	 */
	public void updateUpperScore(int score) {
		this.upperScore += score;
		if (this.upperScore >= 63) this.upperBonus = 35;
	}
	
	
	/**
	 * Method: getUpperScore
	 * Returns the player's upper score.
	 * @param None
	 * @return int upperScore: int representing the upper score for the player.
	 */
	public int getUpperScore() {
		return this.upperScore;
	}
	
	
	/**
	 * Method: getUpperBonus
	 * Returns the player's upper bonus.
	 * @param None
	 * @return int upperBonus: int representing the upper bonus for the player.
	 */
	public int getUpperBonus() {
		return this.upperBonus;
	}
	
	
	/**
	 * Method: updateLowerScore
	 * Takes a score to add to the player's upper score, and adds the score to
	 * 	the upper score.
	 * @param int score: int to add to the upper score of the player.
	 * @return void The lower score is modified in place!
	 */
	public void updateLowerScore(int score) {
		this.lowerScore += score;
	}
	
	
	/**
	 * Method: getLowerScore
	 * Returns the player's lower score.
	 * @param None
	 * @return int upperScore: int representing the lower score for the player.
	 */
	public int getLowerScore() {
		return this.lowerScore;
	}
	
	
	/**
	 * Method: getName
	 * Returns the name of the player.
	 * @return String player: String representing the name of the player.
	 */
	public String getName() {
		return name;
	}
	

	/* Instance Variables */
	private String name;
	private ArrayList<Integer> selectedCategories = new ArrayList<Integer>();
	private int total;
	private int upperScore;
	private int upperBonus;
	private int lowerScore;
}
