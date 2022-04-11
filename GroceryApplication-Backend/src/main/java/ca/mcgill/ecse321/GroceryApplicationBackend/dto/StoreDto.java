package ca.mcgill.ecse321.GroceryApplicationBackend.dto;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Address;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;

import java.time.LocalTime;

public class StoreDto {
    private String name;
    private LocalTime weekDayOpening;
    private LocalTime weekDayClosing;
    private LocalTime weekEndOpening;
    private LocalTime weekEndClosing;
    private Address address;
    private GroceryStoreApplication groceryStoreApplication;

    public StoreDto() {

    }

    public StoreDto(String name, LocalTime weekDayOpening, LocalTime weekDayClosing, LocalTime weekEndOpening, LocalTime weekEndClosing, Address address, GroceryStoreApplication groceryStoreApplication) {
        this.name = name;
        this.weekDayOpening = weekDayOpening;
        this.weekDayClosing = weekDayClosing;
        this.weekEndOpening = weekEndOpening;
        this.weekEndClosing = weekEndClosing;
        this.address = address;
        this.groceryStoreApplication = groceryStoreApplication;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getWeekDayOpening() {
        return weekDayOpening;
    }

    public void setWeekDayOpening(LocalTime weekDayOpening) {
        this.weekDayOpening = weekDayOpening;
    }

    public LocalTime getWeekDayClosing() {
        return weekDayClosing;
    }

    public void setWeekDayClosing(LocalTime weekDayClosing) {
        this.weekDayClosing = weekDayClosing;
    }

    public LocalTime getWeekEndOpening() {
        return weekEndOpening;
    }

    public void setWeekEndOpening(LocalTime weekEndOpening) {
        this.weekEndOpening = weekEndOpening;
    }

    public LocalTime getWeekEndClosing() {
        return weekEndClosing;
    }

    public void setWeekEndClosing(LocalTime weekEndClosing) {
        this.weekEndClosing = weekEndClosing;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public GroceryStoreApplication getGroceryStoreApplication() {
        return groceryStoreApplication;
    }

    public void setGroceryStoreApplication(GroceryStoreApplication groceryStoreApplication) {
        this.groceryStoreApplication = groceryStoreApplication;
    }
}