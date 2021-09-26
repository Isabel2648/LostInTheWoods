package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;


//TODO Rename Class: Generally classes are named with a Capital letter first.
public class BallPerson {

	private int x = 0;
	private int y = 100;


	//TODO Have an instance variable for a Forest
	private Forest forest;

	//TODO Maybe makes sense to have a Random variable too?
	Random rand = new Random();

	//TODO Have a constructor that takes parameters and sets the Forest, x, and y variables.
	public BallPerson (int x, int y, Forest forest) {
		this.x = x;
		this.y = y;
		this.forest = forest;

	}

	//TODO Pretty similar to my implementation.  We used different GUI frameworks.  You used Swing.  I used JavaFx.
	//Probably reimplement to javaFx and a GraphicsContext since there is relatively little code to change here.
	public void draw (GraphicsContext gc) {

		gc.setFill(Color.RED);
		gc.fillOval(forest.getPaddingX() + x * forest.getCellSize(), forest.getPaddingY()+ y * forest.getCellSize(), forest.getCellSize(), forest.getCellSize());
	}

public static void main(String[] args) {
		BallPerson ballPerson = new BallPerson(1, 1, new Forest());
		ballPerson.move();
}

	public void move() {
		//Used random example from https://stackoverflow.com/questions/42895933/how-can-i-make-an-object-move-randomly
		int xMove = rand.nextInt(3) - 1;

		int yMove = rand.nextInt(3) - 1;

		if (xMove + x <= forest.getWidth()-1 && xMove + x >= 0) {
			//xMove += get_speed();
			if (yMove + y <= forest.getHeight()-1 && yMove + y >= 0) {
				//yMove += get_speed();
				y = y + yMove;
				x = x + xMove;
			}
		}

//		System.out.println(xMove);
//		System.out.println(yMove);
	}

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
