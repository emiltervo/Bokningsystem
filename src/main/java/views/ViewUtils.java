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
        String [] breadcrumbTitlesPatient = {"Home Page", "Booked Times", "Journal"};

        if (frame.getTitle().equals("Home Page")) {
            createBreadCrumbsForViews(frame, columnPanel, breadcrumbPanel, gbc, breadcrumbTitles, "Home");
        } else if (frame.getTitle().equals("Calendar Page")) {
            createBreadCrumbsForViews(frame, columnPanel, breadcrumbPanel, gbc, breadcrumbTitles, "Calendar");
        } else if (frame.getTitle().equals("Recipe Page")) {
            createBreadCrumbsForViews(frame, columnPanel, breadcrumbPanel, gbc, breadcrumbTitles, "Recipes");
        } else if (frame.getTitle().equals("Patient page")) {
            createBreadCrumbsForViews(frame, columnPanel, breadcrumbPanel, gbc, breadcrumbTitles, "Patients");
        } else if(frame.getTitle().equals("HomeViewPatient")){
            createBreadCrumbsForViews(frame, columnPanel, breadcrumbPanel, gbc, breadcrumbTitlesPatient, "Home Page");
        } else if(frame.getTitle().equals("Recipe Patient Page")){
            createBreadCrumbsForViews(frame, columnPanel, breadcrumbPanel, gbc, breadcrumbTitlesPatient, "Journal");
        }
        else if(frame.getTitle().equals("BookedTimesPatientPage")){
            createBreadCrumbsForViews(frame, columnPanel, breadcrumbPanel, gbc, breadcrumbTitlesPatient, "Booked Times");
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
            if (title == "Home") {
                CreateViews.getInstance().getHomeView().setVisible(true);
                frame.setVisible(false);
            } else if (title == "Calendar") {
                CreateViews.getInstance().getCalendarView().setVisible(true);
                frame.setVisible(false);
            } else if (title == "Recipes") {
                CreateViews.getInstance().getRecipeView().setVisible(true);
                frame.setVisible(false);
            } else if (title == "Patients") {
                CreateViews.getInstance().getSearchLogicView().setVisible(true);
                frame.setVisible(false);
            } else if (title == "Journal") {
                CreateViews.getInstance().getRecipeViewPatient().setVisible(true);
                frame.setVisible(false);
            } else if (title == "Booked Times") {
                CreateViews.getInstance().getBookedTimesViewPatient().setVisible(true);
                frame.setVisible(false);
            } else if (title == "Home Page") {
                CreateViews.getInstance().getHomeViewPatient().setVisible(true);
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

    public static JPanel createHeaderPanel(JFrame frame) {
        JPanel headPanel = new JPanel();
        headPanel.setPreferredSize(new Dimension(frame.getWidth(), 125));
        headPanel.setLayout(new BorderLayout());
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
        return headPanel;
    }

    private static void createBreadCrumbsForViews(JFrame frame, JPanel columnPanel, JPanel breadcrumbPanel, GridBagConstraints gbc, String[] breadcrumbTitles, String ViewCalled) {
        if (ViewCalled.equals("Patients")) {
            for (String title : breadcrumbTitles) {
                if (title.equals(ViewCalled)) {
                    JLabel activeLabel = new JLabel(ViewCalled, SwingConstants.CENTER);
                    setActiveLabelFont(breadcrumbPanel, gbc, activeLabel);

                } else {
                    SetBreadcrumbButton(breadcrumbPanel, gbc, title, frame);
                    columnPanel.add(breadcrumbPanel);
                    frame.add(columnPanel, BorderLayout.CENTER);

                    gbc.gridx++;
                }
            }
        } else {
            for (String title : breadcrumbTitles) {

                if (title.equals(ViewCalled)) {
                    JLabel activeLabel = new JLabel(ViewCalled, SwingConstants.CENTER);
                    setActiveLabelFont(breadcrumbPanel, gbc, activeLabel);
                    gbc.gridx++;
                } else {
                    SetBreadcrumbButton(breadcrumbPanel, gbc, title, frame);
                    columnPanel.add(breadcrumbPanel);
                    frame.add(columnPanel, BorderLayout.CENTER);

                    gbc.gridx++;
                }
            }
        }


    }
}
