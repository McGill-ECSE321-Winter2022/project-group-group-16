package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Manager;

@Service
public class ManagementService {

    @Autowired
    public Manager manager;

    @Transactional
    public Manager createManager(){
        return null;

    }
    
}
