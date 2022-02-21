package ca.mcgill.ecse321.GroceryApplicationBackend.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder;


public interface GroceryOrderRepository extends CrudRepository<GroceryOrder, String> {

    GroceryOrder findGroceryOrderById(int id);
}