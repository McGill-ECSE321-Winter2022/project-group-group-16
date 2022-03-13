package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.*;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.*;

@Service
public class ManagerService {

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    GroceryStoreApplicationRepository groceryStoreApplicationRepository;

    @Autowired
    GroceryUserRepository groceryUserRepository;

    /**
     * Service method for creating a manager
     * 
     * @param applicationId
     * @param email
     * @return created manager
     */
    @Transactional
    public Manager createManager(Integer applicationId, String email) { // no id? email should alr be unique id

        if (email == null) {
            throw new ApiRequestException("The email cannot be null.");
        }

        GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);

        if (gs == null) {
            throw new ApiRequestException("The application doesn't exist.");
        }

        GroceryUser user = groceryUserRepository.findGroceryUserByEmail(email);
        if (user == null) {
            throw new ApiRequestException("The user doesn't exist.");
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
     * @param id
     * @param email
     * @param applicationId
     * @return updated manager
     */
    @Transactional
    public Manager updateManager(Integer id, String email, Integer applicationId) {

        Manager manager = managerRepository.findManagerById(id);

        if (manager == null) {
            throw new ApiRequestException("Manager not found.");
        }

        if (email != null) {
            GroceryUser groceryUser = groceryUserRepository.findGroceryUserByEmail(email);
            if (groceryUser == null)
                throw new ApiRequestException("This user does not exist");
            manager.setUser(groceryUser);
        }

        if (applicationId != null) {
            GroceryStoreApplication groceryStoreApplication = groceryStoreApplicationRepository
                    .findGroceryStoreApplicationById(applicationId);
            if (groceryStoreApplication == null) {
                throw new ApiRequestException("Application with id " + applicationId + " does not exist.");
            }

            manager.setGroceryStoreApplication(groceryStoreApplication);

            managerRepository.save(manager);
        }
        return manager;
    }

    // can the store exist even if we delete the manager?

    /**
     * Service method for deleting a manager by id
     * 
     * @param id
     */
    @Transactional
    public void deleteManagerById(Integer id) {

        Manager manager = managerRepository.findManagerById(id);

        if (manager == null) {
            throw new ApiRequestException("Manager does not exist");
        }
        managerRepository.deleteManagerById(id);
    }

    /**
     * Service method for deleting a manager by email
     * 
     * @param email
     */
    @Transactional
    public void deleteManagerByEmail(String email) {
        GroceryUser user = groceryUserRepository.findGroceryUserByEmail(email);
        if (user == null)
            throw new ApiRequestException("User does not exist");

        Manager manager = managerRepository.findManagerByUser(user);
        if (manager == null)
            throw new ApiRequestException("Manager does not exist");

        managerRepository.deleteManagerByUser(user);

    }

    /**
     * Service method for retrieving a manager by id
     * 
     * @param id
     * @return requested manager
     */
    @Transactional
    public Manager getManagerbyId(Integer id) {
        Manager manager = managerRepository.findManagerById(id);

        if (manager == null) {
            throw new ApiRequestException("Manager does not exist");
        }

        return manager;
    }

    /**
     * Service method for retrieving a manager by email
     * 
     * @param email
     * @return
     */
    @Transactional
    public Manager getManagerByEmail(String email) {
        GroceryUser user = groceryUserRepository.findGroceryUserByEmail(email);
        if (user == null)
            throw new ApiRequestException("This user does not exist");

        Manager manager = managerRepository.findManagerByUser(user);
        if (manager == null)
            throw new ApiRequestException("Manager does not exist");

        return manager;
    }

}
