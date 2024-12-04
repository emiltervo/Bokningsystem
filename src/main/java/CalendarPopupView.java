import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CalendarPopupView {

    private JFrame calendarPopupFrame;
    private boolean isBooked;

    public CalendarPopupView() {
        isBooked = false; // Initial state: not booked

        calendarPopupFrame = new JFrame("Room Booking");
        calendarPopupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        calendarPopupFrame.setSize(500, 400);
        calendarPopupFrame.setLayout(new BorderLayout());
        calendarPopupFrame.setResizable(false);
        calendarPopupFrame.setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0; // Ensure components grow to fill space

        // Labels
        JLabel dateLabel = new JLabel("Date: Sat 7 YYYY-MM-DD");
        JLabel timeLabel = new JLabel("Time: X AM - Y AM");
        JLabel locationLabel = new JLabel("Room: EG-2515");
        JLabel doctorLabel = new JLabel("Doctor: Dr. Smith");
        JLabel ownTextLabel = new JLabel("Patient:");

        // Book and Unbook Buttons
        JButton bookButton = new JButton("Book");
        JButton unbookButton = new JButton("Unbook");

        unbookButton.setVisible(false); // Initially, Unbook button is hidden

        bookButton.addActionListener(e -> toggleBooking(bookButton, unbookButton));
        unbookButton.addActionListener(e -> toggleBooking(bookButton, unbookButton));

        // Layout Setup
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // Span across both columns
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
        gbc.gridwidth = 2; // Ensure buttons take up the full width

        // Center the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center align the buttons
        buttonPanel.add(bookButton);
        buttonPanel.add(unbookButton); // Add both buttons to the panel
        mainPanel.add(buttonPanel, gbc);

        // Add mainPanel to the frame
        calendarPopupFrame.add(mainPanel);
        calendarPopupFrame.setVisible(true);
    }

    private void toggleBooking(JButton bookButton, JButton unbookButton) {
        if (isBooked) {
            bookButton.setText("Book");
            unbookButton.setVisible(false); // Hide Unbook button
            isBooked = false;
        } else {
            bookButton.setText("Booked");
            unbookButton.setVisible(true); // Show Unbook button
            isBooked = true;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalendarPopupView::new);
    }
}