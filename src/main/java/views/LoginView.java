package views;
import javax.swing.*;
import java.awt.*;

public class LoginView {
    private JFrame frame;
    private final JTextField usernameField = new JTextField();
    private final JButton loginButton = new JButton("Login");
    private final JLabel errorLabel = new JLabel("");
    private final JPasswordField passwordField = new JPasswordField();
    protected static long currentUser;
    private IHandleLogin loginHandler;

    public static long getCurrentUser() {
        return currentUser;
    }

    public void setLoginHandler(IHandleLogin loginHandler) {
        this.loginHandler = loginHandler;
    }


    public void createUI() {
        frame = createFrame();
        JPanel headPanel = ViewUtils.createHeaderPanel(frame);
        frame.add(headPanel, BorderLayout.NORTH);
        JPanel contentPanel = createContentPanel();
        frame.add(contentPanel, BorderLayout.CENTER);
        frame.setVisible(true);


        loginButton.addActionListener(e -> {
            long userID = Long.parseLong(usernameField.getText());
            String password = new String(passwordField.getPassword());
            if (loginHandler.handleLogin(userID, password)) {
                setCurrentUser(userID);
            }
            System.out.println("Login button pressed");
        });
    }

    public JFrame createFrame() {
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 750);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        return frame;
    }



    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        addComponentsToContentPanel(contentPanel);
        return contentPanel;
    }

    private void addComponentsToContentPanel(JPanel contentPanel) {
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel infoLabel = new JLabel("Please enter user as: YYYYMMDDXXXX");
        infoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField.setMaximumSize(new Dimension(400, 30));
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setMaximumSize(new Dimension(400, 30));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(infoLabel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(usernameLabel);
        contentPanel.add(Box.createVerticalStrut(5));
        contentPanel.add(usernameField);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(passwordLabel);
        contentPanel.add(Box.createVerticalStrut(5));
        contentPanel.add(passwordField);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(loginButton);
        contentPanel.add(Box.createVerticalStrut(50));
        contentPanel.add(errorLabel);
    }

    public void showSuccessPatient() {
        JOptionPane.showMessageDialog(null, "Login successful PATIENT");
        System.out.print("Login Success");
        frame.setVisible(false); // Hide the LoginView
        HomeViewPatient homeViewPatient = CreateViews.getInstance().getHomeViewPatient();
        homeViewPatient.setVisible(true);
    }

    public void showSuccessAdmin() {
        JOptionPane.showMessageDialog(null, "Login successful ADMIN");
        System.out.print("Login Success");
        frame.setVisible(false); // Hide the LoginView
        HomeView homeView = CreateViews.getInstance().getHomeView();
        homeView.setVisible(true);
    }

    public void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    public void setCurrentUser(long  userID) {
        currentUser = userID;
    }

    public void logoutCurrentUser() {
        currentUser = 0;
        usernameField.setText(null);
        passwordField.setText(null);
    }


    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}