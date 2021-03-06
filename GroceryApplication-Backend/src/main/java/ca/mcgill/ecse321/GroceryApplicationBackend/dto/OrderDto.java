package ca.mcgill.ecse321.GroceryApplicationBackend.dto;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Address;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Customer;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder.OrderStatus;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder.PurchaseType;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Payment;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product;

import javax.persistence.Enumerated;
import java.sql.Date;
import java.util.Set;

public class OrderDto {

    // attributes
    @Enumerated
    private OrderStatus status;
    private Integer id;
    private Date datePlaced;
    private Date deliveryDate;
    private String customerNote;
    @Enumerated
    private PurchaseType purchaseType;
    private Payment payment;
    // associations
    private Set<Product> product;
    private Address billingAddress;
    private Customer customer;
    private Address shippingAddress;

    public OrderDto() {
    }

    public OrderDto(OrderStatus status, Integer id, Date datePlaced, Date deliveryDate, String customerNote, PurchaseType purchaseType, Set<Product> product, Address billingAddress, Customer customer, Address shippingAddress, Payment payment) {
        this.status = status;
        this.id = id;
        this.datePlaced = datePlaced;
        this.deliveryDate = deliveryDate;
        this.customerNote = customerNote;
        this.purchaseType = purchaseType;
        this.payment = payment;
        this.product = product;
        this.billingAddress = billingAddress;
        this.customer = customer;
        this.shippingAddress = shippingAddress;
    }

    public Integer getOrderId() {
        return id;
    }

    public void setOrderId(Integer id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return status;
    }

    public void setOrderStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(Date datePlaced) {
        this.datePlaced = datePlaced;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }

    public PurchaseType getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(PurchaseType purchaseType) {
        this.purchaseType = purchaseType;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<Product> getProducts() {
        return product;
    }

    public void setProduct(Set<Product> products) {
        this.product = products;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
