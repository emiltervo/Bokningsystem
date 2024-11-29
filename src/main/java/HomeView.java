import javax.swing.*;
import java.awt.*;

public class HomeView {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Home Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 750);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Header panel
        JPanel headPanel = new JPanel(null); // Use null layout for manual positioning
        headPanel.setPreferredSize(new Dimension(frame.getWidth(), 125));
        frame.add(headPanel, BorderLayout.NORTH);

        // Load image into an ImageIcon
        ImageIcon headerImage = new ImageIcon("src/main/resources/Namnlös.png");
        Image image = headerImage.getImage();

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
        imagePanel.setBounds(0, 0, 1200, 125); // Place imagePanel to fit header
        headPanel.add(imagePanel);

        // Add clickable profile button in the top-right corner
        JButton profileButton = new JButton();
        profileButton.setBounds(1090, 0, 110, 125); // Set size and position for the button
        profileButton.setOpaque(false); // Make button transparent
        profileButton.setContentAreaFilled(false); // Remove background fill
        profileButton.setBorderPainted(false); // Remove border
        profileButton.setFocusPainted(false); // Remove focus rectangle
        profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand when hovered

        // Create the dropdown menu
        JPopupMenu profileMenu = new JPopupMenu();
        JMenuItem myAccount = new JMenuItem("My Account");
        JMenuItem logout = new JMenuItem("Logout");

        // Set hand cursor for menu items
        myAccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logout.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add menu items to the dropdown
        profileMenu.add(myAccount);
        profileMenu.add(logout);

        // Add action listeners to menu items
        myAccount.addActionListener(e -> JOptionPane.showMessageDialog(frame, "My Account clicked!"));
        logout.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Logged out!"));

        // Show the dropdown when the profile button is clicked
        profileButton.addActionListener(e -> profileMenu.show(profileButton, 0, profileButton.getHeight()));

        // Add the profile button on top of the header
        headPanel.add(profileButton);

        // New column panel (outer panel)
        JPanel columnPanel = new JPanel();
        columnPanel.setPreferredSize(new Dimension(1200, 100));
        columnPanel.setBackground(Color.LIGHT_GRAY);
        columnPanel.setLayout(new GridBagLayout());

        // Inner panel for breadcrumbs
        JPanel breadcrumbPanel = new JPanel(new GridBagLayout());
        breadcrumbPanel.setBackground(Color.WHITE);
        breadcrumbPanel.setPreferredSize(new Dimension(600, 50));

        // GridBagConstraints for centering
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Updated breadcrumb titles
        String[] breadcrumbTitles = {"Hem", "Boka Tid", "Mitt Recept"};
        for (int i = 0; i < breadcrumbTitles.length; i++) {
            if (breadcrumbTitles[i].equals("Hem")) {
                JLabel activeLabel = new JLabel(breadcrumbTitles[i], SwingConstants.CENTER);
                activeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                activeLabel.setOpaque(true);
                activeLabel.setBackground(new Color(173, 216, 230));
                activeLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                activeLabel.setPreferredSize(new Dimension(120, 40));
                breadcrumbPanel.add(activeLabel, gbc);
            } else {
                JButton breadcrumb = new JButton(breadcrumbTitles[i]);
                breadcrumb.setFont(new Font("Arial", Font.PLAIN, 18));
                breadcrumb.setFocusPainted(false);
                breadcrumb.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                breadcrumb.setBackground(Color.WHITE);
                breadcrumb.setPreferredSize(new Dimension(120, 40));
                breadcrumb.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand
                breadcrumbPanel.add(breadcrumb, gbc);
            }
            gbc.gridx++;
        }

        // Add the inner breadcrumb panel to the outer column panel
        columnPanel.add(breadcrumbPanel);

        // Add the column panel to the frame
        frame.add(columnPanel, BorderLayout.CENTER);

        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPanel.setPreferredSize(new Dimension(1200, 525));
        frame.add(contentPanel, BorderLayout.SOUTH);

        // Set frame visibility
        frame.setVisible(true);
    }
}
