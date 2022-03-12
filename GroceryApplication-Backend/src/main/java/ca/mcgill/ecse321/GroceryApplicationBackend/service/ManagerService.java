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
     * @param applicationId
     * @param null
     * @return Manager
     */
    @Transactional
    public Manager createManager(Integer applicationId, String email){ //no id? email should alr be unique id

        if(email == null){
            throw new ApiRequestException("The email cannot be null.");
        }

        GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);

        if(gs == null){
            throw new ApiRequestException("The application doesn't exist.");
        }

        GroceryUser user = groceryUserRepository.findGroceryUserByEmail(email);
        if(user == null){
            throw new ApiRequestException("The user doesn't exist.");
        }

        Manager manager = new Manager();
        manager.setGroceryStoreApplication(gs);
        manager.setUser(user);

        managerRepository.save(manager);
        return manager;
    }

    
    /** 
     * @return boolean
     */
    //there's only 1 manager/owner, don't need to getAll managers

    //do we ever need to update the managers account?
    @Transactional
    public Manager updateManager(int id, String email) {

        if(managerRepository.findManagerById(id)==null) {
    		throw new ApiRequestException("Manager not found.");
    	}

        Manager manager = managerRepository.findManagerById(id);

        if (email != null) {
            GroceryUser groceryUser = groceryUserRepository.findGroceryUserByEmail(email);
            if (groceryUser == null)
                throw new ApiRequestException("This user does not exist");
            manager.setUser(groceryUser);
        }

        managerRepository.save(manager);
        return manager;
    }


    //can the store exist even if we delete the manager?
    @Transactional
    public boolean deleteManagerById(Integer id){

        Manager manager = managerRepository.findManagerById(id);
        
        if(manager == null){
            throw new ApiRequestException("Manager does not exist");
        }
        managerRepository.deleteManagerById(id);

        return true;
    }

    @Transactional
    public boolean deleteManagerByEmail(String email){
        GroceryUser user = groceryUserRepository.findGroceryUserByEmail(email);
        if (user == null) throw new ApiRequestException("User does not exist");


        Manager manager = managerRepository.findManagerByUser(user);
        if (manager == null)
            throw new ApiRequestException("Manager does not exist");

        managerRepository.deleteManagerByUser(user);

        return true;
        
    }


    
    /** 
     * @param id
     * @return Manager
     */
    @Transactional
    public Manager getManagerbyId(Integer id){
        Manager manager = managerRepository.findManagerById(id);

        if(manager == null){
            throw new ApiRequestException("Manager does not exist");
        }

        return manager;
    }

    @Transactional
    public Manager getManagerByEmail(String email){
        GroceryUser user = groceryUserRepository.findGroceryUserByEmail(email);
        if (user == null) throw new ApiRequestException("This user does not exist");

        Manager manager = managerRepository.findManagerByUser(user);
        if (manager == null)
            throw new ApiRequestException("Manager does not exist");

        return manager;
    }
    
}
