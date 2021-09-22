package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

//TODO Rename Class: Generally classes are named with a Capital letter first.
public class ballPerson extends JPanel {

	//TODO These shouldn't be static.  That means the data is the same for all instances that is created with this class.
	static JFrame f;
	static int x = 0;
	static int y = 100;
	static boolean right = true;  //TODO Not sure if we will need this

	//TODO Have an instance variable for a Forest
	//TODO Maybe makes sense to have a Random variable too?

	//TODO Have a constructor that takes parameters and sets the Forest, x, and y variables.

	//TODO Pretty similar to my implementation.  We used different GUI frameworks.  You used Swing.  I used JavaFx.
	//Probably reimplement to javaFx and a GraphicsContext since there is relatively little code to change here.
	public void paint(Graphics g) {
		
		setSize(f.getSize());
		g.setColor(Color.RED);
		g.fillOval(x, y, 50, 50);
	}

	//TODO Need to combine some of this with the Model class or View.
	public static void main(String[] args) {
		
		f = new JFrame("Woods");
		ballPerson b = new ballPerson();
		f.add(b);
		f.setVisible(true);
		f.setSize(600, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while(true) {
			move(f);
		}
	}

	//TODO Add Random Component for X and Y directions. https://www.educative.io/edpresso/how-to-generate-random-numbers-using-random-class-in-java
	//TODO Don't move if it goes out of the bounds of the grid/forest
	public static void move(JFrame f) {
		try {
			TimeUnit.MILLISECONDS.sleep(5);//This was probably done because the animation was super fast.  This will be done later too.
			if(right) {
				x++;
				if (x>f.getWidth()-50) {
					right = !right;	
				}
			}
			if(!right) {
				x--;
				if (x==0) {
					right = !right;	
				}
			}
			f.repaint();// TODO The repaint method will be moved to another spot..
			
		} catch (Exception e) {
			
		}
	}
}
