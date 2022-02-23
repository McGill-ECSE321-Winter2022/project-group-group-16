package ca.mcgill.ecse321.GroceryApplicationBackend.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder;


public interface GroceryOrderRepository extends CrudRepository<GroceryOrder, String> {

	/**
	 * Find grocery order via id
	 * @param id
	 * @param groceryOrder (GroceryOrder)
	 *  
	 *  
	 *  */
	
    GroceryOrder findGroceryOrderById(int id);
}