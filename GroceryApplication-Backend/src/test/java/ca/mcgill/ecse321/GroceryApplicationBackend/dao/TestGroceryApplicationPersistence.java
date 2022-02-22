package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.*;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product.Availability;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Shift.Day;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestGroceryApplicationPersistence {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GroceryStoreApplicationRepository groceryStoreApplicationRepository;

    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired 
    ProductRepository productRepository;
 
    
    @Autowired 
    ShiftRepository shiftRepository;
    
    @Autowired 
    EmployeeRepository employeeRepository;
    
    @Autowired 
    GroceryOrderRepository groceryOrderRepository;
    
    @Autowired 
    PaymentRepository paymentRepository;
    

    @Autowired
    GroceryUserRepository groceryUserRepository;
    
    @Autowired   
    ManagerRepository managerRepository;
    
    @Autowired    
    CustomerRepository customerRepository;
    
    @AfterEach
   public void clearDatabase() {
<<<<<<< Updated upstream
//        // First, we clear registrations to avoid exceptions due to inconsistencies
//        // Then we can clear the other tables
       
=======
       // First, we clear registrations to avoid exceptions due to inconsistencies
       // Then we can clear the other tables
       //customerRepository.deleteAll();
>>>>>>> Stashed changes
       groceryOrderRepository.deleteAll();
       storeRepository.deleteAll();
       addressRepository.deleteAll();
       productRepository.deleteAll();
       categoryRepository.deleteAll();
       shiftRepository.deleteAll();
       employeeRepository.deleteAll();
       paymentRepository.deleteAll();
       groceryUserRepository.deleteAll();
<<<<<<< Updated upstream
       managerRepository.deleteAll();
       customerRepository.deleteAll();
       
       
       
      

      
=======
       managerRepository.deleteAll();    
>>>>>>> Stashed changes
   }

   //persistence test for store
   @Test
    public void testPersistAndLoadStore() {
        //create a grocery store application
        //mandatory to create for a store to exist
        GroceryStoreApplication gs = new GroceryStoreApplication();
        gs.setId(66);
        groceryStoreApplicationRepository.save(gs);

        //create an address for the store
        Address address = new Address();
        int id = 10;
        address.setId(id);
        addressRepository.save(address);

        //create and set up the store
        String name = "Grocery";
    	Store store = new Store();
    	store.setGroceryStoreApplication(gs);
        store.setAddress(address);
        store.setName(name);
    	storeRepository.save(store);
        
    	address= null;
    	store = null;

        //find store from unique id
    	store = storeRepository.findStoreByName(name);
        //test for references w address
    	address =  addressRepository.findAddressById(id);
        assertNotNull(store);
        assertNotNull(address);
        //Can't get address here 
        //assertNotNull(store.getAddress());
        assertEquals(name, store.getName());    	    
    }

    //persistence test for Category
   @Test
    public void testPersistAndLoadCategory() {
        //create a set of products
        Set<Product> productSet = new HashSet();

        //create a grocery store application
        //mandatory to create for a category to exist
        GroceryStoreApplication gs = new GroceryStoreApplication();
        gs.setId(66);
        groceryStoreApplicationRepository.save(gs);

        //create and set up the category
        int id = 11;
        Category category = new Category();
        category.setId(id);
        category.setGroceryStoreApplication(gs);
        category.setName("meat");
        categoryRepository.save(category);
        
        //set up a product
        //for references between objects
        Product  product1 = new Product();
        product1.setGroceryStoreApplication(gs);
        product1.setBarcode(123);
        product1.setCategory(category);
        productRepository.save(product1);
        
        //set up another product
        Product  product2 = new Product();
        product2.setGroceryStoreApplication(gs);
        product2.setBarcode(1234);
        product2.setCategory(category);
        productRepository.save(product2);
        
        //add products in the set
        productSet.add(product2);
        productSet.add(product1);
        category.setProduct(productSet);
        
        category = null;
    
        //find category throufromgh its unique id
        category = categoryRepository.findCategoryById(id);
        //test references of products from category
        productSet = category.getProduct();
        assertNotNull(category);
        assertNotNull(productSet);
        assertEquals(id, category.getId());  
        assertEquals("meat", category.getName());  
     	   
   }

   //persistence test for address
    @Test
    public void testPersistAndLoadAddress() {
        //create a grocery store application
        //for references
        GroceryStoreApplication groceryStoreApplication = new GroceryStoreApplication();
        groceryStoreApplication.setId(90);
        groceryStoreApplicationRepository.save(groceryStoreApplication);
        
        //set up a store
        //for references
        String storeName = "storeName";
        Store store = new Store();
        store.setName(storeName);
        store.setGroceryStoreApplication(groceryStoreApplication);
        storeRepository.save(store);
       
        //set up attributes of address
        int id = 69;
        String country = "USA";
        String province = "Province";
        String city = "Seattle";
        String postalCode = "J3X 1E1";
        String streetName = "Street";
        // First example for object save/load
        Address address = new Address();
        // First example for attribute save/load
        address.setId(id);
        address.setCountry(country);
        address.setCity(city);
        address.setPostalCode(postalCode);
        address.setProvince(province);
        address.setStreetName(streetName);
        address.setStore(store);
        addressRepository.save(address);
        
        address = null;
        store=null;

        //test references to address with store
        store = storeRepository.findStoreByName(storeName);
        address = store.getAddress();
        assertNotNull(address);
        assertNotNull(address.getStore());
        assertNotNull(store);   
        assertEquals(postalCode, address.getPostalCode());
        assertEquals(streetName, address.getStreetName());
        assertEquals(province, address.getProvince());
        assertEquals(id, address.getId());
        assertEquals(country, address.getCountry());
        assertEquals(city, address.getCity());
    }
    

    //persistence test for product
    @Test
    public void testPersistAndLoadProduct() {
        //set up attributes for a product
        int barCode = 123;
        String name = "apple";
        String description = "good apple";
        float price = 23.50f;
        String image = "image";
        float weight = 11f;
        boolean refund = false;
        float volume = 20f;
        int quantity = 30;
        
<<<<<<< Updated upstream
    int barCode = 123;
    String name = "apple";
    String description = "good apple";
    float price = 23.50f;
    String image = "image";
    float weight = 11f;
    boolean refund = false;
    float volume = 20f;
    int quantity = 30;

    GroceryStoreApplication groceryStoreApplication = new GroceryStoreApplication();
    groceryStoreApplication.setId(90);
    groceryStoreApplicationRepository.save(groceryStoreApplication);
    
    Category category = new Category();
    category.setGroceryStoreApplication(groceryStoreApplication);
    category.setId(11);
    categoryRepository.save(category);
    
    Product  product = new Product();
    product.setGroceryStoreApplication(groceryStoreApplication);
    product.setCategory(category);
    product.setBarcode(barCode);
    product.setName(name);
    product.setDescription(description);
    product.setPrice(price);
    product.setWeight(weight);
    product.setImage(image);
    product.setIsRefundable(refund);
    product.setVolume(volume);
    product.setAvailableQuantity(quantity);
    product.setAvailability( Availability.DELIVERY);
    productRepository.save(product);
    
    product = null;
    category = null;
    
    product = productRepository.findProductByBarcode(barCode);
    category = product.getCategory();
    assertNotNull(product);
    assertNotNull(product.getCategory());
    assertNotNull(category);
    assertEquals(product.getCategory().getId(),category.getId());
    assertEquals(product.getBarcode(),barCode);
    assertEquals(product.getName(),name);

    
   
=======
        //create a grocery store application
        //mandatory to create for a store to exist
        GroceryStoreApplication groceryStoreApplication = new GroceryStoreApplication();
        groceryStoreApplication.setId(90);
        groceryStoreApplicationRepository.save(groceryStoreApplication);
        
        //create a new category
        //for references
        Category category = new Category();
        category.setGroceryStoreApplication(groceryStoreApplication);
        category.setId(11);
        categoryRepository.save(category);
        
        //create and set up the product attributes
        Product  product = new Product();
        product.setGroceryStoreApplication(groceryStoreApplication);
        product.setCategory(category);
        product.setBarcode(barCode);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setWeight(weight);
        product.setImage(image);
        product.setIsRefundable(refund);
        product.setVolume(volume);
        product.setAvailableQuantity(quantity);
        product.setAvailability( Availability.DELIVERY);
        productRepository.save(product);
        
        product = null;
        category = null;
        
        //find product with unique id
        product = productRepository.findProductByBarcode(barCode);
        //test references of category from product
        category = product.getCategory();
        assertNotNull(product);
        assertNotNull(product.getCategory());
        assertNotNull(category);
        assertEquals(product.getCategory().getId(),category.getId());
        assertEquals(product.getBarcode(),barCode);
        assertEquals(product.getName(),name);
>>>>>>> Stashed changes
    }
    
    //persistence test for shift
    @Test
    public void testPersistAndLoadShift() {
    	//create a grocery store app
    	GroceryStoreApplication gs = new GroceryStoreApplication ();
    	gs.setId(222);
    	groceryStoreApplicationRepository.save(gs);
    	
        //create an employee
        //mandatory to create for a shift to exist
    	Employee employee = new Employee();
    	employee.setId(123);
    	employee.setGroceryStoreApplication(gs);
    	employeeRepository.save(employee);
    	
        //create and set up a shift
    	Shift shift = new Shift();
    	shift.setId(111);
    	shift.setDay(Shift.Day.FRIDAY);
    	shift.setShift(Shift.ShiftType.CLOSING);
    	shift.setEmployee(employee);
    	shiftRepository.save(shift);
    	
    	shift = null;
    	employee = null;
    	
        //find shift from unique id
    	shift = shiftRepository.findShiftById(111);
    	employee = shift.getEmployee();
    	assertNotNull(shift);
    	assertNotNull(employee);
    	assertNotNull(shift.getEmployee());
    	assertEquals(shift.getEmployee().getId(),123);
    	assertEquals(shift.getDay(),Day.FRIDAY);	
    }

    //persistence test for a grocery order
    @Test
    public void testPersistAndLoadGroceryOrder() {
        //create a grocery store application
        //mandatory to create for a store to exist
      	GroceryStoreApplication gs = new GroceryStoreApplication ();
    	gs.setId(011);
    	groceryStoreApplicationRepository.save(gs);
    	
        //create an address
    	Address address = new Address();
    	address.setId(808);
    	addressRepository.save(address);
    	
    	//create and set up an order
    	GroceryOrder order = new GroceryOrder();
    	order.setGroceryStoreApplication(gs);
    	order.setId(999);
    	order.setCustomerNote("no nuts");
    	order.setBillingAddress(address);
    	groceryOrderRepository.save(order);
    	
    	order=null;
    	address=null;
    	
        //find grocery order from unique id
    	order = groceryOrderRepository.findGroceryOrderById(999);
        //test references of order to associated address
    	address = order.getBillingAddress();
    	assertNotNull(order);
    	assertNotNull(address);
    	assertEquals(order.getCustomerNote(),"no nuts");
    	assertEquals(order.getBillingAddress().getId(),808);
    }
    
    //persistence test for payment
    @Test
    public void testPersistAndLoadPayment() {
    	//set up a grocery store application
       	GroceryStoreApplication gs = new GroceryStoreApplication ();
    	gs.setId(90);
    	groceryStoreApplicationRepository.save(gs);
    	
        //set up a grocery order
        //mandatory to create for a payment to exist
    	GroceryOrder order = new GroceryOrder();
    	order.setGroceryStoreApplication(gs);
    	order.setId(666);
    	groceryOrderRepository.save(order);
    	
        //create and set up a payment
    	Payment payment = new Payment();
    	payment.setId(59);
    	payment.setPaymentCode("f4x");
    	payment.setOrder(order);
    	paymentRepository.save(payment);
    	
    	payment = null;
    	order=null;

        //find payment from unique id
    	payment = paymentRepository.findPaymentById(59);
        //test payment to associated order
    	order =  payment.getOrder();    	
    	assertNotNull(payment);
    	assertNotNull(order);
    	assertEquals(payment.getId(),59);
    	assertEquals(payment.getPaymentCode(),"f4x");	
    }
<<<<<<< Updated upstream
       
=======
    
    //persistence test for employee
>>>>>>> Stashed changes
    @Test
    public void testPersistAndLoadEmployee() {
    	//create a grocery store application
    	GroceryStoreApplication gs = new GroceryStoreApplication ();
    	gs.setId(95);
    	groceryStoreApplicationRepository.save(gs);
    	
        //create a grocery user
    	GroceryUser gu = new GroceryUser();
    	gu.setEmail("johnnysins@gmail.com");
    	groceryUserRepository.save(gu);
    	
        //create and set up an employee
    	Employee employee = new Employee();
    	employee.setId(1234567);
    	employee.setUser(gu);
    	employee.setGroceryStoreApplication(gs);
    	employeeRepository.save(employee);
    	
    	gu=null;
    	employee = null;
    	
        //find employee from unique id
    	employee = employeeRepository.findEmployeeById(1234567);
        //test if employee is proper user
    	gu = employee.getUser();
    	assertNotNull(employee);
    	assertNotNull(gu);
    	assertNotNull(employee.getUser());
    	assertEquals(employee.getId(),1234567);
    	assertEquals(employee.getUser().getEmail(),"johnnysins@gmail.com");
    }
    
    //persistence test for grocery user
    @Test
    public void testPersistAndLoadGroceryUser() {
<<<<<<< Updated upstream
    	
    	Set <UserRole> userSet = new HashSet();
    	
    	
=======
    	//create and set up a grocery user
>>>>>>> Stashed changes
    	GroceryUser user = new GroceryUser();
    	user.setEmail("danny@gmail.com");
    	groceryUserRepository.save(user);
    	
<<<<<<< Updated upstream
    	Employee employee = new Employee();   	
    	userSet.add(employee);
    	
=======
>>>>>>> Stashed changes
    	user = null;

        //find user from unique id
    	user = groceryUserRepository.findGroceryUserByEmail("danny@gmail.com");
    	assertNotNull(user);
    	assertEquals("danny@gmail.com", user.getEmail());    	
    }
    
    //persistence test for manager
    @Test
    public void testPersistAndLoadManager() {

    	//create a grocery store application
    	GroceryStoreApplication gs = new GroceryStoreApplication ();
    	gs.setId(110);
    	groceryStoreApplicationRepository.save(gs);

<<<<<<< Updated upstream
    	
=======
        //create and set up a manager
>>>>>>> Stashed changes
    	Manager manager = new Manager();
    	manager.setId(12345);
    	manager.setGroceryStoreApplication(gs);
    	managerRepository.save(manager);
    	
    	manager = null;
    	
        //find manager from unique id
    	manager = managerRepository.findManagerById(12345);
    	assertNotNull(manager);
    	assertEquals(12345,manager.getId());
    }
<<<<<<< Updated upstream
      
=======
    
    //persistence test for grocery app
>>>>>>> Stashed changes
    @Test
    public void testPersistAndLoadGroceryApplication() {
    	//create a grocery store application
    	GroceryStoreApplication gs = new GroceryStoreApplication ();
    	gs.setId(120);
    	groceryStoreApplicationRepository.save(gs);
	
    	gs = null;
    	
        //find grocery store from unique id
    	gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(120);
    	assertNotNull(gs);
    	assertEquals(120,gs.getId());	
    }
    
    @Test
    public void testPersistAndLoadCustomer() {
    	
    	GroceryStoreApplication gs = new GroceryStoreApplication ();
<<<<<<< Updated upstream
        gs.setId(195);
        groceryStoreApplicationRepository.save(gs);

        GroceryUser gu = new GroceryUser();
        gu.setEmail("johnnysin@gmail.com");

        groceryUserRepository.save(gu);

        Address address = new Address();
        address.setId(245);
        addressRepository.save(address);

        Customer customer = new Customer();
        customer.setId(12);
        customer.setUser(gu);
        customer.setAddress(address);
        customer.setGroceryStoreApplication(gs);
        customerRepository.save(customer);

        gu=null;
        customer = null;

        customer = customerRepository.findCustomberById(12);
        gu = customer.getUser();

        assertNotNull(customer);
        assertNotNull(gu);
        assertNotNull(customer.getUser());
        assertEquals(customer.getId(),12);
        assertEquals(customer.getUser().getEmail(),"johnnysin@gmail.com");
    }
    
    
    
    
    
    
    
    

=======
    	gs.setId(168);
    	groceryStoreApplicationRepository.save(gs);
    	
    	
    	
    	Customer customer = new Customer();
    	
    	customer.setId(789);
    	customer.setGroceryStoreApplication(gs);
    	customerRepository.save(customer);
    	
    	customer = null;
    	
    	customer = customerRepository.findCustomberById(789);
    	
    	assertNotNull(customer);
    	assertEquals(789, customer.getId());
    }*/
>>>>>>> Stashed changes
}