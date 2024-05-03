package prob4;

import javax.swing.*;
import java.awt.*;

public class P6 extends JFrame {
	public P6() {
		setTitle("Random Labels");
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(null);

		for (int i = 0; i < 20; i++) {
			int x = (int) (Math.random() * 200) + 50;
			int y = (int) (Math.random() * 200) + 50;
			
			JLabel label = new JLabel((int)(Math.random() * 100)+"");
			label.setLocation(x,y);
			label.setSize(20,20);
			label.setOpaque(true);
			label.setBackground(Color.BLUE);
			c.add(label);
		}
		setVisible(true);
	}
	public static void main(String[] args) {
		new P6();
	}
}