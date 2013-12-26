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

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);	
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		
		displayedEntries.clear();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		
		displayedEntries.add(entry);
	}	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		
		removeAll();
		createGrid();
		displayData();			    
	}
	
	private void createGrid() {
		addVerticalLines();
		addHorizontalLines();		
		addDecadeLabels();
	}
	
	private void addVerticalLines() {
		for (int i = 1; i < NDECADES; i++) {
			add(new GLine(getWidth() / NDECADES * i, 0, getWidth() / NDECADES * i, getHeight()));
		}
	}
	
	private void addHorizontalLines() {
		add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
		add(new GLine(0, getHeight()-GRAPH_MARGIN_SIZE, getWidth(), getHeight()-GRAPH_MARGIN_SIZE));
	}
	
	private void addDecadeLabels() {
		for (int i = 0; i < NDECADES; i++) {
		String decade = Integer.toString(1900 + 10 * i);
		add(new GLabel(decade, getWidth() / NDECADES * i + 3, getHeight()-3));
		}
	}
	
	private void displayData() {
		
		c = Color.BLACK;
		
		Iterator<NameSurferEntry> it = displayedEntries.iterator();
		while (it.hasNext()) {
			NameSurferEntry entry = it.next();			
												
			drawLine(entry);
			
			if (c == Color.BLACK) {
				c = Color.RED;
			} else if (c == Color.RED) {
				c = Color.MAGENTA;
			} else {
				c = Color.BLACK	;
			}
		}
	}
	
	private void drawLine(NameSurferEntry entry) {
		
		double point = (getHeight() - 2*GRAPH_MARGIN_SIZE) / 1000.00;		
		double x2 = 0;
		double y2 = 0;
		
		for (int i = 0; i < NDECADES; i++) {
						
			double x = getWidth() / NDECADES * i;
			double y;
			
			if (entry.getRank(i) == 0) {
				y = getHeight() - GRAPH_MARGIN_SIZE;
			} else {
				y = entry.getRank(i) * point + GRAPH_MARGIN_SIZE;
			}

			String text = entry.getName() + " ";
			if (entry.getRank(i) == 0){
				text = text + "*";
			} else {
				text = text + entry.getRank(i);
			}
			
 			GLabel label= new GLabel(text, x+LABEL_OFFSET, y-LABEL_OFFSET);
			label.setColor(c);
			add(label);
						
			if (i != 0) {
				GLine line = new GLine(x2, y2, x, y);
				line.setColor(c);
				add(line);
			}
			
			x2 = x;
			y2 = y;
		}		
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	private ArrayList<NameSurferEntry> displayedEntries = new ArrayList<NameSurferEntry>();
	Color c;
}
