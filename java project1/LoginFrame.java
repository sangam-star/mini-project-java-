import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private ArrayList<UserAccount> users = new ArrayList<>();

    public LoginFrame() {
        setTitle("Login or Sign Up");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(4, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");

        add(loginButton);
        add(signupButton);

        loginButton.addActionListener(e -> login());
        signupButton.addActionListener(e -> signUp());
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        for (UserAccount user : users) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                JOptionPane.showMessageDialog(this, "Login successful");
                new CarRentalFrame(user, this);
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
}
