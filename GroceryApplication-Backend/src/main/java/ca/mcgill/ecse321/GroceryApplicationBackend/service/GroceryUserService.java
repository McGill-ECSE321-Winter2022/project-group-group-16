package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import javax.management.InvalidApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryStoreApplicationRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryUserRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Address;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Customer;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Employee;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryUser;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.UserRole;

public class GroceryUserService {
	@Autowired
	GroceryStoreApplicationRepository groceryStoreApplicationRepository;
	
	  @Autowired
	  GroceryUserRepository groceryUserRepository;

	
	@Transactional
	public GroceryUser createGroceryUser(int custiomerId, int applicationId, int addressId, int customerId, String userEmail) throws InvalidApplicationException {
		GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);
		if (gs == null) {
			throw new InvalidApplicationException("No application associated with this Id.");
		}

        GroceryUser gu = new GroceryUser();
        gu.setEmail("johnnysins@gmail.com");
        groceryUserRepository.save(gu);

        Employee employee1 = new Employee();
        employee1.setId(1234567111);
        employee1.setGroceryStoreApplication(gs);
        employee1.setUser(gu);
        //employeeRepository.save(employee1);

        Employee employee2 = new Employee();
        employee2.setId(12343111);
        employee2.setGroceryStoreApplication(gs);
        employee2.setUser(gu);
      // employeeRepository.save(employee2);

       // roleSet.add(employee2);
       // roleSet.add(employee1);

        GroceryUser user = new GroceryUser();
        user.setEmail("danny@gmail.com");
        
        groceryUserRepository.save(user);

        return null;
	}
}
