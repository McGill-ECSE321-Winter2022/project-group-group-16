package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import org.springframework.data.repository.CrudRepository;

public interface GroceryStoreApplicationRepository extends CrudRepository<GroceryStoreApplication, String> {

    /**
     * Find GroceryStore Application via id
     *
     * @param id
     * @return groceryStoreApplication(GroceryStoreApplication)
     */
    GroceryStoreApplication findGroceryStoreApplicationById(int id);
}
