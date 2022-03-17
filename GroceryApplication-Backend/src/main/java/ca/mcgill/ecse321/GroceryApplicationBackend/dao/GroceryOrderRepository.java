package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder.OrderStatus;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface GroceryOrderRepository extends CrudRepository<GroceryOrder, String> {

    /**
     * Find grocery order via id
     * @param id
     */
    GroceryOrder findGroceryOrderById(int id);
    
    void deleteGroceryOrderById(int id);

    List<GroceryOrder> findAll();

    List<GroceryOrder> findAllByStatus(OrderStatus status);

    List<GroceryOrder> findAllByStatusOrderByDatePlacedDesc(OrderStatus status);

}