package prob2and3;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class FinalExam extends JFrame {
	JPanel contentPane = new JPanel();	
	FinalExam() {
		setTitle("기말시험");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 2, 5, 5));
	
		contentPane.setVisible(false);
		question1();
		
		createMenu();
		setSize(500,300);
		setVisible(true);
	}
	
	// 메뉴바와 Text 메뉴를 생성하고 Text 메뉴에 4개의 메뉴아이템을 삽입한다.
	void createMenu() {
		JMenuBar mb = new JMenuBar(); // 메뉴바 생성
		JMenu menu1 = new JMenu("1번문제"); 
		JMenuItem oneItem = new JMenuItem("실행");
		oneItem.addActionListener(new OneActionListener());
		menu1.add(oneItem);
		
		JMenu menu2 = new JMenu("2-1번문제"); 
		menu2.add(new JMenuItem("New"));
		menu2.add(new JMenuItem("Open"));
		menu2.addSeparator(); // 분리선 삽입
		menu2.add(new JMenuItem("Save"));
		menu2.add(new JMenuItem("SaveAs"));
		
		JMenu menu3 = new JMenu("2-2번문제"); 
		menu3.add(new JMenuItem("Open"));
		menu3.addSeparator(); // 분리선 삽입
		menu3.add(new JMenuItem("Color"));
		menu3.addSeparator(); // 분리선 삽입
		menu3.add(new JMenuItem("Line"));
		menu3.add(new JMenuItem("Rect"));
		menu3.add(new JMenuItem("Oval"));
		menu3.add(new JMenuItem("RndRect"));
		menu3.add(new JMenuItem("Text"));
		menu3.add(new JMenuItem("FreeLine"));
		
		JMenu menu4 = new JMenu("3,4,5번문제"); 
		menu4.add(new JMenuItem("3. 게임시작"));
		menu4.add(new JMenuItem("4. 계산기"));
		//menu4.add(new JMenuItem("5. 자바란?"));
		
		
		JMenuItem q5Item = new JMenuItem("5. 자바란?");
		menu4.add(q5Item);
		q5Item.addActionListener(new Q5ItemActionListener());

		
		mb.add(menu1); 
		mb.add(menu2); 
		mb.add(menu3); 
		mb.add(menu4); 
		setJMenuBar(mb);
		
	}
	
	class Q5ItemActionListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	    	new TabbedJava();
	    }
	}
	
	
	class OneActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("실행")) {
				contentPane.setVisible(true);
			}
		}
	}
	
	void question1() {
		contentPane.removeAll();
		contentPane.add(new JLabel("이름: "));	
		contentPane.add(new JTextField("임지민"));
		contentPane.add(new JLabel("학번: "));	
		contentPane.add(new JTextField("22140038"));
		contentPane.add(new JLabel("주소: "));	
		contentPane.add(new JTextField("대구"));
		contentPane.add(new JLabel("전화번호: "));	
		contentPane.add(new JTextField("010")); 
	}
	
	public static void main(String [] args) {
		new FinalExam();
	}
}