package for_test;

import javax.swing.*;
import java.awt.*;

/*
public class Game {

 public static void main(String[] args) {
     // 게임 시작 시간 측정
     long startTime = System.currentTimeMillis();
     
     // 게임 실행을 시뮬레이션 (예: 5초 동안)
     simulateGame();
     
     // 게임 종료 시간 측정
     long endTime = System.currentTimeMillis();
     long elapsedTimeMillis = endTime - startTime;
     float elapsedTimeSec = elapsedTimeMillis / 1000F;
     
     // 게임 실행 시간 출력
     System.out.println("게임 실행 시간: " + elapsedTimeSec + " 초");
 }

 // 게임 실행을 시뮬레이션하는 메서드
 private static void simulateGame() {
     try {
         System.out.println("게임이 시작됩니다...");
         Thread.sleep(5000); // 5초 동안 게임 실행 시뮬레이션
         System.out.println("게임이 종료되었습니다.");
     } catch (InterruptedException e) {
         e.printStackTrace();
     }
 }
}
*/



import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {

    public Game() {
        setLayout(new BorderLayout());
        
        // 게임 실행 시간을 콘솔에 출력하는 예시 코드
        long startTime = System.currentTimeMillis();
        simulateGame();
        long endTime = System.currentTimeMillis();
        long elapsedTimeMillis = endTime - startTime;
        float elapsedTimeSec = elapsedTimeMillis / 1000F;
        System.out.println("게임 실행 시간: " + elapsedTimeSec + " 초");
        
        // 화면에 출력할 내용
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        textArea.append("게임이 시작되었습니다.\n");
        textArea.append("게임 실행 시간: " + elapsedTimeSec + " 초\n");

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    // 게임 실행을 시뮬레이션하는 메서드
    private void simulateGame() {
        try {
            Thread.sleep(5000); // 5초 동안 게임 실행 시뮬레이션
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
