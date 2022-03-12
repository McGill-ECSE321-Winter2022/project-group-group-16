package ca.mcgill.ecse321.GroceryApplicationBackend.dto;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Customer;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Store;

import java.util.Set;

public class AddressDto {
    private Integer id;
    private Integer streetNumber;
    private String streetName;
    private String province;
    private String city;
    private String country;
    private String postalCode;
    private Store store;
    private Set<GroceryOrder> order;
    private Set<GroceryOrder> order1;
    private Customer customer;

    public AddressDto(Integer id, Integer streetNumber, String streetName, String province, String city, String country, String postalCode, Store store, Set<GroceryOrder> order, Set<GroceryOrder> order1, Customer customer) {
        this.id = id;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.province = province;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.store = store;
        this.order = order;
        this.order1 = order1;
        this.customer = customer;
    }

    public AddressDto() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Set<GroceryOrder> getOrder() {
        return order;
    }

    public void setOrder(Set<GroceryOrder> order) {
        this.order = order;
    }

    public Set<GroceryOrder> getOrder1() {
        return order1;
    }

    public void setOrder1(Set<GroceryOrder> order) {
        this.order1 = order;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
