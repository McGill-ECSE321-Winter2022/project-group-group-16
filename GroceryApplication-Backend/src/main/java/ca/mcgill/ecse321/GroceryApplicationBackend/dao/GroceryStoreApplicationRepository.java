package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;

public interface GroceryStoreApplicationRepository extends CrudRepository<GroceryStoreApplication, String> {

    GroceryStoreApplication finGroceryOrderById(int id);
}
