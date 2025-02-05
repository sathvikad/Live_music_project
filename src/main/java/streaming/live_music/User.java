package streaming.live_music;

public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isManager;

    public User(String username, String password, String firstName, String lastName, boolean isManager) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isManager = isManager;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isManager() {
        return isManager;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
