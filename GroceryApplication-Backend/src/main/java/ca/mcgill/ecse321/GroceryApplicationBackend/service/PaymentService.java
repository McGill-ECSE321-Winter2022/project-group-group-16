package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryOrderRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryStoreApplicationRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.PaymentRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Payment;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Payment.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    GroceryOrderRepository groceryOrderRepository;

    @Autowired
    GroceryStoreApplicationRepository groceryStoreApplicationRepository;


    /**
     * @param applicationId
     * @param orderId
     * @param amount
     * @param paymentType
     * @param paymentCode
     * @return Payment
     */
    @Transactional
    public Payment createPayment(int orderId, Float amount, PaymentType paymentType, String paymentCode) {


        GroceryOrder go = groceryOrderRepository.findGroceryOrderById(orderId);
        if (go == null) {
            throw new ApiRequestException("The order doesn't exist.");
        }

        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setPaymentType(paymentType);
        payment.setPaymentCode(paymentCode);
        payment.setOrder(go);

        paymentRepository.save(payment);
        return payment;
    }


    /**
     * @return List<Payment>
     */
    @Transactional
    public List<Payment> getAllPayments() {
        return toList(paymentRepository.findAll());
    }


    /**
     * @return List<Float>
     */
    @Transactional
    public List<Payment> getAllSortedPayment() {
        return paymentRepository.findAllByOrderByAmountDesc();
    }


    /**
     * @param paymentId
     * @return Payment
     */
    @Transactional
    public Payment getPaymentById(int paymentId) {
        if (paymentRepository.findPaymentById(paymentId) == null) {
            throw new ApiRequestException("This id has no associated payment");
        }
        return paymentRepository.findPaymentById(paymentId);
    }


    /**
     * @param id
     * @param amount
     * @param paymentType
     * @param paymentCode
     * @return Payment
     */
    @Transactional
    public Payment updatePayment(Integer id, Float amount, PaymentType paymentType, String paymentCode) {

        if (paymentRepository.findPaymentById(id) == null) {
            throw new ApiRequestException("Payment id is not valid!");
        }

        Payment payment = paymentRepository.findPaymentById(id);
        if (amount != 0) {
            payment.setAmount(amount);
        }
        if (paymentType != null) {
            payment.setPaymentType(paymentType);
        }
        if (paymentCode != null) {
            payment.setPaymentCode(paymentCode);
        }

        paymentRepository.save(payment);
        return payment;
    }


    /**
     * @param id
     * @return
     */
    @Transactional
    public void deletePayment(Integer id) {
        if (id == null) {
            throw new ApiRequestException("Payment id is not valid!");
        }

        Payment payment = paymentRepository.findPaymentById(id);

        if (payment == null) {
            throw new ApiRequestException("payment doesn't exist");
        }

        paymentRepository.deletePaymentById(id);

    }


    //helper
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

}
