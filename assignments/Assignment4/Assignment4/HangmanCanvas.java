/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

/*
 * Decomposition:
 * 2. Draw scaffold and add it in reset()
 * 1. Draw man and add by part with ArrayList of parts
 * 3. Draw word status label
 * 4. Run drawing functions in Hangman game
 * 
 */

/*
 * The
center line of the body should be centered horizontally on the screen, and the scaffold
should be displayed a bit higher than the center so that there is room underneath for two
labels: a label in a large font showing the secret word as it currently stands and a label in
a smaller font showing the incorrect guesses. Figure 5 shows how the screen appears at
the end of the tragic session in which the user was unable to guess FUZZY.
 */

/*
 * Make the man first given this description, then add the scaffold
 */

import acm.graphics.*;
import java.util.*;


//Import acm.program to pass programs to the canvas for sizing
import acm.program.*;


public class HangmanCanvas extends GCanvas {
	
	/*
	 * Make an object for each body part and scafold part.
	 * To do this, make each object extend its superclass.
	 * For the constructor, call super(args for superclass constructor).
	 * Be sure to make annotations for each class and constructor.
	 */
	
	private class Body extends GLine {
		public Body(int midX, int midY) {
			super(midX, midY, midX, midY - BODY_LENGTH);
		}
	}
	
	private class Head extends GOval {
		public Head(int headDiam, Body body) {
			super(
				body.getEndPoint().getX() - HEAD_RADIUS,
				body.getEndPoint().getY() - headDiam,
				headDiam,
				headDiam
			);
		}
	}
	
	private class LeftArm extends GCompound {
		public LeftArm(Body body) {
			super();
			GLine leftArm = new GLine(
				body.getEndPoint().getX(), 
				body.getEndPoint().getY() + ARM_OFFSET_FROM_HEAD,
				body.getEndPoint().getX() - UPPER_ARM_LENGTH, 
				body.getEndPoint().getY() + ARM_OFFSET_FROM_HEAD
			);
			GLine leftHand = new GLine(
				leftArm.getEndPoint().getX(),
				leftArm.getEndPoint().getY(),
				leftArm.getEndPoint().getX(),
				leftArm.getEndPoint().getY() + LOWER_ARM_LENGTH
			);
			this.add(leftArm);
			this.add(leftHand);
		}
	}
	
	private class RightArm extends GCompound {
		public RightArm(Body body) {
			super();
			GLine rightArm = new GLine(
				body.getEndPoint().getX(), 
				body.getEndPoint().getY() + ARM_OFFSET_FROM_HEAD,
				body.getEndPoint().getX() + UPPER_ARM_LENGTH, 
				body.getEndPoint().getY() + ARM_OFFSET_FROM_HEAD
			);
			GLine rightHand = new GLine(
				rightArm.getEndPoint().getX(),
				rightArm.getEndPoint().getY(),
				rightArm.getEndPoint().getX(),
				rightArm.getEndPoint().getY() + LOWER_ARM_LENGTH
			);
			this.add(rightArm);
			this.add(rightHand);
		}
	}
	
	private class LeftLeg extends GCompound {
		public LeftLeg(Body body) {
			super();
			GLine leftHip = new GLine(
				body.getStartPoint().getX(),
				body.getStartPoint().getY(),
				body.getStartPoint().getX() - HIP_WIDTH,
				body.getStartPoint().getY()
			);
			GLine leftLeg = new GLine(
				leftHip.getEndPoint().getX(),
				leftHip.getEndPoint().getY(),
				leftHip.getEndPoint().getX(),
				leftHip.getEndPoint().getY() + LEG_LENGTH
			);
			this.add(leftHip);
			this.add(leftLeg);
		}
	}
	
	private class RightLeg extends GCompound {
		public RightLeg(Body body) {
			super();
			GLine rightHip = new GLine(
					body.getStartPoint().getX(),
					body.getStartPoint().getY(),
					body.getStartPoint().getX() + HIP_WIDTH,
					body.getStartPoint().getY()
				);
				GLine rightLeg = new GLine(
					rightHip.getEndPoint().getX(),
					rightHip.getEndPoint().getY(),
					rightHip.getEndPoint().getX(),
					rightHip.getEndPoint().getY() + LEG_LENGTH
				);
			this.add(rightHip);
			this.add(rightLeg);
			this.add(x);
		}
		public GLine x = new GLine(1, 1, 1, 1);
	}
	
