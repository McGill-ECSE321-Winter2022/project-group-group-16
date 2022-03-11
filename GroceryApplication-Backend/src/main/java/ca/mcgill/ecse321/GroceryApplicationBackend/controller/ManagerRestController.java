package ca.mcgill.ecse321.GroceryApplicationBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    
    /** 
     * @param createManager(
     * @return ManagerDto
     */
    @PostMapping(value = {"/manager", "/manager/"})
    public ManagerDto createManager(
        @RequestParam("applicationId") Integer applicationId,
        @RequestParam("email") String email,
        @RequestParam("id") Integer id){
        return convertToDto(managerService.createManager(applicationId, email, id));

    }

    
    /** 
     * @param id
     * @return ManagerDto
     */
    @GetMapping(value = {"/manager/{id}", "/manager/{id}/"})
    public ManagerDto getManagerById(@PathVariable("id") Integer id){
        return convertToDto(managerService.getManager(id));
    }

    
    /** 
     * @param id
     * @return boolean
     */
    @DeleteMapping(value = {"/manager/{id}", "/manager/{id}/"})
    public boolean deleteManager(@PathVariable("id") Integer id){
        if(id == 0) {
            throw new InvalidInputException("The id is not valid.");
        }
        return managerService.deleteManager(id);
    }

    
    /** 
     * @param m
     * @return ManagerDto
     */
    //helper
    public static ManagerDto convertToDto(Manager m) {
    	if(m == null) {
    		throw new IllegalArgumentException("Payment does not exist");
    	}

        return new ManagerDto();
    }

}
