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
     * Method to create a new payment in the payment repo
     * @param orderId The order ID
     * @param amount The amount
     * @param paymentType The payment type
     * @param paymentCode The payment code
     * @return The payment object
     */
    @Transactional
    public Payment createPayment(Integer orderId, Float amount, PaymentType paymentType, String paymentCode) {

        GroceryOrder go = groceryOrderRepository.findGroceryOrderById(orderId);
        if (go == null) {
            throw new ApiRequestException("The order doesn't exist!");
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
     * Method to return a list of all the payments
     * @return A list<Payments> of all payments
     */
    @Transactional
    public List<Payment> getAllPayments() {
        return toList(paymentRepository.findAll());
    }


    /**
     * Method to return a sorted list of all payments
     * @return A sorted list<Payment> of all payments
     */
    @Transactional
    public List<Payment> getAllSortedPayment() {
        return paymentRepository.findAllByOrderByAmountDesc();
    }


    /**
     * Method to get a payment from ID
     * @param paymentId The payment ID
     * @return The payment object
     */
    @Transactional
    public Payment getPaymentById(Integer paymentId) {
        if (paymentId == null) {
            throw new ApiRequestException("Payment id cannot be null!");
        }

        if (paymentRepository.findPaymentById(paymentId) == null) {
            throw new ApiRequestException("Payment doesn't exist!");
        }
        return paymentRepository.findPaymentById(paymentId);
    }


    /**
     * Method to update an existing payment
     * @param paymentId The payment ID
     * @param amount The new amount
     * @param paymentType The new payment type
     * @param paymentCode The new payment code
     * @return The updated payment object
     */
    @Transactional
    public Payment updatePayment(Integer paymentId, Float amount, PaymentType paymentType, String paymentCode) {
        if (paymentId == null) {
            throw new ApiRequestException("Payment id cannot be null!");
        }

        if (paymentRepository.findPaymentById(paymentId) == null) {
            throw new ApiRequestException("Payment doesn't exist!");
        }

        Payment payment = paymentRepository.findPaymentById(paymentId);

        if (amount != null) {
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
     * Method to delete an existing payment
     * @param paymentId The payment ID
     */
    @Transactional
    public void deletePayment(Integer paymentId) {
        if (paymentId == null) {
            throw new ApiRequestException("Payment id cannot be null!");
        }

        if (paymentRepository.findPaymentById(paymentId) == null) {
            throw new ApiRequestException("Payment doesn't exist!");
        }

        paymentRepository.deletePaymentById(paymentId);

    }

    /**
     * Method to return true if the input is PAYED
     * @param input the input
     * @return if payed
     */
    public boolean validatePayment(String input) {
        return input.equals("PAYED");
    }


    /**
     * Helper method to convert an iterable to a list
     * @param iterable The object to be converted
     * @param <T> The type
     * @return The converted list
     */
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

}
