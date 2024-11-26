import javax.swing.*;
import java.awt.*;

public class LoginView {

    public static void main(String[] args) {

        //Main frame - STATIC
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 750);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false); //No resizing - no scaling issues
        frame.setLocationRelativeTo(null); //Pops up in the center of screen

        //Header panel - STATIC
        JPanel headPanel = new JPanel();
        headPanel.setPreferredSize(new Dimension(frame.getWidth(), 125));
        headPanel.setLayout(new BorderLayout());
        frame.add(headPanel, BorderLayout.NORTH);

        // Image in header
        ImageIcon headerImage = new ImageIcon("src/main/resources/header.png"); //src/... path
        JLabel imageLabel = new JLabel(headerImage);

        //TODO

        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        //Username text
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align


        //Username goes here (index personnummer)
        JTextField usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(400, 30));
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        //TODO

        //Password text
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Password goes here (index password)
        JPasswordField passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(400, 30)); // Set size for password field
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        //TODO

        //Login button triggers user/pass check. If successful - check role (admin/non-admin) & redirect.
        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //TODO

        //Populate panel
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(usernameLabel);
        contentPanel.add(Box.createVerticalStrut(5));
        contentPanel.add(usernameField);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(passwordLabel);
        contentPanel.add(Box.createVerticalStrut(5));
        contentPanel.add(passwordField);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(loginButton);

        frame.add(contentPanel, BorderLayout.CENTER);

        //Visible
        frame.setVisible(true);
    }
}