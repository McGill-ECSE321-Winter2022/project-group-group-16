package ca.mcgill.ecse321.GroceryApplicationBackend.controller;

import ca.mcgill.ecse321.GroceryApplicationBackend.dto.AddressDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Address;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class AddressRestController {
    @Autowired
    private AddressService addressService;

    /**
     * Rest controller method for creating an address.
     *
     * @param streetNumber
     * @param streetName
     * @param province
     * @param city
     * @param country
     * @param postalCode
     * @return created address.
     * @throws ApiRequestException
     */
    @PostMapping(value = {"/address", "/address/"})
    public AddressDto createAddress(
            @RequestParam Integer streetNumber,
            @RequestParam String streetName,
            @RequestParam String province,
            @RequestParam String city,
            @RequestParam String country,
            @RequestParam String postalCode
    ) throws ApiRequestException {
        Address address = addressService.createAddress(streetNumber, streetName, province, city, country, postalCode);
        return convertToDto(address);
    }

    /**
     * Rest controller method for getting an address
     *
     * @param id of the address to retrieve.
     * @return address.
     * @throws ApiRequestException
     */
    @GetMapping(value = {"/address/{id}", "/address/{id}/"})
    public AddressDto getAddress(@PathVariable("id") int id) throws ApiRequestException {
        Address address = addressService.getAddressById(id);
        return convertToDto(address);
    }

    /**
     * Rest controller method for deleting an address.
     *
     * @param id id of the address to delete.
     * @throws ApiRequestException
     */
    @DeleteMapping(value = {"/address/{id}", "/address/{id}/"})
    public void deleteAddress(@PathVariable("id") Integer id) throws ApiRequestException {
        addressService.deleteAddress(id);
    }

    /**
     * Rest controller method for updating an address.
     *
     * @param id           of the address to update.
     * @param streetNumber
     * @param streetName
     * @param province
     * @param city
     * @param country
     * @param postalCode
     * @return updated address.
     * @throws ApiRequestException
     */
    @PutMapping(value = {"/address/{id}", "/address/{id}/"})
    public AddressDto updateAddress(
            @PathVariable("id") int id,
            @RequestParam Integer streetNumber,
            @RequestParam String streetName,
            @RequestParam String province,
            @RequestParam String city,
            @RequestParam String country,
            @RequestParam String postalCode
    ) throws ApiRequestException {
        Address address = addressService.updateAddress(id, streetNumber, streetName, province, city, country, postalCode);
        return convertToDto(address);
    }

    /**
     * Converts an address to a data transfer object.
     *
     * @param address address to convert.
     * @return corresponding data transfer object.
     */
    private static AddressDto convertToDto(Address address) {
        if (address == null) return null;
        return new AddressDto(address.getId(), address.getStreetNumber(), address.getStreetName(),
                address.getProvince(), address.getCity(), address.getCountry(), address.getPostalCode(),
                address.getStore(), address.getOrder(), address.getOrder1(), address.getCustomer());
    }
}
