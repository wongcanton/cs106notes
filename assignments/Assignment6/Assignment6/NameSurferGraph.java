/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import acm.program.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	* @param program: Program being used for the canvas.
	*/
	public NameSurferGraph(Program program) {
		addComponentListener(this);
		//	 You fill in the rest //
		mainProgram = program;
		entries = new ArrayList<NameSurferEntry>();
		colors = COLORS;
		colorIterator = 0;
		init();
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		entries.clear();	
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	* @param entry: NameSurferEntry to add to the display.
	*/
	public void addEntry(NameSurferEntry entry) {
		entries.add(entry);
	}
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {		
		// Remove graph and make a new one.
		clearGraph();
		init();
		// Reset the colorIterator, otherwise each update assigns new color
		// to already plotted names.
		colorIterator = 0;
		plotEntries();
	}
	
	
	/**
	 * Clears the display.
	 */
	private void clearGraph() {
		this.removeAll();
	}
	
	
	/**
	 * Create the display.
	 * @param program: Program being used for the canvas.
	 */
	public void init() {
		// Initialize size.
		initSize();
		
		// Make and add lines.
		int offset = getOffset();
		addHorizontalLines(offset);
		addVerticalLines();
				
		// Get X positions of vertical lines and Y positions of horizontal
		// lines, for reference.
		horizontalLinesY = getHorizontalLinesY();
		verticalLinesX = getVerticalLinesX();
		verticalLinesY = getVerticalLinesY();
		
		// Make and add years.
		addYears();
		
		// Initialize lengthPerRank.
		initLengthPerRank();
	}
	
	
	/**
	 * Initializes the offset for a label, and returns the value.
	 * @return offset: Offset for a label.
	 */
	private int getOffset() {
		// Make test label.
		Integer tmpYear = YEARS[0];
		GLabel tmpLabel = new GLabel(tmpYear.toString());
		return (int) tmpLabel.getAscent();
	}
	
	
	/**
	 * Initialize graph height and width with the program height and width.
	 */
	private void initSize() {
		// Initialize instance variables.
		graphHeightMax = mainProgram.getHeight() - GRAPH_MARGIN_SIZE;
		graphHeightMin = 0 + GRAPH_MARGIN_SIZE;
		graphWidthMax = mainProgram.getWidth();
		graphWidthMin = 0;
		lineSpace = graphWidthMax / N_SPACES;
	}
	
	
	/**
	 * Makes and adds horizontal lines to canvas.
	 * @param offset: Offset for horizontal line.
	 */
	private void addHorizontalLines(int offset) {
		// Make lines array.
		horizontalLines = new GLine[N_HORIZONTAL_LINES];
		
		// Make horizontal GLines.
		GLine topLine = new GLine(
			graphWidthMin, graphHeightMin,
			graphWidthMax, graphHeightMin
		);
		GLine bottomLine = new GLine(
			graphWidthMin, graphHeightMax - offset - BOTTOM_OFFSET,
			graphWidthMax, graphHeightMax - offset - BOTTOM_OFFSET
		);
		horizontalLines[TOP_LINE] = topLine;
		horizontalLines[BOTTOM_LINE] = bottomLine;
		for (int i = 0; i < horizontalLines.length; i++) {
			add(horizontalLines[i]);
		}
	}
	
	
	/**
	 * Uses the horizontalLines array from the horizontal line addition and
	 * returns an int array of Y positions for each of the lines.
	 * @return horizontalLinesY[]: Array of Y positions for the lines added by
	 * 	addHorizontalLines()
	 */
	private int[] getHorizontalLinesY() {
		int[] horizontalLinesY = new int[horizontalLines.length];
		for (int i = 0; i < horizontalLinesY.length; i++) {
			horizontalLinesY[i] = (int) horizontalLines[i].getEndPoint().getY();
		}
		return horizontalLinesY;
	}
	
	
	/**
	 * Makes and adds vertical lines to canvas.
	 */
	private void addVerticalLines() {
		// Make lines array.
		verticalLines = new GLine[N_SPACES];
		// Fill and add lines.
		int lineLocation = 0;
		for (int i = 0; i < verticalLines.length; i++) {
			verticalLines[i] = new GLine(
				lineLocation, graphHeightMin,
				lineLocation, graphHeightMax
			);
			lineLocation += lineSpace;
			this.add(verticalLines[i]);
		}		
	}
	
	
	/**
	 * Uses the verticalLines array from the vertical line addition and returns an int
	 * array of X positions for each of the lines.
	 * @return verticalLinesX[]: Array of X positions for the lines added by
	 * 	addVerticalLines()
	 */
	private int[] getVerticalLinesX() {
		int[] verticalLinesX = new int[verticalLines.length];
		for (int i = 0; i < verticalLines.length; i++) {
			verticalLinesX[i] = (int) verticalLines[i].getEndPoint().getX();
		}
		return verticalLinesX;
	}
	
	
	/**
	 * Uses the verticalLines array from the vertical line addition and returns an int
	 * array of Y positions for each of the lines' endpoint.
	 * @return verticalLinesY[]: Array of Y positions for the lines added by
	 * 	addVerticalLines()
	 */
	private int [] getVerticalLinesY() {
		int[] verticalLinesY = new int[verticalLines.length];
		for (int i = 0; i < verticalLines.length; i++) {
			verticalLinesY[i] = (int) verticalLines[i].getEndPoint().getY();
		}
		return verticalLinesY;
	}
	
	
	/**
	 * Adds list of years to graph next to the vertical lines representing the
	 * years.
	 */
	private void addYears() {
		// Initialize yearsLabels and yearInts.
		yearsLabels = new GLabel[YEARS.length];
		yearInts = new Integer[YEARS.length];
		// Initialize local variables.
		double labelAscent = 0;
		// Configure yearsLabels.
		for (int i = 0; i < yearsLabels.length; i++) {
			yearInts[i] = YEARS[i];
			yearsLabels[i] = new GLabel(
				yearInts[i].toString()
			);
			labelAscent = yearsLabels[i].getAscent();
			yearsLabels[i].setLocation(
				verticalLinesX[i] + YEAR_OFFSET,
				verticalLinesY[i]
			);			
		}
		
		// Add yearsLabels.
		for (int i = 0; i < yearsLabels.length; i++) {
			add(yearsLabels[i]);
		}
	}
	
	
	/**
	 * Initializes the length per rank on graph.
	 */
	private void initLengthPerRank() {
		double horizontalRange = (
			horizontalLines[BOTTOM_LINE].getEndPoint().getY() - 
			horizontalLines[TOP_LINE].getEndPoint().getY()
		);
		lengthPerRank = horizontalRange / MAX_RANK;
	}
	
	
	/**
	 * Graphs an entry.
	 * @param entry: NameSurferEntry to graph.
	 */
	private void graphEntry(NameSurferEntry entry) {
		// Initialize local variables. May not be necessary.
		String entryName = entry.getName();
		int[] yearData = new int[YEARS.length];
		for (int i = 0; i < yearData.length; i++) {
			yearData[i] = entry.getRank(i);
		}
		double position = 0;
		Color lineColor = nextColor();
		// Make list of points per entry.
		GPoint[] entryPoints = new GPoint[YEARS.length];
		for (int i = 0; i < entryPoints.length; i ++) {
			if (yearData[i] == MAX_RANK || yearData[i] == 0) {
				position = horizontalLinesY[BOTTOM_LINE];
			} else {
				position = (
					horizontalLinesY[TOP_LINE] +
					(yearData[i] * lengthPerRank)
				);
			}
			entryPoints[i] = new GPoint(
				(double) verticalLinesX[i],
				position
			);
		}
		// Make lines between points and add them to the canvas.
		GLine[] entryLines = new GLine[entryPoints.length - 1];
		for (int i = 0; i < entryLines.length; i++) {
			entryLines[i] = new GLine(
				entryPoints[i].getX(),
				entryPoints[i].getY(),
				entryPoints[i + 1].getX(),
				entryPoints[i + 1].getY()
			);
			entryLines[i].setColor(lineColor);
			this.add(entryLines[i]);
		}
		// Make data per point and add it to the canvas.
		GLabel[] entryLabels = new GLabel[entryPoints.length];
		double labelAscent = 0;
		for (int i = 0; i < entryLabels.length; i++) {
			entryLabels[i] = new GLabel(
					entry.getName() + " " + yearData[i]
			);
			if (yearData[i] == 0) {
				entryLabels[i].setLabel(
					entry.getName() + "*"
				);
			}
			labelAscent = entryLabels[i].getAscent();
			entryLabels[i].setLocation(
				entryPoints[i].getX(),// + LABEL_OFFSET,
				entryPoints[i].getY() - labelAscent
			);
			entryLabels[i].setColor(lineColor);
			this.add(entryLabels[i]);
		}
	}
	
	
	/**
	 * Plots the entries.
	 */
	private void plotEntries() {
		for (int i = 0; i < entries.size(); i++) {
			graphEntry(entries.get(i));
		}
	}
	
	
	/**
	 * Returns a color within COLORS at index colorIterator. The colorIterator
	 * is then iterated unless is it is at value 3, by which it is set to zero.
	 * @return nextColor: Color within COLORS at index colorIterator.
	 */
	private Color nextColor() {
		Color nextColor = colors[colorIterator];
		if (colorIterator < 3) {
			colorIterator++;
		} else {
			colorIterator = 0;
		}
		return nextColor;
	}
	
	
	/* Instance variables. */
	// Program.
	private Program mainProgram;
	// Graphics variables.
	private int graphHeightMax;
	private int graphHeightMin;
	private int graphWidthMax;
	private int graphWidthMin;
	private int lineSpace;
	private GLine[] horizontalLines;
	private GLine[] verticalLines;
	private int[] horizontalLinesY;
	private int[] verticalLinesX;
	private int[] verticalLinesY;
	private GLabel[] yearsLabels;
	private Integer[] yearInts;
	private double lengthPerRank;
	private Color[] colors;
	private int colorIterator;
	// Entry variables.
	private ArrayList<NameSurferEntry> entries;
	
	
	/* Constants. */
	/** Offset for graphics from bottom of GCanvas. */
	private static final int BOTTOM_OFFSET = 5;
	/** Offset for years labels. */
	private static final int YEAR_OFFSET = 5;
	/** Number of horizontal lines in graph. */
	private static final int N_HORIZONTAL_LINES = 2;
	/** Number spaces in graph. */
	private static final int N_SPACES = 11;
	/** Enumeration for horizontal lines. */
	private static final int TOP_LINE = 0;
	private static final int BOTTOM_LINE = 1;
	/** Color set. */
	private static final Color[] COLORS = {
			Color.BLACK, Color.RED,
			Color.BLUE, Color.MAGENTA
	};
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
