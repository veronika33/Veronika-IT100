/*
 * File: Breakout_extended.java
 * -------------------
 * 
 * Extended version of Breakout game.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout_extended extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/** Offset of the bricks from the left side*/	
	private static final double BRICK_X_OFFSET = (WIDTH - NBRICKS_PER_ROW * BRICK_WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP)/2;
	
/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		setUp();
		while (turns > 0){
			play();
		}
	}
	
/* Method: setUp() */
/** Sets up the bricks and the paddle */ 
	private void setUp(){
		addBricks();
		createPaddle();	
		scoreLabel = score(0);
		add(scoreLabel);
	}
	
/* Method: addBricks() */	
/** Sets up the bricks */
	private void addBricks(){
		
		double y = BRICK_Y_OFFSET;
		Color c = Color.RED;;
		
		for (int i = 0; i < 10; i++){
						
			switch (i) {
				case 2 : c = Color.ORANGE;
				break;
				case 3 : c = Color.ORANGE;
				break;	
				case 4 : c = Color.YELLOW;
				break;					
				case 5 : c = Color.YELLOW;
				break;					
				case 6 : c = Color.GREEN;
				break;	
				case 7 : c = Color.GREEN;
				break;		
				case 8 : c = Color.CYAN;
				break;	
				case 9 : c = Color.CYAN;
				break;	
				default: break;
			}
			
			addRow(y, c);
			
			y += BRICK_HEIGHT + BRICK_SEP;
		}
	}
	
/* Method: addRow() */
/** Creates one row of bricks */
	private void addRow(double y, Color c){
		
		double x = BRICK_X_OFFSET;

		for (int i = 0; i < NBRICKS_PER_ROW; i++) {			
			addBrick(x, y, c);
			x += BRICK_WIDTH + BRICK_SEP;
		}
	}
	
/* Method: addBrick() */	
/** Creates one brick */
	private void addBrick(double x, double y, Color c){
		GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
		brick.setColor(c);
		brick.setFilled(true);
		add(brick);
		}
	
/* Method: createPaddle() */	
/** Creates paddle */
	private void createPaddle() {
		paddle = new GRect((WIDTH-PADDLE_WIDTH)/2, getHeight()-PADDLE_Y_OFFSET-PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);	
		paddle.setFilled(true);
		add(paddle);				
	}
	
/* Method: init() */	
/** Initialize mouse listeners */
	public void init() {
		addMouseListeners();
	}
	
/* Method: mousePressed() */	
/** Called on mouse press to record the coordinates of the click */
	public void mousePressed(MouseEvent e) {
		last = new GPoint(e.getPoint());		
	}

/* Method: mousePressed() */
/** Called on mouse drag to reposition the object */ 
	public void mouseDragged(MouseEvent e) {
				double x = e.getX() - last.getX();
				if (paddle.getX()+x < 0)
					paddle.setLocation(0, getHeight()-PADDLE_Y_OFFSET-PADDLE_HEIGHT);
				else if (paddle.getX()+x > WIDTH-PADDLE_WIDTH)
					paddle.setLocation(WIDTH-PADDLE_WIDTH, getHeight()-PADDLE_Y_OFFSET-PADDLE_HEIGHT);
				else {
					paddle.move(x, 0);
					last = new GPoint(e.getPoint());
				}				
	}

/* Method: play() */
/** Creates the ball and moves it around until it is terminated */
	private void play(){
		/* Asks user to click to start the game */
		GLabel click = createMessage("Click to play");
		add(click);
		waitForClick();
		remove(click);
		
		createBall();		
		playBall();
	}
	

/* Method: createBall() */
/** Creates the ball and sets it in the middle of the window */ 
	private void createBall(){
		ball = new GOval(WIDTH/2-BALL_RADIUS, HEIGHT/2-BALL_RADIUS, BALL_RADIUS*2,BALL_RADIUS*2);
		ball.setFilled(true);
		add(ball);
	}

