package mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Payment {
    private int id;

    public void setId(int value) {
        this.id = value;
    }

    @Id
    public int getId() {
        return this.id;
    }

    private float amount;

    public void setAmount(float value) {
        this.amount = value;
    }

    public float getAmount() {
        return this.amount;
    }

    private String paymentCode;

    public void setPaymentCode(String value) {
        this.paymentCode = value;
    }

    public String getPaymentCode() {
        return this.paymentCode;
    }

    private PaymentType paymentType;

    private void setPaymentType(PaymentType value) {
        this.paymentType = value;
    }

    private PaymentType getPaymentType() {
        return this.paymentType;
    }

    private Order order;

    @OneToOne(optional = false)
    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public enum PaymentType {
    }


}
