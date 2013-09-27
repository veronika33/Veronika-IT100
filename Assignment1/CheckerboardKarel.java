/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run(){
		
		putBeeper();
		
		while (frontIsClear() || (facingEast() && leftIsClear()) || (facingWest() && rightIsClear())) {
			
			if (frontIsClear()) 
				move();
			else if (facingEast() && leftIsClear()){
				turnLeft();
				move();
				turnLeft();
			} else if (facingWest() && rightIsClear()){
				turnRight();
				move();
				turnRight();								
			}
			
			if (frontIsClear()) {
				move();
				putBeeper();
			} else if (facingEast() && leftIsClear()){
				turnLeft();
				move();
				turnLeft();
				putBeeper();
			} else if (facingWest() && rightIsClear()){
				turnRight();
				move();
				turnRight();
				putBeeper();
			}
						
		}	
		
	}
}
