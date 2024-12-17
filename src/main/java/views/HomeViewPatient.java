package views;

import controllers.HomeViewPatientController;
import models.UserRepository;
import models.Secretary;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HomeViewPatient {
    private JFrame frame;
    private JPanel headPanel;
    private JPanel columnPanel;
    private JPanel contentPanel;
    private HomeViewPatientController controller;

    public HomeViewPatient() {
        initializeFrame();
        createHeader();
        this.columnPanel = createBreadcrumbs();
        createMainContent();
        createContactInformationBox();
        frame.setVisible(true);
    }

    private void initializeFrame() {
        frame = new JFrame("HomeViewPatient");
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

        myAccount.addActionListener(e -> JOptionPane.showMessageDialog(frame, "My Account clicked!"));
        logout.addActionListener(e -> {
            // Clear the current user session
            CreateViews.getInstance().getLoginView().logoutCurrentUser();

            // Show a logout successful message
            JOptionPane.showMessageDialog(frame, "Logout successful!");

            // Redirect to the login screen
            frame.setVisible(false);
            CreateViews.getInstance().getLoginView().setVisible(true);
        });

        profileButton.addActionListener(e -> profileMenu.show(profileButton, 0, profileButton.getHeight()));
        headPanel.add(profileButton);
    }


        private JPanel createBreadcrumbs() {
            return ViewUtils.createBreadcrumbs(frame);
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

    private void createContactInformationBox() {
        JPanel contactInfoPanel = new JPanel();
        contactInfoPanel.setLayout(new BoxLayout(contactInfoPanel, BoxLayout.Y_AXIS));
        contactInfoPanel.setBorder(BorderFactory.createTitledBorder("Kontaktinformation"));
        contactInfoPanel.setBackground(Color.WHITE);

        ArrayList<Secretary> secretaries = UserRepository.getSecretaryList();
        for (Secretary secretary : secretaries) {
            JLabel emailLabel = new JLabel(secretary.getEmail());
            contactInfoPanel.add(emailLabel);
        }

        contentPanel.add(contactInfoPanel);
    }

    public void setController(HomeViewPatientController controller) {
        this.controller = controller;
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}