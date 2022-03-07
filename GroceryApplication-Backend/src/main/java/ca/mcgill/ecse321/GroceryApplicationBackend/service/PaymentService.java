package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import java.util.ArrayList;
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
    public Payment createPayment(int applicationId, int groceryId, int id, float amount, PaymentType paymentType, String paymentCode, GroceryOrder order) throws Exception{
        
        GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);
        if(gs == null){
            throw new Exception("The application doesn't exist.");
        }

        GroceryOrder go = groceryOrderRepository.findGroceryOrderById(groceryId);
        if(go == null){
            throw new Exception("The order doesn't exist.");
        }

        Payment payment = new Payment();
        payment.setId(id);
        payment.setAmount(amount);
        payment.setPaymentType(paymentType);
        payment.setPaymentCode(paymentCode);
        payment.setOrder(order);
        
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
     * @param paymentId
     * @return Payment
     * @throws Exception
     */
    @Transactional
    public Payment getPaymentById(int paymentId) throws Exception {
    	if(paymentRepository.findPaymentById(paymentId) == null) {
    		throw new Exception("This id has no associated payment");
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
    public Payment updatePayment(int id, float amount, PaymentType paymentType, String paymentCode, GroceryOrder order) throws Exception{
        
        if(paymentRepository.findPaymentById(id)==null) {
    		throw new Exception("Payment id is not valid!");
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
        if(order != null){
            payment.setOrder(order);
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
    public Payment deletePayment(int id) throws Exception{
        
        if(paymentRepository.findPaymentById(id)==null) {
    		throw new Exception("Payment id is not valid!");
    	}

        Payment payment = paymentRepository.findPaymentById(id);
        if(payment == null){
            throw new Exception("payment doesn't exist");
        }

        Payment deletedP = null;
        paymentRepository.deletePaymentById(id);

        return deletedP;
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
