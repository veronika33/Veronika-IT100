/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	public void run() {
		int smallest = 0;
		int largest = 0;
		println("This program finds the largest and smallest numbers.");
		while (true) {
			int n = readInt("? ");
			if (n == 0){
				break;
			}
			
			if (n < smallest || smallest == 0)
				smallest = n;

			if (n > largest || largest == 0)
				largest = n;
		}
		if (smallest != 0){
			println("smallest: " + smallest);
			println("largest: " + largest);
		} else {
			print("No values have been entered.");
		}
	}
}

