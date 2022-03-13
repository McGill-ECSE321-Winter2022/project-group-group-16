package ca.mcgill.ecse321.GroceryApplicationBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * Rest method for creating a manager
     * 
     * @param applicationId
     * @param email
     * @return created manager
     * @throws ApiRequestException
     */
    @PostMapping(value = {"/manager", "/manager/"})
    public ManagerDto createManager(
        @RequestParam Integer applicationId,
        @RequestParam String email) throws ApiRequestException{
        return convertToDto(managerService.createManager(applicationId, email));
    }

    /**
     * Rest method to retrieve the manager by id
     * 
     * @param id
     * @return requested manager
     * @throws ApiRequestException
     */
    @GetMapping(value = {"/manager/id/{id}", "/manager/id/{id}/"})
    public ManagerDto getManagerById(@PathVariable("id") Integer id) throws ApiRequestException{
        return convertToDto(managerService.getManagerbyId(id));
    }

    /**
     * Rest method to retrieve the manager by email
     * 
     * @param email
     * @return requested manager
     * @throws ApiRequestException
     */
    @GetMapping(value = {"/manager/email/{email}", "/manager/email/{email}/"})
    public ManagerDto getManagerByEmail(@PathVariable("email") String email) throws ApiRequestException{
        return convertToDto(managerService.getManagerByEmail(email));
    }

  
   /**
    * Rest method to delete the manager by id
    * 
    * @param id
    * @throws ApiRequestException
    */
    @DeleteMapping(value = {"/manager/id/{id}", "/manager/id/{id}/"})
    public void deleteManagerById(@PathVariable("id") Integer id) throws ApiRequestException{
        managerService.deleteManagerById(id);
    }

    /**
     * Rest method for deleting the manager by email
     * 
     * @param email
     * @throws ApiRequestException
     */
    @DeleteMapping(value = {"/manager/email/{email}", "/manager/email/{email}/"})
    public void deleteManagerByEmail(@PathVariable("email") String email) throws ApiRequestException{
        managerService.deleteManagerByEmail(email);
    }

  
    public static ManagerDto convertToDto(Manager m) {
    	if(m == null) {
    		throw new ApiRequestException("Payment does not exist");
    	}

        return new ManagerDto();
    }

}
