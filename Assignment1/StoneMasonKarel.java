/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass makes Karel repair a set of arches 
 * where some of the stones (represented by beepers, of course) 
 * are missing from the columns supporting the arches
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {
		
	public void run() {
		
		repairColumn();		
		
		while (frontIsClear()) {
			
			for (int i=0; i<4; i++)
				move();
			
			repairColumn();											
		}
												
	}

	private void repairColumn(){		
		turnLeft();		
		if (noBeepersPresent())
			putBeeper();
		
		while (frontIsClear()){
			move();
			if (noBeepersPresent()) 
				putBeeper();				
		}
		
		turnAround();
		while (frontIsClear())
			move();		
		turnLeft();			
	}	
			
}
