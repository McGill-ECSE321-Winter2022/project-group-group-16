package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {


    /**
     * Find payment via id
     *
     * @param id
     * @return payment (Payment)
     */
    Payment findPaymentById(int id);
    void deletePaymentById(int id);
}