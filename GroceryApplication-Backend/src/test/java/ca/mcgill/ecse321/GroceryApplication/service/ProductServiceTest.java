package ca.mcgill.ecse321.GroceryApplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.CategoryRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryStoreApplicationRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.ProductRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Category;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product.Availability;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.ProductService;



@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
		
	@Mock
	private ProductRepository productServiceRepository;
	
	@Mock
	private GroceryStoreApplicationRepository groceryStoreApplicationRepository;
	
	@Mock
	private CategoryRepository categoryRepository;
	
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
	
	private static final String INVALID_NAME = "Me";
	private static final String INVALID_DESCRIPTION = "bla bla";
	private static final Integer INVALID_APP_ID = 3249;
	private static final Integer INVALID_CATEGORY_ID = 9991;
	private static final Integer INVALID_BARCODE = 821;
	
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
		 
		 lenient().when(productServiceRepository.findProductByAvailability(any())).thenAnswer((InvocationOnMock invocation) -> {
			 
			 if(invocation.getArgument(0).equals(AVAILABILITY)) {
				 Product product = new Product();
				 
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
		 
		 lenient().when(groceryStoreApplicationRepository.findGroceryStoreApplicationById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			 
			 if(invocation.getArgument(0).equals(GROCERYAPPID)) {
				 GroceryStoreApplication gsa = new GroceryStoreApplication();
				 
				 gsa.setId(GROCERYAPPID);

				 return gsa;
				 
			 } else {

			 return null;
			 }

		 });
		 
		 lenient().when(categoryRepository.findCategoryById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
				
				if(invocation.getArgument(0).equals(CATEGORYID)) {
					Category category = new Category();
					
					category.setId(CATEGORYID);
					
					return category;
					
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
			product = productService.createProduct(IMAGE, CATEGORYID, GROCERYAPPID, INVALID_NAME, PRODUCTDESCRIPTION, PRICE, WEIGHT, VOLUME, AVAILABILITY, ISREFUNDABLE, AVAILABLEQUANTITY);
		} catch(ApiRequestException e){
			error = e.getMessage();
		}
		
		assertNull(product);
		assertEquals("Requested name is null or length 0. Please enter valid namel.\n", error);
	}
	
	//test for create product with null name
	@Test
	public void testCreateProductWithoutDescription() {
		Product product = null;
		String error = null;
			
		try {
			product = productService.createProduct(IMAGE, CATEGORYID, GROCERYAPPID, PRODUCTNAME, INVALID_DESCRIPTION, PRICE, WEIGHT, VOLUME, AVAILABILITY, ISREFUNDABLE, AVAILABLEQUANTITY);
		} catch(ApiRequestException e){
			error = e.getMessage();
		}
			
		assertNull(product);
		assertEquals("Requested description is null or length 0. Please enter valid descriptionl.\n", error);
	}
	
	//test for create product with null name
	@Test
	public void testCreateProductWithoutGroceryStoreApp() {
		Product product = null;
		String error = null;
		
		try {
			
			product = productService.createProduct(IMAGE, CATEGORYID, INVALID_APP_ID, PRODUCTNAME, PRODUCTDESCRIPTION, PRICE, WEIGHT, VOLUME, AVAILABILITY, ISREFUNDABLE, AVAILABLEQUANTITY);
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
		}
		
		assertNull(product);
		assertEquals("No application associated with this Id.",error);
	}
	
	//test for create product with null category
	@Test
	public void testCreateProductWithoutCategory() {
		Product product = null;
		String error = null;
		
		try {
			product = productService.createProduct(IMAGE, GROCERYAPPID, INVALID_CATEGORY_ID, PRODUCTNAME, PRODUCTDESCRIPTION, PRICE, WEIGHT, VOLUME, AVAILABILITY, ISREFUNDABLE, AVAILABLEQUANTITY);
		} catch(ApiRequestException e) {
			error =e.getMessage();
		}
		
		assertNull(product);
		assertEquals("No category associated with this Id.",error);
	}
	
	//test for updating product
	@Test
	public void testUpdateProduct() {
		Product product = null;
		
		try {
			product = productService.updateProduct(NEWIMAGE, NEWGROCERYAPPID, NEWCATEGORYID, NEWPRODUCTNAME, NEWPRODUCTDESCRIPTION, NEWPRICE, NEWWEIGHT, NEWVOLUME, NEWAVAILABILITY, NEWBARCODE, false, NEWAVAILABLEQUANTITY);
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
	@Test
	public void testUpdateProductWithoutName() {
		Product product = null;
		String error = null;
		
		try {
			product = productService.updateProduct(NEWIMAGE, NEWGROCERYAPPID, NEWCATEGORYID, null, NEWPRODUCTDESCRIPTION, NEWPRICE, NEWWEIGHT, NEWVOLUME, NEWAVAILABILITY, NEWBARCODE, false, NEWAVAILABLEQUANTITY);
		} catch(ApiRequestException e) {
			error = e.getMessage();
		}
		assertNull(product);
		assertEquals("Requested name is null or length 0. Please enter valid namel.\n",error);
	}
	
	//test for updating product with null description
	@Test
	public void testUpdateProductWithoutDescription() {
		Product product = null;
		String error = null;
		
		try {
			product = productService.updateProduct(NEWIMAGE, NEWGROCERYAPPID, NEWCATEGORYID, NEWPRODUCTNAME, null, NEWPRICE, NEWWEIGHT, NEWVOLUME, NEWAVAILABILITY, NEWBARCODE, false, NEWAVAILABLEQUANTITY);
		} catch(ApiRequestException e) {
			error = e.getMessage();
		}
		assertNull(product);
		assertEquals("Requested description is null or length 0. Please enter valid descriptionl.\n",error);
	}
	
	//test for updating product with null grocery store application
	@Test
	public void testUpdateProductWithInvalidGroceryStoreApp() {
		Product product = null;
		String error = null;
		
		try {
			product = productService.updateProduct(NEWIMAGE, INVALID_APP_ID, NEWCATEGORYID, NEWPRODUCTNAME, null, NEWPRICE, NEWWEIGHT, NEWVOLUME, NEWAVAILABILITY, NEWBARCODE, false, NEWAVAILABLEQUANTITY);
		} catch(ApiRequestException e) {
			error = e.getMessage();
		}
		assertNull(product);
		assertEquals("No application associated with this Id.",error);
	}
	
	//test for updating product with null grocery store application
	@Test
	public void testUpdateProductWithInvalidCategoryApp() {
		Product product = null;
		String error = null;
		
		try {
			product = productService.updateProduct(NEWIMAGE, NEWGROCERYAPPID, INVALID_CATEGORY_ID, NEWPRODUCTNAME, null, NEWPRICE, NEWWEIGHT, NEWVOLUME, NEWAVAILABILITY, NEWBARCODE, false, NEWAVAILABLEQUANTITY);
		} catch(ApiRequestException e) {
			error = e.getMessage();
		}
		assertNull(product);
		assertEquals( "No category associated with this Id.",error);
	}
	
	//Test delete product by bar code
	@Test
	public void testDeleteProductbyBarCode() {
			
		try{
			productService.deleteProduct(BARCODE);
				
		} catch(ApiRequestException e) {
			fail();	
		}

	}
	
	//test delete product by invalid bar code
	@Test
	public void testDeleteProductbyInvalidBarCode() {
		String error = null;
		
		try {
			productService.deleteProduct(INVALID_BARCODE);
		} catch(ApiRequestException e) {
			error = e.getMessage();
		}
		assertEquals("Product with provided barcode does not exist.",error);
	}
	
	//test refund product by bar code
	@Test
	public void testRefundProductbyBarCode() {
		Product product = null;
		try {
			product = productService.refundProduct(BARCODE);
		} catch(ApiRequestException e) {
			fail();
		}
		assertNotNull(product);
		assertEquals(product.getBarcode(),BARCODE);
		
	}
	
	//test refund product by bar code
	@Test
	public void testRefundProductbyInvalidBarCode() {
		Product product = null;
		String error = null;
		try {
			product =productService.refundProduct(INVALID_BARCODE);
		} catch(ApiRequestException e) {
			error = e.getMessage();
		}
		assertNull(product);
		assertEquals("Product is not refundable.",error);
	}
	
	//test get product by bar code
	@Test
	public void testGetProductbyBarCode() {
		Product product = null;
		try {
			product = productService.getProductByBarcode(BARCODE);
		} catch(ApiRequestException e) {
			fail();
		}
		
		assertNotNull(product);
		assertEquals(product.getBarcode(),BARCODE);
	}
	
	//test get all products 
	@Test
	public void testGetAllProducts() {
		List<Product> products = null;
		products = productService.getAllProduct();
		assertNotNull(products);
	}
	
}
