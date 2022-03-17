package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryStoreApplicationRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryUserRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.ManagerRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryUser;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManagerService {

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    GroceryStoreApplicationRepository groceryStoreApplicationRepository;

    @Autowired
    GroceryUserRepository groceryUserRepository;

    /**
     * Method to create a new manager
     *
     * @param applicationId the application
     * @param email         the user email
     * @return the created manager
     */
    @Transactional
    public Manager createManager(Integer applicationId, String email) { // no id? email should alr be unique id

        if (email == null) {
            throw new ApiRequestException("The email cannot be null!");
        }

        GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);

        if (gs == null) {
            throw new ApiRequestException("The application doesn't exist!");
        }

        GroceryUser user = groceryUserRepository.findGroceryUserByEmail(email);
        if (user == null) {
            throw new ApiRequestException("The user doesn't exist!");
        }

        Manager manager = new Manager();
        manager.setGroceryStoreApplication(gs);
        manager.setUser(user);

        managerRepository.save(manager);
        return manager;
    }

    // there's only 1 manager/owner, don't need to getAll managers

    // do we ever need to update the managers account?

    /**
     * Service method for updating a manager
     *
     * @param id            the manager id
     * @param email         the new user email
     * @param applicationId the new application id
     * @return the updated manager
     */
    @Transactional
    public Manager updateManager(Integer id, String email, Integer applicationId) {

        Manager manager = managerRepository.findManagerById(id);

        if (manager == null) {
            throw new ApiRequestException("Manager not found!");
        }

        if (email != null) {
            GroceryUser groceryUser = groceryUserRepository.findGroceryUserByEmail(email);
            if (groceryUser == null)
                throw new ApiRequestException("This user does not exist!");
            manager.setUser(groceryUser);
        }

        if (applicationId != null) {
            GroceryStoreApplication groceryStoreApplication = groceryStoreApplicationRepository
                    .findGroceryStoreApplicationById(applicationId);
            if (groceryStoreApplication == null) {
                throw new ApiRequestException("Application does not exist!");
            }

            manager.setGroceryStoreApplication(groceryStoreApplication);

            managerRepository.save(manager);
        }
        return manager;
    }

    /**
     * Service method for retrieving a manager by id
     *
     * @param id the manager ID
     * @return requested manager
     */
    @Transactional
    public Manager getManagerById(Integer id) {
        Manager manager = managerRepository.findManagerById(id);

        if (manager == null) {
            throw new ApiRequestException("Manager does not exist!");
        }

        return manager;
    }

    /**
     * Service method for retrieving a manager by email
     *
     * @param email The manager's user email
     * @return the manager
     */
    @Transactional
    public Manager getManagerByEmail(String email) {
        GroceryUser user = groceryUserRepository.findGroceryUserByEmail(email);
        if (user == null)
            throw new ApiRequestException("User does not exist!");

        Manager manager = managerRepository.findManagerByUser(user);
        if (manager == null)
            throw new ApiRequestException("User is not a manager!");

        return manager;
    }

    // can the store exist even if we delete the manager?

    /**
     * Service method for deleting a manager by id
     *
     * @param id the manager ID to be deleted
     */
    @Transactional
    public void deleteManagerById(Integer id) {

        Manager manager = managerRepository.findManagerById(id);

        if (manager == null) {
            throw new ApiRequestException("Manager does not exist!");
        }
        managerRepository.deleteManagerById(id);
    }

    /**
     * Service method for deleting a manager by email
     *
     * @param email the manager's user email
     */
    @Transactional
    public void deleteManagerByEmail(String email) {
        GroceryUser user = groceryUserRepository.findGroceryUserByEmail(email);
        if (user == null)
            throw new ApiRequestException("User does not exist!");

        Manager manager = managerRepository.findManagerByUser(user);
        if (manager == null)
            throw new ApiRequestException("User is not a manager!");

        managerRepository.deleteManagerByUser(user);

    }

}
