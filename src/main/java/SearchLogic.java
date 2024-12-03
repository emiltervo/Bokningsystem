import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class SearchLogic {

    private static JComboBox<String> comboBox;
    private static JTextField searchText; //Search text box connected to comboBox.
    private static JLabel label;
    private static JButton okButton;

    private static JLabel firstNameLabel;
    private static final List<List<String>> patientList = new ArrayList<>();
    public static void main(String[] args) {
        JFrame frame = new JFrame("Search Logic");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 750);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false); // No resizing - no scaling issues
        frame.setLocationRelativeTo(null); // Pops up in the center of screen

        okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(80, 30));


        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // FlowLayout for alignment
        JLabel searchLabel = new JLabel("Sök");
        searchText = new JTextField(25);
        searchText.setPreferredSize(new Dimension(250, 30));
        searchText.setBorder(new EmptyBorder(5, 10, 5, 10));

        label = new JLabel("", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setForeground(Color.RED);
        label.setPreferredSize(new Dimension(100, 30));

        comboBox = new JComboBox<>();
        comboBox.setPreferredSize(new Dimension(250, 30));
        comboBox.setForeground(Color.black);
        comboBox.addItem("");

        topPanel.add(searchLabel);
        topPanel.add(searchText);
        topPanel.add(comboBox);
        topPanel.add(okButton);
        topPanel.add(label);

        firstNameLabel = new JLabel("");
        firstNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));



        topPanel.add(firstNameLabel);

        frame.add(topPanel, BorderLayout.NORTH);

        frame.setVisible(true);

        okButton.addActionListener(abc -> {
            populatePatient();
        });

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




}
    public static List<List<String>> docuRead(String path){

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

    public static void updateComboBox(List<String> matches) {
        comboBox.removeAllItems();

        if (matches.isEmpty()) {
            label.setText("No result");
            label.setVisible(true);
        } else {
            for (String x : matches) {
                comboBox.addItem(x);
            }

        }
    }

    public static void searchBoxChecker(String input){

        // Checks if the input is a string or int & sends it forward...

        label.setText(null);

        if (input.matches(".*[a-zA-Z].*") && (input.matches(".*\\d.*"))) {

            System.out.println("Both letters & numbers");

        }

        else if (input.matches((".*[a-zA-Z].*"))) {

            System.out.println("Only letters. Should search in index for first & last surname");

            letterSearch(input,patientList);

        } else if (input.matches(".*\\d.*")) {

            System.out.println("Only numbers. Should search in index for personnummer only.");

            numberSearch(input,patientList);
        }

    }

    public static void letterSearch(String letters, List<List<String>> patients) {
        List<String> matches = new ArrayList<>();

        for (List<String> patient : patients) {

            String firstName = patient.get(0).toLowerCase();
            String surName = patient.get(1).toLowerCase();

            if (firstName.startsWith(letters.toLowerCase()) || surName.startsWith(letters.toLowerCase())) {
                matches.add(patient.get(0) + " " + patient.get(1) + " [" + patient.get(2) + "]");
            }

        }

        updateComboBox(matches);
        comboBox.showPopup();

    }

    public static void numberSearch(String letters, List<List<String>> patients) {
        List<String> matches2 = new ArrayList<>();

        for (List<String> patient : patients) {

            String personnummer = patient.get(2);

            if (personnummer.startsWith(letters)){
                matches2.add(patient.get(0) + " " + patient.get(1) + " [" + personnummer + "]");
            }

        }

        updateComboBox(matches2);
        comboBox.showPopup();
    }

    private static void populatePatient() {
        String selectedPatient = (String) comboBox.getSelectedItem();
        firstNameLabel.setText(selectedPatient);


    }
}
