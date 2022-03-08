package ca.mcgill.ecse321.GroceryApplicationBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.GroceryApplicationBackend.dto.ManagerDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Manager;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.InvalidInputException;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.ManagerService;

@CrossOrigin(origins = "*")
@RestController
public class ManagerRestController {
    
    @Autowired
    ManagerService managerService;

    @GetMapping(value = {"/manager", "/manager/"})
    public ManagerDto createManager(
        @PathVariable("applicationId") int applicationId,
        @PathVariable("email") String email,
        @PathVariable("id") int id){
        return convertToDto(managerService.createManager(applicationId, email, id));

    }

    @GetMapping(value = {"/manager/", "/manager/{id}/"})
    public ManagerDto getManagerById(@PathVariable("id") int id){
        return convertToDto(managerService.getManager(id));
    }

    @DeleteMapping(value = {"/manager", "/manager/"})
    public boolean deleteManager(@PathVariable("id") int id){
        if(id == 0) {
            throw new InvalidInputException("The id is not valid.");
        }
        return managerService.deleteManager(id);
    }

    //helper
    public static ManagerDto convertToDto(Manager m) {
    	if(m == null) {
    		throw new IllegalArgumentException("Payment does not exist");
    	}

        return new ManagerDto();
    }

}
