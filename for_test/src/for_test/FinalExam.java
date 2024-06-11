package for_test;

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
		
		/*
		 * 2-1번 문제를 위해 주석 처리
		JMenu menu2 = new JMenu("2-1번문제"); 
		menu2.add(new JMenuItem("New"));
		menu2.add(new JMenuItem("Open"));
		menu2.addSeparator(); // 분리선 삽입
		menu2.add(new JMenuItem("Save"));
		menu2.add(new JMenuItem("SaveAs"));
		*/
		
		// 2-1번 문제를 위해 추가		
		JMenu menu2 = new JMenu("2-1번문제"); 
		JMenuItem newItem = new JMenuItem("New");
		JMenuItem openItem = new JMenuItem("Open");
		JMenuItem saveItem = new JMenuItem("Save");
		JMenuItem saveAsItem = new JMenuItem("SaveAs");

		newItem.addActionListener(new MenuActionListener("New"));
		openItem.addActionListener(new MenuActionListener("Open"));
		saveItem.addActionListener(new MenuActionListener("Save"));
		saveAsItem.addActionListener(new MenuActionListener("SaveAs"));

		menu2.add(newItem);
		menu2.add(openItem);
		menu2.addSeparator(); // 분리선 삽입
		menu2.add(saveItem);
		menu2.add(saveAsItem);
		//
		
		
		/* 
		 * 2-2번 문제를 위해 주석 처리
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
		*/
		
		// 2-2번 문제를 위해 추가
		JMenu menu3 = new JMenu("2-2번문제"); 
		JMenuItem open2Item = new JMenuItem("Open");
		JMenuItem colorItem = new JMenuItem("Color");
		JMenuItem lineItem = new JMenuItem("Line");
		JMenuItem rectItem = new JMenuItem("Rect");
		JMenuItem ovalItem = new JMenuItem("Oval");
		JMenuItem rndRectItem = new JMenuItem("RndRect");
		JMenuItem textItem = new JMenuItem("Text");
		JMenuItem freeLineItem = new JMenuItem("FreeLine");

		open2Item.addActionListener(new MenuActionListener("Open"));
		colorItem.addActionListener(new MenuActionListener("Color"));
		lineItem.addActionListener(new MenuActionListener("Line"));
		rectItem.addActionListener(new MenuActionListener("Rect"));
		ovalItem.addActionListener(new MenuActionListener("Oval"));
		rndRectItem.addActionListener(new MenuActionListener("RndRect"));
		textItem.addActionListener(new MenuActionListener("Text"));
		freeLineItem.addActionListener(new MenuActionListener("FreeLine"));

		menu3.add(open2Item);
		menu3.addSeparator(); // 분리선 삽입
		menu3.add(colorItem);
		menu3.addSeparator(); // 분리선 삽입
		menu3.add(lineItem);
		menu3.add(rectItem);
		menu3.add(ovalItem);
		menu3.add(rndRectItem);
		menu3.add(textItem);
		menu3.add(freeLineItem);
		//
		
		
		JMenu menu4 = new JMenu("3,4,5번문제"); 
		// menu4.add(new JMenuItem("3. 게임시작"));
		
		// 3번 문제를 위해 추가
		JMenuItem gameStartItem = new JMenuItem("3. 게임시작");
	    gameStartItem.addActionListener(new GameStartActionListener()); // 게임 시작 리스너 추가
	    menu4.add(gameStartItem);
	    //
	    
		
		// menu4.add(new JMenuItem("4. 계산기"));
		
		// 4번 문제를 위해 추가
	    JMenuItem Q4Item = new JMenuItem("4. 계산기");
	    menu4.add(Q4Item);
	    Q4Item.addActionListener(new Q4ItemActionListener());
	    //
	    
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
	
	// 2-1번 문제를 위해 추가
	class MenuActionListener implements ActionListener {
		private String command;
		
		MenuActionListener(String command) {
			this.command = command;
		}
		
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, command + " selected.");
		}
	}

	
	// 3번 문제를 위해 추가
	class GameStartActionListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	        // 시작 시간 측정
	        long startTime = System.currentTimeMillis();
	        
	        // 게임 실행을 시뮬레이션 (예: 5초 동안)
	        try {
	            Thread.sleep(5000); // 5초 동안 게임 실행을 시뮬레이션합니다.
	        } catch (InterruptedException ex) {
	            ex.printStackTrace();
	        }
	        
	        // 종료 시간 측정
	        long endTime = System.currentTimeMillis();
	        long elapsedTimeMillis = endTime - startTime;
	        float elapsedTimeSec = elapsedTimeMillis / 1000F;
	        
	        JOptionPane.showMessageDialog(null, "게임 실행 시간: " + elapsedTimeSec + " 초");
	    }
	}
	//
	
	
	// 4번 문제를 위해 추가
	class Q4ItemActionListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	        new Calculator();
	    }
	}
	//
	
	
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