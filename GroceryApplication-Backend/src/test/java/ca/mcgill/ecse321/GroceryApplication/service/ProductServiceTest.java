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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryStoreApplicationRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryUserRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.ProductRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Category;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product.Availability;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.CategoryService;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.ProductService;


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	
	
	@Mock
	private ProductRepository productRepository;
	
    @Mock
    private GroceryStoreApplicationRepository gsRepository;
    
    @Mock
	private CategoryRepository categoryRepository;
	
	@InjectMocks
	private ProductService productService;
	
	private static final String IMAGE = "IMAGE";
	private static final Integer APPID = 1;
	private static final Integer CATEGORYID = 2;
	private static final String NAME ="NAME";
	private static final String DESCRIPTION ="DESCRIPTION";
	private static final Float PRICE = (float) 14;
	private static final Float WEIGHT = (float) 90;
	private static final Float VOLUME = (float) 100;
	private static final Integer BARCODE =123;
	private static final Availability AVAILABILITY = Availability.IN_STORE;
	private static final Boolean ISREFUNDABLE = true;
	private static final Integer AVAQUANTITY = 10;
	
	private static final String NEWIMAGE = "FRUIT";
	private static final String NEWNAME ="NEWNAME";
	private static final String NEWDESCRIPTION ="NEWDESCRIPTION";
	private static final Integer NEWBARCODE =555;
	private static final Float NEWPRICE = (float) 15;
	private static final Float NEWWEIGHT = (float) 95;
	private static final Float NEWVOLUME = (float) 155;
	private static final Integer NEWAPPID = 6666;
	private static final Integer NEWCATEGORYID= 777;

	private static final Availability NEWAVAILABILITY = Availability.DELIVERY;
	private static final Boolean NEWISREFUNDABLE = false;
	private static final Integer NEWAVAQUANTITY = 11;
	
	
	private static final Integer NONREFUNDABLEBARCODE = 420420;
	
	
	private static final Integer INVALIDBARCODE =696;
	private static final Integer INVALIDAPPID =3;
	private static final Integer INVALIDCATEGORYID= 4;

	@BeforeEach
	public void setMockOutput() {
		
		lenient().when(productRepository.findProductByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(NAME)) {
				
				Product product = new Product();
				
				
				product.setName(NAME);
				return product;

			}
			
			else {
				
				return null;
			}
			});
		
		lenient().when(productRepository.findProductByBarcode(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(BARCODE)) {
				Product product = new Product();
				product.setBarcode(BARCODE);
				product.setIsRefundable(true);
				product.setAvailableQuantity(AVAQUANTITY);
				return product;

			} else if (invocation.getArgument(0).equals(NONREFUNDABLEBARCODE)) {
				Product product = new Product();
				product.setBarcode(NONREFUNDABLEBARCODE);
				product.setIsRefundable(false);
				
				return product;
				
				
			}
			
			else {
				
				return null;
				
			}
			});
		
		lenient().when(productRepository.findProductByAvailability(any())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(AVAILABILITY)) {
				Product product = new Product();
				product.setAvailability(AVAILABILITY);
				return product;
	
			
			}
			
			else {
				
				return null;
				
			}
			});
		
		 lenient().when(gsRepository.findGroceryStoreApplicationById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {

	            if (invocation.getArgument(0).equals(APPID)) {
	                GroceryStoreApplication gsApplication = new GroceryStoreApplication();
	                gsApplication.setId(APPID);
	                return gsApplication;
	                
	            }
	                else {
	                return null;
	            }

	        });
		 
		 lenient().when(categoryRepository.findCategoryById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {

	            if (invocation.getArgument(0).equals(CATEGORYID)) {
	                Category category = new Category();
	                category.setId(CATEGORYID);
	                return category;
	                
	            }
	                else {
	                return null;
	            }

	        });
		 
		 
		
		 Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
				
				return invocation.getArgument(0);
				
			};
			lenient().when(productRepository.save(any(Product.class))).thenAnswer(returnParameterAsAnswer);

	}
	
	//Test to create product
	@Test
	public void testCreateProduct() {
		Product product = null;
		try {
			
			product = productService.createProduct(IMAGE, APPID, CATEGORYID, NAME, DESCRIPTION, PRICE, WEIGHT, VOLUME, AVAILABILITY, ISREFUNDABLE, AVAQUANTITY);
		} catch(ApiRequestException e) {
			fail();
			
			
			
		}
		
		assertNotNull(product);
		assertEquals(product.getImage(),IMAGE);
		assertEquals(product.getGroceryStoreApplication().getId(),APPID);
		assertEquals(product.getCategory().getId(),CATEGORYID);
		assertEquals(product.getName(),NAME);
		assertEquals(product.getDescription(), DESCRIPTION);
		assertEquals(product.getPrice(),PRICE);
		assertEquals(product.getWeight(),WEIGHT);
		assertEquals(product.getVolume(),VOLUME);
		assertEquals(product.getAvailability(),AVAILABILITY);
		assertEquals(product.getIsRefundable(),ISREFUNDABLE);
		assertEquals(product.getAvailableQuantity(),AVAQUANTITY);

	}
	
	//Test to create product with invalid APPID
	
	//test for create product with invalid grocery app Id
	@Test
	public void testCreateProductWithInvalidGroceryStoreAppId() {
		Product product = null;
		String error = null;
		
		try {
			
			product = productService.createProduct(IMAGE, INVALIDAPPID, CATEGORYID, NAME, DESCRIPTION, PRICE, WEIGHT, VOLUME, AVAILABILITY, ISREFUNDABLE,  AVAQUANTITY);
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
		}
		
		assertNull(product);
		assertEquals("No application associated with this Id.",error);
	}
	
	//Test to create product with invalid category id
	@Test
	public void testCreateProductWithInvalidCategoryId() {
		
		Product product = null;
		String error = null;
		
		try {
			
			product = productService.createProduct(IMAGE, APPID, INVALIDCATEGORYID, NAME, DESCRIPTION, PRICE, WEIGHT, VOLUME, AVAILABILITY, ISREFUNDABLE,  AVAQUANTITY);
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
		}
		
		assertNull(product);
		assertEquals("No category associated with this Id.",error);

	}
	
	//Test to create a product with nullName
	@Test
	public void testCreateProductWithNullName() {
		
		Product product = null;
		String error = null;
		
		try {
			
			product = productService.createProduct(IMAGE, APPID, CATEGORYID, null, DESCRIPTION, PRICE, WEIGHT, VOLUME, AVAILABILITY, ISREFUNDABLE,  AVAQUANTITY);
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
		}
		
		assertNull(product);
		assertEquals("Requested name is null or length 0. Please enter valid namel.\n",error);
		
		
		
	}
	
	//Test to create a product with null description
	@Test
	public void testCreateProductWithNullDesc() {
		
		Product product = null;
		String error = null;
		
		try {
			
			product = productService.createProduct(IMAGE, APPID, CATEGORYID, NAME, null, PRICE, WEIGHT, VOLUME, AVAILABILITY, ISREFUNDABLE,  AVAQUANTITY);
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
		}
		
		assertNull(product);
		assertEquals("Requested description is null or length 0. Please enter valid descriptionl.\n",error);

	}
	
	//Test to update a product
	@Test
	public void testUpdateProduct() {
		Product product = null;
		
		try {
			product = productService.updateProduct(NEWIMAGE, APPID, CATEGORYID, NAME, DESCRIPTION, NEWPRICE, NEWWEIGHT, NEWVOLUME, NEWAVAILABILITY, BARCODE, NEWISREFUNDABLE, NEWAVAQUANTITY);
		} catch(ApiRequestException e) {
			fail();
		}
		assertNotNull(product);
		assertEquals(product.getGroceryStoreApplication().getId(),APPID);
		assertEquals(product.getCategory().getId(),CATEGORYID);
		assertEquals(product.getImage(),NEWIMAGE);
		assertEquals(product.getName(),NAME);
		assertEquals(product.getDescription(),DESCRIPTION);
		assertEquals(product.getIsRefundable(),NEWISREFUNDABLE);
		assertEquals(product.getPrice(),NEWPRICE);
		assertEquals(product.getVolume(),NEWVOLUME);
		assertEquals(product.getWeight(),NEWWEIGHT);
		assertEquals(product.getAvailability(),NEWAVAILABILITY);
		assertEquals(product.getAvailableQuantity(), NEWAVAQUANTITY);


	}
	
	//Test to update with invalid APPID
	@Test
	public void testUpdateProductWithInvalidAppId() {
		Product product = null;
		String error  =null;
		
		try {
			product = productService.updateProduct(NEWIMAGE, INVALIDAPPID, CATEGORYID, NAME, DESCRIPTION, NEWPRICE, NEWWEIGHT, NEWVOLUME, NEWAVAILABILITY, BARCODE, NEWISREFUNDABLE, NEWAVAQUANTITY);
		} catch(ApiRequestException e) {
			error = e.getMessage();
		}
		assertNull(product);
		assertEquals("No application associated with this Id.",error);


	}
	
	//Test to update with invalid category ID
	@Test
	public void testUpdateProductWithInvalidCatId() {
		Product product = null;
		String error  =null;
		
		try {
			product = productService.updateProduct(NEWIMAGE, APPID, INVALIDCATEGORYID, NAME, DESCRIPTION, NEWPRICE, NEWWEIGHT, NEWVOLUME, NEWAVAILABILITY, BARCODE, NEWISREFUNDABLE, NEWAVAQUANTITY);
		} catch(ApiRequestException e) {
			error = e.getMessage();
		}
		assertNull(product);
		assertEquals("No category associated with this Id.",error);
		
		
		
	}
	
	//Test to update with null name
	@Test
	public void testUpdateProductWithNullName() {
		Product product = null;
		String error  =null;
		
		try {
			product = productService.updateProduct(NEWIMAGE, APPID, CATEGORYID, null, DESCRIPTION, NEWPRICE, NEWWEIGHT, NEWVOLUME, NEWAVAILABILITY, BARCODE, NEWISREFUNDABLE, NEWAVAQUANTITY);
		} catch(ApiRequestException e) {
			error = e.getMessage();
		}
		assertNull(product);
		assertEquals("Requested name is null or length 0. Please enter valid namel.\n",error);
		

		
	}
	
	
	//Test to update with null description
	@Test
	public void testUpdateProductWithNullDesc() {
		
		Product product = null;
		String error  =null;
		
		try {
			product = productService.updateProduct(NEWIMAGE, APPID, CATEGORYID, NAME, null, NEWPRICE, NEWWEIGHT, NEWVOLUME, NEWAVAILABILITY, BARCODE, NEWISREFUNDABLE, NEWAVAQUANTITY);
		} catch(ApiRequestException e) {
			error = e.getMessage();
		}
		assertNull(product);
		assertEquals("Requested description is null or length 0. Please enter valid descriptionl.\n",error);

	}
	
	//Test to get product by barcode
	@Test
	public void testGetProduct() {
		
	Product product = null;
		
		try {
			product = productService.getProductByBarcode(BARCODE);
		} catch(ApiRequestException e) {
			fail();
		}
		
		assertNotNull(product);
		assertEquals(product.getBarcode(),BARCODE);

	}
	
	//Test to get all products
	@Test
	public void testGetAllProduct() {
		
		List<Product> products = null;
		products = productService.getAllProduct();
		assertNotNull(products);
		
		
	}
	
	//Test to delete product
	@Test
	public void testDeleteProduct() {
		
	Product product = null;
		
		try {
			product = productService.deleteProduct(BARCODE);
		} catch(ApiRequestException e) {
			fail();
		}
		
		
	}
	
	
	//test delete product by invalid bar code
	@Test
	public void testDeleteProductbyInvalidBarCode() {
		String error = null;
		
		try {
			productService.deleteProduct(INVALIDBARCODE);
		} catch(ApiRequestException e) {
			error = e.getMessage();
		}
		assertEquals("Product with provided barcode does not exist.",error);
	}
	
	//Test to test refund 
	@Test
	public void testRefundProductbyBarCode() {
		Product product = null;
		try {
			product = productService.refundProduct(BARCODE);
		} catch(ApiRequestException e) {
			fail();
		}
		assertNotNull(product);
		assertEquals(product.getAvailableQuantity(),AVAQUANTITY +1);
		
	}
	
	//test refund product by bar code
	@Test
	public void testRefundProductbyInvalidBarCode() {
		Product product = null;
		String error = null;
		try {
			product =productService.refundProduct(NONREFUNDABLEBARCODE);
		} catch(ApiRequestException e) {
			error = e.getMessage();
		}
		assertNull(product);
		assertEquals("Product is not refundable.",error);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
