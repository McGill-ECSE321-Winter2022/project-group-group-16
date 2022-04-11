package ca.mcgill.ecse321.GroceryApplicationBackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class Store {
    // attributes
    private String name;
    private LocalTime weekDayOpening;
    private LocalTime weekDayClosing;
    private LocalTime weekEndOpening;
    private LocalTime weekEndClosing;

    // associations
    private Address address;
    private GroceryStoreApplication groceryStoreApplication;

    @JsonBackReference
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

    public LocalTime getWeekDayOpening() {
        return this.weekDayOpening;
    }

    public void setWeekDayOpening(LocalTime value) {
        this.weekDayOpening = value;
    }

    public LocalTime getWeekDayClosing() {
        return this.weekDayClosing;
    }

    public void setWeekDayClosing(LocalTime value) {
        this.weekDayClosing = value;
    }

    public LocalTime getWeekEndOpening() {
        return this.weekEndOpening;
    }

    public void setWeekEndOpening(LocalTime value) {
        this.weekEndOpening = value;
    }

    public LocalTime getWeekEndClosing() {
        return this.weekEndClosing;
    }

    public void setWeekEndClosing(LocalTime value) {
        this.weekEndClosing = value;
    }

    @JsonManagedReference
    @OneToOne(cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "addr_id", referencedColumnName = "id")
    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
