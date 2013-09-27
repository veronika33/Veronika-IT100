/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {
	
	public void run() {
		
		putBeepersAcross();				
		turnRight();
		moveToWall();
		turnRight();		
		lookForBeeper();
		
		if (noBeepersPresent()){
			turnAround();
			move();
			lookForBeeper();
			turnAround();
		}
		
		turnLeft();
		moveToWall();
		putBeeper();
		turnRight();
		moveToWall();
		turnAround();
		collectBeepers();			
		turnRight();
		moveToWall();
		turnRight();
		
		while (noBeepersPresent())
			move();				
	}
	
	private void moveToWall(){
		while (frontIsClear())
			move();
	}
	
	private void lookForBeeper(){
		while (noBeepersPresent() && frontIsClear()){
			move();
			turnRight();
			move();
			turnLeft();
		}
	}
	
	private void putBeepersAcross(){
		putBeeper();		
		while (frontIsClear()){
			move();
			turnLeft();
			move();
			turnRight();
			putBeeper();
		}
	}
	
	private void collectBeepers(){
		pickBeeper();		
		while (frontIsClear()){
			move();
			turnLeft();
			move();
			turnRight();
			pickBeeper();
		}
	}

}
