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

    @PostMapping(value = {"/grocerystoreapplication", "/grocerystoreapplication/"})
    public GroceryStoreApplicationDto createGroceryStoreApplication(
    ) throws ApiRequestException {
        GroceryStoreApplication groceryStoreApplication = groceryStoreApplicationService.createGroceryStoreApplication();
        return convertToDto(groceryStoreApplication);
    }

    @DeleteMapping(value = {"/grocerystoreapplication", "/grocerystoreapplication/"})
    public void deleteGroceryStoreApplication() throws ApiRequestException {
        groceryStoreApplicationService.deleteGroceryStoreApplication();
    }

    @GetMapping(value = {"/grocerystoreapplication", "/grocerystoreapplication/"})
    public GroceryStoreApplicationDto getGroceryStoreApplication() {
        GroceryStoreApplication groceryStoreApplication = groceryStoreApplicationService.getGroceryStoreApplication();
        return convertToDto(groceryStoreApplication);
    }

    private static GroceryStoreApplicationDto convertToDto(GroceryStoreApplication groceryStoreApplication) {
        if (groceryStoreApplication == null) return null;
        return new GroceryStoreApplicationDto(groceryStoreApplication.getId(), groceryStoreApplication.getOrder(),
                groceryStoreApplication.getProduct(), groceryStoreApplication.getStore(),
                groceryStoreApplication.getCategory(), groceryStoreApplication.getUserRole());
    }

}
