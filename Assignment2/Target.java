/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	public void run() {
		
		double x = getWidth() / 2;
		double y = getHeight() / 2;
		double r1 = 72;
		double r2 = 72*0.65;
		double r3 = 72*0.3;
		
		add(circle(x, y, r1, Color.RED));
		add(circle(x, y, r2, Color.WHITE));
		add(circle(x, y, r3, Color.RED));
		
	}
	
	private GOval circle(double x, double y, double r, Color color) {
		GOval circle = new GOval (x-r, y-r, 2*r, 2*r);
		circle.setFilled(true);
		circle.setColor(color);
		return circle;
	}
}