/* Method: playBall() */
/** Moves the ball around the window until the game is won or lost */  
	private void playBall(){
		/* sets the initial velocity of the ball */
		vy = 4.5;
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) vx = -vx;
				
		while(true){
			moveBall();
			checkForCollision();
			
			/* check if there are no more bricks and display winning message */
			if (numberOfBricks == 0){
				add(createMessage("You Won!"));
				turns = 0;
				break;
			}
			
			/* check if the ball hit the bottom wall */
			if (ball.getY() > getHeight()-2*BALL_RADIUS){	
				turns--;
				/* check if there are any turns remaining */
				if (turns == 0)
					add(createMessage("Game Over!"));
				else {
					pause(200);
					remove(ball);
					paddleHits = 0;
				}
				break;
			}
		}
	}
	
/* Method: moveBall() */
/** Moves ball */
	private void moveBall(){
		ball.move(vx,vy);
		pause(40);
	}
	
/* Method: checkForCollision() */
/** Checks if the ball is colliding with a brick, paddle or a wall */
	private void checkForCollision(){
		GObject collider = getCollidingObject();
		if (collider != null){
			vy = -(vy);
			bounceClip.play();
			
			if (collider == paddle){
				/* check if the ball collides with padlle on the edge and changes the velocity x 8 */
				if (vx < 0 && getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()+2*BALL_RADIUS) == null)
					vx = -(vx);
				if (vx > 0 && getElementAt(ball.getX(), ball.getY()+2*BALL_RADIUS) == null)
					vx = -(vx);		
				
				/* accelerates the velocity of ball on the seventh time it hits the paddle */
				paddleHits++;
				if (paddleHits == 7)
					vy *= 2;
			}
			
			if (collider != paddle){
				/* set number of points according to the color of the brick hit */
				Color c = collider.getColor();
				if (c == Color.CYAN || c == Color.GREEN)
					points = 5;
				else if (c == Color.YELLOW || c == Color.ORANGE)
					points = 10;
				else 
					points = 15;
				
				/* update score */
				remove(scoreLabel);
				scoreLabel = score(points);
				add(scoreLabel);
				
				/*removes brick hit */
				remove(collider);
				numberOfBricks--;
				
			}
		}	
		
		/* checks if the ball collides with wall */
		if (ball.getY() < 0)
			vy = -(vy);
		if (ball.getX() <= 0 || ball.getX()+2*BALL_RADIUS >= WIDTH)
			vx = -(vx);
	}
	
/* Method: getCollidingObject() */
/** Returns colliding object of the ball */ 
	private GObject getCollidingObject() {
		GObject element = getElementAt(ball.getX(), ball.getY());
		if (element == null)
			element = getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY());
		if (element == null)
			element = getElementAt(ball.getX(), ball.getY()+2*BALL_RADIUS);
		if (element == null)
			element = getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()+2*BALL_RADIUS);
    	if (ball.getY()+2*BALL_RADIUS > paddle.getY())		// makes sure the ball doesn't get 'glued' to the paddle
			element = null;	
		return element;
	}
	
/* Method: createMessage() */
/** Creates message on the screen for the player */
	private GLabel createMessage(String message){
		GLabel label = new GLabel(message);
		label.setFont("SansSerif-22");
		label.setLocation((WIDTH-label.getWidth())/2, (HEIGHT+label.getHeight())/2);
		return label;
	}

/* Method: score() */
/** Sets score on the screen */	
	private GLabel score(int p){
		score += p;
		GLabel label = new GLabel("score: " + score, 20, getHeight()-10);
		return label;
	}
	
/* Private instance variables */ 
	private GPoint last;		/* The last mouse position */ 
	private GRect paddle;
	private GOval ball;
	private double vx, vy;		/* velocity coordinates */ 
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private int numberOfBricks = NBRICK_ROWS * NBRICKS_PER_ROW;
	private int turns = NTURNS;
	private int paddleHits = 0;
	private GLabel scoreLabel;
	private int score = 0;
	private int points;		/* number of points for one brick hit */ 
	AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au"); /* loads the sound for bouncing ball */ 
}
