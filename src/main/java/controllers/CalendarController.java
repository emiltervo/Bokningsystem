package controllers;

import views.CalendarPopupView;
import views.CalendarView;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CalendarController {
    private final CalendarView calendarView;
    private LocalDate currentDate;
    private final Map<String, Boolean> bookings;

    public CalendarController(CalendarView calendarView) {
        this.calendarView = calendarView;
        this.currentDate = LocalDate.now(); // Start with the current date
        this.bookings = new HashMap<>();

        // Initialize the week display
        updateWeekDisplay();

        // Initialize data (simulate bookings data)
        initializeData();

        // Add listeners to handle interactions
        addListeners();
    }

    private void updateWeekDisplay() {
        // Use Sweden's locale to define the first day of the week as Monday
        WeekFields weekFields = WeekFields.of(Locale.forLanguageTag("sv-SE"));

        // Calculate the start and end of the current week
        LocalDate startOfWeek = currentDate.with(weekFields.dayOfWeek(), 1); // Monday
        LocalDate endOfWeek = currentDate.with(weekFields.dayOfWeek(), 7);   // Sunday

        // Format dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateRange = startOfWeek.format(formatter) + " - " + endOfWeek.format(formatter);

        // Update the labels in the view
        calendarView.getWeekLabel().setText("Week: " + startOfWeek.get(weekFields.weekOfWeekBasedYear()));
        calendarView.getDateRangeLabel().setText(dateRange);
    }

    private void initializeData() {
        // Example: Initialize the bookings grid (10 rows x 5 columns for simplicity)
        // Example: holds booking statuses for the grid
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 5; col++) {
                String key = generateBookingKey(currentDate, row, col);
                bookings.put(key, false); // Default status is available
            }
        }
    }

    private void addListeners() {
        calendarView.getLeftArrow().addActionListener(e -> changeWeek(-1)); // Navigate back
        calendarView.getRightArrow().addActionListener(e -> changeWeek(1)); // Navigate forward
        // Add listeners to schedule grid slots
        JPanel scheduleGrid = calendarView.getScheduleGrid();
        for (Component component : scheduleGrid.getComponents()) {
            if (component instanceof JPanel slot) {

                // Attach mouse listener
                slot.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        handleSlotClick(slot);
                    }
                });
            }
        }
    }

    private void changeWeek(int delta) {
        // Adjust the current date by delta weeks
        currentDate = currentDate.plusWeeks(delta);
        updateWeekDisplay();

        // Update the background color of all slots based on the booking data
        JPanel scheduleGrid = calendarView.getScheduleGrid();
        int row = 0;
        int col = 0;
        for (Component component : scheduleGrid.getComponents()) {
            if (component instanceof JPanel slot) {
                String key = generateBookingKey(currentDate, row, col);
                boolean isBooked = bookings.getOrDefault(key, false);
                slot.setBackground(getSlotColor(isBooked));
                col++;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }
    }

    private void handleSlotClick(JPanel slot) {
        // Determine the row and column of the slot
        int row = -1;
        int col = -1;
        JPanel scheduleGrid = calendarView.getScheduleGrid();
        for (int i = 0; i < scheduleGrid.getComponentCount(); i++) {
            if (scheduleGrid.getComponent(i) == slot) {
                row = i / 5;
                col = i % 5;
                break;
            }
        }

        // Generate the booking key
        String key = generateBookingKey(currentDate, row, col);
        boolean isBooked = bookings.getOrDefault(key, false);

        // Show the CalendarPopupView with the current booking state
        CalendarPopupView popup = new CalendarPopupView(isBooked);
        popup.setVisible(true);

        // Wait for the popup to close
        popup.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                // Check if the booking was confirmed
                if (popup.isConfirmed()) {
                    // Toggle the booking state
                    boolean newBookingState = !isBooked;
                    bookings.put(key, newBookingState);
                    slot.setBackground(getSlotColor(newBookingState));
                }
            }
        });
    }

    private String generateBookingKey(LocalDate date, int row, int col) {
        // Generate a unique key for each slot based on the date, row, and column
        return date.toString() + "-R" + row + "-C" + col;
    }

    private Color getSlotColor(boolean isBooked) {
        // Determine the color based on the booking state
        if (isBooked) {
            return Color.RED; // Booked
        } else {
            return Color.GREEN; // Available to book
        }
    }
}