package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.management.InvalidApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.*;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.*;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	GroceryStoreApplicationRepository groceryStoreApplicationRepository;

	@Autowired
	GroceryUserRepository groceryUserRepository;
	
	/**
	 * 
	 * @param custiomerId
	 * @param applicationId
	 * @param addressId
	 * @param customerId
	 * @param userEmail
	 * @return
	 * @throws InvalidApplicationException
	 */
	@Transactional
	public Customer createCustomer(int custiomerId, int applicationId, int addressId, int customerId, String userEmail)
			throws InvalidApplicationException {
		if (userEmail == null || userEmail.trim().length() == 0) {
			throw new InvalidApplicationException(
					"requested userEmail is null or length 0. Please enter valid userEmail.\n");
		}

		GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);
		if (gs == null) {
			throw new InvalidApplicationException("No application associated with this Id.");
		}

		GroceryUser gu = groceryUserRepository.findGroceryUserByEmail(userEmail);
		if (gs == null) {
			throw new InvalidApplicationException("No user associated with this email");
		}

		Address address = addressRepository.findAddressById(addressId);
		if (gs == null) {
			throw new InvalidApplicationException("No address associated with this Id");
		}

		Customer customer = new Customer();
		gu.setEmail(userEmail);
		customer.setId(customerId);
		customer.setAddress(address);
		customer.setUser(gu);
		customer.setAddress(address);
		customer.setGroceryStoreApplication(gs);
		customerRepository.save(customer);

		return customer;
	}
	/**
	 * 
	 * @param Id
	 * @return
	 */
	@Transactional
	public Customer getCustomer(int Id) {
		Customer customer = customerRepository.findCustomerById(Id);
		return customer;
	}
	/**
	 * 
	 * @return
	 */
	@Transactional
	public List<Customer> getAllCustomers() {
		return toList(customerRepository.findAll());

	}

	// update customer through Groceyuser?
	/**
	 * 
	 * @param id
	 * @return
	 * @throws InvalidApplicationException
	 */
	@Transactional
	public Customer deleteCustomer(int id) throws InvalidApplicationException {
		if (customerRepository.findCustomerById(id) == null) {
			throw new InvalidApplicationException("Customer account with provided id does not exist.");
		}
		Customer customer = customerRepository.findCustomerById(id);
		customerRepository.delete(customer);
		return customer;
	}
	


	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
