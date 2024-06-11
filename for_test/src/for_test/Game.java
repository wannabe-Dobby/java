package for_test;

// 틱택토 게임

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame {
    private JButton[] buttons = new JButton[9];
    private char currentPlayer = 'X';
    private JLabel statusLabel;

    public Game() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(new ButtonClickListener());
            panel.add(buttons[i]);
        }

        add(panel, BorderLayout.CENTER);

        statusLabel = new JLabel("Player X's turn");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(statusLabel, BorderLayout.NORTH);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton buttonClicked = (JButton) e.getSource();
            if (buttonClicked.getText().equals("")) {
                buttonClicked.setText(String.valueOf(currentPlayer));
                if (checkForWin()) {
                    JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                    resetBoard();
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(null, "The game is a tie!");
                    resetBoard();
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    statusLabel.setText("Player " + currentPlayer + "'s turn");
                }
            }
        }
    }

    private boolean checkForWin() {
        int[][] winPositions = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
        };

        for (int[] winPosition : winPositions) {
            if (buttons[winPosition[0]].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[winPosition[1]].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[winPosition[2]].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    private void resetBoard() {
        for (JButton button : buttons) {
            button.setText("");
        }
        currentPlayer = 'X';
        statusLabel.setText("Player X's turn");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Game().setVisible(true);
        });
    }
}


/*

// 숫자 맞추기 게임

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Game extends JFrame {
    private int targetNumber;
    private int attempts;
    private JTextField inputField;
    private JLabel messageLabel;
    private JLabel timerLabel;
    private Timer timer;
    private long startTime;

    public Game() {
        setTitle("Number Guessing Game");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        targetNumber = new Random().nextInt(100) + 1;
        attempts = 0;
        startTime = System.currentTimeMillis();

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel instructionLabel = new JLabel("Guess a number between 1 and 100:");
        panel.add(instructionLabel);

        inputField = new JTextField(10);
        panel.add(inputField);

        JButton guessButton = new JButton("Guess");
        guessButton.addActionListener(new GuessButtonListener());
        panel.add(guessButton);

        messageLabel = new JLabel(" ");
        panel.add(messageLabel);

        add(panel, BorderLayout.CENTER);

        timerLabel = new JLabel("Time: 0.0s");
        add(timerLabel, BorderLayout.NORTH);

        timer = new Timer(100, e -> {
            long elapsed = System.currentTimeMillis() - startTime;
            timerLabel.setText("Time: " + elapsed / 1000.0 + "s");
        });
        timer.start();
    }

    private class GuessButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(inputField.getText());
                attempts++;
                if (guess < targetNumber) {
                    messageLabel.setText("Too low! Try again.");
                } else if (guess > targetNumber) {
                    messageLabel.setText("Too high! Try again.");
                } else {
                    timer.stop();
                    long elapsedTimeMillis = System.currentTimeMillis() - startTime;
                    float elapsedTimeSec = elapsedTimeMillis / 1000F;
                    JOptionPane.showMessageDialog(null, "Correct! You guessed it in " + attempts + " attempts and " + elapsedTimeSec + " seconds.");
                    resetGame();
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Please enter a valid number.");
            }
            inputField.setText("");
        }
    }

    private void resetGame() {
        targetNumber = new Random().nextInt(100) + 1;
        attempts = 0;
        startTime = System.currentTimeMillis();
        messageLabel.setText(" ");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Game().setVisible(true);
        });
    }
}

*/


/*

// MemoryMatchingGame : 짝 맞추기 게임

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class Game extends JFrame {
    private long startTime;
    private JLabel timerLabel;
    private Timer timer;
    private ArrayList<JButton> buttons;
    private ArrayList<String> icons;
    private String[] iconSet = {"A", "B", "C", "D", "E", "F", "G", "H"};
    private JButton firstButton;
    private JButton secondButton;
    private int matchedPairs;

    public Game() {
        setTitle("Memory Matching Game");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        timerLabel = new JLabel("Time: 0.0s");
        add(timerLabel, BorderLayout.NORTH);

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(4, 4));
        buttons = new ArrayList<>();
        icons = new ArrayList<>();

        for (String icon : iconSet) {
            icons.add(icon);
            icons.add(icon);
        }
        Collections.shuffle(icons);

        for (int i = 0; i < 16; i++) {
            JButton button = new JButton();
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            button.addActionListener(new ButtonClickListener());
            buttons.add(button);
            gamePanel.add(button);
        }

        add(gamePanel, BorderLayout.CENTER);

        startTime = System.currentTimeMillis();
        timer = new Timer(100, e -> {
            long elapsed = System.currentTimeMillis() - startTime;
            timerLabel.setText("Time: " + elapsed / 1000.0 + "s");
        });
        timer.start();
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            int index = buttons.indexOf(clickedButton);
            clickedButton.setText(icons.get(index));

            if (firstButton == null) {
                firstButton = clickedButton;
            } else if (secondButton == null && clickedButton != firstButton) {
                secondButton = clickedButton;

                if (icons.get(buttons.indexOf(firstButton)).equals(icons.get(buttons.indexOf(secondButton)))) {
                    matchedPairs++;
                    if (matchedPairs == 8) {
                        timer.stop();
                        long elapsedTimeMillis = System.currentTimeMillis() - startTime;
                        float elapsedTimeSec = elapsedTimeMillis / 1000F;
                        JOptionPane.showMessageDialog(null, "You matched all pairs in " + elapsedTimeSec + " seconds!");
                    }
                    firstButton = null;
                    secondButton = null;
                } else {
                    Timer delayTimer = new Timer(500, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            firstButton.setText("");
                            secondButton.setText("");
                            firstButton = null;
                            secondButton = null;
                        }
                    });
                    delayTimer.setRepeats(false);
                    delayTimer.start();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Game().setVisible(true);
        });
    }
}

*/


/*

// SpotTheDifferenceGame : 두 개의 유사한 이미지 사이에서 차이점을 찾아내는 게임

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Game extends JFrame {
    private long startTime;
    private ArrayList<Point> differences;
    private int foundDifferences;
    private JLabel timerLabel;
    private Timer timer;

    public Game() {
        setTitle("Spot the Difference Game");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        differences = new ArrayList<>();
        differences.add(new Point(100, 150));
        differences.add(new Point(200, 250));
        // 더 많은 차이점 추가

        foundDifferences = 0;
        timerLabel = new JLabel("Time: 0.0s");
        add(timerLabel, BorderLayout.NORTH);

        JPanel gamePanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // 이미지 로드 및 그리기 (여기서는 예시로 사각형을 그려서 차이점을 표시)
                g.setColor(Color.RED);
                for (Point p : differences) {
                    g.fillRect(p.x, p.y, 20, 20);
                }
            }
        };
        gamePanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                for (Point p : differences) {
                    if (p.distance(e.getPoint()) < 20) {
                        foundDifferences++;
                        if (foundDifferences == differences.size()) {
                            timer.stop();
                            JOptionPane.showMessageDialog(null, "Congratulations! You found all differences in " + (System.currentTimeMillis() - startTime) / 1000.0 + " seconds.");
                        }
                        break;
                    }
                }
            }
        });

        add(gamePanel, BorderLayout.CENTER);

        startTime = System.currentTimeMillis();
        timer = new Timer(100, e -> {
            long elapsed = System.currentTimeMillis() - startTime;
            timerLabel.setText("Time: " + elapsed / 1000.0 + "s");
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Game().setVisible(true);
        });
    }
}


*/


/*


// 시간 측정

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

*/
