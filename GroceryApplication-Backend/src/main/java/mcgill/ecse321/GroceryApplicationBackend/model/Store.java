package mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.*;

import java.util.Set;

@Entity
public class Store {
    // attributes
    private String name;
    private String weekDayOpening;
    private String weekDayClosing;
    private String weekEndOpening;

    // associations
    private Set<Address> address;
    private GroceryStoreApplication groceryStoreApplication;

    @OneToOne(optional = false)
    public GroceryStoreApplication getGroceryStoreApplication() {
        return this.groceryStoreApplication;
    }

    public void setGroceryStoreApplication(GroceryStoreApplication groceryStoreApplication) {
        this.groceryStoreApplication = groceryStoreApplication;
    }

    public void setName(String value) {
        this.name = value;
    }

    @Id
    public String getName() {
        return this.name;
    }

    public void setWeekDayOpening(String value) {
        this.weekDayOpening = value;
    }

    public String getWeekDayOpening() {
        return this.weekDayOpening;
    }

    public void setWeekDayClosing(String value) {
        this.weekDayClosing = value;
    }

    public String getWeekDayClosing() {
        return this.weekDayClosing;
    }

    public void setWeekEndOpening(String value) {
        this.weekEndOpening = value;
    }

    public String getWeekEndOpening() {
        return this.weekEndOpening;
    }

    private String weekEndClosing;

    public void setWeekEndClosing(String value) {
        this.weekEndClosing = value;
    }

    public String getWeekEndClosing() {
        return this.weekEndClosing;
    }

    @OneToMany(mappedBy = "store", cascade = {CascadeType.ALL})
    public Set<Address> getAddress() {
        return this.address;
    }

    public void setAddress(Set<Address> addresss) {
        this.address = addresss;
    }

}
