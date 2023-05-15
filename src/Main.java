import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        Home();
    }

    static void Home() {

        // Make a new frame
        final JFrame frame = new JFrame();
        frame.setSize(400,400);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) screenSize.getWidth() / 2;
        int centerY = (int) screenSize.getHeight() / 2;

        // Set the title and default operations of the frame
        frame.setTitle("Verbal Memory");
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make the panel for the home page
        JPanel homePane = new JPanel();
        // Set the background color of the home page.
        homePane.setBackground(Color.gray);
        // Make a container to contain the home pages panes.
        Container home = frame.getContentPane();
        // Have the home page have a 1x1 pane.
        //home.setLayout(new GridLayout(1, 1));
        home.setLayout(new BorderLayout());
        // Add the homePane to the home container.
        home.add(homePane);

        // Create a new button.
        final JButton start = new JButton(new ImageIcon("src/startButton.png"));
        final int startButtonWidth = 150;
        final int startButtonHeight = 30;
        start.setBounds(centerX - startButtonWidth / 2,centerY - startButtonHeight / 2,startButtonWidth,startButtonHeight);
        start.setHorizontalAlignment(JButton.CENTER);
        start.setVerticalAlignment(JButton.CENTER);

        // Add in a new text field.
        final JLabel welcomeText = new JLabel("Welcome to Verbal Memory!");
        final int welcomeTextWidth = 200;
        final int welcomeTextHeight = 20;
        final int offset = startButtonHeight + 20;
        welcomeText.setBounds(centerX - welcomeTextWidth / 2,centerY - welcomeTextHeight / 2 - offset,welcomeTextWidth,welcomeTextHeight);
        welcomeText.setHorizontalAlignment(JLabel.CENTER);
        welcomeText.setVerticalAlignment(JLabel.CENTER);

        // Set the event of the button
        start.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                // Close the current frame.
                frame.dispose();
                Game();
            }
        });

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int width = frame.getWidth();
                int height = frame.getHeight();
                int buttonWidth = startButtonWidth;
                int buttonHeight = startButtonHeight;
                int textWidth = welcomeTextWidth;
                int textHeight = welcomeTextHeight;
                int textX = (width - textWidth) / 2;
                int textY = (height - textHeight) / 2 - offset;
                int x = (width - buttonWidth) / 2;
                int y = (height - buttonHeight) / 2;
                start.setBounds(x, y, buttonWidth, buttonHeight);
                welcomeText.setBounds(textX, textY, welcomeTextWidth, welcomeTextHeight);
            }
        });

        home.add(start, BorderLayout.CENTER);
        home.add(welcomeText, BorderLayout.CENTER);
        frame.setLayout(null);

        // Set the frame to visible.
        frame.setVisible(true);

    }

    static void Game() {
        // Put the game in a new frame.
        final JFrame frame = new JFrame();
        frame.setSize(400,400);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) screenSize.getWidth() / 2;
        int centerY = (int) screenSize.getHeight() / 2;

        // Set the title and default operations of the frame
        frame.setTitle("Verbal Memory");
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the array of words.
        final String[] words = {"apple", "banana", "cat", "dog", "fish", "tree", "house", "car", "ball", "kite", "sun", "moon", "star", "cloud", "rainbow"};

        // Create two array lists, one for new words, and one for words that have been seen before. Add all the words from the array words, to the new array list to start.
        ArrayList<String> newWords = new ArrayList<String>(Arrays.asList(words));
        ArrayList<String> seenWords = new ArrayList<String>();

        // Display one word in the center of the screen.
        final JLabel word = new JLabel(words[(int)(Math.random() * words.length)]);
        final int wordWidth = 200;
        final int wordHeight = 100;
        word.setBounds(centerX - wordWidth / 2,centerY - wordHeight / 2,wordWidth,wordHeight);

        // Display the word in a fancier manner.
        word.setFont(new Font("Serif", Font.BOLD, 50));
        word.setForeground(Color.red);
        word.setHorizontalAlignment(JLabel.CENTER);
        word.setVerticalAlignment(JLabel.CENTER);

        // Create a score tracker
        final JLabel score = new JLabel("Score: 0");
        final int scoreWidth = 100;
        final int scoreHeight = 20;
        // Put the score at the top middle of the screen
        score.setBounds(centerX - scoreWidth / 2,centerY - scoreHeight / 2 - 100,scoreWidth,scoreHeight);
        score.setHorizontalAlignment(JLabel.CENTER);
        score.setLocation(centerX - scoreWidth / 2 - 50, centerY - scoreHeight / 2 - 100);

        // Create a lives tracker
        final JLabel lives = new JLabel("Lives: 3");
        final int livesWidth = 100;
        final int livesHeight = 20;
        // Put the lives next to the score.
        lives.setBounds(centerX - livesWidth / 2, centerY - livesHeight / 2 - 100, livesWidth, livesHeight);
        lives.setHorizontalAlignment(JLabel.CENTER);
        lives.setLocation(centerX - livesWidth / 2 + 50, centerY - livesHeight / 2 - 100);

        // Create the "new" and "seen" buttons to go under the word.
        final JButton newButton = new JButton("New");
        final JButton seenButton = new JButton("Seen");
        final int buttonWidth = 100;
        final int buttonHeight = 30;
        final int offset = wordHeight + 20;
        newButton.setBounds(centerX - buttonWidth / 2,centerY - buttonHeight / 2 + offset,buttonWidth,buttonHeight);
        seenButton.setBounds(centerX - buttonWidth / 2,centerY - buttonHeight / 2 + offset + buttonHeight + 10,buttonWidth,buttonHeight);

        // Set the event of the new button.
        newButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){

                String currentWord = word.getText();

                if (newWords.contains(currentWord)) {

                    // Increment the score by 1.
                    score.setText("Score: " + (Integer.parseInt(score.getText().substring(7)) + 1));

                    // Remove the word from new words and add it to seen words.
                    newWords.remove(currentWord);
                    seenWords.add(currentWord);

                }

                else {

                    // Take away a life.
                    lives.setText("Lives: " + (Integer.parseInt(lives.getText().substring(7)) - 1));

                }

                if (Integer.parseInt(lives.getText().substring(7)) == 0) {
                    frame.dispose();
                    gameOver((Integer.parseInt(score.getText().substring(7))));
                }

                word.setText(words[(int)(Math.random() * words.length)]);
            }
        });

        // Set the event of the seen button.
        seenButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){

                String currentWord = word.getText();

                if (seenWords.contains(currentWord)) {

                    // Increment the score by 1.
                    score.setText("Score: " + (Integer.parseInt(score.getText().substring(7)) + 1));

                }

                else {

                    // Take away a life.
                    lives.setText("Lives: " + (Integer.parseInt(lives.getText().substring(7)) - 1));

                }

                if (Integer.parseInt(lives.getText().substring(7)) == 0) {
                    frame.dispose();
                    gameOver((Integer.parseInt(score.getText().substring(7))));
                }

                word.setText(words[(int)(Math.random() * words.length)]);
            }
        });

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {

                int centerX = (int) frame.getWidth() / 2;
                int centerY = (int) frame.getHeight() / 2;

                word.setBounds(centerX - wordWidth / 2,centerY - wordHeight / 2,wordWidth,wordHeight);

                score.setBounds(centerX - scoreWidth / 2,centerY - scoreHeight / 2 - 100,scoreWidth,scoreHeight);
                score.setHorizontalAlignment(JLabel.CENTER);
                score.setLocation(centerX - scoreWidth / 2 - 50, centerY - scoreHeight / 2 - 100);

                lives.setBounds(centerX - livesWidth / 2, centerY - livesHeight / 2 - 100, livesWidth, livesHeight);
                lives.setHorizontalAlignment(JLabel.CENTER);
                lives.setLocation(centerX - livesWidth / 2 + 50, centerY - livesHeight / 2 - 100);

                newButton.setBounds(centerX - buttonWidth / 2,centerY - buttonHeight / 2 + offset,buttonWidth,buttonHeight);
                seenButton.setBounds(centerX - buttonWidth / 2,centerY - buttonHeight / 2 + offset + buttonHeight + 10,buttonWidth,buttonHeight);
            }
        });

        // Add the word and buttons to the frame.
        frame.add(word);
        frame.add(score);
        frame.add(lives);
        frame.add(newButton);
        frame.add(seenButton);
        frame.setLayout(null);

        // Set the frame to visible.
        frame.setVisible(true);
    }

    static void gameOver(int score) {
        // Put the game in a new frame.
        final JFrame frame = new JFrame();
        frame.setSize(400,400);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) screenSize.getWidth() / 2;
        int centerY = (int) screenSize.getHeight() / 2;

        // Set the title and default operations of the frame
        frame.setTitle("Game Over");
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display one word in the center of the screen.
        final JLabel word = new JLabel("Game Over!");
        final int wordWidth = 500;
        final int wordHeight = 200;
        word.setBounds(centerX - wordWidth / 2,centerY - wordHeight / 2,wordWidth,wordHeight);

        // Display the word in a fancier manner.
        word.setFont(new Font("Serif", Font.BOLD, 50));
        word.setForeground(Color.red);
        word.setHorizontalAlignment(JLabel.CENTER);
        word.setVerticalAlignment(JLabel.CENTER);

        // Create a score tracker
        final JLabel finalScore = new JLabel("Your Final Score Was: " + score);
        final int scoreWidth = 1000;
        final int scoreHeight = 200;
        // Put the score at the top middle of the screen
        finalScore.setBounds(centerX - scoreWidth / 2,centerY - scoreHeight / 2 - 100,scoreWidth,scoreHeight);
        finalScore.setHorizontalAlignment(JLabel.CENTER);

        // Display the word in a fancier manner.
        finalScore.setFont(new Font("Serif", Font.BOLD, 50));
        finalScore.setForeground(Color.darkGray);

        // Create the "new" and "seen" buttons to go under the word.
        final JButton homeButton = new JButton("Home");
        final JButton restartButton = new JButton("Restart");
        final int buttonWidth = 100;
        final int buttonHeight = 30;
        final int offset = wordHeight + 20;
        homeButton.setBounds(centerX - buttonWidth / 2,centerY - buttonHeight / 2 + offset,buttonWidth,buttonHeight);
        restartButton.setBounds(centerX - buttonWidth / 2,centerY - buttonHeight / 2 + offset + buttonHeight + 10,buttonWidth,buttonHeight);

        // Set the event of the new button.
        homeButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                frame.dispose();
                Home();
            }
        });

        // Set the event of the seen button.
        restartButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                frame.dispose();
                Game();

            }
        });

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {

                int centerX = (int) frame.getWidth() / 2;
                int centerY = (int) frame.getHeight() / 2;

                word.setBounds(centerX - wordWidth / 2,centerY - wordHeight / 2,wordWidth,wordHeight);

                finalScore.setBounds(centerX - scoreWidth / 2,centerY - scoreHeight / 2 - 100,scoreWidth,scoreHeight);
                finalScore.setHorizontalAlignment(JLabel.CENTER);

                homeButton.setBounds(centerX - buttonWidth / 2,centerY - buttonHeight / 2 + offset,buttonWidth,buttonHeight);
                restartButton.setBounds(centerX - buttonWidth / 2,centerY - buttonHeight / 2 + offset + buttonHeight + 10,buttonWidth,buttonHeight);
            }
        });

        // Add the word and buttons to the frame.
        frame.add(word);
        frame.add(finalScore);
        frame.add(homeButton);
        frame.add(restartButton);
        frame.setLayout(null);

        // Set the frame to visible.
        frame.setVisible(true);
    }

}