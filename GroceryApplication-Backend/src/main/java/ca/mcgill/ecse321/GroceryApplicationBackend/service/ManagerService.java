package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.*;
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
     * @param email
     * @param id
     * @return Manager
     * @throws Exception
     */
    @Transactional
    public Manager createManager(int applicationId, String email, int id) throws Exception{ //no id? email should alr be unique id
        
        if(id == 0){
            throw new Exception("The id cannot be null");
        }

        if(email == null){
            throw new Exception("The email cannot be null.");
        }

        GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);

        if(gs == null){
            throw new Exception("The application doesn't exist.");
        }

        GroceryUser user = groceryUserRepository.findGroceryUserByEmail(email);
        if(user == null){
            throw new Exception("The user doesn't exist.");
        }

        Manager manager = new Manager();
        manager.setId(id);
        manager.setGroceryStoreApplication(gs);
        manager.setUser(user);

        managerRepository.save(manager);
        return manager;
    }

    //there's only 1 manager/owner, don't need to getAll managers

    //do we ever need to update the managers account?
    
    /** 
     * @param id
     * @return Manager
     * @throws Exception
     */
    @Transactional
    public Manager updateManager(int id) throws Exception{

        if(managerRepository.findManagerById(id)==null) {
    		throw new Exception("Manager not found.");
    	}

        Manager manager = managerRepository.findManagerById(id);

        managerRepository.save(manager);
        return manager;
    }

    
    /** 
     * @param id
     * @return Manager
     * @throws Exception
     */
    //can the store exist even if we delete the manager?
    @Transactional
    public Manager deleteManager(int id) throws Exception{

        Manager manager = managerRepository.findManagerById(id);
        
        if(manager == null){
            throw new Exception("Manager does not exist");
        }
        managerRepository.deleteManagerById(id);

        return null;
    }

    
}
