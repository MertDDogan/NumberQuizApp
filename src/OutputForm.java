import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * OutputForm class - displays feedback to the user based on whether their answer was correct or incorrect.
 * Includes a result message, visual icon (tick or cross), and options to retry or quit the game.
 */
public class OutputForm extends JFrame {
    private JButton retryButton;
    private JButton quitButton;
    private JLabel resultLabel;
    private JLabel imageLabel;
    private ImageIcon imageIcon;
    private int pickedNumber;

    String tickImagePath = "resources/tick.png";
    String crossImagePath = "resources/cross.png";

    // Builds and displays the result form based on the user's input.
    public OutputForm(boolean isCorrect, int pickedNumber) {
        this.pickedNumber = pickedNumber;
        setTitle("Output Form");
        setSize(400, 300);
        setLocationRelativeTo(null); //Centers the form on the Screen
        setLayout(new BorderLayout());


        if (isCorrect) { // If the answer was correct

            resultLabel = new JLabel("✅ Correct!", SwingConstants.CENTER);
            resultLabel.setFont(new Font("arial", Font.BOLD, 20));
            add(resultLabel, BorderLayout.NORTH);

            imageIcon = new ImageIcon(tickImagePath); // Correct attempt image (tick)


            imageLabel = new JLabel(imageIcon);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(imageLabel, BorderLayout.CENTER);
        }

        if (!isCorrect) { // If the answer is incorrect
            resultLabel = new JLabel("❌ Wrong Answer!",SwingConstants.CENTER);
            resultLabel.setFont(new Font("arial", Font.BOLD, 20));
            add(resultLabel, BorderLayout.NORTH);

            imageIcon = new ImageIcon(crossImagePath); // Incorrect attempt image (cross)

            imageLabel = new JLabel(imageIcon);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(imageLabel, BorderLayout.CENTER);
        }

            // Bottom panel with Retry and Quit buttons
            JPanel buttonPanel = new JPanel(new FlowLayout());
            retryButton = new JButton("Retry");
            quitButton = new JButton("Quit");
            retryButton.setLayout(new FlowLayout(FlowLayout.LEFT));
            quitButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.add(retryButton);
            buttonPanel.add(quitButton);

            add(buttonPanel, BorderLayout.SOUTH);

            // Retry logic for correct answer → new question
            if(isCorrect) {
            retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Retry Button is Clicked, Generating new Question");
                dispose();
                new InputForm(-1); // Asks a new question which has not been attempted yet.
                    }
                });
            }

            // Retry logic for an incorrect answer attempt → same number again (InputForm → fixed number)
            if(!isCorrect) {
                retryButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Retry Button is Clicked, Answer it correctly");
                        dispose();
                        new InputForm(pickedNumber); // If answer is wrong keeps continue to ask user the wrong guessed number
                    }
                });
            }

            // Quit logic -> quits game and show final stats of the user
            quitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Quit is selected!");
                    dispose();
                    JOptionPane.showMessageDialog(null,"Thank you for playing!","Quit",JOptionPane.INFORMATION_MESSAGE);
                    InputForm.finalStats(); // Calls final stats from InputForm
                }
            });

        setVisible(true);
    }
}
