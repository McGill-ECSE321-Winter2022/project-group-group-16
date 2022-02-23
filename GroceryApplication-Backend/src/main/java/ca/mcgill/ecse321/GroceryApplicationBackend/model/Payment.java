package ca.mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Payment {
    // attributes
    private int id;
    private float amount;
    @Enumerated
    private PaymentType paymentType;
    private String paymentCode;
    // associations
    private GroceryOrder order;

    @Id
    public int getId() {
        return this.id;
    }

    public void setId(int value) {
        this.id = value;
    }

    public float getAmount() {
        return this.amount;
    }

    public void setAmount(float value) {
        this.amount = value;
    }

    public String getPaymentCode() {
        return this.paymentCode;
    }

    public void setPaymentCode(String value) {
        this.paymentCode = value;
    }

    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(PaymentType value) {
        this.paymentType = value;
    }

    @OneToOne(optional = false)
    public GroceryOrder getOrder() {
        return this.order;
    }

    public void setOrder(GroceryOrder order) {
        this.order = order;
    }

    // enums
    public enum PaymentType {
        CREDIT, DEBIT, GIFTCARD
    }

}
