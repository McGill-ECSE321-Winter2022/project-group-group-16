package ca.mcgill.ecse321.GroceryApplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import java.time.LocalDate;
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
public class GroceryUserServiceTest {
	
	@Mock
	private GroceryUserRepository groceryUserRepository;
	
	@InjectMocks
	private GroceryUserService groceryUserService;
	
	private static final String USERNAME ="John Cena";
	private static final String PASSWORD ="Apassword";
	private static final String FNAME ="First Name";
	
	private static final String LNAME ="Last Name";
	private static final String EMAIL ="john@gmail.com";
	
;
	private static final Date DATEOFBIRTH = Date.valueOf("2001-03-18");
	
	
	@BeforeEach
	public void setMockOutPut() {
		
		lenient().when(groceryUserRepository.findGroceryUserByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			
			
			if(invocation.getArgument(0).equals(EMAIL)) {
				
				GroceryUser user = new GroceryUser();
				user.setFirstName(FNAME);
				user.setLastName(LNAME);
				user.setUsername(USERNAME);
				user.setPassword(PASSWORD);
				user.setEmail(EMAIL);
				user.setDateOfBirth(DATEOFBIRTH);
				
				return user;
				
			} else {
				
				return null;
				
			}
			
			
			
		});
		
		
		 Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> invocation.getArgument(0);
	     lenient().when(groceryUserRepository.save(any(GroceryUser.class))).thenAnswer(returnParameterAsAnswer);

	}
	
	//Test for create a Grocery user
	@Test 
	public void testCreateGroceryUser() {
		GroceryUser groceryUser = null;
		
		try {
			
			groceryUser = groceryUserService.createGroceryUser(USERNAME, PASSWORD, FNAME, LNAME, EMAIL, DATEOFBIRTH);
			
			
		} catch(ApiRequestException e) {
			fail();
			
			
		}
		
		assertNotNull(groceryUser);
		assertEquals(USERNAME,groceryUser.getUsername());
		assertEquals(PASSWORD,groceryUser.getPassword());
		assertEquals(FNAME, groceryUser.getFirstName());
		assertEquals(LNAME,groceryUser.getLastName());
		assertEquals(EMAIL,groceryUser.getEmail());
		assertEquals(DATEOFBIRTH,groceryUser.getDateOfBirth());

		
	}
	
	//Test for creating groceryUser when date is null
	@Test
	public void testCreateGroceryUserWithNullDate() {
		GroceryUser groceryUser = null;
		String error = null;
		
		try {
			groceryUser = groceryUserService.createGroceryUser(USERNAME, PASSWORD, FNAME, LNAME, EMAIL, null);
			
			
		}catch(ApiRequestException e) {
			error = e.getMessage();
			
			
			
		}
		assertNull(groceryUser);
		assertEquals("A date is null and has to be created.\n",error);

		
	}
	
	
	//Test for creating groceryUser with null email
	@Test
	public void testCreateGroceryUserWithNullEmail() {
		GroceryUser groceryUser =null;
		String error = null;
		try {
			
			groceryUser = groceryUserService.createGroceryUser(USERNAME, PASSWORD, FNAME, LNAME, null, DATEOFBIRTH);
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
			
		}
		assertNull(groceryUser);
		assertEquals("Requested email is null or length 0. Please enter valid email.\n",error);

	}
	
	//Test for creating a groceryUser with valid email
	@Test
	public void testCreateGroceryUserWithInvalidEmail() {
		GroceryUser groceryUser =null;
		String error = null;
		try {
			
			groceryUser = groceryUserService.createGroceryUser(USERNAME, PASSWORD, FNAME, LNAME, "usermail.com", DATEOFBIRTH);
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
			
		}
		
		assertNull(groceryUser);
		assertEquals("Requested email is not valid.\n",error);

	}
	
	//Test for creating a groceryUser with invalid password
	@Test
	public void testCreateGroceryUserWithBadPass() {
		GroceryUser groceryUser =null;
		String error = null;
		try {
			
			groceryUser = groceryUserService.createGroceryUser(USERNAME, "ert", FNAME, LNAME, EMAIL, DATEOFBIRTH);
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
			
		}
		assertNull(groceryUser);
		assertEquals("Your password must be at least 5 characters long and contian a uppercasecase and lowercase character",error);
		
		
		
	}
	
	//Test for creating a groceryUser
	@Test
	public void testCreateGroceryUserWithNullUsername() {
		GroceryUser groceryUser =null;
		String error = null;
		try {
			
			groceryUser = groceryUserService.createGroceryUser(null, PASSWORD, FNAME, LNAME, EMAIL, DATEOFBIRTH);
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
			
		}
		
		assertNull(groceryUser);
		assertEquals("Requested username is null or length 0. Please enter valid username.\n",error);

	}
	
	//Test for creating a grocery user with a null  first name
	@Test
	public void testCreateGroceryUserWithNullFName() {
		GroceryUser groceryUser = null;
		String error = null;
		
		try {
			
			groceryUser = groceryUserService.createGroceryUser(USERNAME, PASSWORD, null, LNAME, EMAIL, DATEOFBIRTH);
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
			
		}
		
		assertNull(groceryUser);
		assertEquals("Requested first name is null or length 0. Please enter valid first namel.\n", error);

	}
	
	
	//Test for creating a grocery user with a number of spec in first name
	@Test
	public void testCreateGroceryUserWithSpecFName() {
		GroceryUser groceryUser = null;
		String error = null;
		try {
			
			groceryUser = groceryUserService.createGroceryUser(USERNAME, PASSWORD, "*NoahYe", LNAME, EMAIL, DATEOFBIRTH);
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
			
			
		}
		
		assertNull(groceryUser);
		assertEquals("Your first name cannot contain a number or a special character.",error);
		
		
		
		
	}
	
	//Test for creating a grocery user with null last name
	@Test
	public void testCreateGroceryUserWithNullName() {
		GroceryUser groceryUser = null;
		String error = null;
		
		try {
			
			groceryUser = groceryUserService.createGroceryUser(USERNAME, PASSWORD, FNAME, null, EMAIL, DATEOFBIRTH);
			
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
		}
		
		assertNull(groceryUser);
		assertEquals("Requested last name is null or length 0. Please enter valid last namel.\n",error);
		
		
		
	}
	
	
	//Test for creating a grocery user with spec last name
	
	@Test
	public void testCreateGroceryUserWithSpecLName() {
		GroceryUser groceryUser = null;
		String error = null;
		
		try {
			
			groceryUser = groceryUserService.createGroceryUser(USERNAME, PASSWORD, FNAME, "Tyronne.", EMAIL, DATEOFBIRTH);
			
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
		}
		
		assertNull(groceryUser);
		assertEquals("Your last name cannot contain a number or a special character.",error);
		
		
		
	}
	
	//Test for getting groceryUser by email
	@Test
	public void testGetGroceryUser() {
		
		GroceryUser groceryUser = null;
		try {
			
		groceryUser = groceryUserService.getGroceryUserByEmail(EMAIL);
		} catch(ApiRequestException e) {
			
			fail();
			
		}
		
		assertNotNull(groceryUser);
		assertEquals(EMAIL,groceryUser.getEmail());
		
		
		
		
	}
	
	//Test for getting a groceryUser with invalid email
	@Test
	public void testGetGroceryUserWithInvalidEmail() {
		
		GroceryUser groceryUser = null;
		String error = "";
		try {
			
			groceryUser = groceryUserService.getGroceryUserByEmail("yaboidre@hotmail.com");
			
		} catch(ApiRequestException e) {
			error = e.getMessage();
			
			
		}
		assertNull(groceryUser);
		assertEquals(error,"Invalid email, could not find associated user with email" + "yaboidre@hotmail.com" );
	
	}
	
	//Test for getting a groceryUser with null email
	@Test
	public void testGetGroceryUserWithNullEmail() {
		
		GroceryUser groceryUser = null;
		String error = "";
		try {
			
			groceryUser = groceryUserService.getGroceryUserByEmail(null);
			
		} catch(ApiRequestException e) {
			error = e.getMessage();
			
			
		}
		assertNull(groceryUser);
		assertEquals(error,"Invalid email, could not find associated user with email" + null);
	
	}
	
	//Test for getting all the list of grocery users
	@Test
	public void testGetAllGroceryUsers() {
		
		List<GroceryUser> groceryUsers = null;
		groceryUsers = groceryUserService.getAllGroceryUser();
		assertNotNull(groceryUsers);

		
	}
	
	//Test for updating grocery user by finding the user by email
	@Test
	public void testUpdateGroceryUsers() {
		GroceryUser groceryUser = null;
		String USERNAME2= "YoMam";
		String PASSWORD2 =" ILoveYouand";
		String FNAME2 ="Danny";
		String LNAME2 ="Too";		
		Date DATEOFBIRTH2= Date.valueOf("2001-03-27");
		
		try {
			
			groceryUser = groceryUserService.updateGroceryUser(USERNAME2, PASSWORD2, FNAME2, LNAME2, EMAIL, DATEOFBIRTH2);
			
			
		} catch(ApiRequestException e) {
			fail();
			
			
		}
		
		assertNotNull(groceryUser);
		assertEquals(USERNAME2,groceryUser.getUsername());
		assertEquals(PASSWORD2,groceryUser.getPassword());
		assertEquals(FNAME2, groceryUser.getFirstName());
		assertEquals(LNAME2,groceryUser.getLastName());
		assertEquals(DATEOFBIRTH2,groceryUser.getDateOfBirth());

	}
	
	//Test for updating grocery user by finding the by email with date as null
	@Test
	public void testUpdateGroceryUserWithNullDate() {
		GroceryUser groceryUser = null;
		String error = null;
		
		String USERNAME2= "YoMam";
		String PASSWORD2 =" ILoveYouand";
		String FNAME2 ="Danny";
		String LNAME2 ="Too";	
		
		try {
			groceryUser = groceryUserService.updateGroceryUser(USERNAME2, PASSWORD2, FNAME2, LNAME2, EMAIL, null);
			
			
		}catch(ApiRequestException e) {
			error = e.getMessage();
			
			
			
		}
		assertNull(groceryUser);
		assertEquals("A date is null and has to be created.\n",error);
		
	}
		
	//Test for updating grocery user by finding the by email with email as null
	@Test
	public void testUpdateGroceryUserWithNullEmail() {
		GroceryUser groceryUser = null;
		String error = null;
		
		String USERNAME2= "YoMam";
		String PASSWORD2 =" ILoveYouand";
		String FNAME2 ="Danny";
		String LNAME2 ="Too";	
		Date DATEOFBIRTH2= Date.valueOf("2001-03-27");
		
		try {
			groceryUser = groceryUserService.updateGroceryUser(USERNAME2, PASSWORD2, FNAME2, LNAME2, null, DATEOFBIRTH2);
			
			
		}catch(ApiRequestException e) {
			error = e.getMessage();
			
			
			
		}
		assertNull(groceryUser);
		assertEquals("Requested email is null or length 0. Please enter valid email.\n",error);
		
	}
	
	
	
	    //Test for updating grocery user by finding by email with wrong password
		@Test
		public void testUpdateGroceryUserWithWrongPass() {
			GroceryUser groceryUser = null;
			String error = null;
			
			String USERNAME2= "YoMam";
			String PASSWORD2 =" tre";
			String FNAME2 ="Danny";
			String LNAME2 ="Too";	
			Date DATEOFBIRTH2= Date.valueOf("2001-03-27");
			
			try {
				groceryUser = groceryUserService.updateGroceryUser(USERNAME2, PASSWORD2, FNAME2, LNAME2, EMAIL, DATEOFBIRTH2);
				
				
			}catch(ApiRequestException e) {
				error = e.getMessage();

			}
			assertNull(groceryUser);
			assertEquals("Your password must be at least 5 characters long and contian a uppercasecase and lowercase character",error);
			
		}
		
		//Test for updating grocery user by finding by email with wrong Username
		@Test
		public void testUpdateGroceryUserWithWrongUsername() {
			GroceryUser groceryUser = null;
			String error = null;
			
			String USERNAME2= "";
			String PASSWORD2 =" ILoveYouand";
			String FNAME2 ="Danny";
			String LNAME2 ="Too";	
			Date DATEOFBIRTH2= Date.valueOf("2001-03-27");
			try {
				groceryUser = groceryUserService.updateGroceryUser(USERNAME2, PASSWORD2, FNAME2, LNAME2, EMAIL, DATEOFBIRTH2);
				
				
			}catch(ApiRequestException e) {
				error = e.getMessage();
				
				
				
			}
			assertNull(groceryUser);
			assertEquals("Requested username is null or length 0. Please enter valid username.\n",error);
			
		}
		
		//Test for updating grocery user by finding by email with wrong first name
		@Test
		public void testUpdateGroceryUserWithWrongFName() {
			GroceryUser groceryUser = null;
			String error = null;
			
			String USERNAME2= "Yesyesyesno";
			String PASSWORD2 =" ILoveYouand";
			String FNAME2 ="";
			String LNAME2 ="Too";	
			Date DATEOFBIRTH2= Date.valueOf("2001-03-27");
			try {
				groceryUser = groceryUserService.updateGroceryUser(USERNAME2, PASSWORD2, FNAME2, LNAME2, EMAIL, DATEOFBIRTH2);
				
				
			}catch(ApiRequestException e) {
				error = e.getMessage();
				
				
				
			}
			assertNull(groceryUser);
			assertEquals("Requested first name is null or length 0. Please enter valid first namel.\n",error);
			
		}
		
		//Test for updating grocery user by finding by email with spec char in first name
		
		@Test
		public void testUpdateGroceryUserWithSpecFName() {
			GroceryUser groceryUser = null;
			String error = null;
			
			String USERNAME2= "Yesyesyesno";
			String PASSWORD2 =" ILoveYouand";
			String FNAME2 ="Danny*";
			String LNAME2 ="Too";	
			Date DATEOFBIRTH2= Date.valueOf("2001-03-27");
			try {
				groceryUser = groceryUserService.updateGroceryUser(USERNAME2, PASSWORD2, FNAME2, LNAME2, EMAIL, DATEOFBIRTH2);
				
				
			}catch(ApiRequestException e) {
				error = e.getMessage();
				
				
				
			}
			assertNull(groceryUser);
			assertEquals("Your first name cannot contain a number or a special character.",error);
			
		}
		
		//Test for updating grocery user by finding email with null last name
		
		
		@Test
		public void testUpdateGroceryUserWithNullLName() {
			GroceryUser groceryUser = null;
			String error = null;
			
			String USERNAME2= "Yesyesyesno";
			String PASSWORD2 =" ILoveYouand";
			String FNAME2 ="Danny";
			String LNAME2 ="";	
			Date DATEOFBIRTH2= Date.valueOf("2001-03-27");
			try {
				groceryUser = groceryUserService.updateGroceryUser(USERNAME2, PASSWORD2, FNAME2, LNAME2, EMAIL, DATEOFBIRTH2);
				
				
			}catch(ApiRequestException e) {
				error = e.getMessage();
				
				
				
			}
			assertNull(groceryUser);
			assertEquals("Requested last name is null or length 0. Please enter valid last namel.\n",error);
			
		}
		
		//Test for updating grocery user with spec char in last name
				
		@Test
		public void testUpdateGroceryUserWithSpecLName() {
			
			GroceryUser groceryUser = null;
			String error = null;
			
			String USERNAME2= "Yesyesyesno";
			String PASSWORD2 =" ILoveYouand";
			String FNAME2 ="Danny";
			String LNAME2 ="Too*";	
			Date DATEOFBIRTH2= Date.valueOf("2001-03-27");
			try {
				groceryUser = groceryUserService.updateGroceryUser(USERNAME2, PASSWORD2, FNAME2, LNAME2, EMAIL, DATEOFBIRTH2);
				
				
			}catch(ApiRequestException e) {
				error = e.getMessage();
				
				
				
			}
			assertNull(groceryUser);
			assertEquals("Your last name cannot contain a number or a special character.",error);
			
		}
		
		//Test for logging grocery user 
		@Test
		public void testLoginGroceryUser() {
			
			GroceryUser groceryUser = null;
			
			try {
				
				groceryUser = groceryUserService.loginGroceryUser(EMAIL,PASSWORD);
				
				
			} catch(ApiRequestException e) {
				fail();
				
				
			}
			
			assertNotNull(groceryUser);
			assertEquals(EMAIL, groceryUser.getEmail());
			assertEquals(PASSWORD, groceryUser.getPassword());
			
		}
		
		//Test for with logging grocery users with wrong email
		@Test
		public void testLoginGroceryUserWithWrongEmail() {
			GroceryUser groceryUser = null;
			String error = null;

			try {
				groceryUser = groceryUserService.loginGroceryUser("Noah@hotmail.com", PASSWORD);
				
				
			}catch(ApiRequestException e) {
				error = e.getMessage();
				
				
				
			}
			assertNull(groceryUser);
			assertEquals("No user exists with email " + "Noah@hotmail.com" ,error);
			
			
			
		}
		
		
		//Test for logging grocery user with wrong password
		@Test
		public void testLoginGroceryUserWithWrongPass() {
			GroceryUser groceryUser = null;
			String error = null;
			String INCORRECTPASSWORD = "IncorrectPassword";
			
			try {
				groceryUser = groceryUserService.loginGroceryUser(EMAIL, INCORRECTPASSWORD);
				
				
			}catch(ApiRequestException e) {
				error = e.getMessage();
				
				
				
			}
			assertNull(groceryUser);
			assertEquals("incorrect password : " + INCORRECTPASSWORD + " ,  db password : " + PASSWORD  ,error);
			
			
			
		}
		
		//Test for deleting a grocery user
		@Test
		public void testDeleteGroceryUser() {
			GroceryUser groceryUser = null;
			
			try {
				
				groceryUser = groceryUserService.deleteGroceryUser(EMAIL);
				
				
			} catch(ApiRequestException e) {
				fail();
				
				
			}
			
			assertNotNull(groceryUser);
			assertEquals(EMAIL, groceryUser.getEmail());
	
		}
		
		
		//Test for deleting a grocery user with a incorrect email
		@Test
		public void testDeleteGroceryUserWithIncorrectEmail() {
			GroceryUser groceryUser = null;
			String error = null;

			String INCORRECTEMAIL = "bad@hotmail.com";
			
			try {
				groceryUser = groceryUserService.deleteGroceryUser(INCORRECTEMAIL);
				
				
			}catch(ApiRequestException e) {
				error = e.getMessage();
				
				
				
			}
			assertNull(groceryUser);
			assertEquals("Grocery user with provided email does not exist.",error);

		}
		
		//Test for deleting a grocery user with a null email
		@Test
		public void testDeleteGroceryUserWithNullEmail() {
			GroceryUser groceryUser = null;
			String error = null;
			
			String NULLEMAIL =null;
			
			try {
				groceryUser = groceryUserService.deleteGroceryUser(NULLEMAIL);
				
				
			}catch(ApiRequestException e) {
				error = e.getMessage();
				
				
				
			}
			assertNull(groceryUser);
			assertEquals("Grocery user with provided email does not exist.",error);
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
			
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
