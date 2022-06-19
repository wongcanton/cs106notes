/*
 * File: TestNameSurferEntry.java
 * ------------------------------
 * The purpose of this program is to use a ConsoleProgram to test the
 * NameSurferEntry class.
 */

import acm.util.*;
import acm.program.*;
import java.util.*;


public class TestNameSurferEntry extends ConsoleProgram implements NameSurferConstants {
	
	/**
	 * Runs the ConsoleProgram.
	 */
	public void run() {
		println("Welcome to the NameSurferEntry tester!");
//		userInput = readLine("Enter name data: ");
		userInput = "Josh 936 917 0 0 0 983 794 404 582 881 814";
		entry = new NameSurferEntry(userInput);
		println("The name for the data you entered is: \n" + entry.getName());
		println("The data for the entry you entered is: \n" + entry.toString());
		
		println("The rank for the year 1910 is: " + entry.getRank(1));
	}
	
	/* Instance variables. */
	private String userInput;
	private NameSurferEntry entry;
}
