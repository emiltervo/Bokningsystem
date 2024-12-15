package views;

import models.Patient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.util.List;

public class CalendarPopupView extends JDialog {
    private boolean isBooked;
    private boolean confirmed;
    private JLabel doctorLabel;
    private JComboBox<Patient> patientComboBox;
    private JTextField searchField;

    public CalendarPopupView(JFrame parent, String doctor, String slotText, List<Patient> patients) {
        super(parent, "Room Booking", true);
        isBooked = false; // Default initial state

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

        JLabel slotLabel = new JLabel(slotText);
        slotLabel.setFont(new Font("Arial", Font.BOLD, 16));

        searchField = new JTextField(20);
        searchField.setToolTipText("Search for patients");

        patientComboBox = new JComboBox<>();
        for (Patient patient : patients) {
            patientComboBox.addItem(patient);
        }

        JButton bookButton = createRoundButton("Book", Color.GREEN, Color.BLACK);
        JButton unbookButton = createRoundButton("Unbook", Color.RED, Color.WHITE);
        
        if (isBooked) {
            bookButton.setVisible(false);
        }
        else {
            unbookButton.setVisible(false);
        }
        bookButton.addActionListener(e -> {
            confirmed = true;
            isBooked = true;
            dispose();
        });

        unbookButton.addActionListener(e -> {
            confirmed = true;
            isBooked = false;
            dispose();
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(doctorLabel, gbc);

        gbc.gridy = 1;
        mainPanel.add(slotLabel, gbc);

        gbc.gridy = 2;
        mainPanel.add(searchField, gbc);

        gbc.gridy = 3;
        mainPanel.add(patientComboBox, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 1;
        mainPanel.add(unbookButton, gbc);

        gbc.gridx = 1;
        mainPanel.add(bookButton, gbc);

        add(mainPanel);
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
        return isBooked;
    }

    public Patient getSelectedPatient() {
        return (Patient) patientComboBox.getSelectedItem();
    }
}