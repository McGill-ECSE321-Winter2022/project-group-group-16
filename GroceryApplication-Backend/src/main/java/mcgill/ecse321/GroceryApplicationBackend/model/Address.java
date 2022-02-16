package mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Address {
    private int streetNumber;
    private int id;
    private String streetName;
    private String province;
    private Customer customer;
    private Store store;
    private String city;


    public void setStreetNumber(int value) {
        this.streetNumber = value;
    }

    public int getStreetNumber() {
        return this.streetNumber;
    }

    public void setStreetName(String value) {
        this.streetName = value;
    }

    public String getStreetName() {
        return this.streetName;
    }


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

    public void setProvince(String value) {
        this.province = value;
    }

    public String getProvince() {
        return this.province;
    }

    @OneToOne
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne
    public Store getStore() {
        return this.store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    private Set<GroceryOrder> order;

    @OneToMany(mappedBy = "shippingAddress")
    public Set<GroceryOrder> getOrder() {
        return this.order;
    }

    public void setOrder(Set<GroceryOrder> orders) {
        this.order = orders;
    }

    private Set<GroceryOrder> order1;

    @OneToMany(mappedBy = "billingAddress")
    public Set<GroceryOrder> getOrder1() {
        return this.order1;
    }

    public void setOrder1(Set<GroceryOrder> order1s) {
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
