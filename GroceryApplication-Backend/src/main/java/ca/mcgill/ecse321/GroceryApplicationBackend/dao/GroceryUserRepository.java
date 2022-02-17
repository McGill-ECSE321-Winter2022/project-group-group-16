package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryUser;

public interface GroceryUserRepository extends CrudRepository<GroceryUserRepository,Integer> {
	
	/**
	 * Find a grocery user by their full name
	 * 
	 * @param firstName
	 * @param lastName
	 * @return groceryUser (GroceryUser)
	 * 
	 * */
	GroceryUser findGroceryUserByFirstNameAndLastName(String firstName, String lastName);
	
	
	/**
	 * Find a grocery user by email
	 * 
	 * @param email
	 * @return groceryUser (GroceryUser)
	 *  
	 *  */
	
	GroceryUser findGroceryUserByEmail(String email);
	
	  /**
	   * Check if grocery user exists by full name
	   * 
	   * @param firstName
	   * @param lastName
	   * @return boolean
	   */
	
	
	boolean existsByFirstNameAndLastName(String firstName, String lastName);
	
	
	/**
	 * Check if a grocery user has an email
	 * 
	 * @param email
	 * @return boolean
	 *  
	 *  */
	
	boolean existsByEmail(String email);
	
	
		
	
	
	
	
	
	
	
}
