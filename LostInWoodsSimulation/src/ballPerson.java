import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class ballPerson extends JPanel {
	
	static JFrame f;
	static int x = 0;
	static int y = 100;
	static boolean right = true;

	public void paint(Graphics g) {
		
		setSize(f.getSize());
		g.setColor(Color.RED);
		g.fillOval(x, y, 50, 50);
	}
	
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
	
	public static void move(JFrame f) {
		try {
			TimeUnit.MILLISECONDS.sleep(5);
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
			f.repaint();
			
		} catch (Exception e) {
			
		}
	}
}
