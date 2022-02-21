package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryUser;



public interface GroceryUserRepository extends CrudRepository<GroceryUser, Integer> {
	
	GroceryUser findGroceryUserByEmail(String email);

}
