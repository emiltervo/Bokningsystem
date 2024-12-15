package views;

import models.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CalendarPopupView extends JDialog {
    private boolean confirmed;
    private final JLabel doctorLabel;
    private final JComboBox<Patient> patientComboBox;
    private final Date slotDate;
    private final String doctor;
    private final BookingState bookingState;

    private static class BookingState {
        boolean isBooked;
    }

    public CalendarPopupView(JFrame parent, String doctor, Date slotDate, List<Patient> patients, boolean isBooked) {
        super(parent, "Room Booking", true);
        this.doctor = doctor;
        this.slotDate = slotDate;
        this.bookingState = new BookingState();
        this.bookingState.isBooked = isBooked;

        setTitle("Room Booking");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(parent);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;

        doctorLabel = new JLabel("Doctor: " + doctor);
        doctorLabel.setFont(new Font("Arial", Font.BOLD, 16));

        SimpleDateFormat slotFormat = new SimpleDateFormat("EEEE, yyyy-MM-dd HH:mm");
        JLabel slotLabel = new JLabel(slotFormat.format(slotDate));
        slotLabel.setFont(new Font("Arial", Font.BOLD, 16));

        patientComboBox = new JComboBox<>();
        for (Patient patient : patients) {
            patientComboBox.addItem(patient);
        }
        JButton bookButton = createRoundButton("Book", Color.GREEN, Color.BLACK);
        JButton unbookButton = createRoundButton("Unbook", Color.RED, Color.WHITE);
        // Show the button depending on the booking state
        if (isBooked) {
            bookButton.setVisible(false);
            patientComboBox.setEnabled(false);
        } else {
            unbookButton.setVisible(false);
        }
        bookButton.addActionListener(e -> {
            confirmed = true;
            bookingState.isBooked = true;
            makeAppointment();
            dispose();
        });

        unbookButton.addActionListener(e -> {
            confirmed = true;
            bookingState.isBooked = false;
            unBookPatient();
            dispose();
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(doctorLabel, gbc);

        gbc.gridy = 1;
        mainPanel.add(slotLabel, gbc);

        gbc.gridy = 3;
        mainPanel.add(patientComboBox, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 1;
        if (isBooked) {
            mainPanel.add(unbookButton, gbc);
            gbc.gridx = 1;
        }
        mainPanel.add(bookButton, gbc);

        add(mainPanel);
    }

    private void unBookPatient() {
        Doctor doctor = null;
        List<Doctor> doctors = UserRepository.getDoctorList();
        for (Doctor d : doctors) {
            if (d.getName().equals(this.doctor)) {
                doctor = d;
                break;
            }
        }
        if (doctor == null) {
            throw new IllegalStateException("Doctor not found");
        }
        Patient patient = (Patient) patientComboBox.getSelectedItem();
        assert patient != null;

        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = outputFormat.format(slotDate);

        // Extract the time part from slotDate
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String startTime = timeFormat.format(slotDate);

        AppointmentRepository.deleteAppointment(startTime, formattedDate, patient.getUserID(), doctor.getUserID());
    }

    private void makeAppointment() {
        Doctor doctor = null;
        List<Doctor> doctors = UserRepository.getDoctorList();
        for (Doctor d : doctors) {
            if (d.getName().equals(this.doctor)) {
                doctor = d;
                break;
            }
        }
        if (doctor == null) {
            throw new IllegalStateException("Doctor not found");
        }
        Patient patient = (Patient) patientComboBox.getSelectedItem();
        assert patient != null;

        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = outputFormat.format(slotDate);

        // Extract the time part from slotDate
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String startTime = timeFormat.format(slotDate);

        Appointment appointment = AppointmentFactory.createAppointment(startTime, formattedDate, patient.getUserID(), doctor.getUserID(), 60, 1);
        AppointmentRepository.insertAppointment(appointment);
    }

    private JButton createRoundButton(String text, Color background, Color foreground) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(background.darker());
                } else {
                    g.setColor(background);
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
            }

            @Override
            public void updateUI() {
                setUI(new BasicButtonUI());
            }
        };
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setPreferredSize(new Dimension(70, 40));
        return button;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public boolean isBooked() {
        return bookingState.isBooked;
    }

    public Patient getSelectedPatient() {
        return (Patient) patientComboBox.getSelectedItem();
    }

    public Date getSlotDate() {
        return slotDate;
    }

    public String getDoctor() {
        return doctor;
    }
}