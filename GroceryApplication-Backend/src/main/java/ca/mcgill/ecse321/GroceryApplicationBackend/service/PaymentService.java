package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.*;
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

    
    /** 
     * @param id
     * @param amount
     * @param paymentType
     * @param paymentCode
     * @param order
     * @return Payment
     * @throws Exception
     */
    @Transactional
    public Payment createPayment(int applicationId, int orderId, int id, float amount, PaymentType paymentType, String paymentCode){
        
        GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);
        if(gs == null){
            throw new InvalidInputException("The application doesn't exist.");
        }

        GroceryOrder go = groceryOrderRepository.findGroceryOrderById(orderId);
        if(go == null){
            throw new InvalidInputException("The order doesn't exist.");
        }

        Payment payment = new Payment();
        payment.setId(id);
        payment.setAmount(amount);
        payment.setPaymentType(paymentType);
        payment.setPaymentCode(paymentCode);
        
        paymentRepository.save(payment);
        return payment;
    }
    
    /** 
     * @return List<Payment>
     */
    @Transactional
    public List<Payment> getAllPayments(){
    	return toList(paymentRepository.findAll());
    }

    
    /** 
     * @return List<Float>
     */
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
    
    /** 
     * @param paymentId
     * @return Payment
     * @throws Exception
     */
    @Transactional
    public Payment getPaymentById(int paymentId){
    	if(paymentRepository.findPaymentById(paymentId) == null) {
    		throw new InvalidInputException("This id has no associated payment");
    	}
    	return paymentRepository.findPaymentById(paymentId);
    }

    
    /** 
     * @param id
     * @param amount
     * @param paymentType
     * @param paymentCode
     * @param order
     * @return Payment
     * @throws Exception
     */
    @Transactional
    public Payment updatePayment(Integer id, float amount, PaymentType paymentType, String paymentCode){
        
        if(paymentRepository.findPaymentById(id)==null) {
    		throw new InvalidInputException("Payment id is not valid!");
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

    
    /** 
     * @param id
     * @return Payment
     * @throws Exception
     */
    @Transactional
    public boolean deletePayment(Integer id){
        
        if(paymentRepository.findPaymentById(id)==null) {
    		throw new InvalidInputException("Payment id is not valid!");
    	}

        Payment payment = paymentRepository.findPaymentById(id);
        if(payment == null){
            throw new InvalidInputException("payment doesn't exist");
        }

        paymentRepository.deletePaymentById(id);

        return true;
    }

    
    /** 
     * @param iterable
     * @return List<T>
     */
    //helper
    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
    
}
