package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryUser;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Manager;
import org.springframework.data.repository.CrudRepository;


public interface ManagerRepository extends CrudRepository<Manager, Integer> {


    /**
     * Find manager via Id
     *
     * @param id
     * @return manager(Manager)
     */
    Manager findManagerById(int id);

    Manager findManagerByUser(GroceryUser user);

    void deleteManagerById(int id);

    void deleteManagerByUser(GroceryUser user);


}