	private class LeftFoot extends GLine {
		public LeftFoot(Body body) {
			super(
				body.getStartPoint().getX() - HIP_WIDTH,
				body.getStartPoint().getY() + LEG_LENGTH,
				body.getStartPoint().getX() - HIP_WIDTH - FOOT_LENGTH,
				body.getStartPoint().getY() + LEG_LENGTH
			);
			
		}
	}
	
	private class RightFoot extends GLine {
		public RightFoot(Body body) {
			super(
				body.getStartPoint().getX() + HIP_WIDTH,
				body.getStartPoint().getY() + LEG_LENGTH,
				body.getStartPoint().getX() + HIP_WIDTH + FOOT_LENGTH,
				body.getStartPoint().getY() + LEG_LENGTH
			);
			
		}
	}
	
	private class Gallows extends GCompound {
		public Gallows(Head head) {
			super();
			GLine rope = new GLine(
				head.getX() + HEAD_RADIUS,
				head.getY(),
				head.getX() + HEAD_RADIUS,
				head.getY() - ROPE_LENGTH
			);
			GLine beam = new GLine(
				rope.getEndPoint().getX(),
				rope.getEndPoint().getY(),
				rope.getEndPoint().getX() - BEAM_LENGTH,
				rope.getEndPoint().getY()
			);
			GLine scaffold = new GLine(
				beam.getEndPoint().getX(),
				beam.getEndPoint().getY(),
				beam.getEndPoint().getX(),
				beam.getEndPoint().getY() + SCAFFOLD_HEIGHT
			);
			GLine base = new GLine(
				scaffold.getEndPoint().getX() - BASE_LENGTH,
				scaffold.getEndPoint().getY(),
				scaffold.getEndPoint().getX() + BASE_LENGTH,
				scaffold.getEndPoint().getY()
			);
			this.add(rope);
			this.add(beam);
			this.add(scaffold);
			this.add(base);
		}
	}
	
	private class Counter {
		public Counter() {
			counter = 0;
		}
		private int counter;
		/**
		 * Method: nextValue
		 * Increases the value of the counter by one.
		 */
		public void nextValue() {
			this.counter += 1;
		}
		/**
		 * Method: resetCounter
		 * Resets the couter value to 0.
		 */
		public void resetCounter() {
			this.counter = 0;
		}
	}
	

/** Resets the display so that only the scaffold appears */
	public void reset(Program window) {
		// Initialize midpoint as 0.25 * window because canvas is half size		
		int midX = (int) (window.getWidth() * 0.25);
		int midY = (int) (window.getHeight() * 0.5);

		// Declare parts
		Body body = new Body(midX, midY);
		Head head = new Head(headDiam, body);
		LeftArm leftArm = new LeftArm(body);
		RightArm rightArm = new RightArm(body);
		LeftLeg leftLeg = new LeftLeg(body);
		RightLeg rightLeg = new RightLeg(body);
		LeftFoot leftFoot = new LeftFoot(body);
		RightFoot rightFoot = new RightFoot(body);
		Gallows gallows = new Gallows(head);
		
		// Add parts to ArrayList
		hangmanParts.add(gallows);
		hangmanParts.add(head);
		hangmanParts.add(body);
		hangmanParts.add(leftArm);
		hangmanParts.add(rightArm);
		hangmanParts.add(leftLeg);
		hangmanParts.add(rightLeg);
		hangmanParts.add(leftFoot);
		hangmanParts.add(rightFoot);
		
		// Add gallows and set partCounter to next part
		add(hangmanParts.get(partCounter.counter));
		partCounter.nextValue();
	}


/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word, String wrong, Program window) {
		remove(wordStatus);
		remove(wrongStatus);
		wordStatus = new GLabel(
			word,
			150,
			400
		);
		wrongStatus = new GLabel(
			wrong,
			100,
			450
		);
		wordStatus.setFont("times-bold-24");
		wrongStatus.setFont("times-24");
		add(wordStatus);
		add(wrongStatus);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		add(hangmanParts.get(partCounter.counter));
		partCounter.nextValue();
	}

/** Instance variables */
	private ArrayList<GObject> hangmanParts = new ArrayList<GObject>();
	private int headDiam = HEAD_RADIUS * 2;
	private Counter partCounter = new Counter();
	private GLabel wordStatus = new GLabel("");
	private GLabel wrongStatus = new GLabel("");

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int BASE_LENGTH = 40;

}
