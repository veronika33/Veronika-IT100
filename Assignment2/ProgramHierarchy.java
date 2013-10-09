/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	
	private static final int BOX_WIDTH = 140;
	private static final int BOX_HEIGHT = 50;	
	
	public void run() {

		double midX = getWidth()/2;
		double midY = getHeight()/2;		
		
		createBox(midX-BOX_WIDTH/2, midY-BOX_HEIGHT*1.5, "Program");
		createBox(midX-BOX_WIDTH/2, midY+BOX_HEIGHT*0.5, "ConsoleProgram");
		createBox(midX-BOX_WIDTH*1.5-20, midY+BOX_HEIGHT*0.5, "GraphicsProgram");
		createBox(midX+BOX_WIDTH/2+20, midY+BOX_HEIGHT*0.5, "DialogProgram");
				
		GLine line = new GLine (midX, midY-BOX_HEIGHT*0.5, midX, midY+BOX_HEIGHT*0.5);
		add (line);
		GLine line2 = new GLine (midX, midY-BOX_HEIGHT*0.5, midX-BOX_WIDTH-20, midY+BOX_HEIGHT*0.5);
		add (line2);
		GLine line3 = new GLine (midX, midY-BOX_HEIGHT*0.5, midX+BOX_WIDTH+20, midY+BOX_HEIGHT*0.5);
		add (line3);				
	}
		
	private void createBox(double x, double y, String text) {
		GRect box = new GRect (x, y, BOX_WIDTH, BOX_HEIGHT);
		add(box);		
		GLabel label = new GLabel(text, x+BOX_WIDTH/2, y+BOX_HEIGHT/2);
		label.move(-label.getWidth()/2, label.getAscent()/2);
		add(label);
	}	
	
}

