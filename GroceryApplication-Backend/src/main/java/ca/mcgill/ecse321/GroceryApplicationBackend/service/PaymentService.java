package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.*;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.*;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Payment.PaymentType;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    GroceryOrderRepository groceryOrderRepository;

    @Autowired
    GroceryStoreApplicationRepository groceryStoreApplicationRepository;

    
    @Transactional
    public Payment createPayment(int applicationId, int orderId, Float amount, PaymentType paymentType, String paymentCode){
        
        GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);
        if(gs == null){
            throw new ApiRequestException("The application doesn't exist.");
        }

        GroceryOrder go = groceryOrderRepository.findGroceryOrderById(orderId);
        if(go == null){
            throw new ApiRequestException("The order doesn't exist.");
        }

        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setPaymentType(paymentType);
        payment.setPaymentCode(paymentCode);
        
        paymentRepository.save(payment);
        return payment;
    }
    
   
    @Transactional
    public List<Payment> getAllPayments(){
    	return toList(paymentRepository.findAll());
    }

    
 
    @Transactional
    public List<Float> getAllSortedPayment() {

        List<Float> amounts = new ArrayList<>();

        List<Payment> payments = toList(paymentRepository.findAll());

        for(int i=0; i < payments.size(); i++){
            amounts.add(payments.get(i).getAmount());
        }

        Collections.sort(amounts);

        return amounts;
    }


    @Transactional
    public Payment getPaymentById(int paymentId){
    	if(paymentRepository.findPaymentById(paymentId) == null) {
    		throw new ApiRequestException("This id has no associated payment");
    	}
    	return paymentRepository.findPaymentById(paymentId);
    }

    

    @Transactional
    public Payment updatePayment(Integer id, Float amount, PaymentType paymentType, String paymentCode){
        
        if(paymentRepository.findPaymentById(id)==null) {
    		throw new ApiRequestException("Payment id is not valid!");
    	}
    	
    	Payment payment = paymentRepository.findPaymentById(id);
        if(amount != 0){
            payment.setAmount(amount);
        }
        if(paymentType != null){
            payment.setPaymentType(paymentType);
        }
        if(paymentCode != null){
            payment.setPaymentCode(paymentCode);
        }

        paymentRepository.save(payment);
        return payment;
    }


    @Transactional
    public boolean deletePayment(Integer id){
        
        if(paymentRepository.findPaymentById(id)==null) {
    		throw new ApiRequestException("Payment id is not valid!");
    	}

        Payment payment = paymentRepository.findPaymentById(id);
        if(payment == null){
            throw new ApiRequestException("payment doesn't exist");
        }

        paymentRepository.deletePaymentById(id);

        return true;
    }

    

    //helper
    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
    
}
