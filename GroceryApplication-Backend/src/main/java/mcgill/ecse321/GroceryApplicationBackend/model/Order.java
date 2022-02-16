package mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Order {
    private GroceryStoreApplication groceryStoreApplication;

    @ManyToOne(optional = false)
    public GroceryStoreApplication getGroceryStoreApplication() {
        return this.groceryStoreApplication;
    }

    public void setGroceryStoreApplication(GroceryStoreApplication groceryStoreApplication) {
        this.groceryStoreApplication = groceryStoreApplication;
    }

    private OrderStatus status;

    private void setStatus(OrderStatus value) {
        this.status = value;
    }

    private OrderStatus getStatus() {
        return this.status;
    }

    private int id;

    public void setId(int value) {
        this.id = value;
    }

    @Id
    public int getId() {
        return this.id;
    }

    private String datePlaced;

    public void setDatePlaced(String value) {
        this.datePlaced = value;
    }

    public String getDatePlaced() {
        return this.datePlaced;
    }

    private String deliveryDate;

    public void setDeliveryDate(String value) {
        this.deliveryDate = value;
    }

    public String getDeliveryDate() {
        return this.deliveryDate;
    }

    private String customerNote;

    public void setCustomerNote(String value) {
        this.customerNote = value;
    }

    public String getCustomerNote() {
        return this.customerNote;
    }

    private PurchaseType purchaseType;

    private void setPurchaseType(PurchaseType value) {
        this.purchaseType = value;
    }

    private PurchaseType getPurchaseType() {
        return this.purchaseType;
    }

    private Customer customer;

    @ManyToOne
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private Address shippingAddress;

    @ManyToOne
    public Address getShippingAddress() {
        return this.shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    private Address billingAddress;

    @ManyToOne
    public Address getBillingAddress() {
        return this.billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    private Payment payment;

    @OneToOne(mappedBy = "order", cascade = { CascadeType.ALL })
    public Payment getPayment() {
        return this.payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    private Set<Product> product;

    @ManyToMany
    public Set<Product> getProduct() {
        return this.product;
    }

    public void setProduct(Set<Product> products) {
        this.product = products;
    }

    public enum PurchaseType {
    }

    public enum OrderStatus {
    }

}
