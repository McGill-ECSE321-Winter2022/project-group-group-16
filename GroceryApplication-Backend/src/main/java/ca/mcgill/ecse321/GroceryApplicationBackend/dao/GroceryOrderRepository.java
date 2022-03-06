package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder;
import org.springframework.data.repository.CrudRepository;


public interface GroceryOrderRepository extends CrudRepository<GroceryOrder, String> {

    /**
     * Find grocery order via id
     *
     * @param id
     * @param groceryOrder (GroceryOrder)
     */

    GroceryOrder findGroceryOrderById(int id);
    void deleteGroceryORderById(int id);
}