package mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Shift {
    private Day day;

    private void setDay(Day value) {
        this.day = value;
    }

    private Day getDay() {
        return this.day;
    }

    private Employee employee;

    @ManyToOne(optional = false)
    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    private ShiftType shift;

    public void setShift(ShiftType value) {
        this.shift = value;
    }

    public ShiftType getShift() {
        return this.shift;
    }

    private int id;

    public void setId(int value) {
        this.id = value;
    }

    @Id
    public int getId() {
        return this.id;
    }

    public enum ShiftType {
    }

    public enum Day {
    }

}

