package ca.mcgill.ecse321.GroceryApplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.management.InvalidApplicationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.CategoryRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.CustomerRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryUserRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Address;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Category;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Customer;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryUser;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.CategroyService;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.CustomerService;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.GroceryUserService;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
	@Mock
	private CustomerRepository customerRepository;
	private GroceryUserService groceryUserService;
	private GroceryUserRepository groceryUserRepository;
	
	@InjectMocks
	private CustomerService customerService;
	
	private static final String CUSTOMER_KEY = "TestCustomer";
	
	private static final Integer CUSTOMERID = 770;
	private static final Integer APPLICATIONID = 19;
	private static final Integer ADDRESSID = 203;
	private static final String USEREMAIL = "who@gmail.com";
	
	private static final Integer CUSTOMERID2 = 987;
	private static final Integer APPLICATIONID2 = 28;
	private static final Integer ADDRESSID2 = 746;
	private static final String USEREMAIL2 = "asked@gmail.com";
	
	private static final Integer VALID_ID = 11;
	private static final Integer INVALID_ID = 57;
	private static final Integer NULL_ID = null;
	
	/**
	 * 
	 * Service test method for customer
	 *  
	 *  
	 *  */
	
	@BeforeEach
    public void setMockOutput() {
		lenient().when(customerRepository.findCustomerById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			
			if(invocation.getArgument(0).equals(CUSTOMERID)) {
				Customer customer = new Customer();
				Address addy = new Address();
				GroceryUser user = new GroceryUser();
				
				customer.setId(CUSTOMERID);
				customer.setAddress(addy);
				customer.setUser(user);
				user.setEmail(USEREMAIL);

		
				return customer;
				
				
			} else if(invocation.getArgument(0).equals(CUSTOMERID2)) {
				Customer customer = new Customer();
				Address addy2 = new Address();
				GroceryUser user2 = new GroceryUser();
				
				customer.setId(CUSTOMERID2);
				customer.setAddress(addy2);
				customer.setUser(user2);
				user2.setEmail(USEREMAIL2);

		
				return customer;
			}
			else {

			return null;}
			
		});
		
		
		lenient().when(customerRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			
			Customer customer = new Customer();
			Address address = new Address();
			GroceryUser user = new GroceryUser();
			customer.setAddress(address);
			customer.setId(CUSTOMERID);
			customer.setUser(user);
			
			Customer customer2 = new Customer();
			Address address2 = new Address();
			GroceryUser user2 = new GroceryUser();
			customer.setAddress(address2);
			customer.setId(CUSTOMERID);
			customer.setUser(user2);
			
			List<Customer> customers = new ArrayList<Customer>();
			customers.add(customer);
			customers.add(customer2);
				
			
			return customers;
			
		});
		
		//When anything is saved, return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			
			return invocation.getArgument(0);
		};
		lenient().when(customerRepository.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	// Test for creating a customer successfully
		@Test
		public void testCreateCustomer() {
			Customer customer = null;
			try {
				customer = customerService.createCustomer(APPLICATIONID, ADDRESSID, USEREMAIL);
			} catch(Exception e) {
				fail();
			}
			assertNotNull(customer);
			assertEquals(customer.getId(),CUSTOMERID);
			assertEquals(customer.getAddress(),ADDRESSID);
			assertEquals(customer.getGroceryStoreApplication().getId(), APPLICATIONID);
			assertEquals(customer.getUser().getEmail(),USEREMAIL);
		}
		
		//test for creating a customer with empty name
		@Test
		public void testCreateCategoryEmptyUserEmail() {
			Customer customer = null;
			String error = null;
			try {
				customer = customerService.createCustomer(APPLICATIONID, ADDRESSID, "");
								
			} catch(ApiRequestException e) {
				error = e.getMessage();
								
			}
			
			assertNull(customer);
			assertEquals("Customer name is null or empty.", error);
						
		}
		
		//test for creating a customer with empty grocery store
		@Test
		public void testCreateCategoryEmptyGroceryStore() {
			Customer customer = null;
			String error = null;
			GroceryUser groceryUser = null;
			GroceryStoreApplication gsa = null;
			try {
				customer = customerService.createCustomer(APPLICATIONID, ADDRESSID, USEREMAIL);
				groceryUser = groceryUserService.getGroceryUserByEmail(USEREMAIL);		
				gsa = customer.getGroceryStoreApplication();
			} catch(ApiRequestException e) {
				error = e.getMessage();
								
			}
			
			assertNull(customer);
			assertEquals("Customer grocery store is null or empty.", error);
			
			
		}
		
		//Test delete employee by id
		@Test
		public void testDeleteCustomerByiId() {
			
			try{
				customerService.deleteCustomer(ADDRESSID);
				
			} catch(ApiRequestException e) {
				fail();	
			}

		}
		
		//Test for getting all the employees
		@Test
		public void testGetAllEmployees() {
			List<Customer> customers = null;
			customers = customerService.getAllCustomers();
			assertNotNull(customers);
						
		}		
	
}
