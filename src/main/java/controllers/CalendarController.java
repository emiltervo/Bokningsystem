package controllers;

import views.CalendarView;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class CalendarController {
    private CalendarView calendarView;
    private String[][] bookings; // Example: holds booking statuses for the grid
    private LocalDate currentDate;

    public CalendarController(CalendarView calendarView) {
        this.calendarView = calendarView;
        this.currentDate = LocalDate.now(); // Start with the current date

        // Initialize the week display
        updateWeekDisplay();

        // Initialize data (simulate bookings data)
        initializeData();

        // Add listeners to handle interactions
        addListeners();
    }

    private void updateWeekDisplay() {
        // Calculate the first and last day of the current week
        LocalDate startOfWeek = currentDate.with(WeekFields.of(Locale.getDefault()).dayOfWeek(), 1);
        LocalDate endOfWeek = currentDate.with(WeekFields.of(Locale.getDefault()).dayOfWeek(), 7);

        // Format dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateRange = startOfWeek.format(formatter) + " - " + endOfWeek.format(formatter);

        // Update labels in the view
        calendarView.getWeekLabel().setText("Week: " + startOfWeek.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear()));
        calendarView.getDateRangeLabel().setText(dateRange);
    }

    private void initializeData() {
        // Example: Initialize the bookings grid (10 rows x 5 columns for simplicity)
        bookings = new String[10][5];
        for (int row = 0; row < bookings.length; row++) {
            for (int col = 0; col < bookings[row].length; col++) {
                bookings[row][col] = "Available"; // Default status
            }
        }
        bookings[2][1] = "Booked"; // Example: A slot is booked
    }

    private void addListeners() {
        calendarView.getLeftArrow().addActionListener(e -> changeWeek(-1)); // Navigate back
        calendarView.getRightArrow().addActionListener(e -> changeWeek(1)); // Navigate forward
        // Add listeners to schedule grid slots
        JPanel scheduleGrid = calendarView.getScheduleGrid();
        for (Component component : scheduleGrid.getComponents()) {
            if (component instanceof JPanel) {
                JPanel slot = (JPanel) component;

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
    }

    private void handleSlotClick(JPanel slot) {
        // Example: Toggle slot background color to simulate booking
        Color currentColor = slot.getBackground();
        if (currentColor.equals(Color.WHITE)) {
            slot.setBackground(Color.RED); // Mark as booked
        } else if (currentColor.equals(Color.RED)) {
            slot.setBackground(Color.WHITE); // Mark as available
        }
    }
}
