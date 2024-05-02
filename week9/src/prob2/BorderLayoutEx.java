package prob2;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutEx extends JFrame {
	public BorderLayoutEx() {
		setTitle("BorderLayout Sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		c.setLayout(new BorderLayout(30, 20));
		
		c.add(new JButton("Calculate"));
		c.add(new JButton("add"));
		c.add(new JButton("sub"));
		c.add(new JButton("mul"));
		c.add(new JButton("div"));
		
		setSize(300, 200);
		setVisible(true);
	}

	public static void main(String[] args) {
		new BorderLayout();
	}

}
