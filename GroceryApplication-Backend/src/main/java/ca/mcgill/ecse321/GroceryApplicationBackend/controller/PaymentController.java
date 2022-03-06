package ca.mcgill.ecse321.GroceryApplicationBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.GroceryApplicationBackend.service.PaymentService;

@CrossOrigin(origins = "*")
@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    
}
