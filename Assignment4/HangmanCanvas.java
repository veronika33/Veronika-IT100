/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		/* You fill this in */
		removeAll();
		GLine scaffold = new GLine(getWidth()/2-BEAM_LENGTH, getHeight()/5, getWidth()/2-BEAM_LENGTH, getHeight()/5+SCAFFOLD_HEIGHT);
		add(scaffold);
		GLine beam = new GLine(getWidth()/2-BEAM_LENGTH, getHeight()/5, getWidth()/2, getHeight()/5);
		add(beam);

		GLine rope = new GLine(getWidth()/2, getHeight()/5, startX, startY);
		add(rope);
		
		incorrect = "";
		counter = 0;
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		/* You fill this in */

		GObject o = getElementAt(guessLabelX, guessLabelY);
		if (o != null)
			remove (o);
		
		GLabel guessLabel = new GLabel(word, guessLabelX, guessLabelY);
		guessLabel.setFont("Serif-40");
		add(guessLabel);
		
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		/* You fill this in */
		incorrect += letter;
		GLabel incorrectLabel = new GLabel(incorrect, guessLabelX, guessLabelY+30);
		add(incorrectLabel);
		add(parts[counter]);
		counter++;
		
	}
	
	private final double guessLabelX = 30;
	private final double guessLabelY = 200+SCAFFOLD_HEIGHT;	 
	private double startX = 188;
	private double startY = 112;
	private String incorrect;

	private int counter;
	
	private GOval head = new GOval(startX-HEAD_RADIUS, startY, HEAD_RADIUS*2, HEAD_RADIUS*2);
	private GLine body = new GLine(startX, startY+HEAD_RADIUS*2, startX, startY+HEAD_RADIUS*2+BODY_LENGTH);

	private GLine line1 = new GLine (startX-UPPER_ARM_LENGTH, startY+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD, startX,startY+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD);
	private GLine line2 = new GLine (startX-UPPER_ARM_LENGTH, startY+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD,startX-UPPER_ARM_LENGTH, startY+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
	private GLine line3 = new GLine (startX,startY+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD, startX+UPPER_ARM_LENGTH, startY+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD);
	private GLine line4 = new GLine (startX+UPPER_ARM_LENGTH, startY+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD, startX+UPPER_ARM_LENGTH,startY+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
	private GLine line5 = new GLine (startX-HIP_WIDTH, startY+HEAD_RADIUS*2+BODY_LENGTH, startX, startY+HEAD_RADIUS*2+BODY_LENGTH);
	private GLine line6 = new GLine (startX-HIP_WIDTH, startY+HEAD_RADIUS*2+BODY_LENGTH,startX-HIP_WIDTH, startY+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
	private GLine line7 = new GLine (startX, startY+HEAD_RADIUS*2+BODY_LENGTH, startX+HIP_WIDTH, startY+HEAD_RADIUS*2+BODY_LENGTH);
	private GLine line8 = new GLine (startX+HIP_WIDTH, startY+HEAD_RADIUS*2+BODY_LENGTH, startX+HIP_WIDTH,startY+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
	
	
	private GCompound leftArm = gcomp(line1, line2);
	private GCompound rightArm = gcomp(line3, line4);
	private GCompound leftLeg = gcomp(line5, line6);
	private GCompound rightLeg = gcomp(line7, line8);
	
	private GCompound gcomp(GLine m,GLine n){
		GCompound g = new GCompound();
		g.add(m);
		g.add(n);
		return g;
	}
	
	private GLine leftFoot = new GLine (startX-HIP_WIDTH-FOOT_LENGTH, startY+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH, startX-HIP_WIDTH, startY+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
	private GLine rightFoot = new GLine (startX+HIP_WIDTH, startY+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH, startX+HIP_WIDTH+FOOT_LENGTH, startY+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
	
	private GObject [] parts = {head, body, leftArm, rightArm, leftLeg, rightLeg, leftFoot, rightFoot};
	
	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
}
