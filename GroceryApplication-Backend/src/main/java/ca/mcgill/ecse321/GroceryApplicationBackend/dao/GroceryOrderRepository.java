// package ca.mcgill.ecse321.GroceryApplicationBackend.dao;
// import javax.persistence.criteria.Order;

// import org.springframework.data.repository.CrudRepository;

// import ca.mcgill.ecse321.GroceryApplicationBackend.model.Address;
// import ca.mcgill.ecse321.GroceryApplicationBackend.model.Customer;
// import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder;
// import ca.mcgill.ecse321.GroceryApplicationBackend.model.Payment;

// public interface GroceryOrderRepository extends CrudRepository<GroceryOrder, String> {
    
//     GroceryOrder finGroceryOrderById(int id);
//     GroceryOrder findGroceryOrderPayment(Payment payment);
//     GroceryOrder findGroceryByShippingAddress(Address shippingAddress);
//     GroceryOrder findGroceryByBillingAddress(Address billingAddress);
//     GroceryOrder findGroceryByPayment(Payment payment);
//     GroceryOrder findGroceryByGroceryCustomer(Customer customer);
//     boolean existsByShippingAddressAndpaymnet(Address shipping, Payment payment);
//     boolean existsByBillingAddressAndPayment(Address billing, Payment payment);
//     boolean existsByCustomerAndPayment(Customer customer, Payment payment);
//     boolean existsByShippingAddressAndCustomer(Address shipping, Customer customer);
//     boolean existsByBillingAddressAndCustoemr(Address billing, Customer customer);
    
    
// }
