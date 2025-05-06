import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private ArrayList<UserAccount> users = new ArrayList<>();
    private JPanel mainPanel;

    public LoginFrame() {
        setTitle("Welcome to Car Rental Services");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window

        // Set background color for the frame
        getContentPane().setBackground(new Color(255, 235, 205));  // Soft peach color

        // Create main panel to hold the homepage components
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Add a welcome message with custom font
        JLabel welcomeLabel = new JLabel("Welcome to Car Rental Services", SwingConstants.CENTER);
        JLabel madeByLabel = new JLabel("Made by Sangam Sedai.", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        madeByLabel.setFont(new Font("Verdana", Font.PLAIN, 14));

        // Set color and padding for the labels
        welcomeLabel.setForeground(new Color(60, 60, 60));  // Dark gray
        madeByLabel.setForeground(new Color(100, 100, 100)); // Lighter gray

        // Buttons for Login and Sign Up with custom styles
        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");

        // Customize button styles
        loginButton.setBackground(new Color(0, 123, 255));  // Blue color
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2));

        signupButton.setBackground(new Color(0, 123, 255));  // Blue color
        signupButton.setForeground(Color.WHITE);
        signupButton.setFont(new Font("Arial", Font.BOLD, 14));
        signupButton.setFocusPainted(false);
        signupButton.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2));

        // Add components to the panel
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);
        mainPanel.add(madeByLabel, BorderLayout.CENTER);

        // Create a panel for buttons and add them with spacing
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(255, 235, 205));
        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners for login and sign-up
        loginButton.addActionListener(e -> {
            showLoginForm();  // Display the login form when the Login button is clicked
        });

        signupButton.addActionListener(e -> {
            showLoginForm();  // Display the login form when the Sign Up button is clicked
        });

        // Add the main panel to the frame
        add(mainPanel);
        setVisible(true);
    }

    private void showLoginForm() {
        // Hide the homepage components
        mainPanel.setVisible(false);

        // Set up login form layout
        setTitle("Login or Sign Up");
        setSize(350, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set background color for the frame
        getContentPane().setBackground(new Color(240, 240, 240));  // Light gray background

        // Create a new panel for login/signup form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 10, 10)); // Set gap between rows and columns

        // Add components with padding
        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");

        // Customize button styles
        loginButton.setBackground(new Color(0, 123, 255));  // Blue color
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2));

        signupButton.setBackground(new Color(0, 123, 255));  // Blue color
        signupButton.setForeground(Color.WHITE);
        signupButton.setFont(new Font("Arial", Font.BOLD, 14));
        signupButton.setFocusPainted(false);
        signupButton.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2));

        // Add buttons to the form
        formPanel.add(loginButton);
        formPanel.add(signupButton);

        // Add action listeners for login and sign-up
        loginButton.addActionListener(e -> login());
        signupButton.addActionListener(e -> signUp());

        // Add the form panel to the frame
        add(formPanel);
        setVisible(true);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        for (UserAccount user : users) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                JOptionPane.showMessageDialog(this, "Login successful");
                new CarRentalFrame(user, this, users);
                setVisible(false);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Invalid credentials");
    }

    private void signUp() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        for (UserAccount user : users) {
            if (user.getUsername().equals(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists");
                return;
            }
        }
        users.add(new UserAccount(username, password));
        JOptionPane.showMessageDialog(this, "Account created successfully");
    }

    public static void main(String[] args) {
        new LoginFrame();  // Start the application with the HomePage (LoginFrame)
    }
}
