package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {
	
	
	/**
	 * Find payment via id
	 * @param id
	 * @return payment (Payment)
	 * */
	Payment findPaymentById(int id);
}