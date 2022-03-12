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
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Manager;
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
        @RequestParam Integer applicationId,
        @RequestParam String email) throws ApiRequestException{
        return convertToDto(managerService.createManager(applicationId, email));
    }

     
    /** 
     * @param id
     * @return ManagerDto
     * @throws ApiRequestException
     */
    @GetMapping(value = {"/manager/id/{id}", "/manager/id/{id}/"})
    public ManagerDto getManagerById(@PathVariable("id") Integer id) throws ApiRequestException{
        return convertToDto(managerService.getManagerbyId(id));
    }

    @GetMapping(value = {"/manager/email/{email}", "/manager/email/{email}/"})
    public ManagerDto getManagerByEmail(@PathVariable("email") String email) throws ApiRequestException{
        return convertToDto(managerService.getManagerByEmail(email));
    }

 
    
    /** 
     * @param id
     * @return boolean
     * @throws ApiRequestException
     */
    @DeleteMapping(value = {"/manager/id/{id}", "/manager/id/{id}/"})
    public boolean deleteManagerById(@PathVariable("id") Integer id) throws ApiRequestException{
        if(id == 0) {
            throw new ApiRequestException("The id is not valid.");
        }
        return managerService.deleteManagerById(id);
    }

    @DeleteMapping(value = {"/manager/email/{email}", "/manager/email/{email}/"})
    public boolean deleteManagerByEmail(@PathVariable("email") String email) throws ApiRequestException{
        return managerService.deleteManagerByEmail(email);
    }

  
    //helper
    public static ManagerDto convertToDto(Manager m) {
    	if(m == null) {
    		throw new ApiRequestException("Payment does not exist");
    	}

        return new ManagerDto();
    }

}
