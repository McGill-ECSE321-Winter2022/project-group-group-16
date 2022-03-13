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

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.CategoryRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.CustomerRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Category;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.CategroyService;

@ExtendWith(MockitoExtension.class)
public class CategroyServiceTest {
	
	@Mock
	private CategoryRepository categoryRepository;
	
	@InjectMocks
	private CategroyService categoryService;
	
	private static final String CATEGORY_KEY = "TestCatgeory";
	
	private static final Integer CATEGORYID = 768;
	private static final String IMAGE = "Fruit";
	private static final Integer APPLICATIONID = 14;
	private static final String NAME = "Noah";
	private static final String DESCRIPTION = "Banana";
	
	private static final Integer VALID_ID = 12;
	private static final Integer INVALID_ID = 59;
	private static final Integer NULL_ID = null;
	
	/**
	 * 
	 * Service test method for category
	 *  
	 *  
	 *  */
	
	@BeforeEach
    public void setMockOutput() {
		lenient().when(categoryRepository.findCategoryById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			
			if(invocation.getArgument(0).equals(CATEGORYID)) {
				Category category = new Category ();
				
				category.setId(CATEGORYID);
				category.setImage(IMAGE);
				category.setName(NAME);
				category.setDescription(DESCRIPTION);

		
				return category;
				
				
			}
			else {

			return null;}
			
		});
		
		
		lenient().when(categoryRepository.findCategoryByname(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			
			if(invocation.getArgument(0).equals(NAME)) {
				Category category = new Category();
				
				category.setId(CATEGORYID);
				category.setImage(IMAGE);
				category.setName(NAME);
				category.setDescription(DESCRIPTION);
				return category;

			}
			
			else {
			
			
			return null;
			
			}
		});
		
		//When anything is saved, return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			
			return invocation.getArgument(0);
			
		};
		lenient().when(categoryRepository.save(any(Category.class))).thenAnswer(returnParameterAsAnswer);
    }
	
	//test for creating a category successfully
	@Test
	public void testCreateCategory() {
		Category category = null;
		
		try {
			
			category = categoryService.createCategory(IMAGE, APPLICATIONID, NAME, DESCRIPTION);
			
		} catch(Exception e) {
			fail();
			
			
		}
		
		assertNotNull(category);
		assertEquals(category.getImage(),IMAGE);
		assertEquals(category.getId(),APPLICATIONID);
		assertEquals(category.getName(),NAME);
		assertEquals(category.getDescription(),DESCRIPTION);		
		
	}
	
	//test for creating a category with empty name
	@Test
	public void testCreateCategoryEmptyName() {
		Category category = null;
		String error = null;
		try {
			category = categoryService.createCategory(IMAGE, APPLICATIONID,"", DESCRIPTION);
			
			
		} catch(ApiRequestException e) {
			error = e.getMessage();
			
			
		}
		
		assertNull(category);
		assertEquals("Category name is null or empty.", error);
		
		
	}
	
	//test for creating a category with empty name
	@Test
	public void testCreateCategoryEmptyImage() {
		Category category = null;
		String error = null;
		try {
			category = categoryService.createCategory("", APPLICATIONID, NAME, DESCRIPTION);
				
				
		} catch(ApiRequestException e) {
			error = e.getMessage();
				
				
		}
			
		assertNull(category);
		assertEquals("Category image is null or empty.", error);
			
			
	}	
	
	
	//test for creating a category with empty description
	@Test
	public void testCreateCategoryEmptyDescription() {
		Category category = null;
		String error = null;
		try {
			category = categoryService.createCategory(IMAGE, APPLICATIONID, NAME, "");
						
		} catch(ApiRequestException e) {
			error = e.getMessage();
						
		}
		
		assertNull(category);
		assertEquals("Category description is null or empty.", error);
				
	}
	
	//test for updating the category
	@Test
	public void testUpdateCategory() {
		
		Category category = null;
		String NEWIMAGE = "NEWIMAGE";
		Integer NEWAPPLICATIONID = 92;
		String NEWNAME = "NEWNAME";
		String NEWDESCRIPTION = "NEWDESCRIPTION";
		
		try {
			category = categoryService.updateCategory(VALID_ID, NEWAPPLICATIONID, NEWNAME, NEWDESCRIPTION, NEWIMAGE);
			
		} catch(ApiRequestException e) {
			
			fail();
			
		}
		assertNotNull(category);
		assertEquals(NEWIMAGE,category.getImage());
		assertEquals(NEWAPPLICATIONID,category.getId());
		assertEquals(NEWNAME,category.getName());
		assertEquals(NEWDESCRIPTION,category.getDescription());		
	
	}
	
	//test for updating the category with invalid id
	@Test
	public void testUpdateCategoryByInvalidId() {
		Category category = null;
		String error = null;
		try {
		category = categoryService.updateCategory(INVALID_ID, 92, "NewName", "NewDescription", "NewImage");
		}
		catch(Exception e) {
			
			error = e.getMessage();
		}
		
		assertNull(category);
		assertEquals("No address exists with id:" + INVALID_ID, error);
				
	}
	
	//Test for updating the address with empty Id
	@Test
	public void testUpdateAddressByEmptyId() {
		Category category = null;
		String error = null;
		try {
			category = categoryService.updateCategory(NULL_ID, 92, "NewName", "NewDescription", "NewImage");
						
		}catch(Exception e) {
			error = e.getMessage();
			
		}
		
		assertNull(category);
		assertEquals("No address exists with id:" + null ,error);
				
	}
	
	//Test to delete category with empty id
	@Test
	public void testDeleteCategoryWithNullId() {
		String error = null;
		try {
			
			categoryService.deleteCategory(null);
		}catch(ApiRequestException e) {
			error = e.getMessage();
						
		}
		assertEquals(error,"No category exists with id:" + null);

	}
	
	//Test to delete with invalid id
	@Test
	public void testDeleteAddressWithInvalidId() {
		String error = null;
		try {
				
			categoryService.deleteCategory(INVALID_ID);
		} catch(ApiRequestException e) {
			error = e.getMessage();				
		}
			
		assertEquals(error, "No address exists with id:" + INVALID_ID);
	}
	
	//Test to delete category
	@Test
	public void testDeleteAddress() {
	
		try {
				
			categoryService.deleteCategory(CATEGORYID);
				
		} catch(ApiRequestException e) {
			fail();
				
		}
				
	}
	
	//Test for getting the address by nonexistant Id
	@Test
	public void testGetCategoryByEmptyId() {
		Category category = null;
		String error = null;
			
		try {
			category = categoryService.getCategorybyId(NULL_ID);
		}
			catch(Exception e) {
					
				error = e.getMessage();
		}
			
		assertNull(category);
		assertEquals("No category exists with id:"+ null, error );		
			
	}
	
	//Test getting address with empty id
	@Test
	public void testGetCategoryByInexistantId() {
		Category category = null;
		String error = null;
			
		try {
			category = categoryService.getCategorybyId(INVALID_ID);
			}
			catch(Exception e) {
					
				error = e.getMessage();
			}
			
		assertNull(category);
		assertEquals("No category exists with id:"+ INVALID_ID, error );			
			
	}
	

}
