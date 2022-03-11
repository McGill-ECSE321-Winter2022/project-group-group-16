package ca.mcgill.ecse321.GroceryApplicationBackend.service;

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
    public Manager createManager(Integer applicationId, String email, Integer id){ //no id? email should alr be unique id
        
        if(id == 0){
            throw new InvalidInputException("The id cannot be null");
        }

        if(email == null){
            throw new InvalidInputException("The email cannot be null.");
        }

        GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);

        if(gs == null){
            throw new InvalidInputException("The application doesn't exist.");
        }

        GroceryUser user = groceryUserRepository.findGroceryUserByEmail(email);
        if(user == null){
            throw new InvalidInputException("The user doesn't exist.");
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
    // @Transactional
    // public Manager updateManager(int id) throws Exception{

    //     if(managerRepository.findManagerById(id)==null) {
    // 		throw new InvalidInputException("Manager not found.");
    // 	}

    //     Manager manager = managerRepository.findManagerById(id);

    //     managerRepository.save(manager);
    //     return manager;
    // }

    
    /** 
     * @param id
     * @return Manager
     * @throws Exception
     */
    //can the store exist even if we delete the manager?
    @Transactional
    public boolean deleteManager(Integer id){

        Manager manager = managerRepository.findManagerById(id);
        
        if(manager == null){
            throw new InvalidInputException("Manager does not exist");
        }
        managerRepository.deleteManagerById(id);

        return true;
    }

    
    /** 
     * @param id
     * @return Manager
     */
    @Transactional
    public Manager getManager(Integer id){
        return managerRepository.findManagerById(id);
    }
    
}
