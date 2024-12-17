package views;

import controllers.HomeViewController;
import models.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BookedTimesViewPatient {
    private JFrame frame;
    private JPanel headPanel;
    private JPanel columnPanel;
    private JPanel contentPanel;
    private HomeViewController controller;
    ArrayList<Appointment> appointmentListByCurrentUser = new ArrayList<>();

    public BookedTimesViewPatient() {
        initializeFrame();
        createHeader();
        this.columnPanel = createBreadcrumbs();
        createMainContent();
        frame.setVisible(true);
    }

    public void initializeFrame() {
        frame = new JFrame("BookedTimesPatientPage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 750);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    private void createHeader() {
        JPanel headPanel = new JPanel(null);
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
            long userID = LoginView.getCurrentUser();
            User user = UserRepository.getUserByID(userID);
            if (user != null) {
                JOptionPane.showMessageDialog(frame, "User Info: " + user.toString());
            } else {
                JOptionPane.showMessageDialog(frame, "User not found!");
            }
        });
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
        // Skapa huvudpanelen
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setPreferredSize(new Dimension(1200, 525));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 200));
        contentPanel.setBackground(Color.WHITE);

        JPanel grayPanel = new JPanel();
        grayPanel.setPreferredSize(new Dimension(1100, 150));
        grayPanel.setBackground(Color.WHITE);
        grayPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));

        JLabel test = new JLabel("" + "PRESS TO SEE BOOKED TIMES");
        test.setFont(new Font("Arial", Font.BOLD, 24));
        test.setForeground(Color.BLACK);
        grayPanel.add(test);

        // Add submit button
        JButton submitButton = new JButton("Get Booked Times");
        grayPanel.add(submitButton);

        StringBuilder message = new StringBuilder();
        for (Appointment appointment : appointmentListByCurrentUser) {
            message.append(appointment.toString()).append("\n");
        }


        // Add action listener to the button
        submitButton.addActionListener(e -> {
            appointmentListByCurrentUser.clear();
            appointmentListByCurrentUser = AppointmentRepository.getAllAppointmentsByUserID(LoginView.getCurrentUser());
            for (Appointment appointment : appointmentListByCurrentUser) {
                message.append(appointment.toString()).append("\n");
            }
            if (appointmentListByCurrentUser != null) {
                JOptionPane.showMessageDialog(frame,"Your Booked times:\n" + message);
                }
            else {
                JOptionPane.showMessageDialog(frame, "Recipe not found!\nPlease enter a valid personal number.");
            }

        });

        contentPanel.add(grayPanel);

        frame.add(contentPanel, BorderLayout.SOUTH);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
