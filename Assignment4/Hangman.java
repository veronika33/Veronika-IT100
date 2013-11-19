/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	
	private static final int NUM_OF_GUESSES = 8;
	
    private RandomGenerator rgen = RandomGenerator.getInstance();
    
    private HangmanCanvas canvas;
    
    public void init() {
    	canvas = new HangmanCanvas();
    	add(canvas);
    	}
    
    public void run() {
		/* You fill this in */
    	HangmanLexicon lex = new HangmanLexicon();
    	
	    	canvas.reset();
	
	    	String word = lex.getWord(rgen.nextInt(0, lex.getWordCount()-1));
	   
	    	int guesses = NUM_OF_GUESSES;
	    	   		    	
	    	String guess = "";
	    	
	    	for (int i=0; i < word.length(); i++){
	    		guess += "-";
	    	}
	
	    	println("Welcome to Hangman!");
	    			    	
	    	while (guesses > 0){
	    		
	    		if (guess.indexOf("-") == -1){
	    			println("You guessed the word: " + word);
	    			canvas.displayWord(guess);
	    			println("You win.");
	    			break;
	    		}
	    		
	    		println("The word now looks like this: " + guess);
	    		canvas.displayWord(guess);
	    		println("You have " + guesses + " guesses left.");
	    		String l = readLine("Your guess: ");
	    		
	    		while (l.length() != 1 || !(Character.isLetter(l.charAt(0)))){
	    			println("The guess is illegal.");
	    			l = readLine("Your guess: ");
	    		}
	    		
	    		String letter = l.toUpperCase();
	    		int index = word.indexOf(letter);
	    		
	    		if (index == -1) {
	    			println("There are no " + letter + "'s in the word.");
	    			guesses--;   
	    			canvas.noteIncorrectGuess(letter.charAt(0));
	    		} else {
	    			println("That guess is correct.");
	    			String newGuess = "";
	    			for (int i = 0; i < word.length(); i++){
	    				if (letter.equals(word.substring(i, i+1)))
	    					newGuess += letter;
	    				else
	    					newGuess += guess.substring(i, i+1);
	    			}
	    			guess = newGuess;
	    		}
	    		
	    		if (guesses == 0){
	    			println("You're completely hung.");
	    			println("The word was: " + word);
	    			println("You lose.");
	    		}
	    				    		    		
	    	}
	    		    	   		    	
	} 
        
}
