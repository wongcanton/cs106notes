/*
 * File: Target.java
 * -----------------
 * The purpose of this program is to draw a target logo in a GraphicsProgram.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	/**
	 * Method: makeCircle
	 * Makes a GOval object at (x, y) of radius r and fill color of color
	 * @param x	int x: Horizontal position in pixels
	 * @param y	int y: Vertical position in pixels
	 * @param r	int r: Radius value
	 * @param color Color color: Color to fill circle with
	 * @return circle GOval circle: Oval graphics object
	 */
	private GOval makeCircle(int x, int y, double r, Color color) {
		GOval circle = new GOval(x, y, r, r);
		circle.setFilled(true);
		circle.setColor(color);
		circle.setFillColor(color);
		return circle;
	}
	
	
	/**
	 * Method: getSpace
	 * Computes and returns the x position to make a circle of radius innerRadius that
	 * will be centered in a circle of radius outerRadius
	 * @param x	int x: Coordinate to evaluate
	 * @param outerRadius double outerRadius: Radius of outer circle
	 * @param innerRadius double innerRadius: Radius of inner circle
	 * @return x int x: Evaluated coordinate
	 */
	private int getSpace(int x, double outerRadius, double innerRadius) {
		x = (int) (x + ((outerRadius - innerRadius) / 2));
		return x;
	}
	
	
	public void run() {
		// Initialize coordinate values for circles
		int xOut = getSpace(0, getWidth(), OUTER_RADIUS);
		int yOut = getSpace(0, getHeight(), OUTER_RADIUS);
		int xMid = getSpace(xOut, OUTER_RADIUS, MIDDLE_RADIUS);
		int yMid = getSpace(yOut, OUTER_RADIUS, MIDDLE_RADIUS);
		int xInn = getSpace(xMid, MIDDLE_RADIUS, INNER_RADIUS);
		int yInn = getSpace(yMid, MIDDLE_RADIUS, INNER_RADIUS);
		
		//Make circles and add to canvas
		add(makeCircle(xOut, yOut, OUTER_RADIUS, Color.RED));
		add(makeCircle(xMid, yMid, MIDDLE_RADIUS, Color.WHITE));
		add(makeCircle(xInn, yInn, INNER_RADIUS, Color.RED));
	}
	
	
	/** Radius for outer circle */
	private static final double OUTER_RADIUS = 72.0;

	/** Radius for middle circle */
	private static final double MIDDLE_RADIUS = (72 * 0.65);

	/** Radius for inner circle */
	private static final double INNER_RADIUS = (72 * 0.3);
}
