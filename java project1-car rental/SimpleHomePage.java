import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleHomePage extends JFrame {

    public SimpleHomePage() {
        setTitle("Car Rental Services");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to Car Rental Services", SwingConstants.CENTER);
        JLabel madeByLabel = new JLabel("Made by Sangam Sedai", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);

        add(welcomeLabel, BorderLayout.NORTH);
        add(madeByLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Actions
        loginButton.addActionListener(e -> showLoginWindow());
        signupButton.addActionListener(e -> showSignupWindow());

        setVisible(true);
    }

    private void showLoginWindow() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(300, 200);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setLayout(new GridLayout(3, 2, 10, 10));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton submit = new JButton("Login");

        loginFrame.add(new JLabel("Username:"));
        loginFrame.add(usernameField);
        loginFrame.add(new JLabel("Password:"));
        loginFrame.add(passwordField);
        loginFrame.add(new JLabel());
        loginFrame.add(submit);

        submit.addActionListener(e -> {
            JOptionPane.showMessageDialog(loginFrame, "Logged in as: " + usernameField.getText());
            loginFrame.dispose();
        });

        loginFrame.setVisible(true);
    }

    private void showSignupWindow() {
        JFrame signupFrame = new JFrame("Sign Up");
        signupFrame.setSize(300, 250);
        signupFrame.setLocationRelativeTo(null);
        signupFrame.setLayout(new GridLayout(4, 2, 10, 10));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JPasswordField confirmField = new JPasswordField();
        JButton submit = new JButton("Sign Up");

        signupFrame.add(new JLabel("Username:"));
        signupFrame.add(usernameField);
        signupFrame.add(new JLabel("Password:"));
        signupFrame.add(passwordField);
        signupFrame.add(new JLabel("Confirm Password:"));
        signupFrame.add(confirmField);
        signupFrame.add(new JLabel());
        signupFrame.add(submit);

        submit.addActionListener(e -> {
            String pass = new String(passwordField.getPassword());
            String confirm = new String(confirmField.getPassword());
            if (pass.equals(confirm)) {
                JOptionPane.showMessageDialog(signupFrame, "Signed up as: " + usernameField.getText());
                signupFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(signupFrame, "Passwords do not match.");
            }
        });

        signupFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleHomePage();
    }
}
