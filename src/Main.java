import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Main {
    public static void main(String[] args) {
        Grid();
    }

    static void Game() {
        System.out.println("TEST");
    }

    static void Grid() {

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

        // Add in a new text field.
        final JLabel welcomeText = new JLabel("Welcome to Verbal Memory!");
        final int welcomeTextWidth = 200;
        final int welcomeTextHeight = 20;
        final int offset = startButtonHeight + 20;
        welcomeText.setBounds(centerX - welcomeTextWidth / 2,centerY - welcomeTextHeight / 2 - offset,welcomeTextWidth,welcomeTextHeight);

        // Set the event of the button
        start.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                frame.getContentPane().removeAll();
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

}