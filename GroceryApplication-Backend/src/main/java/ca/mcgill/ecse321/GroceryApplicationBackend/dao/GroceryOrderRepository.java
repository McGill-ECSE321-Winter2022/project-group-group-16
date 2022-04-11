package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder.OrderStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface GroceryOrderRepository extends CrudRepository<GroceryOrder, String> {

    /**
     * Find grocery order via id
     *
     * @param id
     */
    GroceryOrder findGroceryOrderById(int id);

    void deleteGroceryOrderById(int id);

    List<GroceryOrder> findAll();

    List<GroceryOrder> findAllByStatus(OrderStatus status);

    List<GroceryOrder> findAllByStatusOrderByDatePlacedDesc(OrderStatus status);

}