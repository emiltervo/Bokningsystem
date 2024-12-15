package controllers;

import models.*;
import views.CalendarPopupView;
import views.CalendarView;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
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
        // Fetch all appointments from the repository
        ArrayList<Appointment> appointments = AppointmentRepository.getAllAppointments();
        // Mark slots as booked based on the fetched appointments
        for (Appointment appointment : appointments) {
            LocalDate appointmentDate = LocalDate.parse(appointment.getDate().substring(0, 10));
            Doctor doctor = UserRepository.getDoctorList().stream()
                    .filter(user -> user.getUserID() == appointment.getDocuserID())
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Doctor not found for appointment ID: " + appointment.getDocuserID()));
            if (isDateInCurrentWeek(appointmentDate)) {
                String key = generateBookingKey(appointmentDate, appointment.getStartTime(), doctor.getUserID());
                bookings.put(key, true);
            }
        }

        // Update the calendar view
        updateCalendarView();
    }

    private boolean isDateInCurrentWeek(LocalDate date) {
        WeekFields weekFields = WeekFields.of(Locale.forLanguageTag("sv-SE"));
        LocalDate startOfWeek = currentDate.with(weekFields.dayOfWeek(), 1); // Monday
        LocalDate endOfWeek = currentDate.with(weekFields.dayOfWeek(), 7);   // Sunday
        return !date.isBefore(startOfWeek) && !date.isAfter(endOfWeek);
    }

    private void updateCalendarView() {
        JPanel scheduleGrid = calendarView.getScheduleGrid();
        int row = 0;
        int col = 0;
        String selectedDoctor = (String) calendarView.getDoctorDropdown().getSelectedItem();
        long doctorId = getUserIdByName(selectedDoctor);
        String[] times = {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"};
        for (Component component : scheduleGrid.getComponents()) {
            if (component instanceof JPanel slot) {
                LocalDate startOfWeek = currentDate.with(WeekFields.of(Locale.forLanguageTag("sv-SE")).dayOfWeek(), 1); // Monday
                LocalDate specificDate = startOfWeek.plusDays(col);
                String key = generateBookingKey(specificDate, times[row], doctorId);
                boolean isBooked = bookings.getOrDefault(key, false);
                slot.setBackground(getSlotColor(isBooked));
                col++;
                if (col == 7) {
                    col = 0;
                    row++;
                }
            }
        }
    }

    private int getTimeSlotIndex(String time) {
        String[] times = {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"};
        for (int i = 0; i < times.length; i++) {
            if (times[i].equals(time)) {
                return i;
            }
        }
        return -1; // Not found
    }

    private int getDayIndex(int dayOfWeek) {
        // Map Monday (1) to 0, Tuesday (2) to 1, ..., Friday (5) to 4
        return dayOfWeek - 1;
    }

    private void addListeners() {
        calendarView.getLeftArrow().addActionListener(e -> changeWeek(-1)); // Navigate back
        calendarView.getRightArrow().addActionListener(e -> changeWeek(1)); // Navigate forward

        // Add listener to the doctor dropdown
        calendarView.getDoctorDropdown().addActionListener(e -> {
            String selectedDoctor = (String) calendarView.getDoctorDropdown().getSelectedItem();
            initializeData(selectedDoctor);
        });

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

        // Get the selected doctor from the dropdown
        String selectedDoctor = (String) calendarView.getDoctorDropdown().getSelectedItem();

        // Initialize data for the selected doctor
        initializeData(selectedDoctor);

        // Update the calendar view
        updateCalendarView();
    }

    private void handleSlotClick(JPanel slot) {
        // Determine the row and column of the slot
        final int row;
        final int col;
        JPanel scheduleGrid = calendarView.getScheduleGrid();
        int slotIndex = -1;
        for (int i = 0; i < scheduleGrid.getComponentCount(); i++) {
            if (scheduleGrid.getComponent(i) == slot) {
                slotIndex = i;
                break;
            }
        }
        row = slotIndex / 7; // Adjust for 7 columns
        col = slotIndex % 7; // Adjust for 7 columns

        // Get the selected doctor from the dropdown
        JComboBox<String> doctorDropdown = calendarView.getDoctorDropdown();
        String selectedDoctor = (String) doctorDropdown.getSelectedItem();

        // Initialize data for the selected doctor
        initializeData(selectedDoctor);

        long doctorId = getUserIdByName(selectedDoctor);

        // Generate the slot date
        String[] times = {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"};
        String time = times[row];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.forLanguageTag("sv-SE"));
        LocalDate slotDate = currentDate.with(WeekFields.of(Locale.forLanguageTag("sv-SE")).dayOfWeek(), col + 1); // Adjust for correct day
        String slotDateTimeString = slotDate + " " + time;
        LocalDateTime slotDateTime = LocalDateTime.parse(slotDateTimeString, formatter);

        // Get the list of patients
        ArrayList<Patient> patients = UserRepository.getPatientList();

        // Get the booking status for the slot
        Boolean isBooked = bookings.get(generateBookingKey(slotDate, time, doctorId));
        if (isBooked == null) {
            isBooked = false; // Default to false if not found
        }
        CalendarPopupView popup = new CalendarPopupView(calendarView.getFrame(), selectedDoctor, java.sql.Timestamp.valueOf(slotDateTime), patients, isBooked);
        popup.setVisible(true);

        // Wait for the popup to close
        popup.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                // Check if the booking was confirmed
                if (popup.isConfirmed()) {
                    // Generate the booking key
                    String key = generateBookingKey(currentDate, time, doctorId);
                    boolean isBooked = bookings.getOrDefault(key, false);
                    // Toggle the booking state
                    boolean newBookingState = popup.isBooked();
                    bookings.put(key, newBookingState);
                    slot.setBackground(getSlotColor(newBookingState));
                }
            }
        });
    }

    private Color getSlotColor(boolean isBooked) {
        // Determine the color based on the booking state
        if (isBooked) {
            return Color.RED; // Booked
        } else {
            return Color.GREEN; // Available to book
        }
    }

    private long getUserIdByName(String doctorName) {
        ArrayList<Doctor> doctors = UserRepository.getDoctorList();
        for (Doctor doctor : doctors) {
            if (doctor.getName().equals(doctorName)) {
                return doctor.getUserID();
            }
        }
        return -1; // Return -1 if the doctor is not found
    }

    private void initializeData(String selectedDoctor) {
        long doctorId = getUserIdByName(selectedDoctor);
        if (doctorId == -1) {
            // Handle the case where the doctor is not found
            JOptionPane.showMessageDialog(calendarView.getFrame(), "Doctor not found!");
            return;
        }

        // Fetch all appointments for the selected doctor from the repository
        ArrayList<Appointment> appointments = AppointmentRepository.getAllAppointmentsByUserID(doctorId);

        // Initialize the bookings grid (10 rows x 7 columns for the full week)
        bookings.clear();
        LocalDate startOfWeek = currentDate.with(WeekFields.of(Locale.forLanguageTag("sv-SE")).dayOfWeek(), 1); // Monday
        String[] times = {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"};
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 7; col++) {
                LocalDate specificDate = startOfWeek.plusDays(col);
                String key = generateBookingKey(specificDate, times[row], doctorId);
                bookings.put(key, false); // Default status is available
            }
        }

        // Mark slots as booked based on the fetched appointments
        for (Appointment appointment : appointments) {
            LocalDate appointmentDate = LocalDate.parse(appointment.getDate().substring(0, 10));
            if (isDateInCurrentWeek(appointmentDate)) {
                int row = getTimeSlotIndex(appointment.getStartTime());
                String key = generateBookingKey(appointmentDate, appointment.getStartTime(), doctorId);
                bookings.put(key, true);
            }
        }

        // Update the calendar view
        updateCalendarView();
    }

    private String generateBookingKey(LocalDate date, String time, long doctorId) {
        // Generate a unique key for each slot based on the date, time, and doctor ID
        return date.toString() + " " + time + " - " + doctorId;
    }
}