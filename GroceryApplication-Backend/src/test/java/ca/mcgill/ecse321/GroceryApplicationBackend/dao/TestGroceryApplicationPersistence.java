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

    @AfterEach
   public void clearDatabase() {
//        // First, we clear registrations to avoid exceptions due to inconsistencies
//        // Then we can clear the other tables
       storeRepository.deleteAll();
       addressRepository.deleteAll();
    //    categoryRepository.deleteAll();
    //    paymentRepository.deleteAll();
   }

   @Test
    public void testPersistAndLoadStore() {
        GroceryStoreApplication gs = new GroceryStoreApplication();
        gs.setId(66);
        groceryStoreApplicationRepository.save(gs);

        // Address address = new Address();
        // int id = 10;
        // address.setId(id);
        // addressRepository.save(address);

        String name = "Grocery";
    	Store store = new Store();
    	store.setGroceryStoreApplication(gs);
        // store.setAddress(address);
        store.setName(name);
    	storeRepository.save(store);
        
    	store = null;

    	store = storeRepository.findStoreByName(name);
        assertNotNull(store);
        assertEquals(name, store.getName());
        // assertEquals(address, store.getAddress());    	    
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
        int id = 69;
        String country = "USA";
        String city = "Seattle";
        // First example for object save/load
        Address address = new Address();
        // First example for attribute save/load
        address.setId(id);
        address.setCountry(country);
        address.setCity(city);
        addressRepository.save(address);

        address = null;

        address = addressRepository.findAddressById(id);
        assertNotNull(address);
        assertEquals(id, address.getId());
        assertEquals(country, address.getCountry());
        assertEquals(city, address.getCity());
    }

}
