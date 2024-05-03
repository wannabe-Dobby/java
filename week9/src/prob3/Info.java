package prob3;

import javax.swing.*;
import java.awt.*;

public class Info extends JFrame {
    private JLabel nameLabel, idLabel, departmentLabel, futureLabel, summerPlanLabel, nextSemesterLabel, mottoLabel, mentorLabel;
    private JTextField nameField, idField, departmentField, futureField;
    private JTextArea summerPlanArea, nextSemesterArea, mottoArea, mentorArea;

    public Info() {
        setTitle("INFOMATION ABOUT ME");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2, 5, 5));

        nameLabel = new JLabel("성명:");
        add(nameLabel);
        nameField = new JTextField();
        add(nameField);

        idLabel = new JLabel("학번:");
        add(idLabel);
        idField = new JTextField();
        add(idField);

        departmentLabel = new JLabel("학과:");
        add(departmentLabel);
        departmentField = new JTextField();
        add(departmentField);

        futureLabel = new JLabel("장래희망:");
        add(futureLabel);
        futureField = new JTextField();
        add(futureField);

        summerPlanLabel = new JLabel("여름방학 계획:");
        add(summerPlanLabel);
        summerPlanArea = new JTextArea();
        add(new JScrollPane(summerPlanArea));

        nextSemesterLabel = new JLabel("다음학기 계획:");
        add(nextSemesterLabel);
        nextSemesterArea = new JTextArea();
        add(new JScrollPane(nextSemesterArea));

        mottoLabel = new JLabel("좌우명:");
        add(mottoLabel);
        mottoArea = new JTextArea();
        add(new JScrollPane(mottoArea));

        mentorLabel = new JLabel("내 인생의 멘토:");
        add(mentorLabel);
        mentorArea = new JTextArea();
        add(new JScrollPane(mentorArea));

        setVisible(true);
    }

    public static void main(String[] args) {
    	new Info();
    }
}