package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.*;
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

    
    /** 
     * @param status
     * @param id
     * @param datePlaced
     * @param deliveryDate
     * @param customerNote
     * @param purchaseType
     * @param product
     * @param billingAddress
     * @param customer
     * @param shippingAddress
     * @param payment
     * @return GroceryOrder
     * @throws Exception
     */
    @Transactional
    public GroceryOrder placeOrder(int barcode, int applicationId, int addressId, OrderStatus status, int id, Date datePlaced, Date deliveryDate, String customerNote, PurchaseType purchaseType, Address billingAddress, Customer customer, Address shippingAddress, Payment payment) throws Exception{
        
        Set<Product> productSet = new HashSet();

        GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);

        if(gs == null){
            throw new Exception("The application doesn't exist.");
        }

        Address address = addressRepository.findAddressById(addressId);

        if(address == null){
            throw new Exception("The address doesn't exist.");
        }

        Product products = productRepository.findProductByBarcode(barcode);

        if(products == null){
            throw new Exception("The product doesn't exist.");
        }

        productSet.add(products);

        GroceryOrder order = new GroceryOrder();
        order.setGroceryStoreApplication(gs);
        order.setStatus(status);
        order.setId(id);
        order.setDatePlaced(datePlaced);
        order.setDeliveryDate(deliveryDate);
        order.setCustomerNote(customerNote);
        order.setPurchaseType(purchaseType);
        order.setBillingAddress(address);
        order.setProduct(productSet);
        order.setCustomer(customer);
        order.setShippingAddress(shippingAddress);
        order.setPayment(payment);
        
        groceryOrderRepository.save(order);
        return order;
    }

    
    /** 
     * @param status
     * @param id
     * @return GroceryOrder
     * @throws Exception
     */
    @Transactional
    public GroceryOrder updateOrderStatus(OrderStatus status, int id) throws Exception{

        if(groceryOrderRepository.findGroceryOrderById(id)==null) {
    		throw new Exception("Order id is not valid!");
    	}

        GroceryOrder order = groceryOrderRepository.findGroceryOrderById(id);
        if(status != null){
            order.setStatus(status);
        }

        groceryOrderRepository.save(order);
        return order;
    }

    
    /** 
     * @param id
     * @return GroceryOrder
     * @throws Exception
     */
    @Transactional
    public GroceryOrder deleteOrder(int id) throws Exception{

        GroceryOrder order = groceryOrderRepository.findGroceryOrderById(id);
        if(order == null){
            throw new Exception("Order does not exist");
        }
        
        GroceryOrder deletedO = null;
        groceryOrderRepository.deleteGroceryOrderById(id);

        return deletedO;
        
    }

    
    /** 
     * @param id
     * @return GroceryOrder
     * @throws Exception
     */
    @Transactional
    public GroceryOrder getOrderById(int id) throws Exception {
    	if(groceryOrderRepository.findGroceryOrderById(id) == null) {
    		throw new Exception("This id has no associated order");
    	}
    	return groceryOrderRepository.findGroceryOrderById(id);
    }
    
    
    /** 
     * @return List<GroceryOrder>
     */
    @Transactional
    public List<GroceryOrder> getAllOrders() {
        return toList(groceryOrderRepository.findAll());
    }

    
    /** 
     * @param iterable
     * @return List<T>
     */
    //helper
    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
    
}
