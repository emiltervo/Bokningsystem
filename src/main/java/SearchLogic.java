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


public class SearchLogic {

    private static JComboBox<String> comboBox;
    private static JTextField searchText; //Search text box connected to comboBox.
    private static JLabel label;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 750);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false); // No resizing - no scaling issues
        frame.setLocationRelativeTo(null); // Pops up in the center of screen



        comboBox = new JComboBox<>();
        comboBox.setBounds(200, 200, 200, 200);
        comboBox.setForeground(Color.GREEN);
        comboBox.setEditable(true);
        comboBox.addItem("");
        frame.add(comboBox);

        label = new JLabel("", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 50));
        frame.add(label, BorderLayout.SOUTH);


        searchText = new JTextField();
        searchText.setBounds(200, 100, 200, 30);
        frame.add(searchText, BorderLayout.NORTH);

        searchText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchBoxChecker(searchText.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                searchBoxChecker(searchText.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                searchBoxChecker(searchText.getText());
            }
        });


       List<List<String>> test = docuRead("src/main/resources/patientlist.txt");
       populateComboBox(test);


        frame.setVisible(true);

}
    public static List<List<String>> docuRead(String path){

        List<List<String>> patientList = new ArrayList<>();

        try {

            //File taken in & processed

            File file = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            //Append to patientList for future use

            String liner;
            while((liner = reader.readLine()) !=null){
                String[] fullPatientInfo = liner.split(","); //Split on ,
                List<String> tempArray = new ArrayList<>();
                for (String q : fullPatientInfo) {
                    tempArray.add(q.trim()); //Removes spaces etc
                }

                patientList.add(tempArray);

            }

            return patientList;

        } catch(Exception lol) {
            System.out.println("It's fucked");
        }

        return patientList;
    }

    public static void populateComboBox(List<List<String>> patients) {
        for(List<String> x : patients) {
            String firstName = x.get(0);
            String surName = x.get(1);
            String personNummer = x.get(2);

            //Index-sensitive ^^^

            String toAppend = (firstName+' '+surName+' '+'['+personNummer+']');

            comboBox.addItem(toAppend);

        }
    }

    public static void searchBoxChecker(String input){

        // Checks if the input is a string or int & sends it forward...

        label.setText(null);

        if (input.matches(".*[a-zA-Z].*") && (input.matches(".*\\d.*"))) {

            System.out.println("Both letters & numbers");

            label.setText("Please only enter letters OR numbers");
            label.setVisible(true);

        }

        else if (input.matches((".*[a-zA-Z].*"))) {

            System.out.println("Only letters. Should search in index for first & last surname");

        } else if (input.matches(".*\\d.*")) {

            System.out.println("Only numbers. Should search in index for personnummer only.");
        }

    }

    public static void letterSearch(String letters, List<String> patients) {
        List<String> matches = new ArrayList<>();

        //TODO


    }
}
