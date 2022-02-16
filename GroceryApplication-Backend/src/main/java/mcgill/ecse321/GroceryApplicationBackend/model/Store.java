package mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.*;

import java.util.Set;

@Entity
public class Store {
    private GroceryStoreApplication groceryStoreApplication;

    @OneToOne(optional = false)
    public GroceryStoreApplication getGroceryStoreApplication() {
        return this.groceryStoreApplication;
    }

    public void setGroceryStoreApplication(GroceryStoreApplication groceryStoreApplication) {
        this.groceryStoreApplication = groceryStoreApplication;
    }

    private String name;

    public void setName(String value) {
        this.name = value;
    }

    @Id
    public String getName() {
        return this.name;
    }

    private String weekDayOpening;

    public void setWeekDayOpening(String value) {
        this.weekDayOpening = value;
    }

    public String getWeekDayOpening() {
        return this.weekDayOpening;
    }

    private String weekDayClosing;

    public void setWeekDayClosing(String value) {
        this.weekDayClosing = value;
    }

    public String getWeekDayClosing() {
        return this.weekDayClosing;
    }

    private String weekEndOpening;

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

    private Set<Address> address;

    @OneToMany(mappedBy = "store", cascade = {CascadeType.ALL})
    public Set<Address> getAddress() {
        return this.address;
    }

    public void setAddress(Set<Address> addresss) {
        this.address = addresss;
    }

}
