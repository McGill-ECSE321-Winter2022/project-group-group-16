package ca.mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.Entity;
import java.sql.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

@Entity
public class Employee extends UserRole {
    // attributes
    private Date hiredDate;
    @Enumerated
    private EmployeeStatus status;
    private float hourlyPay;

    // associations
    private Set<Shift> shift;

    public void setHiredDate(Date value) {
        this.hiredDate = value;
    }

    public Date getHiredDate() {
        return this.hiredDate;
    }

    public void setHourlyPay(float value) {
        this.hourlyPay = value;
    }

    public float getHourlyPay() {
        return this.hourlyPay;
    }

    public void setStatus(EmployeeStatus value) {
        this.status = value;
    }

    public EmployeeStatus getStatus() {
        return this.status;
    }

    @OneToMany(mappedBy = "employee", cascade = {CascadeType.ALL})
    public Set<Shift> getShift() {
        return this.shift;
    }

    public void setShift(Set<Shift> shifts) {
        this.shift = shifts;
    }

    public enum EmployeeStatus {
        ACTIVE, BANNED, QUIT
    }

}
