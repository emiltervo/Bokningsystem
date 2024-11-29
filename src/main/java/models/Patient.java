package models;

public class Patient extends User {
    private String journal;
    public Patient(int userID, String name, String password, String email, String journal){
        super(userID, name, password, email);
        this.journal = journal;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }
}
