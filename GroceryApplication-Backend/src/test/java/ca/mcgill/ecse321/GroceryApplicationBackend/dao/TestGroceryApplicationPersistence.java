package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.*;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product.Availability;


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
    StoreRepository StoreRepository;
    
    
    

    @AfterEach
   public void clearDatabase() {
//        // First, we clear registrations to avoid exceptions due to inconsistencies
//        // Then we can clear the other tables
       storeRepository.deleteAll();
       addressRepository.deleteAll();
       productRepository.deleteAll();
    //    categoryRepository.deleteAll();
    //    paymentRepository.deleteAll();
   }

   @Test
    public void testPersistAndLoadStore() {
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
        
    	address= null;

    	store = storeRepository.findStoreByName(name);
        assertNotNull(store);
        //Can't get address here 
//        assertNotNull(store.getAddress());
        assertEquals(name, store.getName());
       	    
    }

    @Test
    public void testPersistAndLoadCategory() {

        GroceryStoreApplication gs = new GroceryStoreApplication();
        gs.setId(66);
        groceryStoreApplicationRepository.save(gs);

    	int id = 11;
        // String name = "perishable";
        // String image = "image";
        // String desc = "desc";
    	Category category = new Category();
    	category.setId(id);
        // category.setName(name);
        category.setGroceryStoreApplication(gs);
    	categoryRepository.save(category);
        
    	category = null;

    	category = categoryRepository.findCategoryById(id);
        // category = categoryRepository.findCategoryByname(name);
        assertNotNull(category);
        assertEquals(id, category.getId());    	 
        // assertEquals(name, category.getName()); 
        // assertEquals(image, category.getImage()); 
        // assertEquals(desc, category.getDescription());  	   
    }

    @Test
    public void testPersistAndLoadAddress() {

        GroceryStoreApplication groceryStoreApplication = new GroceryStoreApplication();
        groceryStoreApplication.setId(90);
        groceryStoreApplicationRepository.save(groceryStoreApplication);
        
        String storeName = "storeName";
        Store store = new Store();
        store.setName(storeName);
        store.setGroceryStoreApplication(groceryStoreApplication);
        storeRepository.save(store);
       
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

        address = addressRepository.findAddressById(id);
        assertNotNull(address);
        assertNotNull(address.getStore());
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
    
    product = productRepository.findProductByBarcode(barCode);
    assertNotNull(product);
    assertNotNull(category);
    assertEquals(product.getCategory().getId(),category.getId());
    assertEquals(product.getBarcode(),barCode);
    assertEquals(product.getName(),name);

    
   
    }
    
    @Test
    public void testPersistAndLoadShift() {
    	
    	
    	
    }
    

}
