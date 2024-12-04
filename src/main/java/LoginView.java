import javax.swing.*;
import java.awt.*;

public class LoginView {
    static JTextField usernameField = new JTextField();
    static JButton loginButton = new JButton("Login");
    static JLabel errorLabel = new JLabel("");

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 750);
        frame.setLayout(new BorderLayout()); // Use BorderLayout for structured layout
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Header panel (copied from HomePage)
        JPanel headPanel = new JPanel();
        headPanel.setPreferredSize(new Dimension(frame.getWidth(), 125));  // Set the header height
        headPanel.setLayout(new BorderLayout()); // Make it easier to add the image
        frame.add(headPanel, BorderLayout.NORTH); // Add header panel to the top

        // Load image into an ImageIcon (copied from HomePage)
        ImageIcon headerImage = new ImageIcon("src/main/resources/Namnlös.png"); // Path to your image
        Image image = headerImage.getImage();  // Get the Image object from the ImageIcon

        // Create a custom JPanel to render the image with better quality
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw the image scaled to fit the panel
                g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Set the preferred size of the image panel
        imagePanel.setPreferredSize(new Dimension(1200, 125));

        // Add the image panel to the head panel
        headPanel.add(imagePanel, BorderLayout.CENTER);

        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); // Vertical layout
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // Padding

        // Create components
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align


        JLabel infoLabel = new JLabel("Please enter user as: YYMMDDXXXX");
        infoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        usernameField.setMaximumSize(new Dimension(400, 30)); // Set size for text field
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align

        JPasswordField passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(400, 30)); // Set size for password field
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align

        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);



        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the button
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);



        // Add components to the content panel
        contentPanel.add(Box.createVerticalStrut(20)); // Spacer
        contentPanel.add(infoLabel);
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
        contentPanel.add(Box.createVerticalStrut(50)); // Spacer
        contentPanel.add(errorLabel);


        // Add content panel to the frame
        frame.add(contentPanel, BorderLayout.CENTER);

        // Set frame visibility
        frame.setVisible(true);

        loginButton.addActionListener(e -> {
            usernameCheck();
        });
    }


    public static void usernameCheck() {

        if (usernameField.getText().matches(".*[!@#$%&*()_+=|<>?{}~].*") ||
                usernameField.getText().contains(" ") ||
                usernameField.getText().matches(".*[a-zA-Z].*") ||
                (usernameField.getText().length() != 10 && !usernameField.getText().contains("-"))
        ) {

            errorLabel.setText("Invalid username...");
            errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            errorLabel.setVisible(true);

        } else if (usernameField.getText().length() == 10) {

            System.out.println("Valid username");
            errorLabel.setVisible(false);

        } else if (usernameField.getText().length() == 11 && usernameField.getText().contains("-")) {

            String validUsername;
            validUsername = usernameField.getText().replace("-", "");
            System.out.println(validUsername);

        }
    }
}



