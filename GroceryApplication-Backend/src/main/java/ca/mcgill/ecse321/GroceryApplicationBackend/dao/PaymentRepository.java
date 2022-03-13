package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Payment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {


    /**
     * Find payment via id
     *
     * @param id
     * @return payment (Payment)
     */
    Payment findPaymentById(int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Payment p WHERE p.id = :id")
    void deletePaymentById(int id);

    List<Payment> findAllByOrderByAmountDesc();


}