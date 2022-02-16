package mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Customer extends UserRole {
    private Address address;

    @OneToOne(mappedBy = "customer", cascade = {CascadeType.ALL}, optional = false)
    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    private Set<Order> order;

    @OneToMany(mappedBy = "customer")
    public Set<Order> getOrder() {
        return this.order;
    }

    public void setOrder(Set<Order> orders) {
        this.order = orders;
    }

}
