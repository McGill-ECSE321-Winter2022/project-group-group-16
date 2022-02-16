package mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Address {
    private int streetNumber;
    private int id;

    public void setStreetNumber(int value) {
        this.streetNumber = value;
    }

    public int getStreetNumber() {
        return this.streetNumber;
    }

    private String streetName;

    public void setStreetName(String value) {
        this.streetName = value;
    }

    public String getStreetName() {
        return this.streetName;
    }

    private String city;

    public void setCity(String value) {
        this.city = value;
    }

    public String getCity() {
        return this.city;
    }

    private String country;

    public void setCountry(String value) {
        this.country = value;
    }

    public String getCountry() {
        return this.country;
    }

    private String postalCode;

    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    private String province;

    public void setProvince(String value) {
        this.province = value;
    }

    public String getProvince() {
        return this.province;
    }

    private Customer customer;

    @OneToOne
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private Store store;

    @ManyToOne
    public Store getStore() {
        return this.store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    private Set<Order> order;

    @OneToMany(mappedBy = "shippingAddress")
    public Set<Order> getOrder() {
        return this.order;
    }

    public void setOrder(Set<Order> orders) {
        this.order = orders;
    }

    private Set<Order> order1;

    @OneToMany(mappedBy = "billingAddress")
    public Set<Order> getOrder1() {
        return this.order1;
    }

    public void setOrder1(Set<Order> order1s) {
        this.order1 = order1s;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    public int getId() {
        return id;
    }
}
