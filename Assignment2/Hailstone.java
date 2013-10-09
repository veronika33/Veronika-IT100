/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int n = readInt("Enter a number: ");
		int counter = 0;
		
		while (n != 1){
			print(n);
			if (isEven(n)){
				n /= 2;
				println(" is even, so I take half: " + n);
			} else {
				n = n*3 + 1;
				println(" is odd, so I make 3n+1: " + n);
			}
			counter++;			
		}		
		print("The process took " + counter + " to reach 1");	
	}

	private boolean isEven(int num) {
		if (num % 2 == 0)
			return true;
		return false;
	}
}

