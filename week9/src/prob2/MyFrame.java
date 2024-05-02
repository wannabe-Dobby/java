package prob2;

import javax.swing.*;

public class MyFrame extends JFrame {
	public MyFrame() {
		setTitle("300x300 스윙 프레임 만들기");
		setSize(300, 300);
		setVisible(true);
	}
	
	public static void main (String[] args) {
		System.out.println("학번 : 22140038, 이름 : 임지민\n");
		
		MyFrame frame = new MyFrame();
	}
}