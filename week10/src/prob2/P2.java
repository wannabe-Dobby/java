package prob2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class P2 extends JFrame{
	public P2() {
		setTitle("드래깅동안 YELLOW 유지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setBackground(Color.green);
		
		c.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				c.setBackground(Color.yellow);
			}
		});
		c.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				c.setBackground(Color.green);
			}
		});
		
		setSize(300,200);
		setVisible(true);
	}
	public static void main(String[] args) {
		new P2();
	}
}