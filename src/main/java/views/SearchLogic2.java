package views;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import models.*;
import static models.UserRepository.*;
import static models.UserRepository.*;

public class SearchLogic2 {
    private JComboBox<String> comboBox;
    private JDialog popup;
    private  JPanel contentPanel;
    private JLabel errorMessage;
    private JLabel nameLabel;
    private JLabel userIDLabel;
    private JLabel emailLabel;
    private JLabel roleLabel;
    private JFrame frame;
    private JPanel headPanel;
    private JPanel columnPanel;
    private JLabel searchLabel;
    private JTextField searchText;
    private JButton button;
    private JButton newUser;
    private JPanel searchPanel;

    public SearchLogic2() {

        initializeFrame();
        createHeader();
        initializeAll();
        startListeners();
        createBreadcrumbs();
        createSearchPanel();
        createUserPanel();

        initializeFrame();
        createHeader();
        createBreadcrumbs();
        createSearchPanel();
        framer();

        frame.setLayout(new FlowLayout());
        setVisible();

        getAllUsers();
        populateBox();

    }

    private void initializeAll() {

        comboBox = new JComboBox<>();
        popup = new JDialog();
        contentPanel = new JPanel();
        errorMessage = new JLabel();
        nameLabel = new JLabel("");
        userIDLabel = new JLabel("");
        emailLabel = new JLabel("");
        roleLabel = new JLabel("");
        frame = new JFrame("");
        headPanel = new JPanel();
        columnPanel = new JPanel();
        searchLabel = new JLabel("Sök");
        searchText = new JTextField(20);
        button = new JButton("Go");
        newUser = new JButton("New user");
        searchPanel = new JPanel();

    }

