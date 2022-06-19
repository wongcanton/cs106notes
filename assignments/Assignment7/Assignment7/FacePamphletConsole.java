/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * Milestone 3 status: Complete and passing. Add to main FacePamphlet.
 */

public class FacePamphletConsole extends ConsoleProgram 
					implements FacePamphletConstants {
	/**
	 * Temp main, written by Josh.
	 */
	public static void main(String[] args) {
		new FacePamphletConsole().start(args);	
	}

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		initInteractors();
		addActionListeners();
		db = new FacePamphletDatabase();
    }
	
	/**
	 * Makes the interactors and adds them to the application.
	 */
	private void initInteractors() {
		// Initialize interactors.
		// West
		add(statusField = new JTextField(TEXT_FIELD_SIZE), WEST);
		add(changeStatus = new JButton("Change Status"), WEST);
		add(pictureField = new JTextField(TEXT_FIELD_SIZE), WEST);
		add(changePicture = new JButton("Change Picture"), WEST);
		add(friendField = new JTextField(TEXT_FIELD_SIZE), WEST);
		add(addFriend = new JButton("Add Friend"), WEST);
		// North
		add(nameLabel = new JLabel("Name"), NORTH);
		add(nameField = new JTextField(TEXT_FIELD_SIZE), NORTH);
		add(addName = new JButton("Add"), NORTH);
		add(deleteName = new JButton("Delete"), NORTH);
		add(lookupName = new JButton("Lookup"), NORTH);
		
	}
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods
    	String cmd = e.getActionCommand();
    	if (cmd == "Change Status") this.changeStatus();
    	if (cmd == "Change Picture") this.changePicture();
    	if (cmd == "Add Friend") this.addFriend();
    	if (cmd == "Add") this.addName();
    	if (cmd == "Delete") this.deleteName();
    	if (cmd == "Lookup") this.lookupName();
    	// Print current profile.
    	if (this.currentProfile != null) {
    		println("--> Current Profile: " + this.currentProfile.toString());
    	} else {
    		println("--> No current profile");
    	}
    	
	}
    
    /**
     * Changes status for the current profile.
     */
    private void changeStatus() {
    	// Get the status.
    	String status = this.statusField.getText();
    	// Make sure current profile is not null.
    	if (this.currentProfile != null) {
    		// If not, make status change.
    		this.currentProfile.setStatus(status);
    		println("Status updated to " + status);
    	} else {
    		// Else tell user to select a profile.
    		println(
    			"No current profile set. " + 
    			"Please select a profile by " +
    			"adding a new one or looking one up."
    		);
    	}
    }
    
    /**
     * Changes the picture for the current profile.
     */
    private void changePicture() {
    	// Get the picture.
    	String picture = this.pictureField.getText();
    	// Make sure current profile is not null.
    	if (this.currentProfile != null) {
    		// If not, make the picture change.
    		this.currentProfile.setImage(picture);
    		println("Picture updated to: " + picture);
    	} else {
    		// Else tell user to select a profile.
    		println(
        		"No current profile set. " + 
        		"Please select a profile by " +
        		"adding a new one or looking one up."
        	);
    	}
    }
    
    /**
     * Adds a friend to the current profile.
     */
    private void addFriend() {
    	// Get the friend.
    	String friend = this.friendField.getText();
    	// Make sure friend is valid profile.
    	if (this.db.containsProfile(friend)) {
    		// Make sure the current profile is not null.
    		if (this.currentProfile != null) {
    			// If not, make the friend addition.
    			this.currentProfile.addFriend(friend);
    			println(friend + " added as a friend");
    			// Now add current profile to friends' list of friends.
    			this.db.getProfile(friend).addFriend(
    				this.currentProfile.getName()
    			);
    		} else {
    			// Else tell user to select profile.
    			println(
    					"No current profile set. " + 
    					"Please select a profile by " +
    					"adding a new one or looking one up."
    			);
    		}
    	} else {
    		// else tell user profile is not valid.
    		println("Cannot add friend, friend is not a valid profile.");
    	}

    }
    
    /**
     * Adds a profile for the given name. 
     */
    private void addName() {
    	// Get the name.
    	String name = this.nameField.getText();
    	// If the profile already exists for the name,
    	// print profile.toString().
    	if (this.db.containsProfile(name)) {
    		println(
    			"Add: profile for " + name + " already exists: " +
    			this.db.getProfile(name).toString()
    		);
    		// Else, make profile, add to database,
    		// and print profile.toString()
    	} else {
    		FacePamphletProfile profile = new FacePamphletProfile(name);
    		this.db.addProfile(profile);
    		// Set current profile to the added profile.
    		this.currentProfile = this.db.getProfile(name);
    		// Print the current profile.
    		println(
    			"Add: new profile: " + 
    			this.currentProfile.toString()
    		);
    	}
    }
    
    /**
     * Deletes a profile of the given name.
     */
    private void deleteName() {
    	// Get the name.
    	String name = this.nameField.getText();
    	// Check if profile first exists. If so, get the profile.
    	if (this.db.containsProfile(name)) {
    		FacePamphletProfile profile = this.db.getProfile(name);
    		// Then delete the profile.
    		this.db.deleteProfile(name);
    		// Remove the current profile.
    		this.currentProfile = null;
    		// And print the profile name deleted.
    		println("Delete: profile of " + profile.getName() + " deleted");
    	} else {
    		// Otherwise print that the profile does not exist.
    		println(
    			"Delete: profile with the name " + name + 
    			" does not exist"
    		);
    	}
    }
    
    /**
     * Looks up a profile for the given name.
     */
    private void lookupName() {
    	// Get the name.
    	String name = this.nameField.getText();
    	// Check if the profile first exists. If so, get the profile.
    	if (this.db.containsProfile(name)) {
    		// Set currentProfile as the profile.
    		this.currentProfile = this.db.getProfile(name);
    		// Then print the profile.
    		println("Lookup: " + this.currentProfile.toString());
    	} else {
    		// Print that the profile does not exist.
    		println(
    			"Lookup: profile with the name " + name +
    			" does not exist"
    		);
    	}
    }
    
    /* Instance variables. */
    // West Interactors.
    private JTextField statusField;
    private JButton changeStatus;
    private JTextField pictureField;
    private JButton changePicture;
    private JTextField friendField;
    private JButton addFriend;
    // North Interactors.
    private JLabel nameLabel;
    private JTextField nameField;
    private JButton addName;
    private JButton deleteName;
    private JButton lookupName;
    // Database.
    private FacePamphletDatabase db;
    // Current profile.
    private FacePamphletProfile currentProfile;

}
