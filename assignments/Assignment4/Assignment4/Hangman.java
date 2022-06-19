/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

/*
 * Decomposition:
 * 1. Run program that gets user input of characters until 8 guesses
 * 2. Run program and checks user input against selected word
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.util.*;

public class Hangman extends ConsoleProgram {

    /** Maximum number of wrong guesses from user */
	private static final int MAX_GUESSES = 8;
	
	/**
	 * Method: readLetter
	 * Reads a single character by the user and returns the value
	 * as an upper or lower case char.
	 * @param consoleText String consoleText: String to print to the console.
	 * @param whichCase String case: "upper" if to return upper case,
	 * "lower" if to return lower case
	 * @return userLetter String userLetter: single character string
	 * input by the user.
	 */
	public String readLetter(String consoleText, String whichCase) {
		String userInput = "";
		String userLetter = "";
		char userChar;
		while (true) {
			userInput = readLine(consoleText);
			if (userInput.length() == 1) {
				userChar = userInput.charAt(0);
				if (Character.isLetter(userChar)) {
					if (whichCase == "upper") {
						userInput = userInput.toUpperCase();
					} else if (whichCase == "lower") {
						userInput = userInput.toLowerCase();
					}
					userLetter = userInput.substring(0, 1);
					break;
				} else {
					println("Not a valid letter. Try again.");
				}
			} else {
				println("Not a single character. Try again.");
			}
		
		}
		return userLetter;
	}
	
	/**
	 * Method: wordStatus
	 * Outputs a string the length of the hangman word with letters
	 * yet to be guessed represented with a "-".
	 * @param listLetters ArrayList<String> listLetters: arrayList of
	 * guessed letters
	 * @return wordStatus String wordStatus
	 */
	public String wordStatus(ArrayList listLetters) {
		String wordStatus = "";
		for (int i = 0; i < listLetters.size(); i++) {
			wordStatus += listLetters.get(i);
		}
		return wordStatus;
	}
	
	/**
	 * Method: isCorrectGuess
	 * Predicate method that returns true if the user's character guess
	 * is within the selected hangman word.
	 * @param guessIndex int guessIndex: Return value of
	 * hangman.indexOf(String userGuess)
	 * @return isCorrectGuess boolean isCorrectGuess: true if value not -1
	 */
	public boolean isCorrectGuess(int guessIndex) {
		return guessIndex != -1;
	}
	
	/**
	 * Method: applyGuess
	 * Applies a correctly guessed letter in a hangman game to the word status
	 * of the hangman word, including updating duplicate letters.
	 * @param word String word: hangman word to guess
	 * @param wordStatus ArrayList<String> wordStatus: status of guessed word
	 * characters
	 * @param userGuess String userGuess: one character string representing
	 * user guess
	 * @return void
	 */
	public void applyGuess(
			String word,
			ArrayList<String> wordStatus,
			String userGuess
		) {
		int correctGuessIndex = word.indexOf(userGuess);
		while (isCorrectGuess(correctGuessIndex)) {
			wordStatus.set(correctGuessIndex, userGuess);
			correctGuessIndex = word.indexOf(userGuess, correctGuessIndex + 1);
		}
	}
	
	/**
	 * Method: collapseStatus
	 * Collapses one character strings of an ArrayList to a single String.
	 * @param wordStatus ArrayList<String> wordStatus: ArrayList of one
	 * character strings.
	 * @return word String word: Collapsed word.
	 */
	private String collapseStatus(ArrayList<String> wordStatus) {
		String word = "";
		for (int i = 0; i < wordStatus.size(); i++) {
			word += wordStatus.get(i);
		}
		return word;
	}
	
	public void init() {
		canvas = new HangmanCanvas();
		canvas.reset(this);
		add(canvas);
		
	}
	
	
	public void run() {
		// Initialize variables
		int userGuesses = MAX_GUESSES;
		String word = lexicon.pickWord();
		String userGuess;
		// Hold user guess status
		for (int i = 0; i < word.length(); i++) {
			guessList.add("-");
		}		
		
		/* main */
		println("Welcome to Hangman!");
		canvas.displayWord(
			collapseStatus(guessList),
			collapseStatus(wrongGuesses), 
			this
		);
		while (true) {
			println("The word now looks like this: " + wordStatus(guessList));
			println("You have " + userGuesses + " guesses left.");
			userGuess = readLetter("Your guess: ", "upper");
			if (isCorrectGuess(word.indexOf(userGuess))) {
				println("That guess is correct.");
				//Now to fill letters, including multiple in same word
				applyGuess(word, guessList, userGuess);
				canvas.displayWord(
					collapseStatus(guessList),
					collapseStatus(wrongGuesses), 
					this
				);
			} else {
				println("That guess is incorrect");
				wrongGuesses.add(userGuess);
				canvas.noteIncorrectGuess(userGuess.charAt(0));
				canvas.displayWord(
					collapseStatus(guessList),
					collapseStatus(wrongGuesses), 
					this
				);
				userGuesses--;
			}
			if (wordStatus(guessList).equals(word)) {
				println("The word now looks like this: " + wordStatus(guessList));
				println("You win.");
				break;
			}
			if (userGuesses == 0) {
				println("You lose.");
				break;
			}
		}
	}
	
	/** Instance variables */
	private HangmanLexicon lexicon = new HangmanLexicon();
	private HangmanCanvas canvas;
	ArrayList<String> guessList = new ArrayList<String>();
	ArrayList<String> wrongGuesses = new ArrayList<String>();

}
