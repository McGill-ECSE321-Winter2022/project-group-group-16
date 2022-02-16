package ca.mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Shift {
    // enums
    public enum ShiftType {
        OPENING, CLOSING
    }

    public enum Day {
        MONDAY, THUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    // attributes
    private Day day;
    private int id;
    @Enumerated
    private ShiftType shift;

    // associations
    private Employee employee;

    private void setDay(Day value) {
        this.day = value;
    }

    private Day getDay() {
        return this.day;
    }

    @ManyToOne(optional = false)
    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    public void setShift(ShiftType value) {
        this.shift = value;
    }

    public ShiftType getShift() {
        return this.shift;
    }

    public void setId(int value) {
        this.id = value;
    }

    @Id
    public int getId() {
        return this.id;
    }

}

