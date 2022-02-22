package ca.mcgill.ecse321.GroceryApplicationBackend.dao;


import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Address;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Customer;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Store;

public interface AddressRepository extends CrudRepository<Address, Integer>{

	/**
	 * Find address via address id
	 * @param id
	 * @return address (Address)
	 *  
	 *  
	 *  */
	
    Address findAddressById(int id);
    
    /**
     * Find address via the city
     *  @param city
     *  @return address (Address)
     *  
     *  */
    
    
    
    Address findAddressByCity(String city);

}