import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class Main {

    private static Timer timer;
    private static int timeLimit = 100;
    private static double elapsedTime = 0;
    private static JProgressBar timerProgressBar;
    private static int highScore = 0;
    private static int currentHighScore = 0;
    private static int coinsCollected = 0;
    private static String user_name = "";
    private static String pass_word = "";

    public static void main(String[] args) {
        Gateway();
    }

    static void Gateway() {
        // This is the gateway to the game. This frame will have two buttons only. Register and Login.
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

        // Make the panel for the gateway page
        JPanel gateway = new JPanel();
        // Set the background color of the gateway page.
        frame.getContentPane().setBackground(new java.awt.Color(21, 71, 52));

        // Make a container to contain the gateway pages panes.
        Container gatewayContainer = frame.getContentPane();
        // Have the gateway page have a 1x1 pane.
        gatewayContainer.setLayout(new BorderLayout());
        // Add the gatewayPane to the gateway container.
        gatewayContainer.add(gateway);

        // Create the login button.
        final JButton login = new JButton(new ImageIcon("Images/button_login.png"));
        login.setRolloverIcon(new ImageIcon("Images/button_login_1.png"));
        final int buttonWidth = 100;
        final int buttonHeight = 30;
        login.setBounds(centerX - 100,centerY,buttonWidth,buttonHeight);
        login.setHorizontalAlignment(JButton.CENTER);
        login.setVerticalAlignment(JButton.CENTER);

        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Login();
            }
        });

        // Create the register button.
        final JButton register = new JButton(new ImageIcon("Images/button_register.png"));
        register.setRolloverIcon(new ImageIcon("Images/button_register_1.png"));
        final int registerButtonWidth = 100;
        final int registerButtonHeight = 30;
        register.setBounds(centerX - 100,centerY + 50,registerButtonWidth,registerButtonHeight);
        register.setHorizontalAlignment(JButton.CENTER);
        register.setVerticalAlignment(JButton.CENTER);

        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Register();
            }
        });

        // Add in a new text field.
        final JLabel welcomeText = new JLabel("Welcome to Verbal Memory!");
        welcomeText.setForeground(new java.awt.Color(179, 163, 105));
        final int welcomeTextWidth = 300;
        final int welcomeTextHeight = 20;
        final int offset = 50;
        welcomeText.setBounds(centerX - welcomeTextWidth / 2,centerY - welcomeTextHeight / 2 - offset,welcomeTextWidth,welcomeTextHeight);
        welcomeText.setHorizontalAlignment(JLabel.CENTER);
        welcomeText.setVerticalAlignment(JLabel.CENTER);

        // Change the font of the welcome text.
        welcomeText.setFont(new Font("Serif", Font.BOLD, 20));

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int width = frame.getWidth();
                int height = frame.getHeight();
                int buttonWidth = 150;
                int loginWidth = 100;
                int buttonHeight = 30;
                int textWidth = welcomeTextWidth;
                int textHeight = welcomeTextHeight;
                int textX = (width - textWidth) / 2;
                int textY = (height - textHeight) / 2 - offset;
                int x = (width - buttonWidth) / 2;
                int y = (height - buttonHeight) / 2;
                login.setBounds(x + 25, y, loginWidth, buttonHeight);
                register.setBounds(x, y + 50, buttonWidth, buttonHeight);
                welcomeText.setBounds(textX, textY, welcomeTextWidth, welcomeTextHeight);
            }
        });

        // Add the buttons to the gateway pane.
        gatewayContainer.add(login, BorderLayout.CENTER);
        gatewayContainer.add(register, BorderLayout.CENTER);
        gatewayContainer.add(welcomeText, BorderLayout.CENTER);
        frame.setLayout(null);

        // Make the frame visible.
        frame.setVisible(true);
    }

    static void Register() {
        // This is the register page. This frame will have a text field for the username, a text field for the password,
        // a text field for the password confirmation, and a button to submit the registration.
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

        // Make the panel for the register page
        JPanel registerPane = new JPanel();
        // Set the background color of the register page.
        frame.getContentPane().setBackground(new java.awt.Color(21, 71, 52));
        // Make a container to contain the register pages panes.
        Container register = frame.getContentPane();
        // Have the register page have a 1x1 pane.
        register.setLayout(new BorderLayout());
        // Add the registerPane to the register container.
        register.add(registerPane);

        // Add in a new text field.
        final JLabel registerText = new JLabel("Register");
        registerText.setForeground(new java.awt.Color(179, 163, 105));
        final int registerTextWidth = 200;
        final int registerTextHeight = 20;
        final int offset = 120;
        registerText.setBounds(centerX - registerTextWidth / 2,centerY - registerTextHeight / 2 - offset,registerTextWidth,registerTextHeight);
        registerText.setHorizontalAlignment(JLabel.CENTER);
        registerText.setVerticalAlignment(JLabel.CENTER);

        // Add in a new text field.
        final JLabel usernameText = new JLabel("Username");
        usernameText.setForeground(new java.awt.Color(179, 163, 105));
        final int usernameTextWidth = 200;
        final int usernameTextHeight = 20;
        final int usernameOffset = 70;
        usernameText.setBounds(centerX - usernameTextWidth / 2,centerY - usernameTextHeight / 2 - usernameOffset,usernameTextWidth,usernameTextHeight);
        usernameText.setHorizontalAlignment(JLabel.CENTER);
        usernameText.setVerticalAlignment(JLabel.CENTER);

        // Add in a new text field.
        final JLabel passwordText = new JLabel("Password");
        passwordText.setForeground(new java.awt.Color(179, 163, 105));
        final int passwordTextWidth = 200;
        final int passwordTextHeight = 20;
        final int passwordOffset = 50;
        passwordText.setBounds(centerX - passwordTextWidth / 2,centerY - passwordTextHeight / 2 - passwordOffset,passwordTextWidth,passwordTextHeight);
        passwordText.setHorizontalAlignment(JLabel.CENTER);
        passwordText.setVerticalAlignment(JLabel.CENTER);

        // Add in a new text field.
        final JLabel passwordConfirmText = new JLabel("Confirm Password");
        passwordConfirmText.setForeground(new java.awt.Color(179, 163, 105));
        final int passwordConfirmTextWidth = 200;
        final int passwordConfirmTextHeight = 20;
        final int passwordConfirmOffset = 30;
        passwordConfirmText.setBounds(centerX - passwordConfirmTextWidth / 2,centerY - passwordConfirmTextHeight / 2 - passwordConfirmOffset,passwordConfirmTextWidth,passwordConfirmTextHeight);
        passwordConfirmText.setHorizontalAlignment(JLabel.CENTER);
        passwordConfirmText.setVerticalAlignment(JLabel.CENTER);

        // Add in a new text field.
        final JTextField username = new JTextField();
        final int usernameWidth = 200;
        final int usernameHeight = 20;
        final int usernameX = (frame.getWidth() - usernameWidth) / 2;
        final int usernameY = (frame.getHeight() - usernameHeight) / 2 - usernameOffset;
        username.setBounds(usernameX,usernameY,usernameWidth,usernameHeight);

        // Add in a new text field.
        final JTextField password = new JTextField();
        final int passwordWidth = 200;
        final int passwordHeight = 20;
        final int passwordX = (frame.getWidth() - passwordWidth) / 2;
        final int passwordY = (frame.getHeight() - passwordHeight) / 2 - passwordOffset;
        password.setBounds(passwordX,passwordY,passwordWidth,passwordHeight);

        // Add in a new text field.
        final JTextField passwordConfirm = new JTextField();
        final int passwordConfirmWidth = 200;
        final int passwordConfirmHeight = 20;
        final int passwordConfirmX = (frame.getWidth() - passwordConfirmWidth) / 2;
        final int passwordConfirmY = (frame.getHeight() - passwordConfirmHeight) / 2 - passwordConfirmOffset;
        passwordConfirm.setBounds(passwordConfirmX,passwordConfirmY,passwordConfirmWidth,passwordConfirmHeight);

        // Add in a new button.
        final JButton submit = new JButton("Submit");
        final int submitWidth = 200;
        final int submitHeight = 20;
        final int submitX = (frame.getWidth() - submitWidth) / 2;
        final int submitY = (frame.getHeight() - submitHeight) / 2 + offset;
        submit.setBounds(submitX,submitY,submitWidth,submitHeight);

        // Add in a new button.
        final JButton back = new JButton("Back");
        final int backWidth = 200;
        final int backHeight = 20;
        final int backX = (frame.getWidth() - backWidth) / 2;
        final int backY = (frame.getHeight() - backHeight) / 2 + offset + submitHeight;
        back.setBounds(backX,backY,backWidth,backHeight);

        // Add in an action listener for the submit button.
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the username and password.
                String usernameString = username.getText();
                String passwordString = password.getText();
                String passwordConfirmString = passwordConfirm.getText();

                // Check if the username is already taken.
                if (usernameString.equals("")) {
                    JOptionPane.showMessageDialog(frame,"Please enter a username.");
                } else if (passwordString.equals("")) {
                    JOptionPane.showMessageDialog(frame,"Please enter a password.");
                } else if (passwordConfirmString.equals("")) {
                    JOptionPane.showMessageDialog(frame,"Please confirm your password.");
                } else if (!passwordString.equals(passwordConfirmString)) {
                    JOptionPane.showMessageDialog(frame,"Passwords do not match.");
                } else {
                    // Check if the username is already taken.
                    if (Data.userExists(usernameString)) {
                        JOptionPane.showMessageDialog(frame,"Username already exists.");
                    } else {
                        // Add the username and password to the database.
                        Data.addUser(usernameString,passwordString);
                        // Say that the user has been created
                        JOptionPane.showMessageDialog(frame,"User created.");
                        // Go to the home page.
                        Home();
                        // Close the register page.
                        frame.dispose();
                    }
                }
            }
        });

        // Add in an action listener for the back button.
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Go to the gateway page.
                Gateway();
                // Close the register page.
                frame.dispose();
            }
        });

        // Add the component listener
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                // Get the new size of the frame.
                Dimension newSize = frame.getSize();
                // Get the new center of the frame.
                int newCenterX = (int) newSize.getWidth() / 2;
                int newCenterY = (int) newSize.getHeight() / 2;
                // Set the new bounds of the username text.
                usernameText.setBounds(newCenterX - usernameTextWidth / 2,newCenterY - usernameTextHeight / 2 - usernameOffset,usernameTextWidth,usernameTextHeight);
                // Set the new bounds of the password text.
                passwordText.setBounds(newCenterX - passwordTextWidth / 2,newCenterY - passwordTextHeight / 2 - passwordOffset,passwordTextWidth,passwordTextHeight);
                // Set the new bounds of the password confirm text.
                passwordConfirmText.setBounds(newCenterX - passwordConfirmTextWidth / 2,newCenterY - passwordConfirmTextHeight / 2 - passwordConfirmOffset,passwordConfirmTextWidth,passwordConfirmTextHeight);
                // Set the new bounds of the username text field.
                username.setBounds(newCenterX - usernameWidth / 2,newCenterY - usernameHeight / 2 - usernameOffset,usernameWidth,usernameHeight);
                // Set the new bounds of the password text field.
                password.setBounds(newCenterX - passwordWidth / 2,newCenterY - passwordHeight / 2 - passwordOffset,passwordWidth,passwordHeight);
                // Set the new bounds of the password confirm text field.
                passwordConfirm.setBounds(newCenterX - passwordConfirmWidth / 2,newCenterY - passwordConfirmHeight / 2 - passwordConfirmOffset,passwordConfirmWidth,passwordConfirmHeight);
                // Set the new bounds of the submit button.
                submit.setBounds(newCenterX - submitWidth / 2,newCenterY - submitHeight / 2 + offset,submitWidth,submitHeight);
                // Set the new bounds of the back button.
                back.setBounds(newCenterX - backWidth / 2,newCenterY - backHeight / 2 + offset + submitHeight,backWidth,backHeight);
            }
        });

        // Add the components to the frame.
        register.add(registerText, BorderLayout.NORTH);
        register.add(usernameText, BorderLayout.NORTH);
        register.add(passwordText, BorderLayout.NORTH);
        register.add(passwordConfirmText, BorderLayout.NORTH);
        register.add(username, BorderLayout.NORTH);
        register.add(password, BorderLayout.NORTH);
        register.add(passwordConfirm, BorderLayout.NORTH);
        register.add(submit, BorderLayout.NORTH);
        register.add(back, BorderLayout.NORTH);
        frame.setLayout(null);

        // Make the frame visible.
        frame.setVisible(true);
    }

    static void Login() {
        // Allow the user to login. (Utilize the login method in the Data class)
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

        // Make the panel for the login page
        JPanel loginPane = new JPanel();
        // Set the background color of the login page.
        frame.getContentPane().setBackground(new java.awt.Color(21, 71, 52));
        // Make a container to contain the login pages panes.
        Container login = frame.getContentPane();
        // Have the login page have a 1x1 pane.
        login.setLayout(new BorderLayout());
        // Add the login pane to the login page.
        login.add(loginPane, BorderLayout.CENTER);

        // Add in a new text field.
        final JLabel registerText = new JLabel("Login");
        registerText.setForeground(new java.awt.Color(179, 163, 105));
        final int registerTextWidth = 200;
        final int registerTextHeight = 20;
        final int offset = 120;
        registerText.setBounds(centerX - registerTextWidth / 2,centerY - registerTextHeight / 2 - offset,registerTextWidth,registerTextHeight);
        registerText.setHorizontalAlignment(JLabel.CENTER);
        registerText.setVerticalAlignment(JLabel.CENTER);

        // Add in a new text field.
        final JLabel usernameText = new JLabel("Username");
        usernameText.setForeground(new java.awt.Color(179, 163, 105));
        final int usernameTextWidth = 200;
        final int usernameTextHeight = 20;
        final int usernameOffset = 0;
        usernameText.setBounds(centerX - usernameTextWidth / 2,centerY - usernameTextHeight / 2 - 20,usernameTextWidth,usernameTextHeight);
        usernameText.setHorizontalAlignment(JLabel.CENTER);
        usernameText.setVerticalAlignment(JLabel.CENTER);

        // Add in a new text field.
        final JLabel passwordText = new JLabel("Password");
        passwordText.setForeground(new java.awt.Color(179, 163, 105));
        final int passwordTextWidth = 200;
        final int passwordTextHeight = 20;
        final int passwordOffset = 30;
        passwordText.setBounds(centerX - passwordTextWidth / 2,centerY - passwordTextHeight / 2 + 20,passwordTextWidth,passwordTextHeight);
        passwordText.setHorizontalAlignment(JLabel.CENTER);
        passwordText.setVerticalAlignment(JLabel.CENTER);

        // Add in a new text field.
        final JTextField username = new JTextField();
        final int usernameWidth = 200;
        final int usernameHeight = 20;
        final int usernameX = centerX - usernameWidth / 2;
        final int usernameY = centerY - usernameHeight / 2 + usernameOffset;
        username.setBounds(usernameX,usernameY,usernameWidth,usernameHeight);

        // Add in a new text field.
        final JTextField password = new JTextField();
        final int passwordWidth = 200;
        final int passwordHeight = 20;
        final int passwordX = centerX - passwordWidth / 2;
        final int passwordY = centerY - passwordHeight / 2 + passwordOffset + 10;
        password.setBounds(passwordX,passwordY,passwordWidth,passwordHeight);

        // Add in a new button.
        final JButton submit = new JButton("Submit");
        final int submitWidth = 200;
        final int submitHeight = 20;
        final int submitX = centerX - submitWidth / 2;
        final int submitY = centerY - submitHeight / 2 + offset;
        submit.setBounds(submitX,submitY,submitWidth,submitHeight);

        // Add in a new button.
        final JButton back = new JButton("Back");
        final int backWidth = 200;
        final int backHeight = 20;
        final int backX = centerX - backWidth / 2;
        final int backY = centerY - backHeight / 2 + offset + submitHeight;
        back.setBounds(backX,backY,backWidth,backHeight);

        // Add in a new action listener for the submit button.
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the username and password.
                String usernameString = username.getText();
                String passwordString = password.getText();
                // Check if the username and password are correct.
                if (Data.login(usernameString, passwordString)) {
                    highScore = Data.getHighScore(usernameString);
                    coinsCollected = Data.getCoins(usernameString);
                    user_name = usernameString;
                    pass_word = passwordString;
                    // If they are correct, then go to the home page.
                    Home();
                    // Close the login page.
                    frame.dispose();
                } else {
                    // If they are incorrect, then display an error message.
                    JOptionPane.showMessageDialog(frame, "Incorrect username or password.");
                }
            }
        });

        // Add in a new action listener for the back button.
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Go back to the gateway page.
                Gateway();
                // Close the login page.
                frame.dispose();
            }
        });

        // Add the component listener
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                // Get the new size of the frame.
                Dimension newSize = frame.getSize();
                // Get the new center of the frame.
                int newCenterX = (int) newSize.getWidth() / 2;
                int newCenterY = (int) newSize.getHeight() / 2;
                // Set the new bounds of the register text.
                registerText.setBounds(newCenterX - registerTextWidth / 2,newCenterY - registerTextHeight / 2 - offset,registerTextWidth,registerTextHeight);
                // Set the new bounds of the username text.
                usernameText.setBounds(newCenterX - usernameTextWidth / 2,newCenterY - usernameTextHeight / 2 - 20,usernameTextWidth,usernameTextHeight);
                // Set the new bounds of the password text.
                passwordText.setBounds(newCenterX - passwordTextWidth / 2,newCenterY - passwordTextHeight / 2 + 20,passwordTextWidth,passwordTextHeight);
                // Set the new bounds of the username text field.
                username.setBounds(newCenterX - usernameWidth / 2,newCenterY - usernameHeight / 2 + usernameOffset,usernameWidth,usernameHeight);
                // Set the new bounds of the password text field.
                password.setBounds(newCenterX - passwordWidth / 2,newCenterY - passwordHeight / 2 + passwordOffset + 10,passwordWidth,passwordHeight);
                // Set the new bounds of the submit button.
                submit.setBounds(newCenterX - submitWidth / 2,newCenterY - submitHeight / 2 + offset,submitWidth,submitHeight);
                // Set the new bounds of the back button.
                back.setBounds(newCenterX - backWidth / 2,newCenterY - backHeight / 2 + offset + submitHeight,backWidth,backHeight);
            }
        });

        // Add the text fields to the login pane.
        loginPane.add(registerText);
        loginPane.add(usernameText);
        loginPane.add(passwordText);
        loginPane.add(username);
        loginPane.add(password);
        loginPane.add(submit);
        loginPane.add(back);
        loginPane.setLayout(null);

        // Make the background green
        loginPane.setBackground(new java.awt.Color(21, 71, 52));

        // Make the frame visible.
        frame.setVisible(true);
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
        frame.getContentPane().setBackground(new java.awt.Color(21, 71, 52));
        // Make a container to contain the home pages panes.
        Container home = frame.getContentPane();
        // Have the home page have a 1x1 pane.
        //home.setLayout(new GridLayout(1, 1));
        home.setLayout(new BorderLayout());
        // Add the homePane to the home container.
        home.add(homePane);

        // Create a new button.
        final JButton start = new JButton(new ImageIcon("Images/button_start-game.png"));
        start.setRolloverIcon(new ImageIcon("Images/button_start-game_1.png"));
        final int startButtonWidth = 150;
        final int startButtonHeight = 30;
        start.setBounds(centerX - startButtonWidth / 2,centerY - startButtonHeight / 2,startButtonWidth,startButtonHeight);
        start.setHorizontalAlignment(JButton.CENTER);
        start.setVerticalAlignment(JButton.CENTER);

        // Create the logout button.
        final JButton logout = new JButton(new ImageIcon("Images/button_logout.png"));
        logout.setRolloverIcon(new ImageIcon("Images/button_logout_1.png"));
        final int logoutButtonWidth = 150;
        final int logoutButtonHeight = 30;
        logout.setBounds(centerX - logoutButtonWidth / 2,centerY - logoutButtonHeight / 2 + startButtonHeight + 20,logoutButtonWidth,logoutButtonHeight);
        logout.setHorizontalAlignment(JButton.CENTER);
        logout.setVerticalAlignment(JButton.CENTER);

        // Add in a new text field.
        final JLabel welcomeText = new JLabel("Hey " + user_name + ", Welcome to Verbal Memory!");
        welcomeText.setForeground(new java.awt.Color(179, 163, 105));
        final int welcomeTextWidth = 500;
        final int welcomeTextHeight = 20;
        final int offset = startButtonHeight + 20;
        welcomeText.setBounds(centerX - welcomeTextWidth / 2,centerY - welcomeTextHeight / 2 - offset,welcomeTextWidth,welcomeTextHeight);
        welcomeText.setHorizontalAlignment(JLabel.CENTER);
        welcomeText.setVerticalAlignment(JLabel.CENTER);

        // Add in a new text field.
        final JLabel highScoreText = new JLabel("Your High Score is: " + highScore);
        highScoreText.setForeground(new java.awt.Color(179, 163, 105));
        final int highScoreTextWidth = 200;
        final int highScoreTextHeight = 20;
        highScoreText.setBounds(centerX - highScoreTextWidth / 2,centerY - highScoreTextHeight / 2 + offset,highScoreTextWidth,highScoreTextHeight);
        highScoreText.setHorizontalAlignment(JLabel.CENTER);
        highScoreText.setVerticalAlignment(JLabel.CENTER);

        // Add in a new text field.
        final JLabel coins = new JLabel("You have " + coinsCollected + " coins!");
        coins.setForeground(new java.awt.Color(179, 163, 105));
        final int coinsWidth = 200;
        final int coinsHeight = 20;
        coins.setBounds(centerX - coinsWidth / 2,centerY - coinsHeight / 2 + offset + highScoreTextHeight,coinsWidth,coinsHeight);
        coins.setHorizontalAlignment(JLabel.CENTER);
        coins.setVerticalAlignment(JLabel.CENTER);

        // Set the event of the button
        start.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                // Close the current frame.
                frame.dispose();
                Game();
            }
        });

        // Set the event of the button
        logout.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                // Close the current frame.
                frame.dispose();
                Gateway();
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
                logout.setBounds(x, y + offset, buttonWidth, buttonHeight);
                welcomeText.setBounds(textX, textY, welcomeTextWidth, welcomeTextHeight);
                highScoreText.setBounds(textX, textY + offset, highScoreTextWidth, highScoreTextHeight);
                coins.setBounds(textX, textY + offset + highScoreTextHeight, coinsWidth, coinsHeight);
            }
        });

        home.add(start, BorderLayout.CENTER);
        home.add(logout, BorderLayout.CENTER);
        home.add(welcomeText, BorderLayout.CENTER);
        home.add(highScoreText, BorderLayout.CENTER);
        home.add(coins, BorderLayout.CENTER);
        frame.setLayout(null);

        // Set the frame to visible.
        frame.setVisible(true);

    }

    static void Game() {
        // Put the game in a new frame.
        final JFrame frame = new JFrame();
        frame.setSize(400,400);

        elapsedTime = 0;
        timer = new Timer(1000, null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) screenSize.getWidth() / 2;
        int centerY = (int) screenSize.getHeight() / 2;

        // Set the title and default operations of the frame
        frame.setTitle("Verbal Memory");
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().setBackground(new java.awt.Color(21, 71, 52));

        // Create the array of words.
        final String[][] wordsList = {{"apple", "banana", "cat", "dog", "fish", "tree", "house", "car", "ball", "kite", "sun", "moon", "star", "cloud", "rainbow"}, {"goofy", "red", "orange", "grape", "stereo", "flag", "picnic", "ring","board", "tennis", "ship", "dark", "phone", "law", "camera"}, {"celebrate", "citizen", "damage", "food", "near", "protect", "table", "dish", "owl", "elephant", "world", "snapshot", "future", "past", "airline"}, {"box", "egg", "tape", "horse", "bell", "ladder", "glue", "string", "pig", "eyes", "carbon", "sea", "sleep", "light", "staple"}};

        // Create a Random object
        Random set = new Random();
        // Set rand to an int from 0-3
        int row = set.nextInt(4);
        // Add each word in the selected "set" to the words array below.
        final String[] words = new String[15];
        // Loop through the chosen row (the random int) and add each word to the words array.
        for (int i = 0; i < 15; i++) {
            words[i] = wordsList[row][i];
        }

        // Create two array lists, one for new words, and one for words that have been seen before. Add all the words from the array words, to the new array list to start.
        ArrayList<String> newWords = new ArrayList<String>(Arrays.asList(words));
        ArrayList<String> seenWords = new ArrayList<String>();

        // Display one word in the center of the screen.
        final JLabel word = new JLabel(words[(int)(Math.random() * words.length)]);
        final int wordWidth = 500;
        final int wordHeight = 100;
        word.setBounds(centerX - wordWidth / 2,centerY - wordHeight / 2,wordWidth,wordHeight);

        // Display the word in a fancier manner.
        word.setFont(new Font("Serif", Font.BOLD, 50));
        word.setForeground(new java.awt.Color(179, 163, 105));
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
        score.setForeground(new java.awt.Color(244, 245, 240));

        // Create a coins earned tracker
        final JLabel coinsEarned = new JLabel("Coins Earned: 0");
        final int coinsEarnedWidth = 200;
        final int coinsEarnedHeight = 20;
        // Put the coins earned next to the score.
        coinsEarned.setBounds(centerX - coinsEarnedWidth / 2, centerY - coinsEarnedHeight / 2 - 100, coinsEarnedWidth, coinsEarnedHeight);
        coinsEarned.setHorizontalAlignment(JLabel.CENTER);
        coinsEarned.setLocation(centerX - coinsEarnedWidth / 2 + 150, centerY - coinsEarnedHeight / 2 - 100);
        coinsEarned.setForeground(new java.awt.Color(244, 245, 240));

        // Create a lives tracker
        final JLabel lives = new JLabel("Lives: 3");
        final int livesWidth = 100;
        final int livesHeight = 20;
        // Put the lives next to the score.
        lives.setBounds(centerX - livesWidth / 2, centerY - livesHeight / 2 - 100, livesWidth, livesHeight);
        lives.setHorizontalAlignment(JLabel.CENTER);
        lives.setLocation(centerX - livesWidth / 2 + 50, centerY - livesHeight / 2 - 100);
        lives.setForeground(new java.awt.Color(244, 245, 240));

        timerProgressBar = new JProgressBar();
        final int progressWidth = 400;
        final int progressHeight = 20;
        timerProgressBar.setMaximum(timeLimit);
        timerProgressBar.setBounds(centerX - progressWidth / 2, centerY - progressHeight / 2 + 50, progressWidth, progressHeight);
        // Change the color of the progress bar.
        timerProgressBar.setForeground(new java.awt.Color(179, 163, 105));

        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Have the elapsedTime increase at a rate correlating to the current game score. I.e. Make it harder as the game goes on.
                elapsedTime += (Math.log((Integer.parseInt(score.getText().substring(7))) + 1) / Math.log(2));
                int timeLeft = timeLimit - (int) elapsedTime;
                timerProgressBar.setValue(timeLeft);
                if (timeLeft <= 0) {
                    currentHighScore += Integer.parseInt(score.getText().substring(7));
                    if (currentHighScore > highScore) {
                        Data.updateHighScore(user_name, currentHighScore);
                        highScore = currentHighScore;
                    }
                    currentHighScore = 0;
                    coinsCollected += Integer.parseInt(score.getText().substring(7)) / 10;
                    Data.updateCoins(user_name, coinsCollected);
                    timer.stop();
                    elapsedTime = 0;
                    frame.dispose();
                    gameOver((Integer.parseInt(score.getText().substring(7))));
                }
            }
        });
        timer.start();

        // Create the "new" and "seen" buttons to go under the word
        final JButton newButton = new JButton(new ImageIcon("Images/button_new.png"));
        newButton.setRolloverIcon(new ImageIcon("Images/button_new_1.png"));
        final JButton seenButton = new JButton(new ImageIcon("Images/button_seen.png"));
        seenButton.setRolloverIcon(new ImageIcon("Images/button_seen_1.png"));
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

                    // Check if the score is divisible by 10. If so, add a coin.
                    if (Integer.parseInt(score.getText().substring(7)) % 10 == 0) {
                        coinsEarned.setText("Coins Earned: " + (Integer.parseInt(coinsEarned.getText().substring(14)) + 1));
                    }

                }

                else {

                    // Take away a life.
                    lives.setText("Lives: " + (Integer.parseInt(lives.getText().substring(7)) - 1));

                }

                if (Integer.parseInt(lives.getText().substring(7)) == 0) {
                    currentHighScore += Integer.parseInt(score.getText().substring(7));
                    if (currentHighScore > highScore) {
                        Data.updateHighScore(user_name, currentHighScore);
                        highScore = currentHighScore;
                    }
                    currentHighScore = 0;
                    coinsCollected += Integer.parseInt(score.getText().substring(7)) / 10;
                    Data.updateCoins(user_name, coinsCollected);
                    timer.stop();
                    timer = new Timer(1000, null);
                    frame.dispose();
                    gameOver((Integer.parseInt(score.getText().substring(7))));
                }

                // reset timer and progress bar
                elapsedTime = 0;
                timer.stop();
                timer.start();
                timerProgressBar.setValue(timeLimit);
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

                    // Check if the score is divisible by 10. If so, add a coin.
                    if (Integer.parseInt(score.getText().substring(7)) % 10 == 0) {
                        coinsEarned.setText("Coins Earned: " + (Integer.parseInt(coinsEarned.getText().substring(14)) + 1));
                    }

                }

                else {

                    // Take away a life.
                    lives.setText("Lives: " + (Integer.parseInt(lives.getText().substring(7)) - 1));
                    // Remove the word from new words and add it to seen words.
                    newWords.remove(currentWord);
                    seenWords.add(currentWord);

                }

                if (Integer.parseInt(lives.getText().substring(7)) == 0) {
                    currentHighScore += Integer.parseInt(score.getText().substring(7));
                    if (currentHighScore > highScore) {
                        Data.updateHighScore(user_name, currentHighScore);
                        highScore = currentHighScore;
                    }
                    currentHighScore = 0;
                    coinsCollected += Integer.parseInt(score.getText().substring(7)) / 10;
                    Data.updateCoins(user_name, coinsCollected);
                    timer.stop();
                    timer = new Timer(1000, null);
                    frame.dispose();
                    gameOver((Integer.parseInt(score.getText().substring(7))));
                }

                // reset timer and progress bar
                elapsedTime = 0;
                timer.stop();
                timer.start();
                timerProgressBar.setValue(timeLimit);
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

                coinsEarned.setBounds(centerX - coinsEarnedWidth / 2, centerY - coinsEarnedHeight / 2 - 100, coinsEarnedWidth, coinsEarnedHeight);
                coinsEarned.setHorizontalAlignment(JLabel.CENTER);
                coinsEarned.setLocation(centerX - coinsEarnedWidth / 2 + 150, centerY - coinsEarnedHeight / 2 - 100);

                lives.setBounds(centerX - livesWidth / 2, centerY - livesHeight / 2 - 100, livesWidth, livesHeight);
                lives.setHorizontalAlignment(JLabel.CENTER);
                lives.setLocation(centerX - livesWidth / 2 + 50, centerY - livesHeight / 2 - 100);

                timerProgressBar.setBounds(centerX - progressWidth / 2, centerY - progressHeight / 2 + 50, progressWidth, progressHeight);

                newButton.setBounds(centerX - buttonWidth / 2,centerY - buttonHeight / 2 + offset,buttonWidth,buttonHeight);
                seenButton.setBounds(centerX - buttonWidth / 2,centerY - buttonHeight / 2 + offset + buttonHeight + 10,buttonWidth,buttonHeight);
            }
        });

        // Add the word and buttons to the frame.
        frame.add(word);
        frame.add(score);
        frame.add(coinsEarned);
        frame.add(lives);
        frame.add(timerProgressBar);
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

        frame.getContentPane().setBackground(new java.awt.Color(21, 71, 52));

        // Display one word in the center of the screen.
        final JLabel word = new JLabel("Game Over!");
        final int wordWidth = 500;
        final int wordHeight = 200;
        word.setBounds(centerX - wordWidth / 2,centerY - wordHeight / 2,wordWidth,wordHeight);

        // Display the word in a fancier manner.
        word.setFont(new Font("Serif", Font.BOLD, 30));
        // Have the font size resize with the window.
        word.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                word.setFont(new Font("Serif", Font.BOLD, (int) word.getWidth() / 10));
            }
        });
        word.setForeground(new java.awt.Color(244, 245, 240));
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
        finalScore.setFont(new Font("Serif", Font.BOLD, 30));
        // Have the font size resize with the window.
        finalScore.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                finalScore.setFont(new Font("Serif", Font.BOLD, (int) finalScore.getWidth() / 20));
            }
        });
        finalScore.setForeground(new java.awt.Color(179, 163, 105));

        // Create the "new" and "seen" buttons to go under the word.
        final JButton homeButton = new JButton(new ImageIcon("Images/button_home.png"));
        homeButton.setRolloverIcon(new ImageIcon("Images/button_home_1.png"));
        final JButton restartButton = new JButton(new ImageIcon("Images/button_restart.png"));
        restartButton.setRolloverIcon(new ImageIcon("Images/button_restart_1.png"));
        final int buttonWidth = 100;
        final int buttonHeight = 30;
        final int offset = 100;
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