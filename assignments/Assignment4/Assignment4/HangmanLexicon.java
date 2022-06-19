/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

/*
 * Decomposition:
 * 1. Pseudorandomly pick word
 * 2. Take input from users
 * 3. Check user input against picked value
 */

import acm.util.*;
import java.util.*;
import java.io.*;
import java.lang.*;
public class HangmanLexicon {

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return 10;
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		try {
			BufferedReader rd = new BufferedReader(new FileReader("ShorterLexicon.txt"));
			while (true) {
				if (rd.readLine() == null) break;
				wordsList.add(rd.readLine());
			}	
			rd.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
		return wordsList.get(index);
	}
	
/**
 * Method: pickWord
 * Uses a pseudorandom number generator to pick a word from an
 * indexed list of words.
 * @return word String word: word selected from the getWord method 
 */
	public String pickWord() {
		int wordsMaxIndex = getWordCount() - 1;
		int randomIndex = rgen.nextInt(0, wordsMaxIndex);
		return getWord(randomIndex);
	}
	
	/* Instance variables */
	/** Instance variable for a pseudorandom number generator */
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	/** Instance variable for the ArrayList */
	private ArrayList<String> wordsList = new ArrayList<String>();
}
