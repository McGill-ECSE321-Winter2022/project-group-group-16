package ca.mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customer extends UserRole {
    // associations
    private Address address;
    private Set<GroceryOrder> order;

    @OneToOne(cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "addr_id", referencedColumnName = "id")
    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @OneToMany(mappedBy = "customer")
    public Set<GroceryOrder> getOrder() {
        return this.order;
    }

    public void setOrder(Set<GroceryOrder> orders) {
        this.order = orders;
    }

}
