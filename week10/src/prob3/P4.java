package prob3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class P4 extends JFrame{
	String s = "Love Java";
	
	public P4() {
		setTitle("Left 키로 문자열 이동");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		//StringBuffer sb = new StringBuffer("Love Java");
		JLabel la = new JLabel(s);
		c.add(la);
		
		c.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					s = s.substring(1) + s.substring(0, 1);
					la.setText(s);
				}
			}
		});
		
		setSize(300,200);
		setVisible(true);
		
		c.setFocusable(true);
		c.requestFocus();
	}
	public static void main(String[] args) {
		new P4();
	}
}