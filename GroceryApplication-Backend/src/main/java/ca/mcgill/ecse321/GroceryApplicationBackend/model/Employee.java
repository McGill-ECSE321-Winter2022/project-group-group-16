package ca.mcgill.ecse321.GroceryApplicationBackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.Set;

@Entity
public class Employee extends UserRole {
    // attributes
    private Date hiredDate;
    @Enumerated
    private EmployeeStatus status;
    private Float hourlyPay;

    // associations
    private Set<Shift> shift;

    public Date getHiredDate() {
        return this.hiredDate;
    }

    public void setHiredDate(Date value) {
        this.hiredDate = value;
    }

    public Float getHourlyPay() {
        return this.hourlyPay;
    }

    public void setHourlyPay(Float value) {
        this.hourlyPay = value;
    }

    public EmployeeStatus getStatus() {
        return this.status;
    }

    public void setStatus(EmployeeStatus value) {
        this.status = value;
    }

    @OneToMany(mappedBy = "employee", cascade = {CascadeType.ALL})
    @JsonManagedReference
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
