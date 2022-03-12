package ca.mcgill.ecse321.GroceryApplicationBackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.GroceryApplicationBackend.dto.PaymentDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Payment;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Payment.PaymentType;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.PaymentService;

@CrossOrigin(origins = "*")
@RestController
public class PaymentRestController {

    @Autowired
    PaymentService paymentService;

    
    /** 
     * @param createPayment(
     * @return PaymentDto
     */
    @PostMapping(value = {"/payment", "/payment/"})
    public PaymentDto createPayment(
        @RequestParam Integer applicationId,
        @RequestParam Integer orderId,
        @RequestParam Integer id,
        @RequestParam Float amount,
        @RequestParam PaymentType paymentType,
        @RequestParam String paymentCode) throws ApiRequestException{
            return convertToDto(paymentService.createPayment(applicationId, orderId, amount, paymentType, paymentCode));
    }

    
    /** 
     * @param updatePayment(
     * @return PaymentDto
     */
    @PutMapping(value = {"/payment/updatePayment/{id}", "/payment/updatePayment/{id}/"})
    public PaymentDto updatePayment(
        @PathVariable("id") Integer id,
        @RequestParam Float amount,
        @RequestParam PaymentType paymentType,
        @RequestParam String paymentCode) throws ApiRequestException{
            return convertToDto(paymentService.updatePayment(id, amount, paymentType, paymentCode));
    }

    
    /** 
     * @param id
     * @return PaymentDto
     */
    @GetMapping(value = {"/payment/{id}", "/payment/{id}/"})
    public PaymentDto getPaymentById(@PathVariable("id") Integer id) throws ApiRequestException{
        return convertToDto(paymentService.getPaymentById(id));
    }

    
    /** 
     * @return List<PaymentDto>
     */
    @GetMapping(value = {"/payment", "/payment/"})
    public List<PaymentDto> getAllPayments() throws ApiRequestException{
        List<PaymentDto> paymentDtos = new ArrayList<>();
	    for (Payment payment : paymentService.getAllPayments()) {
	      paymentDtos.add(convertToDto(payment));
	    }
	    return paymentDtos;
    }

    
    /** 
     * @return List<Float>
     */
    @GetMapping(value = {"/payments/sorted", "/payments/sorted/"})
    public List<Float> getSortedPayments() throws ApiRequestException{
        List<Float> paymentDtos = new ArrayList<>();
	    for (Float payment : paymentService.getAllSortedPayment()) {
	      paymentDtos.add(payment);
	    }
	    return paymentDtos;
    }

    
    /** 
     * @param id
     * @return boolean
     */
    @DeleteMapping(value = {"/payments/{id}", "/payments/{id}/"})
    public boolean deletePayment(@PathVariable("id") Integer id) throws ApiRequestException{
        if(id == 0) {
            throw new ApiRequestException("The id is not valid.");
        }
        return paymentService.deletePayment(id);
    }

    
    /** 
     * @param paymentDto
     * @return boolean
     */
    @DeleteMapping(value = {"/payments", "/payments/"})
    public boolean deletePayment(PaymentDto paymentDto) throws ApiRequestException{
        if(paymentDto == null) {
            throw new ApiRequestException("The order is not valid.");
        }
        return paymentService.deletePayment(paymentDto.getId());
    }

    
    //helper
    public static PaymentDto convertToDto(Payment p) {
    	if(p == null) {
    		throw new ApiRequestException("Payment does not exist");
    	}

        return new PaymentDto(p.getId(), p.getAmount(), p.getPaymentType(), p.getPaymentCode(), p.getOrder());
    }

    
}

