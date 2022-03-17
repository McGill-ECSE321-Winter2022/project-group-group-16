package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryStoreApplicationRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroceryStoreApplicationService {
    private static final Integer groceryStoreApplicationDefaultId = 0;

    @Autowired
    GroceryStoreApplicationRepository groceryStoreApplicationRepository;

    /**
     * Service method for creating a grocery store application.
     * Note: Grocery Store Application is a singleton. Only one
     * can exist for simplicity.
     *
     * @return created gs application.
     */
    @Transactional
    public GroceryStoreApplication createGroceryStoreApplication() {
        GroceryStoreApplication groceryStoreApplication =
                groceryStoreApplicationRepository.findGroceryStoreApplicationById(groceryStoreApplicationDefaultId);
        if (groceryStoreApplication == null) {
            groceryStoreApplication = new GroceryStoreApplication();
            groceryStoreApplication.setId(groceryStoreApplicationDefaultId);
        } else {
            throw new ApiRequestException("Grocery Store Application already exists in the system");
        }
        groceryStoreApplicationRepository.save(groceryStoreApplication);
        return groceryStoreApplication;
    }

    /**
     * Service method for deleting the singleton grocery store application.
     */
    @Transactional
    public void deleteGroceryStoreApplication() {
        GroceryStoreApplication groceryStoreApplication =
                groceryStoreApplicationRepository.findGroceryStoreApplicationById(groceryStoreApplicationDefaultId);
        if (groceryStoreApplication == null) throw new ApiRequestException("Grocery Store Application does not exist.");
        groceryStoreApplicationRepository.delete(groceryStoreApplication);
    }

    /**
     * Service method for getting the singleton grocery store application.
     */
    @Transactional
    public GroceryStoreApplication getGroceryStoreApplication() {
        GroceryStoreApplication groceryStoreApplication =
                groceryStoreApplicationRepository.findGroceryStoreApplicationById(groceryStoreApplicationDefaultId);
        if (groceryStoreApplication == null) throw new ApiRequestException("Grocery Store Application does not exist.");
        return groceryStoreApplication;
    }
}
