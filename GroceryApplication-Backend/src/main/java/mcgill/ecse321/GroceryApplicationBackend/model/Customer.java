package mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customer extends UserRole {
    // associations
    private Address address;

    @OneToOne(mappedBy = "customer", cascade = {CascadeType.ALL}, optional = false)
    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    private Set<GroceryOrder> order;

    @OneToMany(mappedBy = "customer")
    public Set<GroceryOrder> getOrder() {
        return this.order;
    }

    public void setOrder(Set<GroceryOrder> orders) {
        this.order = orders;
    }

}
