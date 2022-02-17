package ca.mcgill.ecse321.GroceryApplicationBackend.dao;
import javax.persistence.criteria.Order;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Address;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{
	Customer findCustomerByUserId(int id);
    Customer findCustomerByAddress(Address address);
    Customer findCustomerByOrder(Order order);
    boolean existsByAddressAndOrder(Address addressName, Order orderName);
}
