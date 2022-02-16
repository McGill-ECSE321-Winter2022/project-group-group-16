package ca.mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class GroceryUser {
    // attributes
    private String dateOfBirth;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    // associations
    private Set<UserRole> userRole;

    @OneToMany(mappedBy = "user")
    public Set<UserRole> getUserRole() {
        return this.userRole;
    }

    public void setUserRole(Set<UserRole> userRoles) {
        this.userRole = userRoles;
    }

    public void setUsername(String value) {
        this.username = value;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public String getPassword() {
        return this.password;
    }

    public void setFirstName(String value) {
        this.firstName = value;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String value) {
        this.lastName = value;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    @Id
    public String getEmail() {
        return this.email;
    }

    public void setDateOfBirth(String value) {
        this.dateOfBirth = value;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }
}
