package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.*;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product.Availability;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Shift.Day;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestGroceryApplicationPersistence {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
     GroceryStoreApplicationRepository groceryStoreApplicationRepository;

    @Autowired
     AddressRepository addressRepository;

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
        managerRepository.deleteAll();
        shiftRepository.deleteAll();
        storeRepository.deleteAll();
        paymentRepository.deleteAll();
        groceryOrderRepository.deleteAll();
        productRepository.deleteAll();
        categoryRepository.deleteAll();
        employeeRepository.deleteAll();
        customerRepository.deleteAll();
        groceryUserRepository.deleteAll();
        addressRepository.deleteAll();
        groceryStoreApplicationRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadStore() {
        //create a grocery store application
        //mandatory to create for a store to exist
        GroceryStoreApplication gs = new GroceryStoreApplication();
        gs.setId(66);
        groceryStoreApplicationRepository.save(gs);

        Address address = new Address();
        int id = 10;
        address.setId(id);
        addressRepository.save(address);

        String name = "Grocery";
        Store store = new Store();
        store.setGroceryStoreApplication(gs);
        store.setAddress(address);
        store.setName(name);
        storeRepository.save(store);

        store = storeRepository.findStoreByName(name);
        address = addressRepository.findAddressById(id);
        assertNotNull(store);
        assertNotNull(address);
        //Can't get address here 
        // assertNotNull(store.getAddress());
        assertEquals(name, store.getName());

    }

    @Test
    public void testPersistAndLoadCategory() {

        Set<Product> productSet = new HashSet();
        //create a grocery store application
        //mandatory to create for a store to exist
        GroceryStoreApplication gs = new GroceryStoreApplication();
        gs.setId(66);
        groceryStoreApplicationRepository.save(gs);


        int id = 11;

        Category category = new Category();
        category.setId(id);
        category.setGroceryStoreApplication(gs);
        category.setName("meat");
        categoryRepository.save(category);

        Product product1 = new Product();
        product1.setGroceryStoreApplication(gs);
        product1.setBarcode(123);
        product1.setCategory(category);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setGroceryStoreApplication(gs);
        product2.setBarcode(1234);
        product2.setCategory(category);
        productRepository.save(product2);

        productSet.add(product2);
        productSet.add(product1);
        category.setProduct(productSet);


        category = categoryRepository.findCategoryById(id);
        productSet = category.getProduct();
        assertNotNull(category);
        assertNotNull(productSet);
        assertEquals(id, category.getId());
        assertEquals("meat", category.getName());

    }

    @Test
    public void testPersistAndLoadAddress() {
        //create a grocery store application
        //mandatory to create for a store to exist
        GroceryStoreApplication groceryStoreApplication = new GroceryStoreApplication();
        groceryStoreApplication.setId(90);
        groceryStoreApplicationRepository.save(groceryStoreApplication);

        String storeName = "storeName";
        Store store = new Store();
        store.setName(storeName);
        store.setGroceryStoreApplication(groceryStoreApplication);

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
        store.setAddress(address);
        storeRepository.save(store);
        addressRepository.save(address);

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

    @Test
    public void testPersistAndLoadProduct() {

        int barCode = 123;
        String name = "apple";
        String description = "good apple";
        float price = 23.50f;
        String image = "image";
        float weight = 11f;
        boolean refund = false;
        float volume = 20f;
        int quantity = 30;
        //create a grocery store application
        //mandatory to create for a store to exist
        GroceryStoreApplication groceryStoreApplication = new GroceryStoreApplication();
        groceryStoreApplication.setId(90);
        groceryStoreApplicationRepository.save(groceryStoreApplication);

        Category category = new Category();
        category.setGroceryStoreApplication(groceryStoreApplication);
        category.setId(11);
        categoryRepository.save(category);

        Product product = new Product();
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
        product.setAvailability(Availability.DELIVERY);
        productRepository.save(product);

        product = productRepository.findProductByBarcode(barCode);
        category = product.getCategory();
        assertNotNull(product);
        assertNotNull(product.getCategory());
        assertNotNull(category);
        assertEquals(product.getCategory().getId(), category.getId());
        assertEquals(product.getBarcode(), barCode);
        assertEquals(product.getName(), name);


    }

    @Test
    public void testPersistAndLoadShift() {
        //create a grocery store application
        //mandatory to create for a store to exist
        GroceryStoreApplication gs = new GroceryStoreApplication();
        gs.setId(222);
        groceryStoreApplicationRepository.save(gs);

        Employee employee = new Employee();
        employee.setId(123);
        employee.setGroceryStoreApplication(gs);
        employeeRepository.save(employee);

        Shift shift = new Shift();
        shift.setId(111);
        shift.setDay(Shift.Day.FRIDAY);
        shift.setShift(Shift.ShiftType.CLOSING);
        shift.setEmployee(employee);
        shiftRepository.save(shift);


        shift = shiftRepository.findShiftById(111);
        employee = shift.getEmployee();

        assertNotNull(shift);
        assertNotNull(employee);
        assertNotNull(shift.getEmployee());
        assertEquals(shift.getEmployee().getId(), 123);
        assertEquals(shift.getDay(), Day.FRIDAY);


    }

    @Test
    public void testPersistAndLoadGroceryOrder() {
        //create a grocery store application
        //mandatory to create for a store to exist
        GroceryStoreApplication gs = new GroceryStoreApplication();
        gs.setId(011);
        groceryStoreApplicationRepository.save(gs);

        Address address = new Address();
        address.setId(808);
        addressRepository.save(address);


        GroceryOrder order = new GroceryOrder();
        order.setGroceryStoreApplication(gs);
        order.setId(999);
        order.setCustomerNote("no nuts");
        order.setBillingAddress(address);
        groceryOrderRepository.save(order);

        order = groceryOrderRepository.findGroceryOrderById(999);
        address = order.getBillingAddress();
        assertNotNull(order);
        assertNotNull(address);
        assertEquals(order.getCustomerNote(), "no nuts");
        assertEquals(order.getBillingAddress().getId(), 808);

    }

    @Test
    public void testPersistAndLoadPayment() {
        //create a grocery store application
        //mandatory to create for a store to exist
        GroceryStoreApplication gs = new GroceryStoreApplication();
        gs.setId(90);
        groceryStoreApplicationRepository.save(gs);

        GroceryOrder order = new GroceryOrder();
        order.setGroceryStoreApplication(gs);
        order.setId(666);
        groceryOrderRepository.save(order);

        Payment payment = new Payment();
        payment.setId(59);
        payment.setPaymentCode("f4x");
        payment.setOrder(order);
        paymentRepository.save(payment);

        payment = paymentRepository.findPaymentById(59);
        order = payment.getOrder();
        assertNotNull(payment);
        assertNotNull(order);
        assertEquals(payment.getId(), 59);
        assertEquals(payment.getPaymentCode(), "f4x");

    }

    @Test
    public void testPersistAndLoadEmployee() {

        GroceryStoreApplication gs = new GroceryStoreApplication();
        gs.setId(95);
        groceryStoreApplicationRepository.save(gs);

        GroceryUser gu = new GroceryUser();
        gu.setEmail("johnnysins@gmail.com");
        groceryUserRepository.save(gu);

        Employee employee = new Employee();
        employee.setId(1234567);
        employee.setUser(gu);
        employee.setGroceryStoreApplication(gs);
        employeeRepository.save(employee);

        employee = employeeRepository.findEmployeeById(1234567);
        gu = employee.getUser();

        assertNotNull(employee);
        assertNotNull(gu);
        assertNotNull(employee.getUser());
        assertEquals(employee.getId(), 1234567);
        assertEquals(employee.getUser().getEmail(), "johnnysins@gmail.com");
    }

    @Test
    public void testPersistAndLoadGroceryUser() {
        Set<UserRole> roleSet = new HashSet<>();
        GroceryStoreApplication gs = new GroceryStoreApplication();
        gs.setId(95);
        groceryStoreApplicationRepository.save(gs);

        GroceryUser gu = new GroceryUser();
        gu.setEmail("johnnysins@gmail.com");
        groceryUserRepository.save(gu);

        Employee employee1 = new Employee();
        employee1.setId(1234567111);
        employee1.setGroceryStoreApplication(gs);
        employee1.setUser(gu);
        employeeRepository.save(employee1);

        Employee employee2 = new Employee();
        employee2.setId(12343111);
        employee2.setGroceryStoreApplication(gs);
        employee2.setUser(gu);
        employeeRepository.save(employee2);

        roleSet.add(employee2);
        roleSet.add(employee1);

        GroceryUser user = new GroceryUser();
        user.setEmail("danny@gmail.com");
        user.setUserRole(roleSet);
        groceryUserRepository.save(user);

        Set<UserRole> userRoleSet = user.getUserRole();
        user = groceryUserRepository.findGroceryUserByEmail("danny@gmail.com");
        assertNotNull(user);
        assertEquals("danny@gmail.com", user.getEmail());
        assertNotNull(userRoleSet);
    }

    @Test
    public void testPersistAndLoadManager() {

        GroceryStoreApplication gs = new GroceryStoreApplication();
        gs.setId(110);
        groceryStoreApplicationRepository.save(gs);

        String userEmail = "user@email.com";
        GroceryUser user = new GroceryUser();
        user.setEmail(userEmail);
        groceryUserRepository.save(user);


        Manager manager = new Manager();
        manager.setId(12345);
        manager.setGroceryStoreApplication(gs);
        manager.setUser(user);
        managerRepository.save(manager);

        manager = managerRepository.findManagerById(12345);

        // entity
        assertNotNull(manager);

        // attribute
        assertEquals(12345, manager.getId());

        //relation
        assertNotNull(manager.getUser());
        assertEquals(manager.getUser().getEmail(), userEmail);
    }

    @Test
    public void testPersistAndLoadGroceryApplication() {

        GroceryStoreApplication gs = new GroceryStoreApplication();
        gs.setId(120);

        groceryStoreApplicationRepository.save(gs);

        gs = null;

        gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(120);
        assertNotNull(gs);
        assertEquals(120, gs.getId());


    }

    @Test
    public void testPersistAndLoadCustomer() {

        GroceryStoreApplication gs = new GroceryStoreApplication();
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

        customer = customerRepository.findCustomerById(12);
        gu = customer.getUser();

        assertNotNull(customer);
        assertNotNull(gu);
        assertNotNull(customer.getUser());
        assertEquals(customer.getId(), 12);
        assertEquals(customer.getUser().getEmail(), "johnnysin@gmail.com");
    }


}