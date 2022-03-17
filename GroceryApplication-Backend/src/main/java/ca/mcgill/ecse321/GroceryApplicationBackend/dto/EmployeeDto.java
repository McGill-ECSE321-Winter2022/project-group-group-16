package ca.mcgill.ecse321.GroceryApplicationBackend.dto;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Employee;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryUser;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Shift;

import java.sql.Date;
import java.util.Set;

public class EmployeeDto {
    private Date hiredDate;
    private Employee.EmployeeStatus status;
    private float hourlyPay;
    private Set<Shift> shift;
    private GroceryUser user;
    private GroceryStoreApplication groceryStoreApplication;

    public EmployeeDto() {

    }

    public EmployeeDto(Date hiredDate, Employee.EmployeeStatus status, float hourlyPay,
                       Set<Shift> shift, GroceryUser user, GroceryStoreApplication groceryStoreApplication) {
        this.hiredDate = hiredDate;
        this.status = status;
        this.hourlyPay = hourlyPay;
        this.shift = shift;
        this.user = user;
        this.groceryStoreApplication = groceryStoreApplication;
    }

    public Date getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(Date hiredDate) {
        this.hiredDate = hiredDate;
    }

    public Employee.EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(Employee.EmployeeStatus status) {
        this.status = status;
    }

    public float getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(float hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    public Set<Shift> getShift() {
        return shift;
    }

    public void setShift(Set<Shift> shift) {
        this.shift = shift;
    }

    public GroceryUser getUser() {
        return user;
    }

    public void setUser(GroceryUser user) {
        this.user = user;
    }

    public GroceryStoreApplication getGroceryStoreApplication() {
        return groceryStoreApplication;
    }

    public void setGroceryStoreApplication(GroceryStoreApplication groceryStoreApplication) {
        this.groceryStoreApplication = groceryStoreApplication;
    }
}
