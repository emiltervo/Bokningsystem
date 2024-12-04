import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CalendarView {
    private JFrame calendarFrame;
    private JPanel headPanel;
    private JPanel columnPanel;
    private JPanel contentPanel;
    private JPanel scheduleWrapper;
    private JPanel[][] slots;

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

        JPanel datePanel = createDatePanel();

        JPanel daysHeader = createDaysHeader();
        JPanel timeSlots = createTimeSlots();
        JPanel grid = createScheduleGrid();

        JPanel timeAndGrid = new JPanel(new BorderLayout());
        timeAndGrid.add(timeSlots, BorderLayout.WEST);
        timeAndGrid.add(grid, BorderLayout.CENTER);

        scheduleWrapper.add(datePanel, BorderLayout.NORTH);
        scheduleWrapper.add(daysHeader, BorderLayout.CENTER);
        scheduleWrapper.add(timeAndGrid, BorderLayout.SOUTH);

        calendarFrame.add(scheduleWrapper, BorderLayout.SOUTH);
    }

  private JPanel createDatePanel() {
    JPanel datePanel = new JPanel();
    datePanel.setPreferredSize(new Dimension(1200, 55));
    datePanel.setBackground(Color.GRAY);

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

    // Create doctor selection dropdown
    String[] doctors = {"Dr. Smith", "Dr. Johnson", "Dr. Williams"};
    JComboBox<String> doctorComboBox = new JComboBox<>(doctors);
    doctorComboBox.setPreferredSize(new Dimension(150, 25)); // Adjust size as necessary

    // Add components to the panel
    datePanel.setLayout(new GridLayout(1, 3, 0, 0));

    JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 13));
    leftPanel.setOpaque(false); // Make panel transparent
    leftPanel.add(leftArrow);
    leftPanel.add(weekLabel);
    leftPanel.add(rightArrow);

    JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 18));
    centerPanel.setOpaque(false); // Make panel transparent
    centerPanel.add(dateRangeLabel);

    JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 15));
    rightPanel.setOpaque(false); // Make panel transparent
    rightPanel.add(doctorComboBox);

    datePanel.add(leftPanel);
    datePanel.add(centerPanel);
    datePanel.add(rightPanel);

    return datePanel;
}

    private JPanel createDaysHeader() {
        JPanel daysHeader = new JPanel(new GridLayout(1, 8));
        daysHeader.setPreferredSize(new Dimension(1200, 0));
        daysHeader.setBackground(Color.WHITE);

        String[] days = {"", "Mon date", "Tue date", "Wed date", "Thu date", "Fri date"};
        for (int i = 0; i < days.length; i++) {
            JLabel dayLabel = new JLabel(days[i], JLabel.CENTER);
            dayLabel.setFont(new Font("Arial", Font.BOLD, 14));
            if (i == 0) {
                dayLabel.setBackground(Color.LIGHT_GRAY);  // Empty slot at the beginning
            } else {
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
        timeSlots.setPreferredSize(new Dimension(132, 0));

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
        JPanel grid = new JPanel(new GridLayout(10, 5)); // 7 columns, 10 rows
        grid.setPreferredSize(new Dimension(1200, 400));
        slots = new JPanel[10][5]; // Initialize the slots array

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 5; col++) {
                JPanel slot = new JPanel();
                slot.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                slot.setBackground(Color.WHITE);
                slots[row][col] = slot; // Store the slot in the array

                // Adding mouse listener for click events on the grid slots
                slot.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        boolean isSlotBooked = slot.getBackground().equals(Color.RED);
                        CalendarPopupView popup = new CalendarPopupView(isSlotBooked);
                        popup.getFrame().addWindowListener(new java.awt.event.WindowAdapter() {
                            @Override
                            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                                if (popup.isBooked()) {
                                    slot.setBackground(Color.RED); // Mark slot as booked
                                } else {
                                    slot.setBackground(Color.WHITE); // Mark slot as available
                                }
                            }
                        });
                    }
                });

                grid.add(slot);
            }
        }

        return grid;
    }

    public static void main(String[] args) {
        new CalendarView();
    }
}