    private void startListeners() {
        searchText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkInput(searchText.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                checkInput(searchText.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                checkInput(searchText.getText());
            }
        });
        button.addActionListener(abc -> {
            populatePatient(comboBox.getSelectedItem().toString());
        });
        newUser.addActionListener(abc -> {
            createPopup(frame);
        });
    }

    public void populatePatient(String selectedItem) {
        ArrayList<User> users = UserRepository.getUserList();
        String[] parts = selectedItem.split(" ");
        String selectedID = parts[parts.length - 1];
        for(User who : users) {
            if (String.valueOf(who.getUserID()).equals(selectedID)) {
                nameLabel.setText(who.getName());

                String userID = String.valueOf(who.getUserID());
                userIDLabel.setText(userID);

                emailLabel.setText(who.getEmail());

                roleLabel.setText(who.getRole());

                return;
            }
        }
    }
    private void initializeFrame() {
        frame = new JFrame("Home Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 750);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
    private void createHeader() {
        headPanel = new JPanel(null);
        headPanel.setPreferredSize(new Dimension(frame.getWidth(), 125));
        frame.add(headPanel, BorderLayout.NORTH);

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

        myAccount.addActionListener(e -> {
            long userID = LoginView.getCurrentUser();
            User user = UserRepository.getUserByID(userID);
            if (user != null) {
                JOptionPane.showMessageDialog(frame, "User Info: " + user.toString());
            } else {
                JOptionPane.showMessageDialog(frame, "User not found!");
            }
        });
        logout.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Logged out!"));

        profileButton.addActionListener(e -> profileMenu.show(profileButton, 0, profileButton.getHeight()));
        headPanel.add(profileButton);
    }
    private void createBreadcrumbs() {
        columnPanel = new JPanel();
        columnPanel.setPreferredSize(new Dimension(1200, 50));
        columnPanel.setBackground(Color.white);
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
        for (String title : breadcrumbTitles) {
            if (title.equals("Home")) {
                JLabel activeLabel = new JLabel(title, SwingConstants.CENTER);
                activeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                activeLabel.setOpaque(true);
                activeLabel.setBackground(new Color(173, 216, 230)); // Light blue background for active
                activeLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                activeLabel.setPreferredSize(new Dimension(150, 40)); // Adjusted width
                breadcrumbPanel.add(activeLabel, gbc);
            } else {
                JButton breadcrumb = new JButton(title);
                breadcrumb.setFont(new Font("Arial", Font.PLAIN, 18));
                breadcrumb.setFocusPainted(false);
                breadcrumb.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                breadcrumb.setBackground(Color.WHITE);
                breadcrumb.setPreferredSize(new Dimension(150, 40)); // Adjusted width
                breadcrumb.setCursor(new Cursor(Cursor.HAND_CURSOR));


                breadcrumbPanel.add(breadcrumb, gbc);
            }
            gbc.gridx++;
        }

        columnPanel.add(breadcrumbPanel);
        frame.add(columnPanel, BorderLayout.CENTER);
    }
    private void createPopup(JFrame frame) {

        JDialog popup = new JDialog(frame, "Create new user", true);
        popup.setSize(400, 300);
        popup.setResizable(false);
        popup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


        popup.setLocationRelativeTo(frame);

        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10)); // Padding


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel headLabel = new JLabel();
        headLabel.setText("New user");
        headLabel.setFont(new Font("Content", Font.BOLD, 20));
        contentPanel.add(headLabel);

        gbc.gridx = 0; gbc.gridy = 1;

        contentPanel.add(new JLabel("First name"), gbc);
        contentPanel.setFont(new Font("Content", Font.BOLD, 20));

        gbc.gridx = 1; gbc.gridy = 1;
        JTextField firstNameField = new JTextField(15);
        contentPanel.add(firstNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        contentPanel.add(new JLabel("Surname"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        JTextField surnameField = new JTextField(15);
        contentPanel.add(surnameField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        contentPanel.add(new JLabel("Personnummer"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        JTextField userIDField = new JTextField(15);
        contentPanel.add(userIDField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        contentPanel.add(new JLabel("Password"), gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        JTextField passwordField = new JTextField(15);
        contentPanel.add(passwordField, gbc);


        gbc.gridx = 0; gbc.gridy = 5;
        contentPanel.add(new JLabel("Email"), gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        JTextField emailField = new JTextField(15);
        contentPanel.add(emailField, gbc);


        gbc.gridx = 0; gbc.gridy = 6;
        contentPanel.add(new JLabel("Role"), gbc);
        gbc.gridx = 1; gbc.gridy = 6;

        JComboBox roleBox = new JComboBox();
        roleBox.addItem("patient");
        roleBox.addItem("doctor");
        contentPanel.add(roleBox, gbc);

        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        errorMessage.setText("");
        errorMessage.setVisible(false);

        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2; // Span across both columns
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(errorMessage, gbc);


        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2; // Span across both columns
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(buttonPanel, gbc);

        submitButton.addActionListener(e -> {

            String name =  firstNameField.getText();
            String surname = surnameField.getText();
            String personnummer = userIDField.getText();
            String password = passwordField.getText();
            String email = emailField.getText();
            String role = roleBox.getSelectedItem().toString();

            isValid(name, surname, personnummer, password, email, role);

        });

        cancelButton.addActionListener(e -> popup.dispose());

        popup.add(contentPanel);

        popup.setVisible(true);
    }

    private static void shipNewUser(String name, String surname, String personnummer, String password, String email, String role) {
        ArrayList<String> userToShip = new ArrayList<>();

        userToShip.add(personnummer);
        userToShip.add(name + " " + surname);
        userToShip.add(password);
        userToShip.add(email);
        userToShip.add(role);

        System.out.println("Ready to add to database:" + " " + userToShip);
        addUser(userToShip);



    }

    private void isValid(String name, String surname, String personnummer, String password, String email, String role) {

        String validName = null;
        String validSurname = null;
        String validPersonnummer = null;
        String validPassword = null;
        String validEmail = null;
        String validRole = null;

        if (!name.isEmpty() && (name.matches("[a-zA-Z]+"))) {

            validName = name;
        }

        if (!surname.isEmpty() && (name.matches("[a-zA-Z]+"))) {

            validSurname = surname;
        }

        if ((personnummer.length() == 12) && (personnummer.matches(("\\d+")))) {

            validPersonnummer = personnummer;
        }

        if (email.contains("@")) {

            validEmail = email;
        }

        if (!name.isEmpty()) {

            validPassword = password;
        }

        if (!role.isEmpty()) {

            validRole = role;

        }

        if (validName != null && validSurname != null && validPersonnummer != null
                && validEmail != null && validRole != null && validPassword != null) {
            shipNewUser(validName, validSurname, validPersonnummer, validPassword, validEmail, validRole);
        } else {
            errorMessage.setText("Some entries are invalid. Try again!");
            errorMessage.setFont(new Font("Arial", Font.PLAIN, 15));
            errorMessage.setVisible(true);
        }
    }


    private void createSearchPanel() {
        searchPanel = new JPanel();
        searchPanel.setPreferredSize(new Dimension(1200, 100));
        searchPanel.setBackground(Color.white);

        // Use GridBagLayout for better component arrangement
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        searchPanel.setLayout(layout);

        // Adjust GridBagConstraints for proper spacing
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Adding some padding around components
        gbc.anchor = GridBagConstraints.WEST;
        searchPanel.add(searchLabel, gbc);

        // Set the text field at the next grid position
        gbc.gridx = 1;
        gbc.gridy = 0;
        searchPanel.add(searchText, gbc);

        // Set the combo box to the next grid position
        gbc.gridx = 2;
        gbc.gridy = 0;
        searchPanel.add(comboBox, gbc);

        // Set the button to the next grid position
        gbc.gridx = 3;
        gbc.gridy = 0;
        searchPanel.add(button, gbc);

        gbc.gridx = 6;
        gbc.gridy = 0;
        searchPanel.add(newUser, gbc);

        // Add the searchPanel to the frame
        frame.add(searchPanel, BorderLayout.CENTER);
    }

    private void setVisible() {
        frame.setVisible(true);
    }



    public void framer() {
        // Don't create a new frame; use the existing frame
        JPanel userPanel = createUserPanel();
        frame.add(userPanel);  // Add the user panel to the existing frame
        frame.revalidate();  // Ensure the frame updates its layout
        frame.repaint();  // Refresh the frame
    }

    private JPanel createUserPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1200, 120));
        panel.setLayout(new GridBagLayout());  // GridBagLayout allows for precise control over positioning
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);  // Add some padding around components

        // Create the big labels (first name, surname)
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        gbc.gridx = 0;  // Place the name label in the first column
        gbc.gridy = 0;  // Place the name label in the first row
        gbc.anchor = GridBagConstraints.WEST;  // Align to the left of the cell
        panel.add(nameLabel, gbc);

        // Create the user ID label and set a smaller font
        userIDLabel.setFont(new Font("Arial", Font.PLAIN, 14));  // Regular font for UserID
        userIDLabel.setForeground(Color.BLACK);  // Black color for text
        userIDLabel.setHorizontalAlignment(JLabel.LEFT);  // Align text to the left within the label
        gbc.gridx = 0;  // Place the user ID label under the name
        gbc.gridy = 1;  // Place it in the second row
        gbc.gridwidth = 2;  // Span across both columns (first name and surname)
        gbc.anchor = GridBagConstraints.WEST;  // Align to the left of the cell
        panel.add(userIDLabel, gbc);

        emailLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 0;  // Place the user ID label under the name
        gbc.gridy = 2;  // Place it in the second row
        gbc.gridwidth = 2;  // Span across both columns (first name and surname)
        gbc.anchor = GridBagConstraints.WEST;  // Align to the left of the cell
        panel.add(emailLabel, gbc);

        roleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 0;  // Place the user ID label under the name
        gbc.gridy = 3;  // Place it in the second row
        gbc.gridwidth = 2;  // Span across both columns (first name and surname)
        gbc.anchor = GridBagConstraints.WEST;  // Align to the left of the cell
        panel.add(roleLabel, gbc);
        // Ensure that the panel doesn't expand in width too much and forces left alignment
        panel.setMaximumSize(new Dimension(1200, 100));  // Set the max size for the panel

        // Return the constructed panel
        return panel;
    }

    private ArrayList<String> extractBoth() {
        ArrayList<User> users = getUserList();
        ArrayList<String> nameAndId = new ArrayList<>();

        for (User user : users) {
            String fullName = user.getName();
            long userID = user.getUserID();
            String userIDToString = Long.toString(userID);

            nameAndId.add(fullName + " " + userIDToString);
        }
        return nameAndId;

    }

    public void populateBox() {

        ArrayList<User> users = UserRepository.getUserList();

        for (User user : users) {

            String fullName = user.getName();
            long userID = user.getUserID();
            comboBox.addItem(fullName + " " + userID);

        }
    }


    public void checkInput(String input) {


        if (input.matches(".*[a-zA-Z].*") && (input.matches(".*\\d.*"))) {

            letterCheck(input);

        } else if (input.matches(".*[a-zA-Z]")) {

            letterCheck(input);

        } else if (input.matches(".*\\d.*")) {

            numberCheck(input);

        } else if (input.matches("")) {
            comboBox.removeAllItems();
            populateBox();
            comboBox.hidePopup();
        }
    }

    public void letterCheck(String input) {
        ArrayList<String> matches = new ArrayList<>();
        ArrayList<String> nameAndId = extractBoth();

        for (int abc = 0; abc < nameAndId.size(); abc++) {
            String fullNameAndId = nameAndId.get(abc);
            if (fullNameAndId.toLowerCase().contains(input.toLowerCase())) {
                matches.add(fullNameAndId);
            }
        }
        updateComboBox(matches);
    }

    public void numberCheck(String input) {
        ArrayList<String> matches = new ArrayList<>();
        ArrayList<String> nameAndId = extractBoth();

        for (String fullNameAndId : nameAndId) {
            String[] iWantTheID = fullNameAndId.split(" ");
            String userID = iWantTheID[iWantTheID.length - 1];

            if (userID.contains(input)) {
                matches.add(fullNameAndId);
            }
        }
        updateComboBox(matches);
    }

    public void updateComboBox(ArrayList<String> matching) {

        comboBox.removeAllItems();
        if (matching.isEmpty()) {
            System.out.println("No results");
        } else {
            for (String match : matching) {
                comboBox.addItem(match);
            }
            comboBox.showPopup();
        }
    }
}


