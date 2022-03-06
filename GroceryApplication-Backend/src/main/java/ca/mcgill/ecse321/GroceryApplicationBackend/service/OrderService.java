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

    @Transactional
    public GroceryOrder placeOrder(OrderStatus status, int id, Date datePlaced, Date deliveryDate, String customerNote, PurchaseType purchaseType, Set<Product> product, Address billingAddress, Customer customer, Address shippingAddress, Payment payment){
        
        Set<Product> productSet = new HashSet();

        GroceryStoreApplication gs = new GroceryStoreApplication();
        gs.setId(011);
        groceryStoreApplicationRepository.save(gs);

        Address address = new Address();
        address.setId(808);
        addressRepository.save(address);

        Product product1 = new Product();
        product1.setGroceryStoreApplication(gs);
        product1.setBarcode(123);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setGroceryStoreApplication(gs);
        product2.setBarcode(1234);
        productRepository.save(product2);

        productSet.add(product2);
        productSet.add(product1);

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

    @Transactional
    public GroceryOrder getOrderById(int id) throws Exception {
    	if(groceryOrderRepository.findGroceryOrderById(id) == null) {
    		throw new Exception("This id has no associated order");
    	}
    	return groceryOrderRepository.findGroceryOrderById(id);
    }
    
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
