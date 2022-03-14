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

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.AddressRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.CustomerRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.StoreRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Address;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Customer;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Store;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.AddressService;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {
	
	@Mock
	private AddressRepository addressRepository;
	
	@Mock 
	private CustomerRepository customerRepository;
	
	@InjectMocks
	private AddressService addressService;
	
	private static final Integer CUSTOMERID = 6942969;
	
	
	private static final Integer ADDRESSID = 1;
	private static final int STREETNUMBER = 450;
	private static final String STREETNAME = "Bourg";
	private static final String CITY = "Montreal";
	private static final String COUNTRY = "Canada";
	private static final String PROVINCE ="Quebec";
	private static final String POSTALCODE ="H8R 3R1";

	private static final Integer NEWSTREENUMBER =1234567;
	private static String NEWSTREETNAME = "NEWSTREETNAME";
	private static String NEWCITY = "NEWCITY";
	private String NEWPROVINCE = "NEWPROVINCE";
	private String NEWCOUNTRY = "NEWCOUNTRY";
	private String NEWPOSTALCODE = "NEWPOSTALCODE";
	
	/**
	 * 
	 * Service test method for address
	 *  
	 *  
	 *  */
	
	@BeforeEach
	public void setMockOutput() {
		
		lenient().when(addressRepository.findAddressById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			
			if(invocation.getArgument(0).equals(ADDRESSID)) {
				
				Address address = new Address ();
				Customer customer = new Customer();
				Store store = new Store();	
				address.setStore(store);
				address.setCustomer(customer);
				address.setId(ADDRESSID);
				address.setStreetName(STREETNAME);
				address.setStreetNumber(STREETNUMBER);
				address.setCity(CITY);
				address.setCountry(COUNTRY);
				address.setProvince(PROVINCE);
				address.setPostalCode(POSTALCODE);

		
				return address;
				
				
			} else {
				
				return null;
				
			}
			
			
		});
		
		
		lenient().when(addressRepository.findAddressByCity(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			
			if(invocation.getArgument(0).equals(CITY)) {
				Address address = new Address();
				
				Customer customer = new Customer();
				Store store = new Store();				
				address.setStore(store);
				address.setCustomer(customer);
				
				address.setId(ADDRESSID);
				address.setStreetNumber(STREETNUMBER);
				address.setStreetName(STREETNAME);
				address.setCity(CITY);
				address.setCountry(COUNTRY);
				address.setProvince(PROVINCE);
				address.setPostalCode(POSTALCODE);
				return address;

			}
			
			
			else {
			
			
			return null;
			
			}
		});
		
		lenient().when(customerRepository.findCustomerById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			
			
			if(invocation.getArgument(0).equals(CUSTOMERID)) {
				
				Customer customer = new Customer();
				customer.setId(CUSTOMERID);
				return customer;
				
			}
			
			
			
			return null;
			
		});
		
		
		
		
		
		//When anything is saved, return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> invocation.getArgument(0);
			

		lenient().when(addressRepository.save(any(Address.class))).thenAnswer(returnParameterAsAnswer);

		
	}
	
	//A test for creating an address successfully
	@Test
	public void testCreateAddress() {
		Address address = null;
		
		try {
			
			address = addressService.createAddress(STREETNUMBER, STREETNAME, PROVINCE, CITY, COUNTRY, POSTALCODE);
			
		} catch(ApiRequestException e) {
			fail();
			
			
		}
		
		assertNotNull(address);
		assertEquals(address.getStreetNumber(),STREETNUMBER);
		assertEquals(address.getStreetName(), STREETNAME);
		assertEquals(address.getProvince(), PROVINCE);
		assertEquals(address.getCity(), CITY);
		assertEquals(address.getCountry(), COUNTRY);
		assertEquals(address.getPostalCode(),POSTALCODE);

		
		
	}
	//Test to create address with empty street name
	@Test
	public void testCreateAddressEmptyStreetName() {
		Address address = null;
		String error = null;
		try {
			address = addressService.createAddress(STREETNUMBER,"", PROVINCE, CITY, COUNTRY, POSTALCODE);
			
			
		} catch(ApiRequestException e) {
			error = e.getMessage();
			
			
		}
		
		assertNull(address);
		assertEquals("Street name is null or empty.", error);
		
		
	}
	//Test to create address with empty province
	@Test
	public void testCreateAddressEmptyProvince() {
		Address address = null;
		String error = null;
		try {
			address = addressService.createAddress(STREETNUMBER, STREETNAME, "", CITY, COUNTRY, POSTALCODE);
			
			
		} catch(ApiRequestException e) {
			error = e.getMessage();
			
			
		}
		
		assertNull(address);
		assertEquals("Province is null or empty.", error);
		
		
	}
	//Test to create an address with empty city
	@Test
	public void testCreateAddressEmptyCity() {
		Address address = null;
		String error = null;
		try {
			address = addressService.createAddress(STREETNUMBER, STREETNAME, PROVINCE, "", COUNTRY, POSTALCODE);
			
			
		} catch(ApiRequestException e) {
			error = e.getMessage();
			
			
		}
		
		assertNull(address);
		assertEquals("City is null or empty.", error);
		
		
	}
	//Test to create an address with empty postal code
	@Test
	public void testCreateAddressEmptyPostalCode() {
		Address address = null;
		String error = null;
		try {
			address = addressService.createAddress(STREETNUMBER, STREETNAME, PROVINCE, CITY, COUNTRY, "");
			
			
		} catch(ApiRequestException e) {
			error = e.getMessage();
			
			
		}
		
		assertNull(address);
		assertEquals("Postal Code is null or empty.", error);
		
		
	}
	//Test to create an address with empty country
	@Test
	public void testCreateAddressEmptyCountry() {
		Address address = null;
		String error = null;
		try {
			address = addressService.createAddress(STREETNUMBER, STREETNAME, PROVINCE, CITY, "", POSTALCODE);
			
			
		} catch(ApiRequestException e) {
			error = e.getMessage();
			
			
		}
		
		assertNull(address);
		assertEquals("Country is null or empty.", error);
		
		
	}
	
	//Test for updating the address
	@Test
	public void testUpdateAddress() {
		
		Address address = null;

		try {
			address = addressService.updateAddress(ADDRESSID, NEWSTREENUMBER, NEWSTREETNAME, NEWPROVINCE, NEWCITY, NEWCOUNTRY, NEWPOSTALCODE );
			
		} catch(ApiRequestException e) {
			
			fail();
			
		}
		assertNotNull(address);
		assertEquals(ADDRESSID,address.getId());
		assertEquals(NEWSTREENUMBER, address.getStreetNumber());
		assertEquals(NEWSTREETNAME, address.getStreetName());
		assertEquals(NEWPROVINCE, address.getProvince());
		assertEquals(NEWCITY, address.getCity());
		assertEquals(NEWCOUNTRY, address.getCountry());
		assertEquals(NEWPOSTALCODE, address.getPostalCode());
		
		
		
		
	}
	
	//Test for updating the  address by nonexistant Id
	@Test
	public void testUpdateAddressByInexistantId() {
		Address address = null;
		String error = null;
		try {
		address = addressService.updateAddress(6789, 1234567, "NewStreetName", "NewProvince", "NewCity", "NewCountry", "NewPostalCode");
		}
		catch(Exception e) {
			
			error = e.getMessage();
		}
		
		assertNull(address);
		assertEquals("No address exists with id:" + 6789, error);
		
		
	}
	
	//Test for updating the address with empty Id
	@Test
	public void testUpdateAddressByEmptyId() {
		Address address = null;
		String error = null;
		try {
			address = addressService.updateAddress(null, 1234567, "NewStreetName", "NewProvince", "NewCity", "NewCountry", "NewPostalCode");
			
			
		}catch(Exception e) {
			error = e.getMessage();
			
		}
		assertNull(address);
		assertEquals("No address exists with id:" + null ,error);
		
		
	}

	
	//Test to delete address with empty id
	@Test
	public void testDeleteAdressWithNullId() {
		String error = null;
		try {
			
			addressService.deleteAddress(null);
		}catch(ApiRequestException e) {
			error = e.getMessage();
			
			
		}
		assertEquals(error,"No address exists with id:" + null);

	}
	
	//Test to delete with invalid id
	@Test
	public void testDeleteAddressWithInvalidId() {
		String error = null;
		try {
			
			addressService.deleteAddress(12345);
		} catch(ApiRequestException e) {
			error = e.getMessage();
			
		}
		
		assertEquals(error, "No address exists with id:" + 12345);
		
		
	}
	
	
	//Test to delete address
	@Test
	public void testDeleteAddress() {

		try {
			
			addressService.deleteAddress(ADDRESSID);
			
		} catch(ApiRequestException e) {
			
			fail();
			
		}
		
		
		
	}
	

	//Test get the address by id
	@Test
	public void testGetAddress() {
		Address address = null;
		try {
			
			address = addressService.getAddressById(ADDRESSID);
		} catch(ApiRequestException e) {
			
			fail();
			
		}
		
		assertNotNull(address);
		assertEquals(ADDRESSID, address.getId());
		
	}

	
	//Test for getting the address by nonexistant Id
	@Test
	public void testGetAddressByEmptyId() {
		Address address = null;
		String error = null;
		
		try {
			address = addressService.getAddressById(null);
			}
			catch(Exception e) {
				
				error = e.getMessage();
			}
		
		assertNull(address);
		assertEquals("No address exists with id:"+ null, error );
		
		
		
	}
	
	
	//Test getting address with empty id
	@Test
	public void testGetAddressByInexistantId() {
		Address address = null;
		String error = null;
		
		try {
			address = addressService.getAddressById(1237890);
			}
			catch(Exception e) {
				
				error = e.getMessage();
			}
		
		assertNull(address);
		assertEquals("No address exists with id:"+ 1237890, error );
		
		
		
	}
	
	
	

}
