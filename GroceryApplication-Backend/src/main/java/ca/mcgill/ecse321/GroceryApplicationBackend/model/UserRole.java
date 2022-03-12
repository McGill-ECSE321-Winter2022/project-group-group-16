package ca.mcgill.ecse321.GroceryApplicationBackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UserRole {
    // attributes
    private Integer id;

    // associations
    private GroceryUser user;
    private GroceryStoreApplication groceryStoreApplication;

    @JsonBackReference
    @ManyToOne(optional = false)
    public GroceryStoreApplication getGroceryStoreApplication() {
        return this.groceryStoreApplication;
    }

    public void setGroceryStoreApplication(GroceryStoreApplication groceryStoreApplication) {
        this.groceryStoreApplication = groceryStoreApplication;
    }

    @ManyToOne
    @JsonBackReference
    public GroceryUser getUser() {
        return this.user;
    }

    public void setUser(GroceryUser user) {
        this.user = user;
    }

    @Id
    @GenericGenerator(name = "UseExistingIdOtherwiseGenerateUsingIdentity", strategy = "ca.mcgill.ecse321.GroceryApplicationBackend.model.UseExistingIdOtherwiseGenerateUsingIdentity")
    @GeneratedValue(generator = "UseExistingIdOtherwiseGenerateUsingIdentity")
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer value) {
        this.id = value;
    }
}
