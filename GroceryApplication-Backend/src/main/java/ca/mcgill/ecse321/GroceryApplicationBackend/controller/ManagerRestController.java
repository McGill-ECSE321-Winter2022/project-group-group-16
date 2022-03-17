package ca.mcgill.ecse321.GroceryApplicationBackend.controller;

import ca.mcgill.ecse321.GroceryApplicationBackend.dto.ManagerDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Manager;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class ManagerRestController {

    @Autowired
    ManagerService managerService;

    public static ManagerDto convertToDto(Manager m) {
        if (m == null) {
            throw new ApiRequestException("Payment does not exist");
        }

        return new ManagerDto(m.getId());
    }

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
            @RequestParam String email) throws ApiRequestException {
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
    public ManagerDto getManagerById(@PathVariable("id") Integer id) throws ApiRequestException {
        return convertToDto(managerService.getManagerById(id));
    }

    /**
     * Rest method to retrieve the manager by email
     *
     * @param email
     * @return requested manager
     * @throws ApiRequestException
     */
    @GetMapping(value = {"/manager/email/{email}", "/manager/email/{email}/"})
    public ManagerDto getManagerByEmail(@PathVariable("email") String email) throws ApiRequestException {
        return convertToDto(managerService.getManagerByEmail(email));
    }

    /**
     * Rest method for updating a manager
     *
     * @param id
     * @param email
     * @param groceryStoreApplicationId
     * @return updated manager
     * @throws ApiRequestException
     */
    @PutMapping(value = {"/manager/id/{id}", "/manager/id/{id}/"})
    public ManagerDto updateManager(
            @PathVariable("id") Integer id,
            @RequestParam("email") String email,
            @RequestParam Integer groceryStoreApplicationId) throws ApiRequestException {
        return convertToDto(managerService.updateManager(id, email, groceryStoreApplicationId));
    }

    /**
     * Rest method to delete the manager by id
     *
     * @param id
     * @throws ApiRequestException
     */
    @DeleteMapping(value = {"/manager/id/{id}", "/manager/id/{id}/"})
    public void deleteManagerById(@PathVariable("id") Integer id) throws ApiRequestException {
        managerService.deleteManagerById(id);
    }

    /**
     * Rest method for deleting the manager by email
     *
     * @param email
     * @throws ApiRequestException
     */
    @DeleteMapping(value = {"/manager/email/{email}", "/manager/email/{email}/"})
    public void deleteManagerByEmail(@PathVariable("email") String email) throws ApiRequestException {
        managerService.deleteManagerByEmail(email);
    }

}
