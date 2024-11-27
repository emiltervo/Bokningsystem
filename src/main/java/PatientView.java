import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PatientView {
    public static void main(String[] args) {
        //Main frame - STATIC
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 750);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false); //No resizing - no scaling issues
        frame.setLocationRelativeTo(null); //Pops up in the center of screen

        //Dropdown
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(200, 50, 150, 30);
        frame.add(comboBox);

        //Populate the list (from SQL) -> CONTROLLER
        try {
            // Change the path below to match the file's location on your desktop
            File file = new File("src/main/resources/patientlist.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            //Read the file line by line

            String line;
            while ((line = reader.readLine()) != null) {
                // Remove quotes and split by commas
                String[] patients = line.replace("\"", "").split(",\\s*");
                for (String patient : patients) {
                    comboBox.addItem(patient.trim()); // Add each patient to the dropdown
                }
            }
            reader.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error reading file: " + e.getMessage());
        }

        //Submit button
        JButton button = new JButton("Submit");
        button.setBounds(150, 120, 100, 30);
        frame.add(button);

        //Listener for button -> MOVE TO CONTROLLER
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }
}