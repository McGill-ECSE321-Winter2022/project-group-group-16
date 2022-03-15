package ca.mcgill.ecse321.GroceryApplicationBackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class GroceryOrder {
    // attributes
    @Enumerated
    private OrderStatus status;
    private int id;
    private Date datePlaced;
    private Date deliveryDate;
    private String customerNote;
    @Enumerated
    private PurchaseType purchaseType;
    private Payment payment;
    // associations
    private GroceryStoreApplication groceryStoreApplication;
    private Set<Product> product;
    private Address billingAddress;
    private Customer customer;
    private Address shippingAddress;

    @JsonBackReference
    @ManyToOne(optional = false)
    public GroceryStoreApplication getGroceryStoreApplication() {
        return this.groceryStoreApplication;
    }

    public void setGroceryStoreApplication(GroceryStoreApplication groceryStoreApplication) {
        this.groceryStoreApplication = groceryStoreApplication;
    }

    private OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus value) {
        this.status = value;
    }

    @Id
    public int getId() {
        return this.id;
    }

    public void setId(int value) {
        this.id = value;
    }

    public Date getDatePlaced() {
        return this.datePlaced;
    }

    public void setDatePlaced(Date value) {
        this.datePlaced = value;
    }

    public Date getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(Date value) {
        this.deliveryDate = value;
    }

    public String getCustomerNote() {
        return this.customerNote;
    }

    public void setCustomerNote(String value) {
        this.customerNote = value;
    }

    private PurchaseType getPurchaseType() {
        return this.purchaseType;
    }

    public void setPurchaseType(PurchaseType value) {
        this.purchaseType = value;
    }

    @ManyToOne
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne
    public Address getShippingAddress() {
        return this.shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @ManyToOne
    public Address getBillingAddress() {
        return this.billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    @OneToOne(mappedBy = "order", cascade = {CascadeType.ALL})
    public Payment getPayment() {
        return this.payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @ManyToMany
    public Set<Product> getProduct() {
        return this.product;
    }

    public void setProduct(Set<Product> products) {
        this.product = products;
    }

    public enum PurchaseType {
        INSTORE, ONLINE
    }

    public enum OrderStatus {
        INCART, PLACED, READY_FOR_PICKUP, PICKED_UP, SHIPPED, DELIVERED, CANCELLED, PURCHASED_IN_STORE
    }

}
