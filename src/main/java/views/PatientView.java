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
import controllers.*;

public class PatientView {
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
    private JButton submitButton;
    private JComboBox<String> roleBox;
    private JPanel columnPanel;
    private JLabel searchLabel;
    private JTextField searchText;
    private JButton button;
    private JButton newUser;
    private JPanel searchPanel;
    private JTextField passwordField;
    private JTextField firstNameField;
    private JTextField surnameField;
    private JTextField userIDField;
    private JTextField emailField;
    private PatientViewController patientController;
    private UserServices userServices;

    public PatientView() {

        userServices = new UserServices();

        initializeAll();
        addUserInterface();
        startListeners();

        patientController = new PatientViewController(this);

        patientController.populateComboBox();

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
        roleBox = new JComboBox<>();
        firstNameField = new JTextField(15);
        surnameField = new JTextField(15);
        userIDField = new JTextField(15);
        passwordField = new JTextField(15);
        emailField = new JTextField(15);
        submitButton = new JButton("Submit");


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


        submitButton.addActionListener(zu -> {
            String name = firstNameField.getText();
            String surname = surnameField.getText();
            String personnummer = userIDField.getText();
            String password = passwordField.getText();
            String email = emailField.getText();
            String role = roleBox.getSelectedItem().toString();

            String validationMessage = patientController.inputFromUser(name, surname, personnummer, password, email, role);

            // Returns valid = it's ready to be shipped to database.

            if (validationMessage.equals("Valid")) {

            } else {

                // If not valid, it will inform the user what is wrong with the input.
                // Allows user to correct mistakes & try again.
                showErrorMessage(validationMessage);
            }
        });

    }

    private void populatePatient(String selectedItem) {
        User user = userServices.getPatientDetails(selectedItem);

        if (user != null) {
            nameLabel.setText(user.getName());
            userIDLabel.setText(String.valueOf(user.getUserID())); //Shit solution
            emailLabel.setText(user.getEmail());
            roleLabel.setText(user.getRole());
        } else {
            //Hi
        }



    }


    // Improved & connected to controller now.
    public void populateComboBox(ArrayList<String> patients) {
        comboBox.removeAllItems();
        for (String userInfo : patients) {
            comboBox.addItem(userInfo);
        }
    }


    public void showErrorMessage(String errorMessage) {
        this.errorMessage.setText(errorMessage);
        this.errorMessage.setFont(new Font("Arial", Font.PLAIN, 15));
        this.errorMessage.setVisible(true);
    }

    private void initializeFrame() {
        frame = new JFrame("Patient page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 750);
        frame.setLayout(new BorderLayout());
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
        logout.addActionListener(e -> {
            // Clear the current user session
            CreateViews.getInstance().getLoginView().logoutCurrentUser();

            // Show a logout successful message
            JOptionPane.showMessageDialog(frame, "Logout successful!");

            // Redirect to the login screen
            frame.setVisible(false);
            CreateViews.getInstance().getLoginView().setVisible(true);
        });
        profileButton.addActionListener(e -> profileMenu.show(profileButton, 0, profileButton.getHeight()));
        headPanel.add(profileButton);
        headPanel.setVisible(true);
    }
    private JPanel createBreadcrumbs() {
        return ViewUtils.createBreadcrumbs(frame);
    }
    private void createPopup(JFrame frame) {


        popup = new JDialog(frame, "Create new user", true); // Use the field, not a local variable
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

        setRoleBox();

        gbc.gridx = 0; gbc.gridy = 1;

        contentPanel.add(new JLabel("First name"), gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        contentPanel.add(firstNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        contentPanel.add(new JLabel("Surname"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        contentPanel.add(surnameField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        contentPanel.add(new JLabel("Personnummer"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        contentPanel.add(userIDField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        contentPanel.add(new JLabel("Password"), gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        contentPanel.add(passwordField, gbc);


        gbc.gridx = 0; gbc.gridy = 5;
        contentPanel.add(new JLabel("Email"), gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        contentPanel.add(emailField, gbc);


        gbc.gridx = 0; gbc.gridy = 6;
        contentPanel.add(new JLabel("Role"), gbc);
        gbc.gridx = 1; gbc.gridy = 6;


        contentPanel.add(roleBox, gbc);

        JPanel buttonPanel = new JPanel();
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        // Logic for checking before shipping user to database.

        gbc.gridx = 0; gbc.gridy = 8;
        gbc.gridwidth = 2; // Span across both columns
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(errorMessage, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2; // Span across both columns
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(buttonPanel, gbc);

        popup.add(contentPanel);

        cancelButton.addActionListener(e -> popup.dispose());

        popup.setVisible(true);
    }

    private void setRoleBox() {

        roleBox.addItem("patient");
        roleBox.addItem("doctor");
        roleBox.addItem("secretary");
    }
    private void addUserInterface() {

        initializeFrame();
        createHeader();
        createBreadcrumbs();
        createSearchPanel();
        createUserPanel();

        frame.setVisible(true);

    }


    private void createSearchPanel() {
        searchPanel.setPreferredSize(new Dimension(1200, 100));
        searchPanel.setBackground(Color.white);


        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        searchPanel.setLayout(layout);


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Adding some padding around components
        gbc.anchor = GridBagConstraints.WEST;
        searchPanel.add(searchLabel, gbc);
        searchPanel.setVisible(true);


        gbc.gridx = 1;
        gbc.gridy = 0;
        searchPanel.add(searchText, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        searchPanel.add(comboBox, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        searchPanel.add(button, gbc);

        gbc.gridx = 6;
        gbc.gridy = 0;
        searchPanel.add(newUser, gbc);


        frame.add(searchPanel, BorderLayout.CENTER);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    private JPanel createUserPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1200, 120));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        // Create the big labels (first name+surname)
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(nameLabel, gbc);

        userIDLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        userIDLabel.setForeground(Color.BLACK);
        userIDLabel.setHorizontalAlignment(JLabel.LEFT);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(userIDLabel, gbc);

        emailLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(emailLabel, gbc);

        roleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(roleLabel, gbc);

        panel.setMaximumSize(new Dimension(1200, 100));


        return panel;
    }


    public void checkInput(String input) {


        if (input.matches(".*[a-zA-Z].*") && (input.matches(".*\\d.*"))) {

            letterCheck(input);

        } else if (input.matches(".*[a-zA-Z]")) {

            letterCheck(input);

        } else if (input.matches(".*\\d.*")) {

            numberCheck(input);

        } else if (input.matches("")) {
            patientController.populateComboBox();
            comboBox.hidePopup();
        }
    }

    public void letterCheck(String input) {

        ArrayList<String> matches = new ArrayList<>();
        ArrayList<String> nameAndId = userServices.extractBoth();

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
        ArrayList<String> nameAndId = userServices.extractBoth();

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



