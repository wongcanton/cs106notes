/* 
 * File: FacePamphlet.java
 * -----------------------
 * This program implements a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;


public class FacePamphlet extends Program 
					implements FacePamphletConstants {
	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		initInteractors();
		addActionListeners();
		this.db = new FacePamphletDatabase();
		this.canvas = new FacePamphletCanvas();
		this.add(this.canvas);
    }
	
	
	/**
	 * Makes the interactors and adds them to the application.
	 */
	private void initInteractors() {
		// Initialize interactors.
		// West
		this.add(statusField = new JTextField(TEXT_FIELD_SIZE), WEST);
		this.add(changeStatus = new JButton("Change Status"), WEST);
		this.add(pictureField = new JTextField(TEXT_FIELD_SIZE), WEST);
		this.add(changePicture = new JButton("Change Picture"), WEST);
		this.add(friendField = new JTextField(TEXT_FIELD_SIZE), WEST);
		this.add(addFriend = new JButton("Add Friend"), WEST);
		// North
		this.add(nameLabel = new JLabel("Name"), NORTH);
		this.add(nameField = new JTextField(TEXT_FIELD_SIZE), NORTH);
		this.add(addName = new JButton("Add"), NORTH);
		this.add(deleteName = new JButton("Delete"), NORTH);
		this.add(lookupName = new JButton("Lookup"), NORTH);
		
	}
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     * @param e: ActionEvent retrieved from the ActionListener.
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
    	// If profile null, clear canvas.
    	if (this.currentProfile != null) {
    		this.canvas.displayProfile(this.currentProfile);
    	} else {
    		// Clear canvas if deleted.
    		this.canvas.clearAllButMessage();
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
    		this.canvas.showMessage("Status updated to " + status);
    	} else {
    		// Else tell user to select a profile.
    		this.canvas.showMessage(
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
    		this.canvas.showMessage("Picture updated to: " + picture);
    	} else {
    		// Else tell user to select a profile.
    		this.canvas.showMessage(
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
    			this.canvas.showMessage(friend + " added as a friend");
    			// Now add current profile to friends' list of friends.
    			this.db.getProfile(friend).addFriend(
    				this.currentProfile.getName()
    			);
    		} else {
    			// Else tell user to select profile.
    			this.canvas.showMessage(
    					"No current profile set. " + 
    					"Please select a profile by " +
    					"adding a new one or looking one up."
    			);
    		}
    	} else {
    		// else tell user profile is not valid.
    		this.canvas.showMessage(
    			"Cannot add friend, friend is not a valid profile."
    		);
    	}
    }
    
    
	/*
	 * If the profile name already exists in the database, 
	 * then it prints out the fact that the profile with that name already 
	 * exists followed by the string representation of the profile.
	 */
    private void addName() {
    	// Get the name.
    	String name = this.nameField.getText();
    	// If the profile already exists for the name,
    	// print profile.toString().
    	if (this.db.containsProfile(name)) {
    		this.canvas.showMessage(
    			"A profile with the name " + name + " already exists"
    		);
    		// Else, make profile, add to database,
    		// and print profile.toString()
    	} else {
    		FacePamphletProfile profile = new FacePamphletProfile(name);
    		this.db.addProfile(profile);
    		// Set current profile to the added profile.
    		this.currentProfile = this.db.getProfile(name);
    		// Print the current profile.
    		this.canvas.showMessage(
    			"New profile created"
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
    		this.canvas.showMessage(
    			"Profile of " + 
    			profile.getName() + " deleted"
    		);
    	} else {
    		// Otherwise print that the profile does not exist.
    		this.canvas.showMessage(
    			"A profile with the name " + name + 
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
    		this.canvas.showMessage(
    			"Displaying " + this.currentProfile.getName()
    		);
    	} else {
    		// Print that the profile does not exist.
    		this.canvas.showMessage(
    			"A profile with the name " + name +
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
    // Canvas.
    private FacePamphletCanvas canvas;

}
