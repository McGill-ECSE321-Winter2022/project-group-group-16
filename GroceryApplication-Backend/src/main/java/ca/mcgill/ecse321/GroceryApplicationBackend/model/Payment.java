package ca.mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Payment {
    // attributes
    private Integer id;
    private Float amount;
    @Enumerated
    private PaymentType paymentType;
    private String paymentCode;
    // associations
    private GroceryOrder order;

    @GenericGenerator(name = "UseExistingIdOtherwiseGenerateUsingIdentity", strategy = "ca.mcgill.ecse321.GroceryApplicationBackend.model.UseExistingIdOtherwiseGenerateUsingIdentity")
    @GeneratedValue(generator = "UseExistingIdOtherwiseGenerateUsingIdentity")
    @Id
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    public Float getAmount() {
        return this.amount;
    }

    public void setAmount(Float value) {
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

    @JsonBackReference
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
