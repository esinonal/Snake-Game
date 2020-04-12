
/*
 * I created the snake game. 
 * The snake moves up/down/left/right with the key buttons. 
 * The red squares are food and if you eat them the snake gains points. 
 * The snake dies if it hits any part of its tail or it goes over
 * the edge of the sides. 
 * There is a score counter at the top left where we can see how
 * many points the snake currently has. 1 food = 10 points.
 * The food the snake eats ends up on its body and follows it around.
 * There is also a play/pause button where if you click on it
 * you can pause the game. The button becomes highlighted when 
 * you stand over it. The button can be kind of finnicky
 * but for the most part if you click hard enough it seems to
 * go through. 
 * 
 * I have two classes, Snake, and Panel. Snake is the object class 
 * and Panel is like the main class. The Panel class uses the draw()
 * method to keep updating and printing the snake. 
 * It also is the class that contains the panel details like clicking
 * the button// moving the up/down/left/right keys. The up/down/L/R
 * keys then edit the xSpeed and ySpeed in the object mySnake which is
 * an instance of the Snake class. The Panel class's main function is
 * to go through and constantly update the snake, print the snake, 
 * create the food, and if food is eaten to create the new food.
 * 
 * The Snake class has variables x, y,xSpeed and ySpeed which control the
 * movement of the snake. Variables x and y give current location and xSpeed  
 * and ySpeed give which direction that the snake is moving.
 * The movement of the tail of the snake is kept in a separate array.
 * There are functions to check if paused and if alive.
 * 
 * I already incorporated a lot of things that improve the quality 
 * of the game, for example, it is not possible to go backwards if you
 * are currently going forwards, or the equivalent in all the other directions, 
 * because this would automatically create a game over. Also, 
 * it is not possible for the food to generate on any part of the snake,
 * head or tail. 
 * 
 */







//import javafx.scene.Parent;
import processing.core.PApplet;
import processing.core.PFont;



public class Panel extends PApplet{
	public int boxSize = 20;
	Snake mySnake;
		
	PFont myFont;
	
	
	
	public static void main(String[] args) {
		PApplet.main("Panel");
	}	

	
	public void settings() {
		size(600,600);
	
	}

	
	public void setup() {
		frameRate(7);
		mySnake = new Snake(this);
		mySnake.createFood();
	
		
	} 

	
	PFont f;
	public void draw() {
		//create the set up for background colors and options bar
		background(51);
		fill(35);
		rect(0, 0, 599, 40);
		if (this.buttonOver()) {
			fill(100);
			rect(513, 7, 73, 29, 7);
		}
		else {
			fill(60);
			rect(513, 7, 73, 29, 7);
		}
		
		//create the appropriate writing/texts
		fill(255);
		textSize(20);
		text("SCORE:", 20, 30);
		text(mySnake.score, 94, 30);
		if (mySnake.isPaused == true) {
			text("PLAY", 520, 30);
		}
		else {
			text("PAUSE", 520, 30);
		}
		
		
		mySnake.checkIfDead();
		
		
		if (mySnake.alive) { //if alive 
			
			if (mySnake.isPaused == false) {
				mySnake.update();
			}
			
			mySnake.printSnake();
			mySnake.printFood();
				
			if (mySnake.eatFood()) {
				mySnake.createFood();
			}

		}
		else { //it must be  dead
			
			//print the food and only the tail of the snake (as head went off board)
			for (int i=0; i<mySnake.tail.length; i++) { //will only print the tail if there is any tail to begin with
				rect(mySnake.tail[i][0], mySnake.tail[i][1], boxSize, boxSize);
			}
			mySnake.printFood();
			
			
			//write Game Over
			fill(40);
			rect(50, 190, 510, 100, 7);
			
			fill(255);
			textSize(80);
			text("GAME OVER!", 60, 270);
		}
		
	
	     
	}
	 
	
	public void keyPressed() {
		if (mySnake.isPaused == false) {
			if ((keyCode == UP) && (mySnake.xSpeed != 0) && (mySnake.ySpeed != 1)) {
				mySnake.direction(0,-1);
			}
			if ((keyCode == DOWN) && (mySnake.xSpeed != 0) && (mySnake.ySpeed != -1)) {
				mySnake.direction(0,1);
			}
			if ((keyCode == LEFT) && (mySnake.xSpeed != 1) && (mySnake.ySpeed != 0)) {
				mySnake.direction(-1,0);
			}
			if ((keyCode == RIGHT) && (mySnake.xSpeed != -1) && (mySnake.ySpeed != 0)) {
				mySnake.direction(1,0);
			}
		}
	}
	
	public boolean buttonOver() {
		
		if (mySnake.alive) {  //if game over dont allow the pause/play button to highlight/be pressed
			if (mouseX >= 513 && mouseX <= 586 &&
					mouseY >= 7   && mouseY <= 36) {
					return true;
				}
				else {
					return false;
				}
		}
		return false;
	}
	
	
	public void mouseClicked() {
		//allow the pause/play
		if (this.buttonOver()) {
			if (mySnake.isPaused == false) {
				mySnake.isPaused = true;
			}
			else {
				mySnake.isPaused = false;
			}
		}
	}
	
	
	
	
}