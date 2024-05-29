package prob2and3;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

// "새창" 
public class NewWindow extends JFrame{
    JLabel label = new JLabel();
    JButton okButton = new JButton("OK");
    public NewWindow(String title) {
    	super(title);
    	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLayout(new FlowLayout());
    	Container c = getContentPane();
        label.setText(title);
        c.add(label);
        c.add(okButton);
        okButton.addMouseListener(new MouseAdapter() {
        		public void mousePressed(MouseEvent e) {
        			setVisible(false);
        		}
        });
        setSize(300,200);
        setVisible(true);
    }
    public static void main(String [] args) {
		new NewWindow("새창");
	}
}
