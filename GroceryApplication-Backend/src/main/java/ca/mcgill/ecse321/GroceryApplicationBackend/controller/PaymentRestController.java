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



    @PostMapping(value = {"/payment", "/payment/"})
    public PaymentDto createPayment(
            @RequestParam Integer orderId,
            @RequestParam Float amount,
            @RequestParam PaymentType paymentType,
            @RequestParam String paymentCode) throws ApiRequestException {
        return convertToDto(paymentService.createPayment(orderId, amount, paymentType, paymentCode));
    }



    @PutMapping(value = {"/payment/updatePayment/{id}", "/payment/updatePayment/{id}/"})
    public PaymentDto updatePayment(
            @PathVariable("id") Integer id,
            @RequestParam Float amount,
            @RequestParam PaymentType paymentType,
            @RequestParam String paymentCode) throws ApiRequestException {
        return convertToDto(paymentService.updatePayment(id, amount, paymentType, paymentCode));
    }


    /**
     * @param id
     * @return PaymentDto
     */
    @GetMapping(value = {"/payment/{id}", "/payment/{id}/"})
    public PaymentDto getPaymentById(@PathVariable("id") Integer id) throws ApiRequestException {
        return convertToDto(paymentService.getPaymentById(id));
    }


    /**
     * @return List<PaymentDto>
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
     * @return List<Float>
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
     * @param id
     * @return boolean
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

