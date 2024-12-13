package views;

import javax.swing.*;
import java.awt.*;

public class ViewUtils {
    public static JPanel createBreadcrumbs(JFrame frame) {
        JPanel columnPanel = new JPanel();
        columnPanel.setPreferredSize(new Dimension(1200, 100));
        columnPanel.setBackground(Color.LIGHT_GRAY);
        columnPanel.setLayout(new GridBagLayout());

        JPanel breadcrumbPanel = new JPanel(new GridBagLayout());
        breadcrumbPanel.setBackground(Color.WHITE);
        breadcrumbPanel.setPreferredSize(new Dimension(800, 50)); // Adjusted width for four boxes

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        String[] breadcrumbTitles = {"Home", "Calendar", "Recipes", "Patients"};
        if (frame.getTitle().equals("Home Page")) {
            JLabel activeLabel = new JLabel("Home", SwingConstants.CENTER);
            setActiveLabelFont(breadcrumbPanel, gbc, activeLabel);

            for (String title : breadcrumbTitles) {
                if (title == "Home") {
                    gbc.gridx++;
                } else {
                    SetBreadcrumbButton(breadcrumbPanel, gbc, title, frame);
                    columnPanel.add(breadcrumbPanel);
                    frame.add(columnPanel, BorderLayout.CENTER);

                    gbc.gridx++;
                }
            }


        } else if (frame.getTitle().equals("Calendar Page")) {


            for (String title : breadcrumbTitles) {
                if (title == "Calendar") {
                    JLabel activeLabel = new JLabel("Calendar", SwingConstants.CENTER);
                    setActiveLabelFont(breadcrumbPanel, gbc, activeLabel);
                    gbc.gridx++;
                } else {
                    SetBreadcrumbButton(breadcrumbPanel, gbc, title, frame);
                    columnPanel.add(breadcrumbPanel);
                    frame.add(columnPanel, BorderLayout.CENTER);
                    gbc.gridx++;
                }
            }

        } else if (frame.getTitle().equals("Recipes")) {
            JLabel activeLabel = new JLabel("Recipes", SwingConstants.CENTER);
            setActiveLabelFont(breadcrumbPanel, gbc, activeLabel);

            for (String title : breadcrumbTitles) {
                if (title == "Recipes") {
                    continue;
                } else {
                    SetBreadcrumbButton(breadcrumbPanel, gbc, title, frame);
                    columnPanel.add(breadcrumbPanel);
                    frame.add(columnPanel, BorderLayout.CENTER);
                    gbc.gridx++;
                }
            }
        } else if (frame.getTitle().equals("Patient page")) {

            for (String title : breadcrumbTitles) {
                if (title == "Patients") {
                    JLabel activeLabel = new JLabel("Patients", SwingConstants.CENTER);
                    setActiveLabelFont(breadcrumbPanel, gbc, activeLabel);
                } else {
                    SetBreadcrumbButton(breadcrumbPanel, gbc, title, frame);
                    columnPanel.add(breadcrumbPanel);
                    frame.add(columnPanel, BorderLayout.CENTER);
                    gbc.gridx++;
                }
            }
        }
        return columnPanel;
    }

    static void SetBreadcrumbButton(JPanel breadcrumbPanel, GridBagConstraints gbc, String title, JFrame frame) {
        JButton breadcrumb = new JButton(title);
        breadcrumb.setFont(new Font("Arial", Font.PLAIN, 18));
        breadcrumb.setFocusPainted(false);
        breadcrumb.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        breadcrumb.setBackground(Color.WHITE);
        breadcrumb.setPreferredSize(new Dimension(150, 40)); // Adjusted width
        breadcrumb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        breadcrumb.addActionListener(e -> {
            if(title == "Home") {
                CreateViews.getInstance().getHomeView().setVisible(true);
                frame.setVisible(false);
            }
            else if(title == "Calendar") {
                CreateViews.getInstance().getCalendarView().setVisible(true);
                frame.setVisible(false);
            }
            else if(title == "Recipes") {

            }
            else if(title == "Patients") {
                CreateViews.getInstance().getSearchLogicView().setVisible(true);
                frame.setVisible(false);
            }
        });


        breadcrumbPanel.add(breadcrumb, gbc);
    }

    static void setActiveLabelFont(JPanel breadcrumbPanel, GridBagConstraints gbc, JLabel activeLabel) {
        activeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        activeLabel.setOpaque(true);
        activeLabel.setBackground(new Color(173, 216, 230)); // Light blue background for active
        activeLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        activeLabel.setPreferredSize(new Dimension(150, 40)); // Adjusted width
        breadcrumbPanel.add(activeLabel, gbc);
    }


}
