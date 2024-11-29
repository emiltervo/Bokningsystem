abstract public class User {
    // userID is personal number
    private int userID;
    private String name;
    private String password;
    private String email;


    public User(int userID, String name, String password, String email){
        this.userID = userID;
        this.name = name;
        this.password = password;
        this.email = email;

    }

    private void setUserID(int value) {
        userID = value;
    }
    
    private void setName (String input) {
        name = input;
    }
    private void setPassword(String input) {
        password = input;
    }
    private void setEmail(String input) {
        email = input;
    }
    private int getUserID() {
        return userID;
    }
    private String getName() {
        return name;
    }
    private String getPassword() {
        return password;
    }
    private String getEmail() {
        return email;
    }

}
