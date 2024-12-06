package views;

import models.User;
import models.UserRepository;

import javax.swing.*;
import java.awt.*;

public class HomeView {
    private JFrame frame;
    private JPanel headPanel;
    private JPanel columnPanel;
    private JPanel contentPanel;

    public HomeView() {
        initializeFrame();
        createHeader();
        createBreadcrumbs();
        createMainContent();
        frame.setVisible(true);
    }

    private void initializeFrame() {
        frame = new JFrame("Home Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 750);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    private void createHeader() {
        headPanel = new JPanel(null);
        headPanel.setPreferredSize(new Dimension(frame.getWidth(), 125));
        frame.add(headPanel, BorderLayout.NORTH);

        ImageIcon headerImage = new ImageIcon("src/main/resources/Namnlös.png");
        Image image = headerImage.getImage();

        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        imagePanel.setBounds(0, 0, 1200, 125);
        headPanel.add(imagePanel);

        JButton profileButton = new JButton();
        profileButton.setBounds(1090, 0, 110, 125);
        profileButton.setOpaque(false);
        profileButton.setContentAreaFilled(false);
        profileButton.setBorderPainted(false);
        profileButton.setFocusPainted(false);
        profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPopupMenu profileMenu = new JPopupMenu();
        JMenuItem myAccount = new JMenuItem("My Account");
        JMenuItem logout = new JMenuItem("Logout");
        myAccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        profileMenu.add(myAccount);
        profileMenu.add(logout);

        myAccount.addActionListener(e -> {
            int userID = LoginView.getCurrentUser();
            User user = UserRepository.getUserByID(userID);
            if (user != null) {
                JOptionPane.showMessageDialog(frame, "User Info: " + user.toString());
            } else {
                JOptionPane.showMessageDialog(frame, "User not found!");
            }
        });
        logout.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Logged out!"));

        profileButton.addActionListener(e -> profileMenu.show(profileButton, 0, profileButton.getHeight()));
        headPanel.add(profileButton);
    }

    private void createBreadcrumbs() {
        columnPanel = new JPanel();
        columnPanel.setPreferredSize(new Dimension(1200, 100));
        columnPanel.setBackground(Color.LIGHT_GRAY);
        columnPanel.setLayout(new GridBagLayout());

        JPanel breadcrumbPanel = new JPanel(new GridBagLayout());
        breadcrumbPanel.setBackground(Color.WHITE);
        breadcrumbPanel.setPreferredSize(new Dimension(800, 50)); // Adjusted width for four boxes

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Updated breadcrumb titles
        // TODO: Think how we implement this, currently we can not change views from the breadcrumb
        String[] breadcrumbTitles = {"Hem", "Lediga Tider", "Recept", "Patienter"};
        for (String title : breadcrumbTitles) {
            if (title.equals("Hem")) {
                JLabel activeLabel = new JLabel(title, SwingConstants.CENTER);
                activeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                activeLabel.setOpaque(true);
                activeLabel.setBackground(new Color(173, 216, 230)); // Light blue background for active
                activeLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                activeLabel.setPreferredSize(new Dimension(150, 40)); // Adjusted width
                breadcrumbPanel.add(activeLabel, gbc);
            } else {
                JButton breadcrumb = new JButton(title);
                breadcrumb.setFont(new Font("Arial", Font.PLAIN, 18));
                breadcrumb.setFocusPainted(false);
                breadcrumb.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                breadcrumb.setBackground(Color.WHITE);
                breadcrumb.setPreferredSize(new Dimension(150, 40)); // Adjusted width
                breadcrumb.setCursor(new Cursor(Cursor.HAND_CURSOR));

                if (title.equals("Recept")) {
                    breadcrumb.addActionListener(e -> {
                        JOptionPane.showMessageDialog(frame, "Recept button clicked!");
                    });
                }

                if (title.equals("Patienter")) {
                    breadcrumb.addActionListener(e -> {
                        JOptionPane.showMessageDialog(frame, "Recept button clicked!");
                    });
                }

                breadcrumbPanel.add(breadcrumb, gbc);

            }
            gbc.gridx++;
        }

        columnPanel.add(breadcrumbPanel);
        frame.add(columnPanel, BorderLayout.CENTER);
    }


    private void createMainContent() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setPreferredSize(new Dimension(1200, 525));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 200));
        contentPanel.setBackground(Color.WHITE);

        // Example of a green panel for content
        JPanel greenPanel = new JPanel();
        greenPanel.setPreferredSize(new Dimension(1100, 150)); // Adjust width to fit content
        greenPanel.setBackground(Color.DARK_GRAY);
        contentPanel.add(greenPanel);

        frame.add(contentPanel, BorderLayout.SOUTH);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

}