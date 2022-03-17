package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.AddressRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryStoreApplicationRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.StoreRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Address;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Store;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private GroceryStoreApplicationRepository groceryStoreApplicationRepository;

    /**
     * Create a store.
     *
     * @param name
     * @param weekDayOpening
     * @param weekDayClosing
     * @param weekEndOpening
     * @param weekEndClosing
     * @param addressId
     * @param groceryStoreApplicationId
     * @return created store.
     */
    @Transactional
    public Store createStore(String name, LocalTime weekDayOpening, LocalTime weekDayClosing, LocalTime weekEndOpening, LocalTime weekEndClosing, Integer addressId, Integer groceryStoreApplicationId) {
        if (Strings.isNullOrEmpty(name)) throw new ApiRequestException("Name is null or empty.");
        if (weekDayOpening == null) throw new ApiRequestException("Weekday opening time is null.");
        if (weekDayClosing == null) throw new ApiRequestException("Weekday closing time is null.");
        if (weekEndOpening == null) throw new ApiRequestException("Weekend opening time is null.");
        if (weekEndClosing == null) throw new ApiRequestException("Weekend closing time is null.");

        Address address = addressRepository.findAddressById(addressId);
        if (address == null) {
            throw new ApiRequestException("Address with id" + addressId + "does not exist.");
        }

        GroceryStoreApplication groceryStoreApplication = groceryStoreApplicationRepository.findGroceryStoreApplicationById(groceryStoreApplicationId);
        if (groceryStoreApplication == null) {
            throw new ApiRequestException("Application with id " + groceryStoreApplicationId + " does not exist.");
        }

        Store store = new Store();
        store.setName(name);
        store.setWeekDayOpening(weekDayOpening);
        store.setWeekDayClosing(weekDayClosing);
        store.setWeekEndOpening(weekEndOpening);
        store.setWeekEndClosing(weekEndClosing);
        store.setAddress(address);
        store.setGroceryStoreApplication(groceryStoreApplication);
        storeRepository.save(store);

        return store;
    }

    /**
     * Update a store given its name.
     *
     * @param name
     * @param weekDayOpening
     * @param weekDayClosing
     * @param weekEndOpening
     * @param weekEndClosing
     * @param addressId
     * @param groceryStoreApplicationId
     * @return the updated store.
     */
    @Transactional
    public Store updateStore(String name, LocalTime weekDayOpening, LocalTime weekDayClosing, LocalTime weekEndOpening, LocalTime weekEndClosing, Integer addressId, Integer groceryStoreApplicationId) {
        Store store = storeRepository.findStoreByName(name);
        if (store == null) throw new ApiRequestException("Store with name " + name + "does not exist");

        if (weekDayOpening != null) store.setWeekDayOpening(weekDayOpening);
        if (weekDayClosing != null) store.setWeekDayClosing(weekDayClosing);
        if (weekEndOpening != null) store.setWeekEndOpening(weekEndOpening);
        if (weekEndClosing != null) store.setWeekEndClosing(weekEndClosing);


        if (addressId != null) {
            Address address = addressRepository.findAddressById(addressId);
            if (address == null) {
                throw new ApiRequestException("Address with id " + addressId + " does not exist.");
            }

            GroceryStoreApplication groceryStoreApplication = groceryStoreApplicationRepository.findGroceryStoreApplicationById(groceryStoreApplicationId);
            if (groceryStoreApplication == null) {
                throw new ApiRequestException("Application with id " + groceryStoreApplicationId + " does not exist.");
            }

            store.setAddress(address);
        }

        if (groceryStoreApplicationId != null) {
            GroceryStoreApplication groceryStoreApplication = groceryStoreApplicationRepository.findGroceryStoreApplicationById(groceryStoreApplicationId);
            if (groceryStoreApplication == null) {
                throw new ApiRequestException("Application with id " + groceryStoreApplicationId + " does not exist.");
            }

            store.setGroceryStoreApplication(groceryStoreApplication);
        }

        return store;
    }

    /**
     * get a store by its name.
     *
     * @param name Store name
     * @return store associated to the name.
     */
    @Transactional
    public Store getStore(String name) {
        Store store = storeRepository.findStoreByName(name);
        if (store == null) throw new ApiRequestException("Store with name " + name + "does not exist");

        return store;
    }

    /**
     * Delete a store by its name.
     *
     * @param name
     */
    @Transactional
    public void deleteStore(String name) {
        Store store = storeRepository.findStoreByName(name);
        if (store == null) throw new ApiRequestException("Store with name " + name + "does not exist");


        storeRepository.deleteStoreByName(name);
    }

}
