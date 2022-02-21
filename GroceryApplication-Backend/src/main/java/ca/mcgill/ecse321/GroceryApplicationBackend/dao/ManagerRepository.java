package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Manager;



public interface ManagerRepository extends CrudRepository<Manager, Integer> {
	
	
	Manager findManagerById(int Id);
	
	

}
