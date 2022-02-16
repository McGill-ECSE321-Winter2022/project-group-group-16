package mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class Employee extends UserRole {
    private String hiredDate;

    public void setHiredDate(String value) {
        this.hiredDate = value;
    }

    public String getHiredDate() {
        return this.hiredDate;
    }

    private String/*No type specified!*/ employeeStatus;

    public void setEmployeeStatus(String/*No type specified!*/ value) {
        this.employeeStatus = value;
    }

    public String/*No type specified!*/ getEmployeeStatus() {
        return this.employeeStatus;
    }

    private float hourlyPay;

    public void setHourlyPay(float value) {
        this.hourlyPay = value;
    }

    public float getHourlyPay() {
        return this.hourlyPay;
    }

    private EmployeeStatus status;

    public void setStatus(EmployeeStatus value) {
        this.status = value;
    }

    public EmployeeStatus getStatus() {
        return this.status;
    }

    private Set<Shift> shift;

    @OneToMany(mappedBy = "employee", cascade = {CascadeType.ALL})
    public Set<Shift> getShift() {
        return this.shift;
    }

    public void setShift(Set<Shift> shifts) {
        this.shift = shifts;
    }

    public enum EmployeeStatus {
    }

}
