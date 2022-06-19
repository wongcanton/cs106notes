/*
 * File: FacePamphletProfile.java
 * ------------------------------
 * This class keeps track of all the information for one profile
 * in the FacePamphlet social network.  Each profile contains a
 * name, an image (which may not always be set), a status (what 
 * the person is currently doing, which may not always be set),
 * and a list of friends.
 */

import acm.graphics.*;
import acm.util.*;
import java.util.*;
import java.awt.*;

public class FacePamphletProfile implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for
	 * the profile.
	 */
	public FacePamphletProfile(String name) {
		// Assign name of profile to the name.
		this.name = name;
		// Assign default values to other properties.
		this.status = "";
		this.profilePicture = null;
		this.friends = new ArrayList<String>();
	}
	

	/** This method returns the name associated with the profile.
	 * @return name: String representing the name of the profile. 
	 */ 
	public String getName() {
		return this.name;
	}

	
	/** 
	 * This method returns the image associated with the profile.  
	 * If there is no image associated with the profile, the method
	 * returns null.
	 * @return profilePicture: GImage of the profile's picture. Null if there
	 * 	is not a valid profile picture.
	 */ 
	public Image getImage() {
		// Return image if not empty.
		if (this.profilePicture != null) {
			return this.profilePicture.getImage();
		} else {
			return null;
		}
	}

	
	/** This method sets the image associated with the profile.
	 * @param image: String name to set the profile's profile picture.
	 */ 
	public void setImage(String image) {
		// Try to set the image with the image.
		try {
			this.profilePicture = new GImage(image);
		} catch (ErrorException e) {
			// Throw error if not valid image path.
			throw new ErrorException(e);
		}
	}
	
	/** 
	 * This method returns the status associated with the profile.
	 * If there is no status associated with the profile, the method
	 * returns the empty string ("").
	 * @return status: String representing the profile's status.
	 */ 
	public String getStatus() {
		return this.status;
	}
	
	
	/** This method sets the status associated with the profile.
	 * @param status: String representing the profile's status.
	 */ 
	public void setStatus(String status) {
		this.status = status;
	}
	

	/** 
	 * This method adds the named friend to this profile's list of 
	 * friends.  It returns true if the friend's name was not already
	 * in the list of friends for this profile (and the name is added 
	 * to the list).  The method returns false if the given friend name
	 * was already in the list of friends for this profile (in which 
	 * case, the given friend name is not added to the list of friends 
	 * a second time.)
	 * @param friend: String representing a name of a friend to add.
	 * @return true if added, false it not added.
	 */
	public boolean addFriend(String friend) {
		// Add friend if friend not in friends, then return true.
		// Otherwise return false and do not add friend.
		if (!friends.contains(friend)) {
			friends.add(friend);
			return true;
		} else {
			return false;
		}
	}
	

	/** 
	 * This method removes the named friend from this profile's list
	 * of friends.  It returns true if the friend's name was in the 
	 * list of friends for this profile (and the name was removed from
	 * the list).  The method returns false if the given friend name 
	 * was not in the list of friends for this profile (in which case,
	 * the given friend name could not be removed.)
	 * @param friend: String representing a name of a friend to remove.
	 * @return true if deleted, false if not deleted.
	 */
	public boolean removeFriend(String friend) {
		// Return true if friend exists, and remove the friend.
		// Otherwise return false and do nothing
		if (friends.contains(friend)) {
			friends.remove(friend);
			return true;
		} else {
			return false;
		}
	}
	

	/** 
	 * This method returns an iterator over the list of friends 
	 * associated with the profile.
	 * @return iterator: Iterator over the ArrayList<String> of friends.
	 */ 
	public Iterator<String> getFriends() {
		return this.friends.iterator();
	}
	
	
	/**
	 * This method returns the number of friends the profile has.
	 * @return nFriends: Int number of friends associated with the profile.
	 */
	public int getNumberFriends() {
		int nFriends = 0;
		Iterator<String> it = this.getFriends();
		while (it.hasNext()) {
			nFriends += 1;
			it.next();
		}
		return nFriends;
	}
	
	
	/** 
	 * This method returns a string representation of the profile.  
	 * This string is of the form: "name (status): list of friends", 
	 * where name and status are set accordingly and the list of 
	 * friends is a comma separated list of the names of all of the 
	 * friends in this profile.
	 * 
	 * For example, in a profile with name "Alice" whose status is 
	 * "coding" and who has friends Don, Chelsea, and Bob, this method 
	 * would return the string: "Alice (coding): Don, Chelsea, Bob"
	 */ 
	public String toString() {
		// Make string with name and status.
		String total = this.name + "(" + this.status + "): ";
		// Get friends iterator.
		Iterator<String> friends = this.getFriends();
		// Add friends to string.
		while (friends.hasNext()) {
			total += friends.next();
			// If not last friend, add comma to string.
			if (friends.hasNext()) {
				total += ", ";
			}
		}
		return total;
	}
	
	
	/* Instance variables. */
	private String name;
	private String status;
	private GImage profilePicture;
	private ArrayList<String> friends;
	
}
