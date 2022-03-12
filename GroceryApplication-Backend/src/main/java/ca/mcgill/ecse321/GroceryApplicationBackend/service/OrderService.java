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
     * @param barcode
     * @param applicationId
     * @param addressId
     * @param status
     * @param datePlaced
     * @param deliveryDate
     * @param customerNote
     * @param purchaseType
     * @param billingAddress
     * @param customer
     * @param shippingAddress
     * @return GroceryOrder
     */
    @Transactional
    public GroceryOrder placeOrder(Integer barcode, Integer applicationId, Integer addressId, OrderStatus status, Date datePlaced, Date deliveryDate, String customerNote, PurchaseType purchaseType, Address billingAddress, Customer customer, Address shippingAddress){
        
        Set<Product> productSet = new HashSet();

        GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);

        if(gs == null){
            throw new ApiRequestException("The application doesn't exist.");
        }

        Address address = addressRepository.findAddressById(addressId);

        if(address == null){
            throw new ApiRequestException("The address doesn't exist.");
        }

        Product products = productRepository.findProductByBarcode(barcode);

        if(products == null){
            throw new ApiRequestException("The product doesn't exist.");
        }

        productSet.add(products);

        GroceryOrder order = new GroceryOrder();
        order.setGroceryStoreApplication(gs);
        order.setStatus(status);
        order.setDatePlaced(datePlaced);
        order.setDeliveryDate(deliveryDate);
        order.setCustomerNote(customerNote);
        order.setPurchaseType(purchaseType);
        order.setBillingAddress(address);
        order.setProduct(productSet);
        order.setCustomer(customer);
        order.setShippingAddress(shippingAddress);
        
        groceryOrderRepository.save(order);
        return order;
    }


    
    /** 
     * @param bAdd
     * @param sAdd
     * @param id
     * @return GroceryOrder
     */
    @Transactional
    public GroceryOrder updateAddress(Address bAdd, Address sAdd, Integer id){

        if(groceryOrderRepository.findGroceryOrderById(id)==null) {
    		throw new ApiRequestException("Order id is not valid!");
    	}

        GroceryOrder order = groceryOrderRepository.findGroceryOrderById(id);
        if(bAdd != null && sAdd != null){
            order.setBillingAddress(bAdd);
            order.setShippingAddress(sAdd);
        }

        groceryOrderRepository.save(order);
        return order;
    }


    
    /** 
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
     * @param id
     * @return boolean
     */
    @Transactional
    public boolean deleteOrder(Integer id){

        GroceryOrder order = groceryOrderRepository.findGroceryOrderById(id);
        if(order == null){
            throw new ApiRequestException("Order does not exist");
        }
        groceryOrderRepository.deleteGroceryOrderById(id);

        return true;
    }

    

    
    /** 
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
     * @return List<GroceryOrder>
     */
    //get all orders from past years
    @Transactional
    public List<GroceryOrder> getAllOrders() {
        return toList(groceryOrderRepository.findAll());
    }


    //helper
    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
    
}
