package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import controllers.CalendarController;
import models.*;

public class CalendarView {

    private final JFrame calendarFrame;
    private JButton leftArrow;
    private JButton rightArrow;
    private JLabel weekLabel;
    private JPanel scheduleGrid;
    private JLabel dateRangeLabel;
    private JComboBox<String> patientDropdown;
    private List<Patient> patients;
    JComboBox<String> doctorDropdown;

    private LocalDate currentDate = LocalDate.now();
    private Map<String, Boolean> bookings = new HashMap<>();

    public CalendarView() {
        calendarFrame = new JFrame("Calendar Page");
        calendarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calendarFrame.setSize(1200, 750);
        calendarFrame.setLayout(new BorderLayout());
        calendarFrame.setResizable(false);
        calendarFrame.setLocationRelativeTo(null);

        createHeader();
        createBreadcrumbs();
        createMainContent();
        createSchedule();

        calendarFrame.setVisible(true);
    }

    private void createHeader() {
        JPanel headPanel = new JPanel(null);
        headPanel.setPreferredSize(new Dimension(calendarFrame.getWidth(), 125));
        calendarFrame.add(headPanel, BorderLayout.NORTH);

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

        myAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long userID = LoginView.getCurrentUser();
                User user = UserRepository.getUserByID(userID);
                if (user != null) {
                    JOptionPane.showMessageDialog(calendarFrame, "User Info: " + user.toString());
                } else {
                    JOptionPane.showMessageDialog(calendarFrame, "User not found!");
                }
            }
        });
        logout.addActionListener(e -> {
            // Clear the current user session
            CreateViews.getInstance().getLoginView().logoutCurrentUser();

            // Show a logout successful message
            JOptionPane.showMessageDialog(calendarFrame, "Logout successful!");

            // Redirect to the login screen
            calendarFrame.setVisible(false);
            CreateViews.getInstance().getLoginView().setVisible(true);
        });

        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profileMenu.show(profileButton, 0, profileButton.getHeight());
            }
        });
        headPanel.add(profileButton);

        // Add patient dropdown
        patients = UserRepository.getPatientList();
        patientDropdown = new JComboBox<>();
        for (Patient patient : patients) {
            patientDropdown.addItem(patient.getName() + " - " + patient.getUserID());
        }
        patientDropdown.setBounds(10, 10, 200, 25);
        patientDropdown.addActionListener(e -> updateScheduleForSelectedPatient());
        headPanel.add(patientDropdown);
    }

    private void updateScheduleForSelectedPatient() {
        // Logic to update the schedule based on the selected patient
        Patient selectedPatient = patients.get(patientDropdown.getSelectedIndex());
        // Fetch and display the schedule for the selected patient
    }

    private JPanel createBreadcrumbs() {
        return ViewUtils.createBreadcrumbs(calendarFrame);
    }

    private void createMainContent() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPanel.setPreferredSize(new Dimension(1200, 525));
        calendarFrame.add(contentPanel, BorderLayout.SOUTH);
    }

    private void createSchedule() {
        JPanel scheduleWrapper = new JPanel(new BorderLayout());
        scheduleWrapper.setPreferredSize(new Dimension(1200, 525));
        scheduleWrapper.setBorder(new EmptyBorder(20, 0, 0, 0));
        scheduleWrapper.setBackground(Color.DARK_GRAY);

        JPanel placeholderPanel = createDatePanel();
        JPanel daysHeader = createDaysHeader();
        JPanel timeSlots = createTimeSlots();
        LocalDate startOfWeek = currentDate.with(WeekFields.of(Locale.forLanguageTag("sv-SE")).dayOfWeek(), 1); // Monday
        JPanel grid = createScheduleGrid(startOfWeek);

        JPanel timeAndGrid = new JPanel(new BorderLayout());
        timeAndGrid.add(timeSlots, BorderLayout.WEST);
        timeAndGrid.add(grid, BorderLayout.CENTER);

        scheduleWrapper.add(placeholderPanel, BorderLayout.NORTH);
        scheduleWrapper.add(daysHeader, BorderLayout.CENTER);
        scheduleWrapper.add(timeAndGrid, BorderLayout.SOUTH);

        calendarFrame.add(scheduleWrapper, BorderLayout.SOUTH);
    }

    private JPanel createDaysHeader() {
        JPanel daysHeader = new JPanel(new BorderLayout());
        daysHeader.setPreferredSize(new Dimension(800, 30));
        daysHeader.setBackground(Color.WHITE);

        JPanel daysPanel = new JPanel(new GridLayout(1, 7)); // Change to 7 columns
        daysPanel.setBackground(Color.WHITE);

        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"}; // Include Saturday and Sunday
        for (String day : days) {
            JLabel dayLabel = new JLabel(day, JLabel.CENTER);
            dayLabel.setFont(new Font("Arial", Font.BOLD, 14));
            dayLabel.setOpaque(true);
            dayLabel.setBackground(Color.LIGHT_GRAY);
            daysPanel.add(dayLabel);
        }

        daysHeader.add(Box.createRigidArea(new Dimension(80, 0)), BorderLayout.WEST);
        daysHeader.add(daysPanel, BorderLayout.CENTER);

        return daysHeader;
    }

    private JPanel createTimeSlots() {
        JPanel timeSlots = new JPanel(new GridLayout(10, 1));
        timeSlots.setPreferredSize(new Dimension(80, 0));

        String[] times = {"8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM"};
        for (String time : times) {
            JLabel timeLabel = new JLabel(time, JLabel.CENTER);
            timeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            timeLabel.setBorder(new EmptyBorder(5, 5, 5, 10));
            timeSlots.add(timeLabel);
        }

        return timeSlots;
    }

    private JPanel createDatePanel() {
        JPanel placeholderPanel = new JPanel();
        placeholderPanel.setPreferredSize(new Dimension(1200, 55));
        placeholderPanel.setBackground(Color.GRAY);

        leftArrow = new JButton("<");
        rightArrow = new JButton(">");
        weekLabel = new JLabel("Week", JLabel.CENTER);
        weekLabel.setFont(new Font("Arial", Font.BOLD, 16));
        weekLabel.setForeground(Color.WHITE);

        dateRangeLabel = new JLabel("YYYY-MM-DD - YYYY-MM-DD", JLabel.RIGHT);
        dateRangeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        dateRangeLabel.setForeground(Color.WHITE);

        List<Doctor> doctors = UserRepository.getDoctorList();
        doctorDropdown = new JComboBox<>();
        for (User doctor : doctors) {
            doctorDropdown.addItem(doctor.getName());
        }
        doctorDropdown.setPreferredSize(new Dimension(200, 25));

        placeholderPanel.setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 13));
        leftPanel.setBackground(Color.GRAY);
        leftPanel.add(doctorDropdown);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 13));
        centerPanel.setBackground(Color.GRAY);
        centerPanel.add(leftArrow);
        centerPanel.add(weekLabel);
        centerPanel.add(rightArrow);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 13));
        rightPanel.setBackground(Color.GRAY);
        rightPanel.add(dateRangeLabel);

        placeholderPanel.add(leftPanel, BorderLayout.WEST);
        placeholderPanel.add(centerPanel, BorderLayout.CENTER);
        placeholderPanel.add(rightPanel, BorderLayout.EAST);

        return placeholderPanel;
    }

    private JPanel createScheduleGrid(LocalDate startOfWeek) {
        scheduleGrid = new JPanel(new GridLayout(10, 7)); // 7 columns for each day of the week
        scheduleGrid.setPreferredSize(new Dimension(800, 400));

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 7; col++) { // Iterate through each day of the week
                JPanel slot = new JPanel();
                slot.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

                slot.setName("Slot: Row " + row + ", Col " + col);

                scheduleGrid.add(slot);
            }
        }
        return scheduleGrid;
    }

    public static void main(String[] args) {
        CalendarView view = new CalendarView();
        CalendarController controller = new CalendarController(view);
    }

    public JButton getLeftArrow() {
        return leftArrow;
    }

    public JButton getRightArrow() {
        return rightArrow;
    }

    public JLabel getWeekLabel() {
        return weekLabel;
    }

    public JPanel getScheduleGrid() {
        return scheduleGrid;
    }

    public JLabel getDateRangeLabel() {
        return dateRangeLabel;
    }

    public void setVisible(boolean visible) {
        calendarFrame.setVisible(visible);
    }

    public JFrame getFrame() {
        return calendarFrame;
    }
    public JComboBox<String> getDoctorDropdown() {
        return doctorDropdown;
    }
}