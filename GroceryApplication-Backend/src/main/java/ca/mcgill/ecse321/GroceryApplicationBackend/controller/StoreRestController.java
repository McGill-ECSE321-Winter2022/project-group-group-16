package ca.mcgill.ecse321.GroceryApplicationBackend.controller;

import ca.mcgill.ecse321.GroceryApplicationBackend.dto.StoreDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Store;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@CrossOrigin(origins = "*")
@RestController
public class StoreRestController {
    @Autowired
    private StoreService storeService;

    /**
     * Converts a store to a data transfer object.
     *
     * @param store store to convert.
     * @return corresponding data transfer object.
     */
    private static StoreDto convertToDto(Store store) {
        if (store == null) return null;
        return new StoreDto(store.getName(), store.getWeekDayOpening(), store.getWeekDayClosing(),
                store.getWeekEndOpening(), store.getWeekEndClosing(), store.getAddress(), store.getGroceryStoreApplication());
    }

    /**
     * Rest controller for creating a store.
     *
     * @param name
     * @param weekDayOpening
     * @param weekDayClosing
     * @param weekEndOpening
     * @param weekEndClosing
     * @param addressId
     * @param groceryStoreApplicationId
     * @return created store Dto.
     * @throws ApiRequestException
     */
    @PostMapping(value = {"/store", "/store/"})
    public StoreDto createStore(
            @RequestParam String name,
            @RequestParam @DateTimeFormat(pattern = "HH:mm:ss") LocalTime weekDayOpening,
            @RequestParam @DateTimeFormat(pattern = "HH:mm:ss") LocalTime weekDayClosing,
            @RequestParam @DateTimeFormat(pattern = "HH:mm:ss") LocalTime weekEndOpening,
            @RequestParam @DateTimeFormat(pattern = "HH:mm:ss") LocalTime weekEndClosing,
            @RequestParam Integer addressId,
            @RequestParam Integer groceryStoreApplicationId
    ) throws ApiRequestException {
        Store store = storeService.createStore(name, weekDayOpening, weekDayClosing, weekEndOpening, weekEndClosing, addressId, groceryStoreApplicationId);
        return convertToDto(store);
    }

    /**
     * Rest method for updating a store attributes given its name.
     *
     * @param name                      name of the store to update.
     * @param weekDayOpening
     * @param weekDayClosing
     * @param weekEndOpening
     * @param weekEndClosing
     * @param addressId
     * @param groceryStoreApplicationId
     * @return updated store Dto.
     * @throws ApiRequestException
     */
    @PutMapping(value = {"/store/{name}", "/store/{name}/"})
    public StoreDto updateStore(
            @PathVariable("name") String name,
            @RequestParam @DateTimeFormat(pattern = "HH:mm:ss") LocalTime weekDayOpening,
            @RequestParam @DateTimeFormat(pattern = "HH:mm:ss") LocalTime weekDayClosing,
            @RequestParam @DateTimeFormat(pattern = "HH:mm:ss") LocalTime weekEndOpening,
            @RequestParam @DateTimeFormat(pattern = "HH:mm:ss") LocalTime weekEndClosing,
            @RequestParam Integer addressId,
            @RequestParam Integer groceryStoreApplicationId
    ) throws ApiRequestException {
        Store store = storeService.updateStore(name, weekDayOpening, weekDayClosing, weekEndOpening, weekEndClosing, addressId, groceryStoreApplicationId);
        return convertToDto(store);
    }

    /**
     * Rest controller to get a store information by name.
     *
     * @param name of the store.
     * @return requested store dto.
     * @throws ApiRequestException
     */
    @GetMapping(value = {"/store/{name}", "/store/{name}/"})
    public StoreDto getAddress(@PathVariable("name") String name) throws ApiRequestException {
        Store store = storeService.getStore(name);
        return convertToDto(store);
    }

    /**
     * Rest controller method for deleting a store.
     *
     * @param name of the store to delete.
     * @throws ApiRequestException
     */
    @DeleteMapping(value = {"/store/{name}", "/store/{name}/"})
    public void deleteStore(@PathVariable("name") String name) throws ApiRequestException {
        storeService.deleteStore(name);
    }
}




