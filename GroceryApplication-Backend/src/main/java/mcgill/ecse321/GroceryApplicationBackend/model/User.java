package mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.Id;

import javax.persistence.Entity;

@Entity
public class User {
    private Set<UserRole> userRole;

    @OneToMany(mappedBy = "user")
    public Set<UserRole> getUserRole() {
        return this.userRole;
    }

    public void setUserRole(Set<UserRole> userRoles) {
        this.userRole = userRoles;
    }

    private String username;

    public void setUsername(String value) {
        this.username = value;
    }

    public String getUsername() {
        return this.username;
    }

    private String password;

    public void setPassword(String value) {
        this.password = value;
    }

    public String getPassword() {
        return this.password;
    }

    private String firstName;

    public void setFirstName(String value) {
        this.firstName = value;
    }

    public String getFirstName() {
        return this.firstName;
    }

    private String lastName;

    public void setLastName(String value) {
        this.lastName = value;
    }

    public String getLastName() {
        return this.lastName;
    }

    private String email;

    public void setEmail(String value) {
        this.email = value;
    }

    @Id
    public String getEmail() {
        return this.email;
    }

    private String dateOfBirth;

    public void setDateOfBirth(String value) {
        this.dateOfBirth = value;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }
}
