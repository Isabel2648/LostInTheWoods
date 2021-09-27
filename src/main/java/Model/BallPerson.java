package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

/**
 * This class controls the movement of the two circles that are used to represent two people wandering randomly in the
 * forest.
 */
public class BallPerson {

	private final Color color; //
	private int x = 0; //Integer used represents the direction on the X plane variable
	private int y = 100; //Integer used represents the direction on the Y plane variable
	private Forest forest; //Reference to the Forest in the simulation

	Random rand = new Random(); //Creates rand variable to reference the movement of the people in the simulation

	/**
	 * The constructor for creating the BallPerson to represent a person wandering on the grid
	 * @param x is the direction in which the BallPerson will move towards on the x plane
	 * @param y is the direction in which the BallPerson will move towards on the y plane
	 * @param forest grid plane that represents the forest in which two people are wandering in
	 * @param color fills in the circle color of the BallPerson
	 */
	public BallPerson(int x, int y, Forest forest, Color color) {
		this.x = x;
		this.y = y;
		this.forest = forest;
		this.color = color;
	}

	/**
	 * This method draws the BallPerson components
	 * @param gc Graphics Context to draw on
	 */
	public void draw (GraphicsContext gc) {

		gc.setFill(color); //Fills in the color for each BallPerson
		gc.fillOval(forest.getPaddingX() + x * forest.getCellSize(), forest.getPaddingY()+ y * forest.getCellSize(), forest.getCellSize(), forest.getCellSize());
		//Sets the shape sizes for each BallPerson
	}

	/**
	 * This method sets up the components needed to run in this program
	 * @param args Optional command line arguments
	 */
	public static void main(String[] args) {
		BallPerson ballPerson = new BallPerson(1, 1, new Forest(), Color.RED);
		ballPerson.move();
}

	/**
	 * This method controls the random movement of the two wandering people
	 */
	public void move() {
		//Used random example from https://stackoverflow.com/questions/42895933/how-can-i-make-an-object-move-randomly
		int xMove = rand.nextInt(3) - 1; //Sets up the random direction the person will move across the x plane
		int yMove = rand.nextInt(3) - 1; //Sets up the random direction the person will move across the y plane

		if (xMove + x <= forest.getWidth()-1 && xMove + x >= 0) {
			if (yMove + y <= forest.getHeight()-1 && yMove + y >= 0) {
				y = y + yMove;
				x = x + xMove;
			} //Sets up parameters to prevent the random movements from moving off-screen
		}
	}

	/**
	 * Gets and Sets both x and y variables used for this simulation
 	 * @return used to return all variables that were set
	 */
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
