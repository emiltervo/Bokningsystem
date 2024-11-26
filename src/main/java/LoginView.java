import javax.swing.*;
import java.awt.*;

public class LoginView {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 900);
        frame.setLayout(new BorderLayout()); // Use BorderLayout for structured layout

        // Header panel
        JPanel headPanel = new JPanel();
        headPanel.setPreferredSize(new Dimension(frame.getWidth(), 125));
        headPanel.setBackground(Color.GRAY);
        frame.add(headPanel, BorderLayout.NORTH); // Add header panel to the top

        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); // Vertical layout
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // Padding

        // Create components
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align

        JTextField usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(400, 30)); // Set size for text field
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align

        JPasswordField passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(400, 30)); // Set size for password field
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align

        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the button

        // Add components to the content panel
        contentPanel.add(Box.createVerticalStrut(20)); // Spacer
        contentPanel.add(usernameLabel);
        contentPanel.add(Box.createVerticalStrut(5)); // Spacer
        contentPanel.add(usernameField);
        contentPanel.add(Box.createVerticalStrut(20)); // Spacer
        contentPanel.add(passwordLabel);
        contentPanel.add(Box.createVerticalStrut(5)); // Spacer
        contentPanel.add(passwordField);
        contentPanel.add(Box.createVerticalStrut(20)); // Spacer
        contentPanel.add(loginButton);

        // Add content panel to the frame
        frame.add(contentPanel, BorderLayout.CENTER);

        // Set frame visibility
        frame.setVisible(true);
    }
}