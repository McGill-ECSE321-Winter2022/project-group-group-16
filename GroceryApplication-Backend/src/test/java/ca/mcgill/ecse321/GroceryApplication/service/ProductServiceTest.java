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
import java.util.List;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.ProductRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Category;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryUser;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product.Availability;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.ProductService;



@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	
	
	@Mock
	private ProductRepository productServiceRepository;
	
	@InjectMocks
	private ProductService productService;
	
	
	
	private static final String PRODUCTNAME = "Cake";
	private static final String PRODUCTDESCRIPTION ="Item to eat correctly";
	private static final Float PRICE = (float) 14.50;
	private static final String IMAGE = "Fruit";
	private static final Float WEIGHT = (float) 10;
	private static final Float VOLUME = (float) 5;
	private static final Availability AVAILABILITY = Availability.IN_STORE;
	private static final Boolean ISREFUNDABLE = true;
	private static final Integer BARCODE = 12345;
	private static final Integer GROCERYAPPID = 444;
	private static final Integer AVAILABLEQUANTITY =10;
	private static final Integer CATEGORYID = 123;
	
	private static final Integer INVALID_APP_ID = 3249;
	
	private static final String NEWPRODUCTNAME = "Bread";
	private static final String NEWPRODUCTDESCRIPTION = "Made of natural wheat";
	private static final Float NEWPRICE = (float) 4.50;
	private static final String NEWIMAGE = "WhiteBread";
	private static final Float NEWWEIGHT = (float) 5;
	private static final Float NEWVOLUME = (float) 20;
	private static final Availability NEWAVAILABILITY = Availability.IN_STORE;
	private static final Boolean NEWISREFUNDABLE = false;
	private static final Integer NEWBARCODE = 54321;
	private static final Integer NEWGROCERYAPPID = 555;
	private static final Integer NEWAVAILABLEQUANTITY =5;
	private static final Integer NEWCATEGORYID = 321;
	
	
	@BeforeEach
	public void setMockOutput() {
		 lenient().when(productServiceRepository.findProductByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			 
			 if(invocation.getArgument(0).equals(PRODUCTNAME)) {
				 Product product = new Product();
				 GroceryStoreApplication app = new GroceryStoreApplication();
				 Category category = new Category();
				 
				 product.setAvailability(AVAILABILITY);
				 product.setName(PRODUCTNAME);
				 product.setDescription(PRODUCTDESCRIPTION);
				 product.setPrice(PRICE);
				 product.setVolume(VOLUME);
				 product.setWeight(WEIGHT);
				 product.setImage(IMAGE);
				 product.setIsRefundable(ISREFUNDABLE);
				 product.setAvailability(AVAILABILITY);
				 product.setBarcode(BARCODE);
				 
				 
				 category.setId(CATEGORYID);
				 product.setAvailableQuantity(AVAILABLEQUANTITY);
				 product.setCategory(category);
				 app.setId(GROCERYAPPID);
				 product.setGroceryStoreApplication(app);
				 

				 return product;
			 } else {

			 return null;
			 }
			 
		 });
		 
		 
		 
		 lenient().when(productServiceRepository.findProductByBarcode(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			 
			 if(invocation.getArgument(0).equals(BARCODE)) {
				 Product product = new Product();
				 product.setAvailability(AVAILABILITY);
				 product.setName(PRODUCTNAME);
				 product.setDescription(PRODUCTDESCRIPTION);
				 product.setPrice(PRICE);
				 product.setVolume(VOLUME);
				 product.setWeight(WEIGHT);
				 product.setImage(IMAGE);
				 product.setIsRefundable(ISREFUNDABLE);
				 product.setAvailability(AVAILABILITY);
				 product.setBarcode(BARCODE);

				 return product;
			 } else {

			 return null;
			 }

		 });
		

		 Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> invocation.getArgument(0);
	     lenient().when(productServiceRepository.save(any(Product.class))).thenAnswer(returnParameterAsAnswer);

	}
	
	//Test for create product
	@Test
	public void testCreateProduct() {
		Product product = null;
		
		try {
			
			product = productService.createProduct(IMAGE, 123, 444, PRODUCTNAME, PRODUCTDESCRIPTION, PRICE, WEIGHT, VOLUME, AVAILABILITY, ISREFUNDABLE, 10);
			
			
		} catch(ApiRequestException e) {
			fail();
			
			
		}
		
		assertNotNull(product);
		assertEquals(IMAGE,product.getImage());
		assertEquals(123,product.getGroceryStoreApplication().getId());
		assertEquals(444,product.getCategory().getId());
		assertEquals(PRODUCTNAME,product.getName());
		assertEquals(PRODUCTDESCRIPTION, product.getDescription());
		assertEquals(PRICE,product.getPrice());
		assertEquals(WEIGHT,product.getWeight());
		assertEquals(VOLUME,product.getVolume());
		assertEquals(AVAILABILITY, product.getAvailability());
		assertEquals(ISREFUNDABLE, product.getIsRefundable());
		assertEquals(10, product.getAvailableQuantity());	
		
	}
	
	//test for create product with null name
	@Test
	public void testCreateProductWithoutName() {
		Product product = null;
		String error = null;
		
		try {
			product = productService.createProduct(IMAGE, CATEGORYID, GROCERYAPPID, null, PRODUCTDESCRIPTION, PRICE, WEIGHT, VOLUME, AVAILABILITY, false, AVAILABLEQUANTITY);
		} catch(ApiRequestException e){
			error = e.getMessage();
		}
		
		assertNull(product);
		assertEquals("Product name is null or length 0", error);
	}
	
	//test for create product with null name
	@Test
	public void testCreateProductWithoutDescription() {
		Product product = null;
		String error = null;
			
		try {
			product = productService.createProduct(IMAGE, CATEGORYID, GROCERYAPPID, PRODUCTNAME, null, PRICE, WEIGHT, VOLUME, AVAILABILITY, false, AVAILABLEQUANTITY);
		} catch(ApiRequestException e){
			error = e.getMessage();
		}
			
		assertNull(product);
		assertEquals("Product description is null or length 0", error);
	}
	
	//test for create product with null name
	@Test
	public void testCreateProductWithoutGroceryStoreApp() {
		Product product = null;
		String error = null;
		
		try {
			
			product = productService.createProduct(IMAGE, CATEGORYID, INVALID_APP_ID, PRODUCTNAME, PRODUCTDESCRIPTION, PRICE, WEIGHT, VOLUME, AVAILABILITY, false, AVAILABLEQUANTITY);
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
		}
		
		assertNull(product);
		assertEquals("Application with id " + INVALID_APP_ID + " does not exist.",error);
	}
	
	//test for updating product
	public void testUpdateProduct() {
		Product product = null;
		
		try {
			product = productService.updateProduct(NEWIMAGE, NEWGROCERYAPPID, NEWCATEGORYID, NEWPRODUCTNAME, NEWPRODUCTDESCRIPTION, NEWPRICE, NEWWEIGHT, NEWVOLUME, AVAILABILITY, NEWBARCODE, false, NEWAVAILABLEQUANTITY);
		} catch(ApiRequestException e) {
			fail();
		}
		assertNotNull(product);
		assertEquals(product.getAvailability(),NEWAVAILABILITY);
		assertEquals(product.getAvailableQuantity(), NEWAVAILABLEQUANTITY);
		assertEquals(product.getBarcode(),NEWBARCODE);
		assertEquals(product.getCategory(),NEWCATEGORYID);
		assertEquals(product.getDescription(),NEWPRODUCTDESCRIPTION);
		assertEquals(product.getImage(),NEWIMAGE);
		assertEquals(product.getIsRefundable(),NEWISREFUNDABLE);
		assertEquals(product.getName(),NEWPRODUCTNAME);
		assertEquals(product.getPrice(),NEWPRICE);
		assertEquals(product.getVolume(),NEWVOLUME);
		assertEquals(product.getWeight(),NEWWEIGHT);
	}
	
	//test for updating product with null name
	
	
	
	
	
	
	
	
	

}
