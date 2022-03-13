package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.*;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.*;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder.OrderStatus;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder.PurchaseType;

@Service
public class OrderService {

    @Autowired
    GroceryOrderRepository groceryOrderRepository;

    @Autowired
    GroceryStoreApplicationRepository groceryStoreApplicationRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PaymentRepository paymentRepository;
    
    /**
     * Service method for placing an order
     * 
     * @param applicationId
     * @param status
     * @param datePlaced
     * @param deliveryDate
     * @param customerNote
     * @param purchaseType
     * @return placed order
     */
    @Transactional
    public GroceryOrder placeOrder(Integer applicationId, OrderStatus status, Date datePlaced, Date deliveryDate, String customerNote, PurchaseType purchaseType){

        GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);

        if(gs == null){
            throw new ApiRequestException("The application doesn't exist.");
        }

        GroceryOrder order = new GroceryOrder();
        order.setGroceryStoreApplication(gs);
        order.setStatus(status);
        order.setDatePlaced(datePlaced);
        order.setDeliveryDate(deliveryDate);
        order.setCustomerNote(customerNote);
        order.setPurchaseType(purchaseType);
        
        groceryOrderRepository.save(order);
        return order;
    }



    /** 
     * Service method to update an order status
     * 
     * @param status
     * @param id
     * @return GroceryOrder
     */
    @Transactional
    public GroceryOrder updateOrderStatus(OrderStatus status, Integer id){

        if(groceryOrderRepository.findGroceryOrderById(id)==null) {
    		throw new ApiRequestException("Order id is not valid!");
    	}

        GroceryOrder order = groceryOrderRepository.findGroceryOrderById(id);
        if(status != null){
            order.setStatus(status);
        }

        groceryOrderRepository.save(order);
        return order;
    }

    
    /**
     * Service method to delete an order by id
     * 
     * @param id
     * @return
     */
    @Transactional
    public void deleteOrder(Integer id){

        GroceryOrder order = groceryOrderRepository.findGroceryOrderById(id);
        if(order == null){
            throw new ApiRequestException("Order does not exist");
        }
        groceryOrderRepository.deleteGroceryOrderById(id);
    }

    

    
    /** 
     * Service method to retrieve an order by id
     * 
     * @param id
     * @return GroceryOrder
     */
    @Transactional
    public GroceryOrder getOrderById(Integer id){

        GroceryOrder order = groceryOrderRepository.findGroceryOrderById(id);
    	if(order == null) {
    		throw new ApiRequestException("This id has no associated order");
    	}
    	return order;
    }

    /**
     * Service method to retrieve all orders in the database
     * 
     * @return all orders
     */
    @Transactional
    public List<GroceryOrder> getAllOrders() {
        return toList(groceryOrderRepository.findAll());
    }


    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
    
}
