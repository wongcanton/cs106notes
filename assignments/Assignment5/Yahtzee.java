/*
 * File: Yahtzee.java
 * ------------------
 * This program plays the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	/**
	 * Method: initCategories
	 * Initializes an array for category tracking.
	 * @param None.
	 * @return void.
	 */
	private void initCategories() {
		for (int i = 0; i < upperCategories.length; i++) {
			uppers.add(upperCategories[i]);
		}
		for (int i = 0; i < lowerCategories.length; i++) {
			lowers.add(lowerCategories[i]);
		}
	}
	
	public void run() {
		// Get number of players, and names.
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];		
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		
		//Make players.
		players = new YahtzeePlayer[nPlayers];
		for (int i = 0; i < nPlayers; i++) {
			players[i] = new YahtzeePlayer(playerNames[i]);
		}
		
		// Play game.
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		// Initialize values.
		int turns = 0;
		int winner;
		initCategories();
		
		// Do turns for all players.
		while (turns < N_TURNS) {
			for (int i = 0; i < nPlayers; i++) {
				doTurn(i);
			}
			turns++;
		}
		
		// Finalize scores for all players.
		for (int i = 0; i < nPlayers; i++) {
			finalizeScores(i);
		}
		winner = determineWinner();
		
		display.printMessage(
			players[winner].getName() + 
			", you won with a score of " +
			players[winner].getTotal() + "!"
		);
	}
	
	/**
	 * Method: doTurn
	 * Runs a turn for a player.
	 * @param int player: int representing the player.
	 * @return void
	 */
	private void doTurn(int player) {
		display.printMessage(
			players[player].getName() + "'s turn to roll!"
		);
		roll = rollPlayer(player);
		display.printMessage(
			players[player].getName() + " select a category."
		);
		scorePlayer(player, roll);
	}
	

	/**
	 * Method: diceRoll
	 * Takes an integer array of size N_DICE and uses a pseudorandrom number
	 * 	generator to generate an integer from 1 to 6 for each element in the
	 * 	array noted to need a roll. The elements in the array are noted to be
	 * 	rolled if an array of size N_DICE is passed with true values at the
	 * 	corresponding index. The parameter array of rolls is modified in place.
	 * @param int[] roll: int array of length N_DICE that has values from
	 * 	1 to 6 for each element in the array.
	 * @param boolean[] isRollNeeded: boolean array of length N_DICE that has
	 * 	values true at each index if user noted values at the same index within
	 * 	the roll array need a re-roll.
	 * @return void The argument roll array is modified in place.
	 */
	private void diceRoll(int[] roll, boolean[] isRollNeeded) {		
		// Roll the dice where isRollNeeded is true for the roll index.
		for (int i = 0; i < roll.length; i++) {
			if (isRollNeeded[i]) {
				roll[i] = rgen.nextInt(1, 6);
			}
		}
	}
	
	
	/**
	 * Method: displayDice
	 * Displays a dice roll to the display.
	 * @param int[] roll: int array of length N_DICE that has values from 1 to
	 * 	6 for each element in the array
	 * @return void
	 */
	private void displayDice(int[] roll) {
		display.displayDice(roll);
	}
	
	
	/**
	 * Method: clearDiceStatus
	 * Takes an boolean array and sets all values to false.
	 * @param boolean[] isRollNeeded: boolean array.
	 * @return void The argument isRollNeeded array is modified in place.
	 */
	private void clearDiceStatus(boolean[] isRollNeeded) {
		for (int i = 0; i < isRollNeeded.length; i++) {
			isRollNeeded[i] = false;
		}
	}
	
	
	/**
	 * Method: rollPlayer
	 * Handles user interaction and performs rolls necessary for a turn in
	 * 	Yahtzee. Takes an int for the player index to roll. Returns the final
	 * 	dice rolls in an int array.
	 * @param int player: int representing the player index. Player indices
	 * 	can be any number greater than or equal to zero.
	 * @return int[] roll: int array of length N_DICE that has values from 1 to
	 * 	6 for each element in the array.
	 */
	private int[] rollPlayer(int player) {
		// Initialize the roll and dice to roll.
		int[] roll = new int[N_DICE];
		boolean[] isRollNeeded = {true, true, true, true, true, true};

		/*
		 * Once player clicks roll, do roll for all dice.
		 * Note that the display methods need player + 1 since they
		 * start with index 1.
		 */
		display.waitForPlayerToClickRoll(player + 1);
		diceRoll(roll, isRollNeeded);
		displayDice(roll);
		// Un-select all dice for re-roll.
		clearDiceStatus(isRollNeeded);
		
		// Let player re-roll two times.
		for (int i = 0; i < 2; i++) {
			display.printMessage(
				playerNames[player] + 
				" select dice for reroll and select 'Roll Again'."
			);
			display.waitForPlayerToSelectDice();
			// Reassign isRollNeeded to value of isDieSelected for each die.
			for (int j = 0; j < isRollNeeded.length - 1; j++) {
				isRollNeeded[j] = display.isDieSelected(j);
			}
			// Roll dice, clear dice re-roll status, and update dice display.
			diceRoll(roll, isRollNeeded);
			clearDiceStatus(isRollNeeded);
			displayDice(roll);
		}
		return roll;
	}
	
	
	/**
	 * Method: scorePlayer
	 * Handles user interaction and handles player scoring for a given roll.
	 * 	The user selects a category for their roll, and the score is displayed
	 * 	for the category based on the validity of the category with the roll.
	 * 	The roll is asserted here, and if true, a score greater than 0 is
	 * 	updated; otherwise the score for the category is assigned 0.
	 * @param int player: int representing the player index. Player indices
	 * 	can be any number greater than or equal to zero.
	 * @param int[] roll: int array representing the player's roll. 
	 * 	Size of N_DICE.
	 * @return void The player's total score are modified in place!
	 */
	private void scorePlayer(int player, int[] roll) {
		// Initialize variables.
		int score = 0;
		int category = 0;
		// Wait for the user to select a category, and assign category.
		while (true) {
			category = display.waitForPlayerToSelectCategory();
			// Only move forward if category not selected before.
			if (!players[player].isInSelectedCategories(category)) {
				players[player].addSelectedCategory(category);
				score = scorer.checkCategory(roll, category);
				// Break only if the category was unselected before.
				break;
			} else {
				display.printMessage(
					players[player].getName() +
					" try again, that category was already selected."
				);
			}
		}
		// Display method needs player starting index of 1.
		display.updateScorecard(category, player + 1, score);
		
		//Update upper or lower score.
		if (uppers.contains(category)) {
			players[player].updateUpperScore(score);
		}
		if (lowers.contains(category)) {
			players[player].updateLowerScore(score);
		}
		
		// Update total.
		players[player].updateTotal(score);
		display.updateScorecard(
			TOTAL, 
			player + 1, 
			players[player].getTotal()
		);
	}
	
	
	/**
	 * Method: finalizeScores
	 * Adds the upper and lower scores to the board, and changes the player's
	 * 	total score with the upper bonus.
	 * @param int player: int representing the player.
	 * @return void
	 */
	private void finalizeScores(int player) {
		// Display scores.
		display.updateScorecard(
			UPPER_SCORE,
			player + 1,
			players[player].getUpperScore()
		);
		display.updateScorecard(
			UPPER_BONUS,
			player + 1,
			players[player].getUpperBonus()
		);
		display.updateScorecard(
			LOWER_SCORE,
			player + 1,
			players[player].getLowerScore()
		);
		// Update total score and display it.
		players[player].updateTotal(
			players[player].getUpperBonus()
		);
		display.updateScorecard(
			TOTAL,
			player + 1,
			players[player].getTotal()
		);
		
	}
	
	
	/**
	 * Method: determineWinner
	 * Returns the index of the player who has the highest score.
	 * @param None.
	 * @return int winner: int index of player with highest total score.
	 */
	private int determineWinner() {
		// Initialize winner.
		int winner = 0;
		for (int i = 0; i < nPlayers; i++) {
			if (players[i].getTotal() > players[winner].getTotal() ) {
				winner = i;
			}
		}
		return winner;
	}
	
		
/* Private instance variables */
	private int nPlayers;
	private int[] roll = new int[N_DICE];
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private YahtzeePlayer[] players;
	private YahtzeeScorer scorer = new YahtzeeScorer();
	private int[] upperCategories = {
		ONES, 
		TWOS, 
		THREES, 
		FOURS, 
		FIVES, 
		SIXES
	};
	private int[] lowerCategories = {
		THREE_OF_A_KIND, 
		FOUR_OF_A_KIND, 
		FULL_HOUSE, 
		SMALL_STRAIGHT, 
		LARGE_STRAIGHT, 
		YAHTZEE, 
		CHANCE
	};
	private ArrayList<Integer> uppers = new ArrayList<Integer>();
	private ArrayList<Integer> lowers = new ArrayList<Integer>();

/* Constants */
	private static final int N_TURNS = 13;
}
