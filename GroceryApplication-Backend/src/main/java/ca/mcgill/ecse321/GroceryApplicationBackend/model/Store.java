package ca.mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.*;
import java.sql.Time;

@Entity
public class Store {
    // attributes
    private String name;
    private Time weekDayOpening;
    private Time weekDayClosing;
    private Time weekEndOpening;
    private Time weekEndClosing;

    // associations
    private Address address;
    private GroceryStoreApplication groceryStoreApplication;

    @OneToOne(optional = false)
    public GroceryStoreApplication getGroceryStoreApplication() {
        return this.groceryStoreApplication;
    }

    public void setGroceryStoreApplication(GroceryStoreApplication groceryStoreApplication) {
        this.groceryStoreApplication = groceryStoreApplication;
    }

    @Id
    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public Time getWeekDayOpening() {
        return this.weekDayOpening;
    }

    public void setWeekDayOpening(Time value) {
        this.weekDayOpening = value;
    }

    public Time getWeekDayClosing() {
        return this.weekDayClosing;
    }

    public void setWeekDayClosing(Time value) {
        this.weekDayClosing = value;
    }

    public Time getWeekEndOpening() {
        return this.weekEndOpening;
    }

    public void setWeekEndOpening(Time value) {
        this.weekEndOpening = value;
    }

    public Time getWeekEndClosing() {
        return this.weekEndClosing;
    }

    public void setWeekEndClosing(Time value) {
        this.weekEndClosing = value;
    }

    @OneToOne(cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "addr_id", referencedColumnName = "id")
    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address addresss) {
        this.address = addresss;
    }

}
