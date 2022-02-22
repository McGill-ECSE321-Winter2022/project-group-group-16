package ca.mcgill.ecse321.GroceryApplicationBackend.dao;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Store;

public interface StoreRepository extends CrudRepository<Store,Integer> {
	
	
	/**
	 * Find store via their name
	 * @param name
	 * @return store(Store)
	 *  */
	Store findStoreByName(String name);
	

	
	// boolean existsByStoreName(String name);
	
}
