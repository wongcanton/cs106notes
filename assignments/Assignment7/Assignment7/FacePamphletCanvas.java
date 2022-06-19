/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display.
	 */
	public FacePamphletCanvas() {
		// Make the name label.
		initName();
		// Make the image.
		initImage();
		// Make the imageHolder.
		initImageHolder();
		// Make the status.
		initStatus();
		// Make the friends.
		initFriends();
		// Make the message label.
		initMessage();	
	}
	
	
	/**
	 * Initializes the name instance variable.
	 */
	private void initName() {
		this.name = new GLabel(EMPTY_LABEL_TEXT);
		this.name.setFont(PROFILE_NAME_FONT);
		this.name.setColor(Color.BLUE);
	}
	
	
	/**
	 * Initializes the image instance variable.
	 */
	private void initImage() {
		this.image = new GImage("StanfordLogo.jpg");
		this.image.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
	}
	
	/**
	 * Initializes the imageHolder instance variable.
	 */
	private void initImageHolder() {
		GRect rect = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
		GLabel label = new GLabel("No Image");
		label.setFont(PROFILE_IMAGE_FONT);
		
		// Declare variables.
		double rectWidth = rect.getWidth();
		double rectHeight = rect.getHeight();
		double labelWidth = label.getWidth();
		double labelHeight = label.getAscent();
		
		
		// Make the GCompound.
		this.imageHolder = new GCompound();
		this.imageHolder.add(rect);
		this.imageHolder.add(
			label,
			(rectWidth - labelWidth) / 2,
			(rectHeight - labelHeight) / 2
		);
	}
	
	/**
	 * Initializes the status instance variable.
	 */
	private void initStatus() {
		this.status = new GLabel(EMPTY_LABEL_TEXT);
		this.status.setFont(PROFILE_STATUS_FONT);
	}
	
	
	/**
	 * Initializes the friendsHeader and friends instance variables.
	 */
	private void initFriends() {
		// Make the friendsHeader GLabel, and set font.
		this.friendsHeader = new GLabel("Friends:");
		this.friendsHeader.setFont(PROFILE_FRIEND_LABEL_FONT);
		// Make the friends variable as an ArrayList.
		this.friends = new ArrayList<GLabel>();
	}
	
	
	/**
	 * Initializes the message instance variable.
	 */
	private void initMessage() {
		this.message = new GLabel(EMPTY_LABEL_TEXT);
		this.message.setFont(MESSAGE_FONT);
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 * @param msg: String to show in the message GLabel.
	 */
	public void showMessage(String msg) {
		// Remove old label.
		this.remove(this.message);
		// Set new message.
		this.message.setLabel(msg);
		// Get new values for positions.
		double labelWidth = this.message.getWidth();
		// Set new positions.
		this.message.setLocation(
			(this.getWidth() - labelWidth) / 2,
			this.getHeight() - BOTTOM_MESSAGE_MARGIN
		);
		this.add(this.message);
	}
	
	
	/**
	 * Clears all GObjects from the canvas except the message.
	 */
	public void clearAllButMessage() {
		// Clear canvas of all but message.
		this.remove(this.imageHolder);
		this.remove(this.name);
		this.remove(this.image);
		this.remove(this.status);
		this.remove(this.friendsHeader);
		for (int i = 0; i < this.friends.size(); i++) {
			this.remove(this.friends.get(i));
		}
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 * @param profile: FacePamphletProfile to display.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		// Clear the canvas except the message.
		this.clearAllButMessage();
		// Add name
		this.addName(profile);
		// Add image.
		this.addImage(profile);
		// Add status.
		this.addStatus(profile);
		// Add Friends, but clear the list first.
		this.friends.clear();
		this.addFriends(profile);
	}
	

	/**
	 * Aligns the name text for a given name and adds it.
	 * @param profile: FacePamphletProfile to add to the canvas.
	 */
	private void addName(FacePamphletProfile profile) {
		// Set name.
		this.name.setLabel(profile.getName());
		// Realign Y position.
		double offset = this.name.getAscent();
		this.name.setLocation(
			LEFT_MARGIN,
			TOP_MARGIN + offset
		);
		this.add(this.name);
	}
	
	
	/**
	 * Scales a profile image to the correct size and adds it.
	 * @param profile: FacePamphletProfile to add the profile image to.
	 */
	private void addImage(FacePamphletProfile profile) {
		// Set both image and imageholder locations.
		this.image.setLocation(
			LEFT_MARGIN,
			this.name.getY() + IMAGE_MARGIN
		);
		this.imageHolder.setLocation(
			LEFT_MARGIN,
			this.name.getY() + IMAGE_MARGIN
		);
		
		// See if image is not null.
		if (profile.getImage() != null) {
			// If not null, adjust size and add.
			GImage profileImage = new GImage(profile.getImage());
			double heightScale = IMAGE_HEIGHT / profileImage.getHeight();
			double widthScale = IMAGE_WIDTH / profileImage.getWidth();
			this.image.setImage(profile.getImage());
			this.image.scale(widthScale, heightScale);
			this.add(this.image);
		} else {
			// If null, add placeholder.
			this.add(this.imageHolder);
		}
		
	}
	
	
	/**
	 * Adds the status to the canvas.
	 * @param profile: FacePamphletProfile to display the status of.
	 */
	private void addStatus(FacePamphletProfile profile) {
		// Set the status text.
		this.status.setLabel(profile.getStatus());
		// Get intended Y position based on image or imageHolder.
		double yPosition;
		if (profile.getImage() != null) {
			yPosition = this.image.getY() + this.image.getHeight();
		} else {
			yPosition = this.imageHolder.getY() + this.imageHolder.getHeight();
		}
		// Add the margin.
		yPosition = (
			yPosition +
			STATUS_MARGIN + this.status.getAscent()
		);
		// Declare position.
		this.status.setLocation(
			LEFT_MARGIN,
			yPosition
		);
		// Add status.
		this.add(this.status);
	}
	
	
	/**
	 * Adds the friends to the canvas.
	 * @param profile: FacePamphletProfile to display.
	 */
	private void addFriends(FacePamphletProfile profile) {
		// Set location of friendsHeader and add it to the canvas.
		this.friendsHeader.setLocation(
				this.getWidth() / 2,
				this.imageHolder.getY()
		);
		this.add(this.friendsHeader);
		// Get friends.
		Iterator<String> it = profile.getFriends();
		String holder = "";
		// Add friends to ArrayList.
		while (it.hasNext()) {
			holder = it.next();
			this.friends.add(new GLabel(holder));			
		}
		// Set locations and fonts for friends.
		for (int i = 0; i < this.friends.size(); i++) {
			this.friends.get(i).setFont(PROFILE_FRIEND_FONT);
			if (i == 0) {
				// If no friends currently listed, base location
				// on friendsHeader.
				this.friends.get(i).setLocation(
					this.friendsHeader.getX(),
					this.friendsHeader.getY() + this.friends.get(i).getHeight()
				);
			} else {
				// Else base location off of most recent friend added.
				this.friends.get(i).setLocation(
					this.friendsHeader.getX(),
					this.friends.get(i - 1).getY() + this.friends.get(i).getHeight()
				);
			}
			this.add(this.friends.get(i));
		}
	}
	
	
	/* Instance variables. */
	private GLabel message;
	private GCompound imageHolder;
	private GLabel name;
	private GImage image;
	private GLabel status;	
	private GLabel friendsHeader;
	private ArrayList<GLabel> friends;

	
}
