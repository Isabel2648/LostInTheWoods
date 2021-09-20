import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class ballPerson2 extends JPanel {
	
	static JFrame f;
	static int x = 100;
	static int y = 0;
	static boolean down = true;

	public void paint(Graphics g) {
		
		setSize(f.getSize());
		g.setColor(Color.GREEN);
		g.fillOval(x, y, 50, 50);
	}
	
	public static void main(String[] args) {
		
		f = new JFrame("Woods 2");
		ballPerson2 c = new ballPerson2();
		f.add(c);
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
			if(down) {
				y++;
				if (y>f.getHeight()-50) {
					down = !down;	
				}
			}
			if(!down) {
				y--;
				if (y==0) {
					down = !down;	
				}
			}
			f.repaint();
			
		} catch (Exception e) {
			
		}
	
	}

}