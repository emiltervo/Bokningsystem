import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CalendarView {
    private JFrame calendarFrame;
    private JPanel headPanel;
    private JPanel columnPanel;
    private JPanel contentPanel;
    private JPanel scheduleWrapper;

    // Private member variables for the labels, panels, etc.
    private JLabel bookingsTitle;
    private JLabel colorBox;
    private JLabel label;

    public CalendarView() {
        calendarFrame = new JFrame("Home Page");
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
        headPanel = new JPanel();
        headPanel.setPreferredSize(new Dimension(calendarFrame.getWidth(), 125));
        headPanel.setLayout(new BorderLayout());
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
        imagePanel.setPreferredSize(new Dimension(1200, 125));
        headPanel.add(imagePanel, BorderLayout.CENTER);
    }

    private void createBreadcrumbs() {
        columnPanel = new JPanel();
        columnPanel.setPreferredSize(new Dimension(1200, 100));
        columnPanel.setBackground(Color.LIGHT_GRAY);
        columnPanel.setLayout(new GridBagLayout());

        JPanel breadcrumbPanel = new JPanel();
        breadcrumbPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        breadcrumbPanel.setBackground(Color.WHITE);
        breadcrumbPanel.setPreferredSize(new Dimension(600, 50));

        String[] breadcrumbTitles = {"Hem", "Lediga Tider", "Recept", "Patienter"};
        for (String title : breadcrumbTitles) {
            JLabel breadcrumb = new JLabel(title);
            breadcrumb.setFont(new Font("Arial", Font.PLAIN, 18));
            breadcrumbPanel.add(breadcrumb);
        }

        columnPanel.add(breadcrumbPanel);
        calendarFrame.add(columnPanel, BorderLayout.CENTER);
    }

    private void createMainContent() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPanel.setPreferredSize(new Dimension(1200, 525));
        calendarFrame.add(contentPanel, BorderLayout.SOUTH);
    }

    private void createSchedule() {
        scheduleWrapper = new JPanel(new BorderLayout());
        scheduleWrapper.setPreferredSize(new Dimension(1200, 525));
        scheduleWrapper.setBorder(new EmptyBorder(20, 200, 0, 200));
        scheduleWrapper.setBackground(Color.DARK_GRAY);

        // Create the placeholder panel (red panel)
        JPanel placeholderPanel = createDatePanel();

        // Schedule components
        JPanel daysHeader = createDaysHeader();
        JPanel timeSlots = createTimeSlots();
        JPanel grid = createScheduleGrid();
        JPanel bookingsPanel = createBookingsPanel();

        JPanel timeAndGrid = new JPanel(new BorderLayout());
        timeAndGrid.add(timeSlots, BorderLayout.WEST);
        timeAndGrid.add(grid, BorderLayout.CENTER);
        timeAndGrid.add(bookingsPanel, BorderLayout.EAST);

        // Add components to the schedule wrapper
        scheduleWrapper.add(placeholderPanel, BorderLayout.NORTH); // Add the red panel at the top
        scheduleWrapper.add(daysHeader, BorderLayout.CENTER);
        scheduleWrapper.add(timeAndGrid, BorderLayout.SOUTH);


        calendarFrame.add(scheduleWrapper, BorderLayout.SOUTH);
    }

    private JPanel createDatePanel() {
    // Create a panel with a red background
    JPanel placeholderPanel = new JPanel();
    placeholderPanel.setPreferredSize(new Dimension(1200, 55)); // Adjust dimensions as necessary
    placeholderPanel.setBackground(Color.GRAY);

    // Create arrow buttons and week label
    JButton leftArrow = new JButton("<");
    JButton rightArrow = new JButton(">");
    JLabel weekLabel = new JLabel("Week", JLabel.LEFT);
    weekLabel.setFont(new Font("Arial", Font.BOLD, 16));
    weekLabel.setForeground(Color.WHITE); // White text for contrast

    // Create date range label
    JLabel dateRangeLabel = new JLabel("YYYY-MM-DD - YYYY-MM-DD", JLabel.CENTER);
    dateRangeLabel.setFont(new Font("Arial", Font.BOLD, 16));
    dateRangeLabel.setForeground(Color.WHITE); // White text for contrast

    // Add components to the panel
    placeholderPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 13));
    placeholderPanel.add(leftArrow);
    placeholderPanel.add(weekLabel);
    placeholderPanel.add(rightArrow);
    placeholderPanel.add(dateRangeLabel);

    return placeholderPanel;
}

    private JPanel createBookingsPanel() {
        JPanel bookingsPanel = new JPanel();
        bookingsPanel.setPreferredSize(new Dimension(160, 0)); // Width for the panel
        bookingsPanel.setLayout(new BoxLayout(bookingsPanel, BoxLayout.Y_AXIS));
        bookingsPanel.setBackground(Color.LIGHT_GRAY);
        bookingsPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // Title Label
        bookingsTitle = new JLabel("Bookings", JLabel.CENTER);
        bookingsTitle.setFont(new Font("Arial", Font.BOLD, 16));
        bookingsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookingsTitle.setBorder(new EmptyBorder(10, 0, 5, 0));

        // Create colored squares and labels for Available and Booked
        JPanel availablePanel = createStatusPanel("Available", Color.GREEN);
        JPanel bookedPanel = createStatusPanel("Booked", Color.RED);

        bookingsPanel.add(bookingsTitle);
        bookingsPanel.add(Box.createVerticalStrut(5)); // Space between elements
        bookingsPanel.add(availablePanel);
        bookingsPanel.add(bookedPanel);

        return bookingsPanel;
    }

    // This method creates a panel with a small colored square and text
    private JPanel createStatusPanel(String text, Color color) {
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));  // Left alignment and space between components

        // Create colored square
        colorBox = new JLabel();
        colorBox.setOpaque(true);
        colorBox.setBackground(color);
        colorBox.setPreferredSize(new Dimension(15, 15)); // Small square

        // Create text label
        label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));

        // Add the color box and label to the status panel
        statusPanel.add(colorBox);
        statusPanel.add(label);

        return statusPanel;
    }

    private JPanel createDaysHeader() {
        JPanel daysHeader = new JPanel(new GridLayout(1, 10));
        daysHeader.setPreferredSize(new Dimension(800, 0));
        daysHeader.setBackground(Color.WHITE);

       String[] days = {"", "Mon date", "Tue date", "Wed date", "Thu date", "Fri date", "Sat date", "Sun date", "", ""};
        for (int i = 0; i < days.length; i++) {
            JLabel dayLabel = new JLabel(days[i], JLabel.CENTER);
            dayLabel.setFont(new Font("Arial", Font.BOLD, 14));
            if (i == 0 || i == 8 || i == 9) {
                dayLabel.setBackground(Color.LIGHT_GRAY);  // Empty slot at the beginning
            }
            else {
                // Alternate row colors for days
                dayLabel.setBackground(i % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
            }
            dayLabel.setOpaque(true);
            daysHeader.add(dayLabel);
        }

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

    private JPanel createScheduleGrid() {
        JPanel grid = new JPanel(new GridLayout(10, 7)); // 7 columns, 10 rows
        grid.setPreferredSize(new Dimension(800, 400));

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 7; col++) {
                JPanel slot = new JPanel();
                slot.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                slot.setBackground(Color.WHITE);

                /* // Adding mouse listener for click events on the grid slots
                slot.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Placeholder event logic when a slot is clicked
                        // For example, show a dialog to book a time
                        JOptionPane.showMessageDialog(null, "You clicked on a slot. Book the time here!");

                        // Future: Here you can add your booking logic (e.g. open a booking form, or mark as booked)
                        // You can use a dialog to confirm the booking or show a date and time selection.
                    }
                }); */

                grid.add(slot);
            }
        }

        return grid;
    }


    public static void main(String[] args) {
        new CalendarView();
    }
}