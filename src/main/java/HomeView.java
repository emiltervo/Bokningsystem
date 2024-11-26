import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class HomeView {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Sjukhus24 - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 750);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.GRAY); // Background for the whole frame
        frame.setLocationRelativeTo(null);

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(frame.getWidth(), 125));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setLayout(new BorderLayout());

        // Add a title and logo to the header
        JLabel titleLabel = new JLabel("Sjukhus24", JLabel.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        JLabel logoLabel = new JLabel(new ImageIcon("your-logo-path.png"), JLabel.RIGHT); // Add your logo here

        headerPanel.add(titleLabel, BorderLayout.WEST);
        // Uncomment this line to add a logo: headerPanel.add(logoLabel, BorderLayout.EAST);

        frame.add(headerPanel, BorderLayout.NORTH);

        // Login panel with rounded borders
        JPanel loginPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(240, 240, 240, 240));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };

        loginPanel.setPreferredSize(new Dimension(400, 300));
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setOpaque(false);

        // Add components to the login panel
        JLabel portalLabel = new JLabel("USER PORTAL");
        portalLabel.setFont(new Font("Arial", Font.BOLD, 24));
        portalLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel userLabel = new JLabel("USER:");
        JTextField userField = new JTextField(15);
        JLabel passLabel = new JLabel("PASS:");
        JPasswordField passField = new JPasswordField(15);
        JButton loginButton = new JButton("LOG IN");
        loginButton.setBackground(Color.LIGHT_GRAY);
        loginButton.setFocusPainted(false);

        // Layout for login panel components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding between components
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        loginPanel.add(portalLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0; gbc.gridy = 1;
        loginPanel.add(userLabel, gbc);

        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1; gbc.gridy = 1;
        loginPanel.add(userField, gbc);

        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0; gbc.gridy = 2;
        loginPanel.add(passLabel, gbc);

        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1; gbc.gridy = 2;
        loginPanel.add(passField, gbc);

        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0; gbc.gridy = 3;
        loginPanel.add(loginButton, gbc);

        // Center the login panel in the frame
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false); // Transparent background
        centerPanel.add(loginPanel);

        frame.add(centerPanel, BorderLayout.CENTER);

        // Set frame visibility
        frame.setVisible(true);
    }
}
