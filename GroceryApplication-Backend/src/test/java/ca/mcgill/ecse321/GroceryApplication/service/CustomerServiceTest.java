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

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.AddressRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.CategoryRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.CustomerRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryStoreApplicationRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryUserRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Address;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Category;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Customer;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryUser;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.CustomerService;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.GroceryUserService;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
	@Mock
	private CustomerRepository customerRepository;
	
	@Mock
	private GroceryUserRepository groceryUserRepository;
	
	@Mock
	private AddressRepository addressRepository;
	
	@Mock
	private GroceryStoreApplicationRepository groceryStoreApplicationRepository;
	
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
	
	private static final String INVALID_USER_EMAIL = "dannytutu@gmail.com";
	private static final Integer INVALID_APPLICATION_ID = 37;
	private static final Integer INVALID_ADDRESS_ID = 459;
	
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
		
		lenient().when(groceryStoreApplicationRepository.findGroceryStoreApplicationById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			
			if(invocation.getArgument(0).equals(APPLICATIONID)) {
				GroceryStoreApplication gs = new GroceryStoreApplication();
				
				gs.setId(APPLICATIONID);
			
				return gs;
				
			} else {
				return null;
			}
		});
		
		lenient().when(groceryUserRepository.findGroceryUserByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			
			if(invocation.getArgument(0).equals(USEREMAIL)) {
				GroceryUser gu = new GroceryUser();
				
				gu.setEmail(USEREMAIL);
			
				return gu;
				
			} else {
				return null;
			}
		});
		
		lenient().when(addressRepository.findAddressById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			
			if(invocation.getArgument(0).equals(ADDRESSID)) {
				Address address = new Address();
				
				address.setId(ADDRESSID);
			
				return address;
				
			} else {
				return null;
			}
		});
		

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			
			return invocation.getArgument(0);
		};
		lenient().when(customerRepository.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);
	}
	
		//Test for creating a customer successfully
		@Test
		public void testCreateCustomer() {
			Customer customer = null;
			try {
				customer = customerService.createCustomer(APPLICATIONID, ADDRESSID, USEREMAIL);
			} catch(ApiRequestException e) {
				fail();
			}
			assertNotNull(customer);
			assertEquals(customer.getGroceryStoreApplication().getId(), APPLICATIONID);
			assertEquals(customer.getAddress().getId(),ADDRESSID);
			assertEquals(customer.getUser().getEmail(),USEREMAIL);
		}
		
		//test for creating a customer with empty name
		@Test
		public void testCreateCustomerWithEmptyUserEmail() {
			Customer customer = null;
			String error = null;
			try {
				customer = customerService.createCustomer(APPLICATIONID, ADDRESSID, "");
								
			} catch(ApiRequestException e) {
				error = e.getMessage();
								
			}
			
			assertNull(customer);
			assertEquals("requested userEmail is null or length 0. Please enter valid userEmail.\n", error);
						
		}
		
		//test for creating a customer with empty grocery store
		@Test
		public void testCreateCustomerEmptyGroceryStore() {
			Customer customer = null;
			String error = null;

			try {
				customer = customerService.createCustomer(INVALID_APPLICATION_ID, ADDRESSID, USEREMAIL);
				
			} catch(ApiRequestException e) {
				error = e.getMessage();
								
			}
			
			assertNull(customer);
			assertEquals("No application associated with this Id.", error);
			
			
		}
		
		//test for creating a customer with empty grocery store
		@Test
		public void testCreateCustomerEmptyGroceryUser() {
			Customer customer = null;
			String error = null;

				try {
					customer = customerService.createCustomer(APPLICATIONID, ADDRESSID, INVALID_USER_EMAIL);	
					
				} catch(ApiRequestException e) {
					error = e.getMessage();
										
				}
					
				assertNull(customer);
				assertEquals("No user associated with this email", error);
					
		}
		
		//test for creating a customer with empty grocery store
		@Test
		public void testCreateCustomerEmptyAddress() {
			Customer customer = null;
			String error = null;

				try {
					customer = customerService.createCustomer(APPLICATIONID, INVALID_ADDRESS_ID, USEREMAIL);
							
				} catch(ApiRequestException e) {
					error = e.getMessage();
												
				}
							
				assertNull(customer);
				assertEquals("No address associated with this Id", error);
							
		}
		
		//Test delete customer by id
		@Test
		public void testDeleteCustomer() {
			
			try{
				customerService.deleteCustomer(CUSTOMERID);
				
			} catch(ApiRequestException e) {
				fail();	
			}

		}
		//Test for deleting a customer with wrong id
		
		@Test 
		public void testDeleteCustomerWrongId() {
			
			Customer customer = null;
			String error = null;

			Integer INCORRECTID = 123123123;
			
			try {
				customer = customerService.deleteCustomer(INCORRECTID);
				
				
			}catch(ApiRequestException e) {
				error = e.getMessage();
				
				
				
			}
			assertNull(customer);
			assertEquals("Customer account with provided id does not exist.",error);
			
			
		}
		
		//Test update customer
		@Test 
		public void testUpdateCustomer() {
			Customer customer = null;
			
			try {
				customer = customerService.updateCustomer(CUSTOMERID,ADDRESSID);
				
			} catch(ApiRequestException e) {		
				fail();
			}
			assertNotNull(customer);
			assertEquals(customer.getAddress().getId(),ADDRESSID);
			assertEquals(customer.getId(),CUSTOMERID);

		}
		
		//Test update customer with invalid address id
		@Test
		public void tesUpdateCustomerWithInvalidAddressId() {
			Customer customer = null;
			String error = "";

			try {
				
				customer = customerService.updateCustomer(CUSTOMERID,INVALID_ADDRESS_ID);
				
			} catch(ApiRequestException e) {
				error = e.getMessage();
				
				
			}
			assertNull(customer);
			assertEquals(error, "No address associated with this Id");
			
			
		}
		

		//Test for getting a customer by id
		@Test
		public void testGetCustomer() {
			
			
			Customer customer = null;
			try {
				
			customer = customerService.getCustomerById(CUSTOMERID);
			} catch(ApiRequestException e) {
				
				fail();
				
			}
			
			assertNotNull(customer);
			assertEquals(CUSTOMERID,customer.getId());
			
			
		}
		
		//Test for getting a customer with wrong id
		
		@Test
		public void testGetCustomerWithWrongId() {
			
			Customer customer = null;
			String error = "";
			
			Integer CUSTOMERWRONGID = 69420;
			try {
				
				customer = customerService.getCustomerById(CUSTOMERWRONGID);
				
			} catch(ApiRequestException e) {
				error = e.getMessage();
				
				
			}
			assertNull(customer);
			assertEquals(error, "Customer account with provided id does not exists");			
			
		}
		

		//Test for getting all the customers
		@Test
		public void testGetAllCustomers() {
			List<Customer> customers = null;
			customers = customerService.getAllCustomers();
			assertNotNull(customers);
						
		}		
	
}