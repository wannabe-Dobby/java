package for_test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private JTextField tf1, tf2;
    private String operator;
    private double result;

    public Calculator() {
        setTitle("계산기 프레임");
        setSize(400, 400);
        // setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        Container c = getContentPane();
        JPanel pn = new JPanel();
        JPanel ps = new JPanel();
        JPanel pc = new JPanel();
        
        pn.setBackground(Color.LIGHT_GRAY);
        pc.setLayout(new GridLayout(4,4));
        ps.setBackground(Color.YELLOW);

        c.add(pc);
        c.add(pn, BorderLayout.NORTH);
        c.add(ps, BorderLayout.SOUTH);
        
        JLabel l1 = new JLabel("수식입력");
        tf1 = new JTextField(10);
        pn.add(l1);
        pn.add(tf1);
        
        JLabel l2 = new JLabel("계산 결과");
        tf2 = new JTextField(10);
        tf2.setEditable(false);  // 결과 입력창은 수정 불가
        ps.add(l2);
        ps.add(tf2);
        
        String[] str = {"CE", "계산", "+", "-", "x", "/"};
        for(int i = 0; i < 16; i++) {
            JButton b = new JButton();
            pc.add(b);
            if(i < 10) {
                b.setText(i+"");
            } else { 
                b.setText(str[i - 10]);
            }
            if(i > 11) {
                b.setBackground(Color.CYAN);
                b.setOpaque(true);
            }
            b.addActionListener(new ButtonClickListener());
        }
        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd.equals("CE")) {
                tf1.setText("");
                tf2.setText("");
                operator = null;
                result = 0;
            } else if (cmd.equals("계산")) {
                performCalculation();
            } else if (cmd.equals("+") || cmd.equals("-") || cmd.equals("x") || cmd.equals("/")) {
                operator = cmd;
                result = Double.parseDouble(tf1.getText());
                tf1.setText("");
            } else {
                tf1.setText(tf1.getText() + cmd);
            }
        }

        private void performCalculation() {
            double secondOperand = Double.parseDouble(tf1.getText());
            switch (operator) {
                case "+":
                    result += secondOperand;
                    break;
                case "-":
                    result -= secondOperand;
                    break;
                case "x":
                    result *= secondOperand;
                    break;
                case "/":
                    result /= secondOperand;
                    break;
            }
            tf2.setText(String.valueOf(result));
            tf1.setText("");
            operator = null;
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
