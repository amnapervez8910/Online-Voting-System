import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Main {
    private JFrame frame;
    private int currentVoterId = -1;
    private String district = "YourDistrict";
    private int nextVoterId;
    private Color lightBlueColor = new Color(173, 216, 230);

    // Constructor - setup initial UI
    public Main() {
        frame = new JFrame("Online Voting System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        showWelcomeScreen();
        frame.setVisible(true);
    }

    // Welcome screen
    private void showWelcomeScreen() {
        JPanel panel = new JPanel();
        panel.setBackground(lightBlueColor);

        JLabel welcomeLabel = new JLabel("Welcome to Online Voting System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JButton loginBtn = new JButton("Login");
        JButton signupBtn = new JButton("Signup");

        loginBtn.addActionListener(e -> showLoginScreen());
        signupBtn.addActionListener(e -> showSignupScreen());

        panel.add(welcomeLabel);
        panel.add(loginBtn);
        panel.add(signupBtn);

        frame.setContentPane(panel);
        frame.revalidate();
    }

    // Login screen
    private void showLoginScreen() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel idLabel = new JLabel("Voter ID:");
        JTextField idField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();
        JButton loginBtn = new JButton("Login");

        loginBtn.addActionListener(e -> {
            String id = idField.getText();
            String pass = new String(passField.getPassword());
            if (authenticateUser(id, pass)) {
                setCurrentVoterId(Integer.parseInt(id));
                showMainMenu();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Login!");
            }
        });

        panel.add(idLabel);
        panel.add(idField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(loginBtn);

        frame.setContentPane(panel);
        frame.revalidate();
    }

    // Signup screen
    private void showSignupScreen() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField nameField = new JTextField();
        JTextField districtField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton signupBtn = new JButton("Register");

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("District:"));
        panel.add(districtField);
        panel.add(new JLabel("Password:"));
        panel.add(passField);
        panel.add(signupBtn);

        signupBtn.addActionListener(e -> {
            // save to database
            JOptionPane.showMessageDialog(frame, "Signup successful!");
            showLoginScreen();
        });

        frame.setContentPane(panel);
        frame.revalidate();
    }

    // Main menu after login
    private void showMainMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton castVoteBtn = new JButton("Cast Vote");
        JButton viewInfoBtn = new JButton("View Voter Info");
        JButton logoutBtn = new JButton("Logout");

        castVoteBtn.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Vote Casted!"));
        viewInfoBtn.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Showing Voter Info"));
        logoutBtn.addActionListener(e -> showWelcomeScreen());

        panel.add(castVoteBtn);
        panel.add(viewInfoBtn);
        panel.add(logoutBtn);

        frame.setContentPane(panel);
        frame.revalidate();
    }

    // Mock DB Authentication (replace with real DB)
    private boolean authenticateUser(String id, String pass) {
        // In real project: query Oracle DB
        return id.equals("123") && pass.equals("123");
    }

    public void setCurrentVoterId(int id) {
        this.currentVoterId = id;
    }

    public static void main(String[] args) {
        new Main();
    }
}
