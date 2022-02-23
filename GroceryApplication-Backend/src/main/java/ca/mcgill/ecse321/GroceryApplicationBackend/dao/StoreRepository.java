package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Store;
import org.springframework.data.repository.CrudRepository;

public interface StoreRepository extends CrudRepository<Store, Integer> {


    /**
     * Find store via their name
     *
     * @param name
     * @return store(Store)
     */
    Store findStoreByName(String name);


    // boolean existsByStoreName(String name);

}
