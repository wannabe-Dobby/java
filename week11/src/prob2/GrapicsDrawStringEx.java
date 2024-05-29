package prob2;

import javax.swing.*;
import java.awt.*;

public class GrapicsDrawStringEx extends JFrame {
	private MyPanel panel = new MyPanel();
	
	public GrapicsDrawStringEx() {
		setTitle("drawString 사용 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		
		setSize(250, 200);
		setVisible(true);
	}
	
	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawString("자바는 재밌다.~~", 30, 30);
			g.drawString("얼마나? 하늘만큼 땅만큼 !!!!", 60, 60);
		}
	}

	public static void main(String[] args) {
		new GrapicsDrawStringEx();

	}

}
