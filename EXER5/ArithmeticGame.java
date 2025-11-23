import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

enum GameLevel {
    LEVEL1(1, 100),
    LEVEL2(101, 500),
    LEVEL3(501, 1000);

    private final int min, max;
    GameLevel(int min, int max) { this.min = min; this.max = max; }
    public int getMin() { return min; }
    public int getMax() { return max; }
    public String getDisplay() { return min + "-" + max; }
}

class QuestionGenerator {
    private final Random random = new Random();
    private int num1, num2, correctAnswer;
    private String operator = "+";

    public void generateQuestion(String operator, GameLevel level) {
        this.operator = operator;
        int min = level.getMin();
        int max = level.getMax();
        switch (operator) {
            case "+": num1 = rand(min, max); num2 = rand(min, max); correctAnswer = num1 + num2; break;
            case "-": num1 = rand(min, max); num2 = rand(min, max); if(num2 > num1){int t=num1; num1=num2; num2=t;} correctAnswer = num1 - num2; break;
            case "*": num1 = rand(min, max); num2 = rand(min, max); correctAnswer = num1 * num2; break;
            case "/": num2 = rand(1, max); int q = rand(1, max / num2); num1 = num2 * q; correctAnswer = num1 / num2; break;
            case "%": num2 = rand(1, max); num1 = rand(min, max); correctAnswer = num1 % num2; break;
        }
    }

    private int rand(int min, int max){ return random.nextInt((max - min) + 1) + min; }
    public int getNum1() { return num1; }
    public int getNum2() { return num2; }
    public String getOperator() { return operator; }
    public int getCorrectAnswer() { return correctAnswer; }
}

