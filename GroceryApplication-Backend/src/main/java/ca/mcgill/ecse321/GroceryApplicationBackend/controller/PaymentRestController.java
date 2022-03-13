package ca.mcgill.ecse321.GroceryApplicationBackend.controller;

import ca.mcgill.ecse321.GroceryApplicationBackend.dto.PaymentDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Payment;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Payment.PaymentType;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class PaymentRestController {

    @Autowired
    PaymentService paymentService;


    /**
     * Rest method for creating a payment
     * 
     * @param orderId
     * @param amount
     * @param paymentType
     * @param paymentCode
     * @return created payment
     * @throws ApiRequestException
     */
    @PostMapping(value = {"/payment", "/payment/"})
    public PaymentDto createPayment(
            @RequestParam Integer orderId,
            @RequestParam Float amount,
            @RequestParam PaymentType paymentType,
            @RequestParam String paymentCode) throws ApiRequestException {
        return convertToDto(paymentService.createPayment(orderId, amount, paymentType, paymentCode));
    }



    /**
     * 
     * @param id
     * @param amount
     * @param paymentType
     * @param paymentCode
     * @return updated payment
     * @throws ApiRequestException
     */
    @PutMapping(value = {"/payment/{id}", "/payment/{id}/"})
    public PaymentDto updatePayment(
            @PathVariable("id") Integer id,
            @RequestParam Float amount,
            @RequestParam PaymentType paymentType,
            @RequestParam String paymentCode) throws ApiRequestException {
        return convertToDto(paymentService.updatePayment(id, amount, paymentType, paymentCode));
    }


    /**
     * Rest method for retrieving a payment by id
     * 
     * @param id
     * @return request payment
     * @throws ApiRequestException
     */
    @GetMapping(value = {"/payment/{id}", "/payment/{id}/"})
    public PaymentDto getPaymentById(@PathVariable("id") Integer id) throws ApiRequestException {
        return convertToDto(paymentService.getPaymentById(id));
    }


    /**
     * Rest method for retrieving all payments
     * The returned list is unsorted
     * 
     * @return all payments
     * @throws ApiRequestException
     */
    @GetMapping(value = {"/payment", "/payment/"})
    public List<PaymentDto> getAllPayments() throws ApiRequestException {
        List<PaymentDto> paymentDtos = new ArrayList<>();
        for (Payment payment : paymentService.getAllPayments()) {
            paymentDtos.add(convertToDto(payment));
        }
        return paymentDtos;
    }


   /**
    * Rest method for retrieving all ascending payments
    * 
    * @return all sorted payments
    * @throws ApiRequestException
    */
    @GetMapping(value = {"/payment/sorted", "/payment/sorted/"})
    public List<PaymentDto> getSortedPayments() throws ApiRequestException {
        List<PaymentDto> paymentDtos = new ArrayList<>();
        List<Payment> allSorted = paymentService.getAllSortedPayment();

        for (Payment payment : allSorted) {
            paymentDtos.add(convertToDto(payment));
        }
        return paymentDtos;
    }


    /**
     * Rest method to delete a payment by id
     * 
     * @param id
     * @throws ApiRequestException
     */
    @DeleteMapping(value = {"/payments/{id}", "/payments/{id}/"})
    public void deletePayment(@PathVariable("id") Integer id) throws ApiRequestException {
        paymentService.deletePayment(id);
    }


    public static PaymentDto convertToDto(Payment p) {
        if (p == null) {
            throw new ApiRequestException("Payment does not exist");
        }

        return new PaymentDto(p.getId(), p.getAmount(), p.getPaymentType(), p.getPaymentCode(), p.getOrder());
    }


}

