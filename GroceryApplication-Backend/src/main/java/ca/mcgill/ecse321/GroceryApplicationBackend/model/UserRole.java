package ca.mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class UserRole {
    // attributes
    private int id;

    // associations
    private GroceryUser user;
    private GroceryStoreApplication groceryStoreApplication;

    @ManyToOne(optional = false)
    public GroceryStoreApplication getGroceryStoreApplication() {
        return this.groceryStoreApplication;
    }

    public void setGroceryStoreApplication(GroceryStoreApplication groceryStoreApplication) {
        this.groceryStoreApplication = groceryStoreApplication;
    }

    @ManyToOne
    public GroceryUser getUser() {
        return this.user;
    }

    public void setUser(GroceryUser user) {
        this.user = user;
    }

    public void setId(int value) {
        this.id = value;
    }

    @Id
    public int getId() {
        return this.id;
    }
}
