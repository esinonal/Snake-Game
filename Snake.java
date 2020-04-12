

import processing.core.PApplet;
import java.lang.Math;


public class Snake extends PApplet{
	PApplet parent;
	
	public int boxSize = 20;
	public int heightOfSettingsBar = 40;
	public int score = 0;
	
	public boolean isPaused = false;
	public boolean alive = true;
	
	private int x = 240;
	private int y = 300;
	public int xSpeed = 20;
	public int ySpeed = 0;
	public int foodX;
	public int foodY;
	
	public int length = 1;
	public int[][] tail = new int[0][0];
	private int[] valueToDeleteX = new int[1];
	private int[] valueToDeleteY = new int[1];
	
	
	
	public Snake(PApplet p) {
		parent = p;
	}
	
	
	public void update() {
			
			//if not eaten food -->
			       
			if (length == (tail.length + 1)) {
				if (tail.length == 0) {
					valueToDeleteX[0] = x;
					valueToDeleteY[0] = y;
				}
				
				else if (tail.length == 1) {
					valueToDeleteX[0] = tail[0][0];
					valueToDeleteY[0] = tail[0][1];
					tail[0][0] = x;
					tail[0][1] = y;
				}
				
				
				else if (tail.length >= 2) {
					//first shift each value down in the array (previous values)
					valueToDeleteX[0] = tail[tail.length-1][0];
					valueToDeleteY[0] = tail[tail.length-1][1];
					
					int[][] newTail = new int[tail.length][2];
					
					for (int i = 0; i < tail.length-1; i++) {
						newTail[i+1][0] = tail[i][0];
						newTail[i+1][1] = tail[i][1];
					}
					tail = newTail;
					
					//then record the one new position into array at index [0][0] (current x position) and [0][1] (current y position)
					tail[0][0] = x;
					tail[0][1] = y;
					
				}
				
				//update x y positions
				x = x + xSpeed; 
				y = y + ySpeed;
				
				
				//constrain the x and y within the game board (this is not necessary anymore)
				//x = constrain(x, 0, 600 - boxSize);
				//y = constrain(y, heightOfSettingsBar, 600 - boxSize); 
				
				
				
				
			}
			else {
				//else, you have just eaten food -->

				//first tail's array size must increase. 
				//the values in tail i=0-3 must go to newTail i=0-3
				int[][] newTail = new int[tail.length + 1][2]; //works
				if (newTail.length == 1) {
					newTail[0][0] = valueToDeleteX[0];
					newTail[0][1] = valueToDeleteY[0]; //works
				}
				else {
					for (int i = 0; i<newTail.length-1; i++) {
						newTail[i][0] = tail[i][0];
						newTail[i][1] = tail[i][1];
					}
					
					//the spot of i=4, the very end, will be taken by the 'old x'
					newTail[newTail.length-1][0] = valueToDeleteX[0];
					newTail[newTail.length-1][1] = valueToDeleteY[0];
					
				}
				tail = newTail;
				
				
				
				
			
			
		}
			
				
			
	}
	

	
	
	public void printSnake() {
		//print head:
		parent.fill(255.0f);
		if (y != 20) { // aka, the 'head' has gone over the score bar
			parent.rect(x, y, boxSize, boxSize); //then we don't want to print it because it looks bad
		}
		
		//print tail: 
		if (tail.length >= 1) {
			for (int i=0; i<tail.length; i++) {
				parent.rect(tail[i][0], tail[i][1], boxSize, boxSize);
			}
		}
				
	}
	
	public void direction(int directionX, int directionY) {
		xSpeed = directionX * boxSize;
		ySpeed = directionY * boxSize;
		
	}
	
	public void createFood() {
		
		foodX = ((int) (Math.random() * 30)) * 20;
		foodY = (((int) (Math.random() * 28)) * 20)+heightOfSettingsBar;
		
		while (foodIsOnSnake()) { //we must make sure the food does not generate on head or tail
			foodX = ((int) (Math.random() * 30)) * 20;
			foodY = (((int) (Math.random() * 28)) * 20)+heightOfSettingsBar;
			
		}
		
	}
	
	public boolean foodIsOnSnake() {
		
		 //check head - if on head return true
		if ((foodX == x) && (foodY == y)) {
			return true;
		}
		
		//check body - if on body return true
		for (int i=0; i<tail.length; i++) {
			if ((foodX == tail[i][0]) && (foodY == tail[i][1])) {
				return true;
			}
		}
		
		//if youve gotten this far, not on head and not on body so return false
		return false;
	}
	
	
	
	public void printFood() {
		parent.fill(255, 61, 107);
		parent.rect(foodX, foodY, boxSize, boxSize);
	}
	
	public boolean eatFood() {
		if ((Math.abs(x - foodX)<5) && ((Math.abs(y - foodY)<5))) {
			score += 10;
			length += 1;
			return true;
		}
		else {
			return false;
		}
	}

 
	
	public void checkIfDead() {
		for (int i=0; i<tail.length; i++) {
			if ((x == tail[i][0]) && (y == tail[i][1])) {
				alive = false;
			}
		}
		
		if  (     
				((x == 600) && (y <=600) && (y>=20))
		        || ((x == -20) && (y <=600) && (y>=20))
		        || ((y == 600) && (x <=600) && (x>=0))
		        || ((y == 20) && (x <=600) && (x>=0))     )     
		{
			alive = false;
		   }
		   
	}
	
	
	
	

}

