import java.util.List;
import java.util.ArrayList;

public class Doctor extends User{
    private boolean availability;
    private String specialty;
    private List<String> freetimes;

    public Doctor(int userID, String name, String password, String email, String specialty) {
        super(userID, name, password, email);
        this.specialty = specialty;
        this.freetimes = new ArrayList<>();
    }
    private void setAvailability(Boolean input){
        availability = input;
    }
    private boolean getAvailability() {
        return availability;
    }

    public List<String> getFreetimes() {
        return freetimes;
    }

    public void setFreetimes(String newFreeTime) {
        freetimes.add(newFreeTime);
    }

    public String getSpeciality() {
        return specialty;
    }

    public void setSpeciality(String speciality) {
        this.specialty = speciality;
    }
}