public class ArithmeticGame extends JFrame implements ActionListener {
    private static final Color BG = new Color(245, 248, 252);
    private static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 28);
    private static final Font BODY_FONT = new Font("SansSerif", Font.PLAIN, 20);

    private final QuestionGenerator generator = new QuestionGenerator();
    private String selectedOperation = "+";
    private GameLevel selectedLevel = GameLevel.LEVEL1;

    private final JTextField answerField = new JTextField(10);
    private final JButton submitButton = new JButton("SUBMIT");
    private final JButton nextButton = new JButton("CONTINUE");
    private final JButton exitButton = new JButton("EXIT");

    private final JLabel correctScoreLabel = new JLabel("0", SwingConstants.CENTER);
    private final JLabel incorrectScoreLabel = new JLabel("0", SwingConstants.CENTER);

    private final JLabel num1Label = new JLabel("0", SwingConstants.CENTER);
    private final JLabel operatorLabel = new JLabel("+", SwingConstants.CENTER);
    private final JLabel num2Label = new JLabel("0", SwingConstants.CENTER);

    private final JLabel feedbackLabel = new JLabel(" ", SwingConstants.CENTER);

    private final ButtonGroup operationGroup = new ButtonGroup();
    private final ButtonGroup levelGroup = new ButtonGroup();
    private int correctCount = 0, incorrectCount = 0;

    public ArithmeticGame() {
        setTitle("Arithmetic Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(BG);
        setLayout(new BorderLayout(10, 10));

        add(createMainPanel(), BorderLayout.CENTER);

        submitButton.addActionListener(this);
        nextButton.addActionListener(this);
        exitButton.addActionListener(this);

        styleButton(submitButton, new Color(53, 132, 228));
        styleButton(nextButton, new Color(34, 197, 94));
        styleButton(exitButton, new Color(239, 68, 68));

        nextButton.setEnabled(true); // always visible
        submitButton.setEnabled(true);

        generateNewQuestion();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Practice Your Arithmetic!", SwingConstants.CENTER);
        title.setFont(TITLE_FONT);
        title.setForeground(new Color(30, 41, 59));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // QUESTION ROW
        JPanel questionRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        questionRow.setBackground(BG);
        Font bigFont = new Font("Monospaced", Font.BOLD, 48);
        Dimension labelSize = new Dimension(140, 90);

        setupLabel(num1Label, bigFont, labelSize);
        setupLabel(operatorLabel, bigFont, new Dimension(100, 90));
        setupLabel(num2Label, bigFont, labelSize);

        questionRow.add(num1Label);
        questionRow.add(operatorLabel);
        questionRow.add(num2Label);
        mainPanel.add(questionRow);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // ANSWER ROW
        JPanel answerRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        answerRow.setBackground(BG);
        answerField.setFont(bigFont);
        answerField.setHorizontalAlignment(SwingConstants.CENTER);
        answerRow.add(answerField);
        answerRow.add(submitButton);
        answerRow.add(nextButton);
        answerRow.add(exitButton);
        mainPanel.add(answerRow);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // FEEDBACK ROW
        feedbackLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        feedbackLabel.setForeground(new Color(80, 80, 80));
        mainPanel.add(feedbackLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // SCORE ROW
        JPanel scoreRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        scoreRow.setBackground(BG);
        JLabel correctLabel = new JLabel("Correct:", SwingConstants.CENTER);
        correctLabel.setFont(BODY_FONT);
        scoreRow.add(correctLabel);
        scoreRow.add(correctScoreLabel);
        JLabel incorrectLabel = new JLabel("Incorrect:", SwingConstants.CENTER);
        incorrectLabel.setFont(BODY_FONT);
        scoreRow.add(incorrectLabel);
        scoreRow.add(incorrectScoreLabel);
        mainPanel.add(scoreRow);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // OPERATIONS & LEVELS
        JPanel selectionRow = new JPanel(new GridLayout(1, 2, 20, 0));
        selectionRow.setBackground(BG);
        selectionRow.add(createOperationPanel());
        selectionRow.add(createLevelPanel());
        mainPanel.add(selectionRow);

        return mainPanel;
    }

    private void setupLabel(JLabel label, Font font, Dimension size) {
        label.setFont(font);
        label.setPreferredSize(size);
        label.setOpaque(true);
        label.setBackground(new Color(220, 220, 255));
        label.setForeground(new Color(40, 40, 40));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 200), 2));
    }

    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(BODY_FONT);
    }

    private JPanel createOperationPanel() {
        JPanel opPanel = new JPanel();
        opPanel.setBackground(new Color(230, 245, 255));
        opPanel.setBorder(BorderFactory.createTitledBorder("Select Operation"));
        opPanel.setLayout(new GridLayout(5, 1, 5, 5));

        String[][] ops = { {"+", "Addition"}, {"-", "Subtraction"}, {"*", "Multiplication"}, {"/", "Division"}, {"%", "Modulo"} };
        for (String[] op : ops) {
            JPanel box = new JPanel(new FlowLayout(FlowLayout.LEFT));
            box.setBackground(new Color(200, 230, 255));
            JRadioButton rb = new JRadioButton(op[0]);
            rb.setBackground(new Color(200, 230, 255));
            rb.setFont(new Font("SansSerif", Font.BOLD, 20));
            rb.addActionListener(e -> selectedOperation = op[0]);
            operationGroup.add(rb);
            JLabel nameLabel = new JLabel(op[1]);
            nameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
            box.add(rb);
            box.add(nameLabel);
            opPanel.add(box);
            if(op[0].equals("+")) rb.setSelected(true);
        }
        return opPanel;
    }

    private JPanel createLevelPanel() {
        JPanel lvlPanel = new JPanel();
        lvlPanel.setBackground(new Color(255, 245, 230));
        lvlPanel.setBorder(BorderFactory.createTitledBorder("Select Level"));
        lvlPanel.setLayout(new GridLayout(3, 1, 5, 5));

        for (GameLevel lvl : GameLevel.values()) {
            JPanel box = new JPanel(new FlowLayout(FlowLayout.LEFT));
            box.setBackground(new Color(255, 230, 200));
            JRadioButton rb = new JRadioButton(lvl.name() + " (" + lvl.getDisplay() + ")");
            rb.setBackground(new Color(255, 230, 200));
            rb.setFont(new Font("SansSerif", Font.BOLD, 20));
            rb.addActionListener(e -> selectedLevel = lvl);
            levelGroup.add(rb);
            box.add(rb);
            lvlPanel.add(box);
            if (lvl == GameLevel.LEVEL1) rb.setSelected(true);
        }
        return lvlPanel;
    }

    private void generateNewQuestion() {
        generator.generateQuestion(selectedOperation, selectedLevel);
        num1Label.setText(String.valueOf(generator.getNum1()));
        num2Label.setText(String.valueOf(generator.getNum2()));
        operatorLabel.setText(generator.getOperator());
        answerField.setText("");
        feedbackLabel.setText(" ");
        submitButton.setEnabled(true);
        nextButton.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            try {
                int answer = Integer.parseInt(answerField.getText().trim());
                if (answer == generator.getCorrectAnswer()) {
                    feedbackLabel.setText("Correct!");
                    correctCount++;
                    correctScoreLabel.setText(String.valueOf(correctCount));
                } else {
                    feedbackLabel.setText("Incorrect! Answer: " + generator.getCorrectAnswer());
                    incorrectCount++;
                    incorrectScoreLabel.setText(String.valueOf(incorrectCount));
                }
                submitButton.setEnabled(false);
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Please enter a valid number!");
            }
        } else if (e.getSource() == nextButton) {
            generateNewQuestion();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ArithmeticGame::new);
    }
}