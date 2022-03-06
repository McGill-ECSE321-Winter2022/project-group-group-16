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

    @Transactional
    public GroceryUser createUser(String email){

        GroceryUser user = new GroceryUser();

        String userEmail = email;
        //add more fields
        user.setEmail(userEmail);

        groceryUserRepository.save(user);
        return user;
    }

    @Transactional
    public Manager createManager(String email, int id){ //no id? email should alr be unique id
        
        GroceryStoreApplication gs = new GroceryStoreApplication();
        gs.setId(110);
        groceryStoreApplicationRepository.save(gs);

        GroceryUser user = groceryUserRepository.findGroceryUserByEmail(email);

        Manager manager = new Manager();
        manager.setId(id);
        manager.setGroceryStoreApplication(gs);
        manager.setUser(user);

        managerRepository.save(manager);
        return manager;
    }

    //there's only 1 manager/owner, don't need to getAll managers

    //do we ever need to update the managers account?
    @Transactional
    public Manager updateManager(int id) throws Exception{

        if(managerRepository.findManagerById(id)==null) {
    		throw new Exception("email is not valid!");
    	}

        Manager manager = managerRepository.findManagerById(id);

        managerRepository.save(manager);
        return manager;
    }

    //can the store exist even if we delete the manager?


    
}
