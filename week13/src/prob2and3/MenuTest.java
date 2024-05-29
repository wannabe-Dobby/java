package prob2and3;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MenuTest extends JFrame {
	Container contentPane;
	MyPanel panel;
	public MenuTest() {
		super("기말시험");
		setTitle("Menu에 연결 새창 띄우기 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		createMenu();
		setSize(300,300);
		setVisible(true);
	}
	// 메뉴바와 "문제" 메뉴를 생성하고 "문제" 메뉴에 3개의 메뉴아이템을 삽입한다.
	void createMenu() {
	    	JMenuBar mb = new JMenuBar(); // 메뉴바 생성
	    	JMenuItem [] menuItem = new JMenuItem [3];
	    	String[] itemTitle = {"기존창", "새창", "새창-다이아로크박스"};
	    	JMenu textMenu = new JMenu("문제");

	    	// 3 개의 메뉴아이템을 "문제" 메뉴에 삽입한다.
	    	for(int i=0; i<menuItem.length; i++) {
			menuItem[i] = new JMenuItem(itemTitle[i]); // 메뉴아이템 생성
	 		//메뉴아이템에  Action 리스너 등록
			menuItem[i].addActionListener(new MenuActionListener()); 
			textMenu.add(menuItem[i]); // 메뉴아이템을 "문제" 메뉴에 삽입
	  	 }
	   	mb.add(textMenu); // 메뉴바에 "문제" 메뉴 삽입
	   	setJMenuBar(mb);  // 프레임에 메뉴바를 삽입한다.
	}
	// Action 리스너로서, 메뉴아이템이 선택되었을 때 처리한다.
	class MenuActionListener implements ActionListener {
	    	public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(cmd.equals("기존창")) { // "기존Frame" 메뉴아이템 선택된 경우
				panel = new MyPanel();
				contentPane.add(panel);
				revalidate();
			}
			else if(cmd.equals("새창")) { // "새창" 메뉴아이템 선택된 경우
				if (panel !=null) panel.setVisible(false); 
		    		new NewWindow("새창");
			}
			else {   // "새창-다이아로크박스" 메뉴아이템 선택된 경우
				if (panel !=null) panel.setVisible(false); 
		    		new NewWindowAndDialog("새창-다이아로크박스");
			}
	    	}
	}
	// "기존 Frame" contentPane에 Panel을 이용하여 내용 display
    	class MyPanel extends JPanel {
    		public MyPanel() {
    			setLayout(new GridLayout(2, 2, 5, 5));
    			add(new JLabel(" 과목명:"));
    			add(new JTextField("컴퓨터프로그래밍실습"));
    			add(new JLabel(" 과목유형:"));
    			add(new JTextField("이론 및 실습"));
    		}
    	}

    	public static void main(String [] args) {
    		new MenuTest();
    	}
} 