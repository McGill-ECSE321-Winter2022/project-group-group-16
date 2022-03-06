package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.*;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.*;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder.OrderStatus;

@Service
public class OrderService {

    @Autowired
    GroceryOrderRepository groceryOrderRepository;

    @Autowired
    GroceryStoreApplicationRepository groceryStoreApplicationRepository;

    @Autowired
    AddressRepository addressRepository;

    @Transactional
    public GroceryOrder placeOrder(OrderStatus status, int id, Date datePlaced, Date deliveryDate, String customerNote){
        
        GroceryStoreApplication gs = new GroceryStoreApplication();
        gs.setId(011);
        groceryStoreApplicationRepository.save(gs);

        GroceryOrder order = new GroceryOrder();
        order.setGroceryStoreApplication(gs);
        order.setStatus(status);
        order.setId(id);
        order.setDatePlaced(datePlaced);
        order.setDeliveryDate(deliveryDate);
        order.setCustomerNote(customerNote);
        
        groceryOrderRepository.save(order);
        return null;
        
    }

    @Transactional
    public GroceryOrder deleteOrder(int id) throws Exception{

        GroceryOrder order = groceryOrderRepository.findGroceryOrderById(id);
        if(order == null){
            throw new Exception("order does not exist");
        }
        
        GroceryOrder deletedO = null;
        groceryOrderRepository.deleteGroceryORderById(id);

        return deletedO;
        
    }
    
    
}
