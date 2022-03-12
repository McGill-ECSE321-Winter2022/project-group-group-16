package ca.mcgill.ecse321.GroceryApplicationBackend.dto;

import javax.persistence.Enumerated;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Payment.PaymentType;

public class PaymentDto {

    // attributes
    private Integer id;
    private float amount;
    @Enumerated
    private PaymentType paymentType;
    private String paymentCode;
    // associations
    private GroceryOrder order;

    public PaymentDto(){

    }

    public PaymentDto(Integer id, float amount, PaymentType paymentType, String paymentCode, GroceryOrder order){
        this.id = id;
        this.amount = amount;
        this.paymentType = paymentType;
        this.paymentCode = paymentCode;
        this.order = order;
    }

    public Integer getId(){
        return id;
    }

    public float getAmount(){
        return amount;
    }

    public PaymentType getPaymentType(){
        return paymentType;
    }
    
    public String getPaymentCode(){
        return paymentCode;
    }

    public GroceryOrder getOrder(){
        return order;
    }
}