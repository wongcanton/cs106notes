/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

/*
 * Decomposition:
 * 1. Assemble GUI Interactors
 * 	Make the interactors work with at least a ConsoleProgram
 * 2. Implement NameSurferEntry
 * 	Read file and store data for an entry
 * 3. Implement NameSurferDataBase
 * 	Read file and store entries, and return entries when called
 * 4. Create Grid for NameSurferGraph class
 * 5. Finish implementation of NameSurferGraph
 * End: Put it all together
 */


/*
 * Step 1: Assemble GUI Interactors
 * Tasks:
 * 1. Make a consoleprprogram
 * 	Done
 * 2. Add interactors that do nothing
 * 	Add JText, JButton, JButton
 * 3. Make interactors work with consoleprogram
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the bottom of the window.
	 */
	public void init() {
		// Make interactors.
		nameLabel = new JLabel("Name:");
		nameField = new JTextField(NAME_MAX);
		graphButton = new JButton("Graph");
		clearButton = new JButton("Clear");
		add(nameLabel, SOUTH);
	    add(nameField, SOUTH);
	    add(graphButton, SOUTH);
	    add(clearButton, SOUTH);
	    
	    // Add action listeners.
	    addActionListeners();
	    nameField.addActionListener(this);
	    
	    // Add database.
	    database = new NameSurferDataBase("names-data.txt");
	    
	    // Add graphics.
	    graph = new NameSurferGraph(this);
	    add(graph, CENTER);
	}


	/* Method: run() */
	/**
	 * This method runs the program.
	 */
	public void run() {
		
	}

	
	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Graph")) doGraph();
		if (cmd.equals("Clear")) doClear();
	}
	
	
	/* Method: doGraph */
	/**
	 * This method performs the action for the Graph button.
	 */
	public void doGraph() {
		userInput = nameField.getText();
		entry = database.findEntry(userInput);
		graph.addEntry(entry);
		graph.update();
	}
	
	
	/* Method: doClear */
	/**
	 * This method clears the console.
	 */
	public void doClear() {
		graph.clear();
		graph.update();
	}
	
	
	/* Instance Variables */
	// Interactors.
	private JLabel nameLabel;
	private JTextField nameField;
	private JButton graphButton;
	private JButton clearButton;
	// Data structures.
	private NameSurferDataBase database;
	private String userInput;
	private NameSurferEntry entry;
	// Graphics.
	private NameSurferGraph graph;
	
	/* Constants */
	private static final int NAME_MAX = 20;
}
