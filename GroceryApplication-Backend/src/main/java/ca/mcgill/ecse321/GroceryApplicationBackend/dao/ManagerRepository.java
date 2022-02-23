package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Manager;



public interface ManagerRepository extends CrudRepository<Manager, Integer> {
	
	
	/**
	 * Find manager via Id
	 * @param id
	 * @return manager(Manager)
	 *  
	 *  */
	Manager findManagerById(int Id);
	
	

}
