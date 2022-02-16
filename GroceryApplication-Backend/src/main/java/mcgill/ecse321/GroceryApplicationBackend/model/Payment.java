package mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Payment {
    // enums
    public enum PaymentType {
        CREDIT, DEBIT, GIFTCARD
    }

    // attributes
    private int id;
    private float amount;
    @Enumerated
    private PaymentType paymentType;
    private String paymentCode;

    // associations
    private GroceryOrder order;

    public void setId(int value) {
        this.id = value;
    }

    @Id
    public int getId() {
        return this.id;
    }

    public void setAmount(float value) {
        this.amount = value;
    }

    public float getAmount() {
        return this.amount;
    }


    public void setPaymentCode(String value) {
        this.paymentCode = value;
    }

    public String getPaymentCode() {
        return this.paymentCode;
    }

    private void setPaymentType(PaymentType value) {
        this.paymentType = value;
    }

    private PaymentType getPaymentType() {
        return this.paymentType;
    }

    @OneToOne(optional = false)
    public GroceryOrder getOrder() {
        return this.order;
    }

    public void setOrder(GroceryOrder order) {
        this.order = order;
    }

}
