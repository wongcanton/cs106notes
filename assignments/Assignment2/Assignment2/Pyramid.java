/*
 * File: Pyramid.java
 * ------------------
 * The purpose of this program is to build a pyramid in a GraphicsProgram
 * with the constant specifications of pyramid brick dimensions and starting
 * number of bricks at the base.
 */


import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {	
	/** 
	 * Method: makeBrick
	 * Makes a GRect object of BRICK_WIDTH x BRICK_HEIGHT at (x, y)
	 * @param x int x: Horizontal position in pixels
	 * @param y int y: Vertical position in pixels
	 * @return GRect: Rectangle graphic
	 */
	private GRect makeBrick(int x, int y) {
		return new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
	}
	
	
	/**
	 * Method: getPadding
	 * Computes the position from the wall to start building a pyramid so it is centered
	 * @param void
	 * @return x int x: position to start building pyramid that will be centered in GraphicsProgram
	 */
	private int getPadding() {
		int lengthNotBricks = getWidth() - (BRICKS_IN_BASE * BRICK_WIDTH);
		return (lengthNotBricks / 2);
	}

	
	/**
	 * Method: buildRow
	 * Builds rows of bricks at (x, y) with starting (bricks = rowBricks) and ending such that
	 * each row has one less brick per row, and ends when the number of bricks in the row is 1
	 * @param x int x: Horizontal position in pixels
	 * @param y	int y: Vertical position in pixels
	 * @param rowBricks	int rowBricks: Number of bricks to make in a row
	 * @return void
	 * Precondition:
	 * 		No bricks at (>x, y)
	 * Postcondition:
	 * 		Row of bricks in row (>x, y)
	 */
	private void buildRow(int x, int y, int rowBricks) {
		for (int i = 0; i < rowBricks; i++) {
			add(makeBrick(x, y));
			x += BRICK_WIDTH;
		}
	}
	
	
	/** Builds pyramid */	
	public void run() {
		// Initialize coordinates and values
		int x = getPadding();
		int y = getHeight();
		int rowBricks = BRICKS_IN_BASE;
		
		// Build Pyramid
		while (rowBricks != 0) {
			buildRow(x, y, rowBricks);
			x += (BRICK_WIDTH / 2);
			y -= (BRICK_HEIGHT);
			rowBricks--;
		}
	}
	
	
	/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

	/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

	/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
}

