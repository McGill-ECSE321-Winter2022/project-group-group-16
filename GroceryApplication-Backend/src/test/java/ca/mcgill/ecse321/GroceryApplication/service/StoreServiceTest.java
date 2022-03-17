package ca.mcgill.ecse321.GroceryApplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;



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
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryStoreApplicationRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.StoreRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Address;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Store;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.StoreService;


@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {
	
	@Mock
	private StoreRepository storeRepository;
	
	@Mock
	private AddressRepository addressRepository;
	
	@Mock 
	private GroceryStoreApplicationRepository groceryStoreApplicationRepository;
	
	@InjectMocks
	private StoreService storeService;
	

	private static final Integer GROCERYSTOREAPPLICATION = 4444;

	
	private static final Integer ADDRESSID = 12;
	private static final String STORENAME = "IGA";
	
	private static final String input ="10:00:00";
	private static final String input1 = "21:00:00";	
	private static final String input2 ="10:00:00";
	private static final String input3 = "20:00:00";
	
	//time for iga
	private static final LocalTime WEEKDAYOPENING = LocalTime.parse(input);
	private static final LocalTime WEEKDAYCLOSING = LocalTime.parse(input1);	
	private static final LocalTime WEEKENDOPENING = LocalTime.parse(input2);
	private static final LocalTime WEEKENDCLOSING= LocalTime.parse(input3);
	
	private static final String input4 ="10:30:00";
	private static final String input5 = "22:00:00";	
	private static final String input6 ="10:00:00";
	private static final String input7 = "16:00:00";
	
	//time for maxi
	private static final LocalTime WEEKDAYOPENING2 = LocalTime.parse(input4);
	private static final LocalTime WEEKDAYCLOSING2 = LocalTime.parse(input5);	
	private static final LocalTime WEEKENDOPENING2 = LocalTime.parse(input6);
	private static final LocalTime WEEKENDCLOSING2 = LocalTime.parse(input7);
	
	private static final String INVALIDSTORENAME = "JimmyNeutron";
	private static final Integer INVALIDADDRESSID = 13;
	private static final Integer INVALIDAPPGROCERYID = 14;

	@BeforeEach
	public void setMockOutput() {
		//might need to put id of store
		lenient().when(storeRepository.findStoreByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(STORENAME)) {
				
				Store store = new Store();				
				Address address = new Address();				
				address.setId(ADDRESSID);
				
				store.setName(STORENAME);				
				store.setAddress(address);
				store.setWeekDayOpening(WEEKDAYOPENING);
				store.setWeekDayClosing(WEEKDAYCLOSING);
				store.setWeekEndOpening(WEEKENDOPENING);
				store.setWeekEndClosing(WEEKENDCLOSING);

				return store;

			}
			else {

			return null;
			
			}
			
			
		});
		
		lenient().when(groceryStoreApplicationRepository.findGroceryStoreApplicationById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			
			if(invocation.getArgument(0).equals(GROCERYSTOREAPPLICATION)) {
				GroceryStoreApplication gs = new GroceryStoreApplication();
				gs.setId(GROCERYSTOREAPPLICATION);
				return gs;
			
			}

			return null;
			
		});
		
	lenient().when(addressRepository.findAddressById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			
			if(invocation.getArgument(0).equals(ADDRESSID)) {
				Address address = new Address ();
				address.setId(ADDRESSID);
				return address;

			}

			return null;
			
		});

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(storeRepository.save(any(Store.class))).thenAnswer(returnParameterAsAnswer);
	}
		
	
	@Test
	public void testCreateStore() {
		Store store = null;
		try {
			
			store = storeService.createStore(STORENAME, WEEKDAYOPENING, WEEKDAYCLOSING, WEEKENDOPENING, WEEKENDCLOSING, ADDRESSID, GROCERYSTOREAPPLICATION);
		} catch (ApiRequestException e) {
			fail();

		}
		
		assertNotNull(store);
		assertEquals(store.getName(),STORENAME);
		assertEquals(store.getWeekDayOpening(), WEEKDAYOPENING);
		assertEquals(store.getWeekDayClosing(), WEEKDAYCLOSING);
		assertEquals(store.getWeekEndOpening(), WEEKENDOPENING);
		assertEquals(store.getWeekEndClosing(),WEEKENDCLOSING);
		assertEquals(store.getAddress().getId(),ADDRESSID);
		assertEquals(store.getGroceryStoreApplication().getId(),GROCERYSTOREAPPLICATION);
		
	}
	
	
	//Test creating a store without a name
	@Test
	public void testCreateStoreWithoutName() {
		
		Store store = null;
		String error = null;
		try {
			
			store = storeService.createStore("", WEEKDAYOPENING, WEEKDAYCLOSING, WEEKENDOPENING, WEEKENDCLOSING, ADDRESSID , GROCERYSTOREAPPLICATION);
			
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
		}
		
		assertNull(store);
		assertEquals("Name is null or empty.",error);
		
		
	}
	
	//Test creating a store without week day opening
	@Test
	public void testCreateStoreWithoutWEEKDAYOPENING() {
		
		Store store = null;
		String error = null;
		
		try {
			
			store = storeService.createStore(STORENAME, null, WEEKDAYCLOSING, WEEKENDOPENING, WEEKENDCLOSING, ADDRESSID, GROCERYSTOREAPPLICATION);			
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
		}
		
		assertNull(store);
		assertEquals("Weekday opening time is null.",error);
		
	}
	
	//Test for creating a store without a week day closing
	@Test
	public void testCreateStoreWithoutWEEKDAYCLOSING() {
		Store store = null;
		String error = null;
		
		try {
			
			store = storeService.createStore(STORENAME, WEEKDAYOPENING, null, WEEKENDOPENING, WEEKENDCLOSING, ADDRESSID, GROCERYSTOREAPPLICATION);			
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
		}
		
		assertNull(store);
		assertEquals("Weekday closing time is null.",error);
		
		
	}
	
	//Test for creating a store without a weekend opening
	@Test
	public void testCreateStoreWithoutWEEKENDOPENING() {
		Store store = null;
		String error = null;
		
		try {
			
			store = storeService.createStore(STORENAME, WEEKDAYOPENING, WEEKDAYCLOSING, null, WEEKENDCLOSING, ADDRESSID, GROCERYSTOREAPPLICATION);			
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
		}
		
		assertNull(store);
		assertEquals("Weekend opening time is null.",error);
		
		
		
	}
	
	//Test for creating a store without a weekend closing
	@Test
	public void testCreateStoreWithoutWEEKENDCLOSING() {
		Store store = null;
		String error = null;
		
		
		try {
			
			store = storeService.createStore(STORENAME, WEEKDAYCLOSING, WEEKDAYCLOSING, WEEKENDOPENING, null, ADDRESSID, GROCERYSTOREAPPLICATION);			
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
		}
		
		assertNull(store);
		assertEquals("Weekend closing time is null.",error);
		
		
	}
	
	
	//Test for creating a store with invalid Id
	@Test
	public void testCreateStoreWithInvalidId() {
		Store store = null;
		String error = null;
		
		
		try {
			
			store = storeService.createStore(STORENAME, WEEKDAYCLOSING, WEEKDAYCLOSING, WEEKENDOPENING, WEEKENDCLOSING, INVALIDADDRESSID , GROCERYSTOREAPPLICATION);			
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
		}
		
		assertNull(store);
		assertEquals("Address with id" +INVALIDADDRESSID  + "does not exist.",error);

	}
	
	
	//Test for creating a store with invalid grocery store application id
	@Test
	public void testCreateStoreWithInvalidGroceryStoreId() {		
		Store store = null;
		String error = null;
		
	try {
			
			store = storeService.createStore(STORENAME, WEEKDAYCLOSING, WEEKDAYCLOSING, WEEKENDOPENING, WEEKENDCLOSING, ADDRESSID, INVALIDAPPGROCERYID );			
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
		}
		
		assertNull(store);
		assertEquals("Application with id " + INVALIDAPPGROCERYID + " does not exist.",error);
		
	}
	
	//Test for updating store
	@Test
	public void testUpdateStore() {
		Store store = null;
		try {
			store = storeService.updateStore(STORENAME, WEEKDAYOPENING2, WEEKDAYCLOSING2, WEEKENDOPENING2, WEEKENDCLOSING2, ADDRESSID, GROCERYSTOREAPPLICATION);
			
		}catch (ApiRequestException e) {
			
			fail();
			
			
		}
		assertNotNull(store);
		assertEquals(WEEKDAYOPENING2,store.getWeekDayOpening());
		assertEquals(WEEKDAYCLOSING2, store.getWeekDayClosing());
		assertEquals(WEEKENDOPENING2,store.getWeekEndOpening());
		assertEquals(WEEKENDCLOSING2,store.getWeekEndClosing());
		
		
		
	}
	
	
	//Test for updating a store with invalid store name	
	@Test
	public void testUpdateStoreWithInvalidName() {
		
		Store store = null;
		String error = null;
		
		
		try {
				
				store = storeService.updateStore( INVALIDSTORENAME, WEEKDAYCLOSING, WEEKDAYCLOSING, WEEKENDOPENING, WEEKENDCLOSING, ADDRESSID, GROCERYSTOREAPPLICATION);			
			} catch(ApiRequestException e) {
				
				error = e.getMessage();
				
			}
			
			assertNull(store);
			assertEquals("Store with name " +  INVALIDSTORENAME + "does not exist",error);

	}
	
	//Test for updating a store with inexistant address id
	@Test
	public void testUpdateStoreWithInexistantAddressId() {
		
		Store store = null;
		String error = null;
		
		try {
			
			store = storeService.updateStore(STORENAME, WEEKDAYCLOSING, WEEKDAYCLOSING, WEEKENDOPENING, WEEKENDCLOSING, INVALIDADDRESSID ,  GROCERYSTOREAPPLICATION);			
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
		}
		
		assertNull(store);
		assertEquals("Address with id " + INVALIDADDRESSID  + " does not exist.",error);
	
	}
	
	//Test for updating a store with inexistant grocery app id
	@Test
	public void testUpdateStoreWithInexistantGroceryAppId() {
		Store store = null;
		String error = null;
		
	try {
			
			store = storeService.updateStore(STORENAME, WEEKDAYCLOSING, WEEKDAYCLOSING, WEEKENDOPENING, WEEKENDCLOSING, ADDRESSID,  INVALIDAPPGROCERYID);			
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
		}
		
		assertNull(store);
		assertEquals("Application with id " + INVALIDAPPGROCERYID + " does not exist.",error);
		
	}
	
	//Test get correct store
	@Test
	public void testGetStore() {
		Store store = null;
		

        try {
        	 store =  storeService.getStore(STORENAME);
        } catch (ApiRequestException e) {
            fail();
        }

        assertNotNull(store);
        assertEquals(STORENAME,store.getName());
	}
	
	//Test getStore with an invalid name
	
	@Test
	public void testGetStoreWithNullName() {
		Store store = null;
		String error = null;
		
		  try {
	        	 store =  storeService.getStore( INVALIDSTORENAME);
	        } catch (ApiRequestException e) {
	            error = e.getMessage();
	        }
		  
		  assertNull(store);
		  assertEquals("Store with name " +  INVALIDSTORENAME+ "does not exist",error);

	}
	
	//Test deleteStore
	@Test
	public void testDeleteStore() {
		try {
			
			storeService.deleteStore(STORENAME);
			
		} catch(ApiRequestException e) {
			
			fail();
			
		}
	
	}
	
	//Test delete store with a null name
	@Test
	public void testDeleteStoreWithNullName() {
		String error = null;
		
		try {
			
			storeService.deleteStore(null);
			
		}catch(ApiRequestException e) {
			error= e.getMessage();
			
			
		}
		
		assertEquals("Store with name " +  null + "does not exist",error);		
	}
	
	//Test delete store with an inexistant name
	@Test
	public void testDeleteStoreWithInexistantName() {
		
		String error = null;
		
		try {
			
			storeService.deleteStore( INVALIDSTORENAME);
			
		}catch(ApiRequestException e) {
			error= e.getMessage();
						
		}		
		assertEquals("Store with name " +  INVALIDSTORENAME + "does not exist",error);
	
	}
	
	
	
	
	
	
	
	
	
	
		
		
		
}
	
	
	
	
	
	
	
	
	
	
	
	


