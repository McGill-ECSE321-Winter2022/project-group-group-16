package ca.mcgill.ecse321.groceryapplication.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String email;
    private String role;

    public LoggedInUser(String userId, String displayName) {
        this.email = userId;
        this.role = displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}