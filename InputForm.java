import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Main form where the user interacts with the quiz.
 * Shows a number and asks the user to type its English name.
 * It includes navigation controls and a visual stats panel.
 */
public class InputForm extends JFrame {

    public static final String FILE_PATH = "resources/numbers.txt"; // Path to the source file (numbers.txt) containing numbers (1-20)
    private static final int MIN = 1;
    private static final int MAX = 20;

    //GUI Components
    private JButton submitButton;
    private JButton quitButton;
    private JButton skipButton;
    private JPanel userInteractionPanel;
    private JPanel bottomPanel;
    private JTextField textField;
    private StatsBarPanel statsBarPanel;


    // Stats tracking
    public static int randomlyPikedNumber;
    public static int totalQuestion = 0;
    public static int wrongAnswer = 0;
    public static int correctAnswer = 0;
    public static int skipCounter = 0;
    public static int successRate = 0;

    // Labels for displaying stats of the game
    private JLabel totalQuestionLabel;
    private JLabel wrongAnswerLabel;
    private JLabel correctAnswerLabel;
    protected JLabel questionLabel;

    // Store all numbers and attempted ones
    List<String> allNumbers;
    private static List<Integer> attemptedQuestion = new ArrayList<>();

    public InputForm(int fixedNumber){
        allNumbers = new ArrayList<>();
        setTitle("Input Form");
        setSize(600, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); //Centers the form on the Screen

        //Theme Color setup
        Color background = new Color(30, 30, 30);    //Dark
        Color foreground = Color.WHITE;   // Fore g white visible text

        // Main panel setup
        JPanel MainPanel = new JPanel();
        questionLabel = new JLabel("");
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setVerticalAlignment(SwingConstants.NORTH);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        questionLabel.setForeground(foreground);

        //Text field and Submit button setup
        userInteractionPanel = new JPanel();
        userInteractionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        textField = new JTextField(20);
        textField.setLayout(new FlowLayout(FlowLayout.LEFT));
        textField.setBackground(new Color(60, 60, 60));
        textField.setForeground(foreground);

        submitButton = new JButton("Submit");
        submitButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        submitButton.setBackground(new Color(50, 50, 50));
        submitButton.setForeground(foreground);

        userInteractionPanel.add(textField);
        userInteractionPanel.add(submitButton);
        userInteractionPanel.setBackground(background);

        //Skip and Quit buttons
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(background);

        skipButton = new JButton("Skip");
        skipButton.setHorizontalAlignment(SwingConstants.CENTER);
        skipButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        skipButton.setBackground(new Color(50, 50, 50));
        skipButton.setForeground(foreground);

        quitButton = new JButton("Quit");
        quitButton.setHorizontalAlignment(SwingConstants.CENTER);
        quitButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
        quitButton.setBackground(new Color(50, 50, 50));
        quitButton.setForeground(foreground);

        bottomPanel.add(skipButton);
        bottomPanel.add(quitButton);

        // Stats label panel (Total Question, Correct answers, Incorrect answers)
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,30,10)); // Flow Layout for next to visualize
        totalQuestionLabel = new JLabel("Total: 1");
        totalQuestionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        totalQuestionLabel.setForeground(Color.BLUE);
        totalQuestionLabel.setForeground(Color.CYAN);

        correctAnswerLabel = new JLabel("Correct: 0");
        correctAnswerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        correctAnswerLabel.setForeground(Color.GREEN);
        correctAnswerLabel.setForeground(Color.GREEN);

        wrongAnswerLabel = new JLabel("Wrong: 0");
        wrongAnswerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        wrongAnswerLabel.setForeground(Color.RED);
        wrongAnswerLabel.setForeground(Color.RED);
        statsPanel.add(totalQuestionLabel, BorderLayout.NORTH);
        statsPanel.add(correctAnswerLabel, BorderLayout.WEST);
        statsPanel.add(wrongAnswerLabel, BorderLayout.EAST);
        statsPanel.setBackground(background);

        // Progress bar panel (Bar Chart)
        statsBarPanel = new StatsBarPanel();

        // Add everything to main panel
        MainPanel.add(questionLabel, BorderLayout.NORTH);
        MainPanel.add(userInteractionPanel, BorderLayout.CENTER);
        MainPanel.add(bottomPanel, BorderLayout.SOUTH);
        MainPanel.add(statsPanel, BorderLayout.SOUTH);

        MainPanel.setBackground(background);

        add(MainPanel, BorderLayout.CENTER); // Attach main panel
        add(statsBarPanel, BorderLayout.SOUTH); // Attach bar chart

        // Pick a random number or use fixed number
        if (fixedNumber == -1) {
            generateProblem(); // (-1) for generating, selecting new random number
        } else {
            randomlyPikedNumber = fixedNumber; // use provided number, especially for repeating the question (Incorrect!).
        }


        questionLabel.setText("Enter the name of the number: "+randomlyPikedNumber);

        // Submit button interaction logic
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean isSameQuestion = !attemptedQuestion.contains(randomlyPikedNumber);

                String submittedText = textField.getText().trim();
                String answerNumberText = generateNameNumber(randomlyPikedNumber);

                if(isSameQuestion && !submittedText.isEmpty()){
                    totalQuestion++;
                    attemptedQuestion.add(randomlyPikedNumber);
                }

                if(submittedText.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please enter the name of the number\nIt can't be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(submittedText.equalsIgnoreCase(answerNumberText)){
                    if (isSameQuestion) correctAnswer++;
                    updateStats();
                    System.out.println("✅ Correct Answer!");
                    dispose();
                    new OutputForm(true,randomlyPikedNumber);
                }else{
                    if (isSameQuestion) wrongAnswer++;
                    updateStats();
                    System.out.println("❌ Wrong Answer!");
                    dispose();
                    new OutputForm(false,randomlyPikedNumber);
                }

            }
        });

        // Skip button interaction logic (It won't count as correct or incorrect if question is skipped).
        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("⏩ Skipping Question!");
                skipCounter++;
                totalQuestion++;
                updateStats();
                generateProblem();
                questionLabel.setText("Enter the name of the number: "+randomlyPikedNumber);
            }
        });

        // Quit button interaction logic
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int choice =  JOptionPane.showConfirmDialog(null,"Thank you for playing \nWould you like to quit?","Want to Quit?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(choice == JOptionPane.YES_OPTION) {
                    finalStats(); // Exit game and display final stats
                }
            }
        });

        updateStats();

        setVisible(true);

    }

    /**
     * Displays final summary of the game stats and reset if the user wants to retry.
     * */
    public static void finalStats(){
        try{
            successRate = (correctAnswer*100)/totalQuestion;
        } catch (ArithmeticException e){
            System.out.println("Arithmetic Exception, No question is answered yet success rate is: 0");
        }


        JOptionPane.showMessageDialog(null,"\uD83E\uDD73 Congrats, Game is Over!\nYou have answered => "+totalQuestion+" questions.\n" + "✅ Correct: " + correctAnswer + "\n❌ Wrong: " + wrongAnswer +"\n⏩ Skipped: "+skipCounter + "\n📊 Total: " + totalQuestion + "\nSuccess rate: %"+successRate,
                "Game Finished", JOptionPane.INFORMATION_MESSAGE);

        int optionForNewGame = JOptionPane.showConfirmDialog(null,"Would you like to play a new game?","Want To Play Again?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

        if(optionForNewGame == JOptionPane.YES_OPTION){
            System.out.println("NEW GAME IS STARTING...");
            //Reset stats for new game.
            randomlyPikedNumber = 0;
            totalQuestion = 0;
            wrongAnswer = 0;
            correctAnswer = 0;
            skipCounter = 0;
            successRate = 0;
            attemptedQuestion.clear();

            new InputForm(-1);

        } else {
            System.out.println("EXITING THE GAME...");
            System.exit(0);
        }

    }

    /**
     * Reads the file and select a random number that hasn't been attempted yet.
     */
    public void generateProblem() {
        Random random = new Random();
        if (attemptedQuestion.size() == 20) {
            finalStats();
        }
        try{
            FileReader fileReader = new FileReader(FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null){
                int testNumber = Integer.parseInt(line.trim());
                if(!attemptedQuestion.contains(testNumber)) {
                    allNumbers.add(line);
                }
            }

            fileReader.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        int randomlyPickedNumber_Index = random.nextInt(allNumbers.size());
        randomlyPikedNumber = Integer.parseInt(allNumbers.get(randomlyPickedNumber_Index));

        System.out.println("RANDOM NUMBER: " + randomlyPikedNumber);
    }

    /**
     * Converts number to its English word equivalent.
     */
    private String generateNameNumber(int number) {
        String[] nameNumbers = {
                "one", "two", "three", "four", "five",
                "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen",
                "sixteen", "seventeen", "eighteen", "nineteen", "twenty"
        };

        if (number >= MIN && number <= MAX) {
            return nameNumbers[number - 1];
        }

        return "";
    }

    /**
     * Updates all stat labels and refreshes the bar chart panel.
     */
    private void updateStats() {
        totalQuestionLabel.setText("Total: " + totalQuestion);
        correctAnswerLabel.setText("Correct: " + correctAnswer);
        wrongAnswerLabel.setText("Wrong: " + wrongAnswer);
        statsBarPanel.repaint();
    }

    /**
     * Inner class that visually draws bars for total, correct, and wrong stats.
     */
    class StatsBarPanel extends JPanel {


        public StatsBarPanel() {
            setPreferredSize(new Dimension(600, 160));
            setBackground(new Color(30, 30, 30)); // match the theme
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int xMeasure = 100; // First Bars location(x)
            int yMeasure = 130; // First Bars Location(y)
            int unit = 5;// Increment unit value

            //Total Question Bar
            int totalBarsHeight = totalQuestion * unit;
            g.setColor(Color.CYAN);
            g.fillRect(xMeasure, yMeasure - totalBarsHeight, 40 , totalBarsHeight);
            g.drawString("Total",xMeasure,yMeasure + 15);

            //Correct Answer Bar
            int correctBarsHeight = correctAnswer * unit;
            g.setColor(Color.GREEN);
            g.fillRect(xMeasure + 80, yMeasure - correctBarsHeight, 40 , correctBarsHeight);
            g.drawString("Correct",xMeasure + 80, yMeasure + 15);

            //Wrong Answer Bar
            int wrongBarsHeight = wrongAnswer * unit;
            g.setColor(Color.RED);
            g.fillRect(xMeasure + 160, yMeasure - wrongBarsHeight, 40 , wrongBarsHeight);
            g.drawString("Wrong",xMeasure + 160, yMeasure + 15);

        }
    }

}
