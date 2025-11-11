import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class ArithmeticGameGUI extends JFrame {

    private JLabel questionLabel, scoreLabel, timerLabel, streakLabel, opModeLabel;
    private JTextField answerField;
    private JButton submitButton, startButton, switchOpButton;
    private JComboBox<String> difficultyBox, timeBox;

    private int score = 0;
    private int streak = 0;
    private int timeLeft;
    private int baseTime = 10;

    private Timer timer;
    private int a, b;
    private char op;
    private double correctAnswer;

    // For operation switching
    private String[] opModes = {"All", "Addition", "Subtraction", "Multiplication", "Division"};
    private int opModeIndex = 0; // Start with "All"

    public ArithmeticGameGUI() {

        setTitle("Advanced Arithmetic Game");
        setSize(550, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.setLayout(new GridLayout(3, 3));

        scoreLabel = new JLabel("Score: 0");
        timerLabel = new JLabel("Time: 10");
        streakLabel = new JLabel("Streak: 0");
        opModeLabel = new JLabel("Mode: All");

        difficultyBox = new JComboBox<>(new String[]{"Easy", "Normal", "Hard"});
        timeBox = new JComboBox<>(new String[]{"5 sec", "10 sec", "15 sec", "20 sec"});

        top.add(scoreLabel);
        top.add(timerLabel);
        top.add(streakLabel);
        top.add(new JLabel("Difficulty:"));
        top.add(difficultyBox);
        top.add(timeBox);
        top.add(new JLabel(""));
        top.add(opModeLabel);
        top.add(new JLabel("")); 

        JPanel center = new JPanel();
        questionLabel = new JLabel("Press START to begin!", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 22));
        center.add(questionLabel);

        JPanel bottom = new JPanel();
        answerField = new JTextField(10);
        submitButton = new JButton("Submit");
        startButton = new JButton("Start");
        switchOpButton = new JButton("Change Operation");

        bottom.add(answerField);
        bottom.add(submitButton);
        bottom.add(startButton);
        bottom.add(switchOpButton);

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> checkAnswer());
        startButton.addActionListener(e -> startGame());
        switchOpButton.addActionListener(e -> switchOperationMode());

        timer = new Timer(1000, e -> countdown());
    }

    private void startGame() {
        score = 0;
        streak = 0;
        scoreLabel.setText("Score: 0");
        streakLabel.setText("Streak: 0");

        setBaseTime();
        generateQuestion();
        timer.start();
    }

    private void setBaseTime() {
        String selected = (String) timeBox.getSelectedItem();

        switch (selected) {
            case "5 sec": baseTime = 5; break;
            case "10 sec": baseTime = 10; break;
            case "15 sec": baseTime = 15; break;
            case "20 sec": baseTime = 20; break;
        }
    }

    private void generateQuestion() {
        Random r = new Random();

        int difficulty = difficultyBox.getSelectedIndex();
        int max = difficulty == 0 ? 10 : (difficulty == 1 ? 50 : 100);

        a = r.nextInt(max) + 1;
        b = r.nextInt(max) + 1;

        char[] ops = {'+', '-', '*', '/'};

        // Apply operation mode
        if (opModes[opModeIndex].equals("All")) {
            op = ops[r.nextInt(ops.length)];
        } else if (opModes[opModeIndex].equals("Addition")) {
            op = '+';
        } else if (opModes[opModeIndex].equals("Subtraction")) {
            op = '-';
        } else if (opModes[opModeIndex].equals("Multiplication")) {
            op = '*';
        } else {
            op = '/';
        }

        if (op == '/') {
            b = r.nextInt(max - 1) + 1; // Avoid dividing by zero
            correctAnswer = Math.round(((double) a / b) * 100) / 100.0;
        } else if (op == '+') {
            correctAnswer = a + b;
        } else if (op == '-') {
            correctAnswer = a - b;
        } else {
            correctAnswer = a * b;
        }

        questionLabel.setText(a + " " + op + " " + b + " = ?");
        timeLeft = baseTime;
        timerLabel.setText("Time: " + timeLeft);
    }

    private void countdown() {
        timeLeft--;
        timerLabel.setText("Time: " + timeLeft);

        if (timeLeft == 0) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Time's up!");
            streak = 0;
            streakLabel.setText("Streak: 0");
            generateQuestion();
            timer.start();
        }
    }

    private void checkAnswer() {
        try {
            double user = Double.parseDouble(answerField.getText());
            answerField.setText("");

            if (Math.abs(user - correctAnswer) < 0.01) {
                int difficulty = difficultyBox.getSelectedIndex() + 1;
                int base = 10 * difficulty;
                streak++;
                int combo = streak >= 3 ? 5 : 0;
                int total = base + combo;

                score += total;

                JOptionPane.showMessageDialog(this, "✅ Correct! +" + total + " points");
                scoreLabel.setText("Score: " + score);
                streakLabel.setText("Streak: " + streak);
            } else {
                JOptionPane.showMessageDialog(this, "❌ Wrong! Correct answer: " + correctAnswer);
                streak = 0;
                streakLabel.setText("Streak: 0");
            }

            generateQuestion();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!");
        }
    }

    private void switchOperationMode() {
        opModeIndex = (opModeIndex + 1) % opModes.length;
        opModeLabel.setText("Mode: " + opModes[opModeIndex]);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ArithmeticGameGUI().setVisible(true));
    }
}
