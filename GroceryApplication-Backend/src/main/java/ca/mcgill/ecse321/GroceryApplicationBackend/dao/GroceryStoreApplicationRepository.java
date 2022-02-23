package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Category;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryUser;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product;

public interface GroceryStoreApplicationRepository extends CrudRepository<GroceryStoreApplication, String> {

	/**
	 * Find GroceryStore Application via id
	 * @param id
	 * @return groceryStoreApplication(GroceryStoreApplication)
	 *  
	 *  
	 *  */
	
    GroceryStoreApplication findGroceryStoreApplicationById(int id);
    // GroceryStoreApplication finGroceryOrderByGroceryUser(GroceryUser user);
    // GroceryStoreApplication finGroceryOrderByProducet(Product product);
    // GroceryStoreApplication finGroceryOrderCategoryd(Category category);
    // GroceryStoreApplication finGroceryOrderByGroceryOrder(GroceryOrder order);
    // boolean existsByGroceryUserAndProduct(GroceryUser user, Product product);
    // boolean existsByGroceryUserAndGroceryOrder(GroceryUser user, GroceryOrder order);
    // boolean existsByCategroyAndGroceryOrder(Category category, GroceryOrder order);

	
    
    
}
