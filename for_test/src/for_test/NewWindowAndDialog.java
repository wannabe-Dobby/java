package for_test;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

// "새창-다이아로크박스"
public class NewWindowAndDialog extends JFrame{
    JLabel label = new JLabel("Open DialogBox");
    JFrame superThis;
    NewDialog newDialog; // 다이얼로그의 레퍼런스
    public NewWindowAndDialog(String title) {
    	super(title);
    	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLayout(new FlowLayout());
    	//setLayout(null);
    	superThis = this;

    	Container c = getContentPane();
    	c.add(label);
    	label.addMouseListener(new MouseAdapter() {
    		public void mousePressed(MouseEvent e) {
    			newDialog = new NewDialog(superThis, title);
    		}
    	});

    	setSize(300,200);
    	setVisible(true);
    }
    public static void main(String [] args) {
		new NewWindowAndDialog("새창-다이아로크박스");
	}
}
// "새창-다이아로크박스"
class NewDialog extends JDialog {
    JTextField tf = new JTextField();
    JButton okButton = new JButton("OK");

    public NewDialog(JFrame frame, String title) {
    	super(frame, title, true);
    	setLayout(new FlowLayout());
    	tf.setText(title);
    	add(tf);
    	add(okButton);
    	setSize(300, 100);

    	// 다이얼로그의 OK 버튼에 Action 리스너 달기
    	// OK 버튼이 선택되면 다이얼로그가 화면에서 사라지게 한다.
    	okButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			setVisible(false); // 다이얼로그를 보이지 않게 한다.
    		}
    	});

    	setVisible(true);
    }
}
