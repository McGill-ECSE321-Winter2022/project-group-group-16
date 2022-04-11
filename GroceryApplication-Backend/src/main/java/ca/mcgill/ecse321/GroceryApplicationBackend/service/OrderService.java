package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.*;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder.OrderStatus;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder.PurchaseType;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
     * @param applicationId the application ID
     * @param status        the order status
     * @param datePlaced    the date placed
     * @param deliveryDate  the planned delivery date
     * @param customerNote  the customer note
     * @param purchaseType  the purchase type
     * @return placed order
     */
    @Transactional
    public GroceryOrder placeOrder(Integer applicationId, OrderStatus status, Date datePlaced, Date deliveryDate, String customerNote, PurchaseType purchaseType) {

        GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);

        if (gs == null) {
            throw new ApiRequestException("The application doesn't exist!");
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
     * @param status the new status
     * @param id     the order id
     * @return GroceryOrder the updated order
     */
    @Transactional
    public GroceryOrder updateOrderStatus(OrderStatus status, Integer id) {

        if (groceryOrderRepository.findGroceryOrderById(id) == null) {
            throw new ApiRequestException("Order does not exist!");
        }

        GroceryOrder order = groceryOrderRepository.findGroceryOrderById(id);
        if (status != null) {
            order.setStatus(status);
        }

        groceryOrderRepository.save(order);
        return order;
    }


    /**
     * Service method to delete an order by id
     *
     * @param id the order id
     */
    @Transactional
    public void deleteOrder(Integer id) {

        GroceryOrder order = groceryOrderRepository.findGroceryOrderById(id);
        if (order == null) {
            throw new ApiRequestException("Order does not exist!");
        }
        groceryOrderRepository.deleteGroceryOrderById(id);
    }


    /**
     * Service method to retrieve an order by id
     *
     * @param id the order id
     * @return GroceryOrder the requested id
     */
    @Transactional
    public GroceryOrder getOrderById(Integer id) {
        GroceryOrder order = groceryOrderRepository.findGroceryOrderById(id);
        if (order == null) {
            throw new ApiRequestException("Order does not exist!");
        }
        return order;
    }

    /**
     * Service method to mark an order as refunded
     *
     * @param id the order id
     * @return the marked order
     */
    @Transactional
    public GroceryOrder refundOrderById(Integer id) {
        GroceryOrder order = groceryOrderRepository.findGroceryOrderById(id);
        if (order == null) {
            throw new ApiRequestException("Order does not exist!");
        }
        order.setStatus(OrderStatus.CANCELLED);
        return order;
    }

    /**
     * Service method to get all orders
     *
     * @return a list of all orders
     */
    @Transactional
    public List<GroceryOrder> getAllOrders() {
        return toList(groceryOrderRepository.findAll());
    }

    /**
     * Service method to get all placed orders sorted by date
     *
     * @return a list of sorted orders
     */
    @Transactional
    public List<GroceryOrder> getOrdersSortedByDateDesc() {
        return groceryOrderRepository.findAllByStatusOrderByDatePlacedDesc(OrderStatus.PLACED);
    }

    /**
     * Service method to view all completed orders (purchased in store, picked up, or delivered)
     *
     * @return a list of completed orders
     */
    @Transactional
    public List<GroceryOrder> viewCompletedOrders() {
        List<GroceryOrder> completedOrders = new ArrayList<>();
        List<GroceryOrder> toAppend;

        toAppend = groceryOrderRepository.findAllByStatus(OrderStatus.PURCHASED_IN_STORE);
        completedOrders.addAll(toAppend);
        toAppend = groceryOrderRepository.findAllByStatus(OrderStatus.PICKED_UP);
        completedOrders.addAll(toAppend);
        toAppend = groceryOrderRepository.findAllByStatus(OrderStatus.DELIVERED);
        completedOrders.addAll(toAppend);

        return completedOrders;
    }

    /**
     * Service method to view all order to be delivered
     *
     * @return a list of orders to be delivered
     */
    @Transactional
    public List<GroceryOrder> viewOrdersToBeDelivered() {
        return groceryOrderRepository.findAllByStatus(OrderStatus.PLACED);
    }


    /**
     * Helper method to convert an iterable to a list
     *
     * @param iterable the iterable
     * @param <T>      the type
     * @return a converted list
     */
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

}
