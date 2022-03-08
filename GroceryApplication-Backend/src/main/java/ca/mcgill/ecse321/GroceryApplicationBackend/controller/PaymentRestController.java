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
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.GroceryApplicationBackend.dto.PaymentDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Payment;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Payment.PaymentType;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.InvalidInputException;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.PaymentService;

@CrossOrigin(origins = "*")
@RestController
public class PaymentRestController {

    @Autowired
    PaymentService paymentService;

    @PostMapping(value = {"/payment", "/payment/"})
    public PaymentDto createPayment(
        @PathVariable("applicationId") int applicationId,
        @PathVariable("orderId") int orderId,
        @PathVariable("id") int id,
        @PathVariable("amount") float amount,
        @PathVariable("paymentType") PaymentType paymentType,
        @PathVariable("paymentCode") String paymentCode){
            return convertToDto(paymentService.createPayment(applicationId, orderId, id, amount, paymentType, paymentCode));
    }

    @PutMapping(value = {"/payment/updatePayment", "/payment/updatePayment/"})
    public PaymentDto updatePayment(
        @PathVariable("id") int id,
        @PathVariable("amount") float amount,
        @PathVariable("paymentType") PaymentType paymentType,
        @PathVariable("paymentCode") String paymentCode){
            return convertToDto(paymentService.updatePayment(id, amount, paymentType, paymentCode));
    }

    @GetMapping(value = {"/payment/{id}", "/payment/{id}/"})
    public PaymentDto getPaymentById(@PathVariable("id") int id){
        return convertToDto(paymentService.getPaymentById(id));
    }

    @GetMapping(value = {"/payment/{id}", "/payment/{id}/"})
    public List<PaymentDto> getAllPayments(){
        List<PaymentDto> paymentDtos = new ArrayList<>();
	    for (Payment payment : paymentService.getAllPayments()) {
	      paymentDtos.add(convertToDto(payment));
	    }
	    return paymentDtos;
    }

    @GetMapping(value = {"/payments/sorted", "/payments/sorted/"})
    public List<Float> getSortedPayments(){
        List<Float> paymentDtos = new ArrayList<>();
	    for (Float payment : paymentService.getAllSortedPayment()) {
	      paymentDtos.add(payment);
	    }
	    return paymentDtos;
    }

    @DeleteMapping(value = {"/payments", "/payments/"})
    public boolean deletePayment(@PathVariable("id") int id){
        if(id == 0) {
            throw new InvalidInputException("The id is not valid.");
        }
        return paymentService.deletePayment(id);
    }

    @DeleteMapping(value = {"/payments", "/payments/"})
    public boolean deletePayment(PaymentDto paymentDto){
        if(paymentDto == null) {
            throw new InvalidInputException("The order is not valid.");
        }
        return paymentService.deletePayment(paymentDto.getId());
    }

    //helper
    public static PaymentDto convertToDto(Payment p) {
    	if(p == null) {
    		throw new IllegalArgumentException("Payment does not exist");
    	}

        return new PaymentDto(p.getId(), p.getAmount(), p.getPaymentType(), p.getPaymentCode(), p.getOrder());
    }

    
}

