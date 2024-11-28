import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class PatientView {
    static List<String> patientList = new ArrayList<>();
    public static void main(String[] args) {
        // Main frame
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 750);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false); // No resizing - no scaling issues
        frame.setLocationRelativeTo(null); // Pops up in the center of screen

        // Dropdown box for patients
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(200, 200, 200, 200);
        comboBox.setForeground(Color.BLUE);
        frame.add(comboBox);



        // Populate the list (from file)
        try {
            File file = new File("src/main/resources/patientlist.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            comboBox.setEditable(true);
            comboBox.addItem(""); // First item slot is empty, to be able to search straight away

            String line;
            while ((line = reader.readLine()) != null) {
                String[] patients = line.split(",");
                for (String patient : patients) {
                    comboBox.addItem(patient); // Add each patient to the dropdown
                    patientList.add(patient);
                }
            }
            reader.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error reading file: " + e.getMessage());
        }

        // Add DocumentListener to the editable part of the JComboBox (JTextField)
        JTextField editor = (JTextField) comboBox.getEditor().getEditorComponent();
        editor.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // Called when text is inserted (e.g., user types something)
                textAreaListener(comboBox);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // Called when text is removed (e.g., user deletes something)
                textAreaListener(comboBox);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // This method is used for styled text (rarely used with plain text)
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    // Method to print the current text in the JComboBox's editable text field
    public static void textAreaListener(JComboBox<String> comboBox) {
        JTextField editor = (JTextField) comboBox.getEditor().getEditorComponent();
        String text = editor.getText();

        List<String> matchingPatients = new ArrayList<>();
        for (String patient : patientList) {
            if (patient.toLowerCase().contains(text.toLowerCase())) {
                matchingPatients.add(patient); // Add matching patient to list
            }
        }


    }
}