/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * The purpose of this program is to center a design of a ProgramHierarchy
 * graphic in a GraphicsProgram.
 */


import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	/**
	 * Method: getSidePadding
	 * Computes and returns the pixels of padding between the three 
	 * lower rectangles and the edges of the left and right side
	 * of the GraphicsProgram window.
	 * @return Padding between rectangles and the walls
	 */
	private int getSidePadding() {
		// Padding between rectangles
		int rectPadding = RECT_HEIGHT / 2;
		int rectsAndPadding = (3 * RECT_WIDTH) + (2 * rectPadding);
		return ((getWidth() - rectsAndPadding) / 2);
	}
	
	
	/**
	 * Method: getRectPadding
	 * Computes the x coordinate, in pixels, to start drawing a rectangle
	 * given a number of rectangles already drawn on the same horizontal plane.
	 * @param numRects Number of rectangles drawn on the same horizontal plane.
	 * @return x coordinate to draw the next rectangle
	 */
	private int getRectPadding(int numRects) {
		int rectWithPadding = RECT_WIDTH + (RECT_HEIGHT / 2);
		return (numRects * rectWithPadding);
	}
	
	
	/**
	 * Method: makeRect
	 * Makes a GRect object at (x, y) of size RECT_WIDTH x RECT_HEIGHT
	 * @param x The x coordinate, in pixels, to put the rectangle
	 * @param y The y coordinate, in pixels, to put the rectangle
	 * @return GRect object of RECT_WIDTH and RECT_HEIGHT
	 */
	private GRect makeRect(int x, int y) {
		return (new GRect(x, y, RECT_WIDTH, RECT_HEIGHT));
	}
	
	
	/**
	 * Method: makeLabel
	 * Takes a String and a GRect, and centers the String in the GRect.
	 * @param labelString String of what to show inside the GRect
	 * @param labelRect GRect to center the labelString inside
	 * @return GRect instance with labelString centered inside
	 */
	private GLabel makeLabel(String labelString, GRect labelRect) {
		GLabel label = new GLabel(labelString);
		int labelXPadding = ((int) labelRect.getWidth() - (int) label.getWidth()) / 2;
		int labelYPadding = ((int) labelRect.getHeight() + (int) label.getAscent()) / 2;
		label.setLocation(labelRect.getX() + labelXPadding, labelRect.getY() + labelYPadding);
		return label;
	}
	
	
	/**
	 * Method: makeLine
	 * Takes two GRects and draws a line between them at the centers of each
	 * @param topRect GRect that is the top rectangle to have the line
	 * @param bottomRect GRect that is the bottom rectangle to have the line
	 * @return GLine between topRect and bottomRect and is centered
	 */
	private GLine makeLine(GRect topRect, GRect bottomRect) {
		int topRectX = (int) topRect.getX() + (int) (RECT_WIDTH / 2);
		int topRectY = (int) topRect.getY() + (int) topRect.getHeight();
		int bottomRectX = (int) bottomRect.getX() + (int) (RECT_WIDTH / 2);
		int bottomRectY = (int) bottomRect.getY();
		return new GLine(topRectX, topRectY, bottomRectX, bottomRectY);
	}
 	
	public void run() {
		// Initialize variables
		int leftPadding = getSidePadding();
		int yMid = getHeight() / 2;
		int yTop = yMid - (2 * RECT_HEIGHT);
		
		// Add rectangles
		GRect leftRect = makeRect(leftPadding, yMid);
		GRect midRect = makeRect(leftPadding + getRectPadding(1), yMid);
		GRect rightRect = makeRect(leftPadding + getRectPadding(2), yMid);
		GRect topRect = makeRect(leftPadding + getRectPadding(1), yTop);
		add(leftRect);
		add(midRect);
		add(rightRect);
		add(topRect);

		// Add labels
		GLabel leftLabel = makeLabel("GraphicsProgram", leftRect);
		GLabel midLabel = makeLabel("ConsoleProgram", midRect);
		GLabel rightLabel = makeLabel("DialogProgram", rightRect);
		GLabel topLabel = makeLabel("ConsoleProgram", topRect);
		add(leftLabel);
		add(midLabel);
		add(rightLabel);
		add(topLabel);
		
		// Add lines
		GLine leftLine = makeLine(topRect, leftRect);
		GLine midLine = makeLine(topRect, midRect);
		GLine rightLine = makeLine(topRect, rightRect);
		add(leftLine);
		add(midLine);
		add(rightLine);

	}
	
	
	/** Width of rectangle */
	private static final int RECT_WIDTH = 150;
	
	/** Height of rectangle */
	private static final int RECT_HEIGHT = RECT_WIDTH / 3;	
}

