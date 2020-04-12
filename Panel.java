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
