package views;

import javax.swing.*;
import java.awt.*;

public class HomeView {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Home Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 750);
        frame.setLayout(new BorderLayout()); // Use BorderLayout for structured layout
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Header panel
        JPanel headPanel = new JPanel();
        headPanel.setPreferredSize(new Dimension(frame.getWidth(), 125));  // Set the header height
        headPanel.setLayout(new BorderLayout()); // Make it easier to add the image
        frame.add(headPanel, BorderLayout.NORTH); // Add header panel to the top

        // Load image into an ImageIcon
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

        // New column panel (outer panel)
        JPanel columnPanel = new JPanel();
        columnPanel.setPreferredSize(new Dimension(1200, 100)); // Set the size of the column
        columnPanel.setBackground(Color.LIGHT_GRAY); // Set background color for visibility
        columnPanel.setLayout(new GridBagLayout()); // Center inner panel in the middle

        // Inner panel for breadcrumbs
        JPanel breadcrumbPanel = new JPanel();
        breadcrumbPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0)); // Center breadcrumbs horizontally
        breadcrumbPanel.setBackground(Color.WHITE); // Set background for the breadcrumb panel
        breadcrumbPanel.setPreferredSize(new Dimension(600, 50)); // Set fixed size for the breadcrumb panel

        // Add breadcrumbs to the inner panel
        String[] breadcrumbTitles = {"Hem", "Lediga Tider", "Recept", "Patienter"};
        for (String title : breadcrumbTitles) {
            JLabel breadcrumb = new JLabel(title);
            breadcrumb.setFont(new Font("Arial", Font.PLAIN, 18)); // Set font style and size
            breadcrumbPanel.add(breadcrumb);
        }

        // Add the inner breadcrumb panel to the outer column panel
        columnPanel.add(breadcrumbPanel);

        // Add the column panel to the frame
        frame.add(columnPanel, BorderLayout.CENTER); // Place it under the header in the center

        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); // Vertical layout
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // Padding
        contentPanel.setPreferredSize(new Dimension(1200, 525)); // Adjust height to fit below the new column
        frame.add(contentPanel, BorderLayout.SOUTH); // Place it below the new column

        // Set frame visibility
        frame.setVisible(true);
    }
}