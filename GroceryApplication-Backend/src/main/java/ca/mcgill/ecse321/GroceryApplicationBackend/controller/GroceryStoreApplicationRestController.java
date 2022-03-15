package ca.mcgill.ecse321.GroceryApplicationBackend.controller;

import ca.mcgill.ecse321.GroceryApplicationBackend.dto.GroceryStoreApplicationDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.GroceryStoreApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class GroceryStoreApplicationRestController {
    @Autowired
    private GroceryStoreApplicationService groceryStoreApplicationService;

    /**
     * Rest method for creating a grocery store application.
     * @return created gs application.
     * @throws ApiRequestException
     */
    @PostMapping(value = {"/grocerystoreapplication", "/grocerystoreapplication/"})
    public GroceryStoreApplicationDto createGroceryStoreApplication(
    ) throws ApiRequestException {
        GroceryStoreApplication groceryStoreApplication = groceryStoreApplicationService.createGroceryStoreApplication();
        return convertToDto(groceryStoreApplication);
    }

    /**
     * Rest delete method for deleting the grocery store application.
     * @throws ApiRequestException
     */
    @DeleteMapping(value = {"/grocerystoreapplication", "/grocerystoreapplication/"})
    public void deleteGroceryStoreApplication() throws ApiRequestException {
        groceryStoreApplicationService.deleteGroceryStoreApplication();
    }

    /**
     * Rest get method for getting the grocery store application (with all dependent objects).
     * @return the single grocery store application (The whole db contents as JSON)
     */
    @GetMapping(value = {"/grocerystoreapplication", "/grocerystoreapplication/"})
    public GroceryStoreApplicationDto getGroceryStoreApplication() {
        GroceryStoreApplication groceryStoreApplication = groceryStoreApplicationService.getGroceryStoreApplication();
        return convertToDto(groceryStoreApplication);
    }

    /**
     * Converts a grocery store application to a data transfer object.
     * @param groceryStoreApplication gs application to convert to dto.
     * @return corresponding Dto.
     */
    private static GroceryStoreApplicationDto convertToDto(GroceryStoreApplication groceryStoreApplication) {
        if (groceryStoreApplication == null) return null;
        return new GroceryStoreApplicationDto(groceryStoreApplication.getId(), groceryStoreApplication.getOrder(),
                groceryStoreApplication.getProduct(), groceryStoreApplication.getStore(),
                groceryStoreApplication.getCategory(), groceryStoreApplication.getUserRole());
    }

}
