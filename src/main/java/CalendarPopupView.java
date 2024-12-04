import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CalendarPopupView {
    private JFrame calendarPopupFrame;
    private boolean isBooked;

    public CalendarPopupView(boolean initialState) {
        isBooked = initialState; // Set initial state based on parameter

        calendarPopupFrame = new JFrame("Room Booking");
        calendarPopupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        calendarPopupFrame.setSize(500, 400);
        calendarPopupFrame.setLayout(new BorderLayout());
        calendarPopupFrame.setResizable(false);
        calendarPopupFrame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;

        JLabel dateLabel = new JLabel("Date: Sat 7 YYYY-MM-DD");
        JLabel timeLabel = new JLabel("Time: X AM - Y AM");
        JLabel locationLabel = new JLabel("Room: EG-2515");
        JLabel doctorLabel = new JLabel("Doctor: Dr. Smith");
        JLabel ownTextLabel = new JLabel("Patient:");

        JButton bookButton = new JButton("Book");
        JButton unbookButton = new JButton("Unbook");

        unbookButton.setVisible(isBooked); // Show Unbook button if initially booked
        bookButton.setVisible(!isBooked); // Show Book button if not initially booked

        bookButton.addActionListener(e -> toggleBooking(bookButton, unbookButton));
        unbookButton.addActionListener(e -> toggleBooking(bookButton, unbookButton));

        // Layout Setup
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(dateLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(timeLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(locationLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(doctorLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(ownTextLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;

        // Center the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(bookButton);
        buttonPanel.add(unbookButton);
        mainPanel.add(buttonPanel, gbc);

        calendarPopupFrame.add(mainPanel);
        calendarPopupFrame.setVisible(true);
    }

    private void toggleBooking(JButton bookButton, JButton unbookButton) {
        if (isBooked) {
            bookButton.setText("Book");
            bookButton.setEnabled(true); // Enable Book button
            unbookButton.setVisible(false); // Hide Unbook button
            isBooked = false;
        } else {
            bookButton.setText("Booked");
            bookButton.setEnabled(false); // Disable Book button
            unbookButton.setVisible(true); // Show Unbook button
            isBooked = true;
        }
        calendarPopupFrame.dispose(); // Close the popup
    }

    public boolean isBooked() {
        return isBooked;
    }

    public JFrame getFrame() {
        return calendarPopupFrame;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalendarPopupView(false)); // Default to not booked
    }
}