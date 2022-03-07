package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryUser;
import org.springframework.data.repository.CrudRepository;


public interface GroceryUserRepository extends CrudRepository<GroceryUser, String> {


    /**
     * Find grocery user via email
     *
     * @param email
     * @return groceryUser(GroceryUser)
     */
    GroceryUser findGroceryUserByEmail(String email);

}
