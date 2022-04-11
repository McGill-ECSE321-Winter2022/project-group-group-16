package ca.mcgill.ecse321.GroceryApplicationBackend.dto;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Employee;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Shift;

public class ShiftDto {
    private Shift.Day day;
    private Integer id;
    private Shift.ShiftType shift;
    private Employee employee;

    public ShiftDto(Shift.Day day, Integer id, Shift.ShiftType shift, Employee employee) {
        this.day = day;
        this.id = id;
        this.shift = shift;
        this.employee = employee;
    }

    public Shift.Day getDay() {
        return day;
    }

    public void setDay(Shift.Day day) {
        this.day = day;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Shift.ShiftType getShift() {
        return shift;
    }

    public void setShift(Shift.ShiftType shift) {
        this.shift = shift;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
