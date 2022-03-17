package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.AddressRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.CustomerRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Address;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Service method for creating an Address.
     * @param streetNumber
     * @param streetName
     * @param province
     * @param city
     * @param country
     * @param postalCode
     * @return created address.
     */
    @Transactional
    public Address createAddress(Integer streetNumber, String streetName, String province, String city, String country, String postalCode) {
        if (Strings.isNullOrEmpty(streetName)) throw new ApiRequestException("Street name is null or empty.");
        if (Strings.isNullOrEmpty(province)) throw new ApiRequestException("Province is null or empty.");
        if (Strings.isNullOrEmpty(city)) throw new ApiRequestException("City is null or empty.");
        if (Strings.isNullOrEmpty(country)) throw new ApiRequestException("Country is null or empty.");
        if (Strings.isNullOrEmpty(postalCode)) throw new ApiRequestException("Postal Code is null or empty.");


        Address address = new Address();
        address.setStreetNumber(streetNumber);
        address.setStreetName(streetName);
        address.setProvince(province);
        address.setCity(city);
        address.setCountry(country);
        address.setPostalCode(postalCode);

        addressRepository.save(address);

        return address;
    }

    /**
     * Service method for deleting an address.
     * @param id Address id.
     */
    @Transactional
    public void deleteAddress(Integer id) {
        Address address = addressRepository.findAddressById(id);
        if (address == null) {
            throw new ApiRequestException("No address exists with id:" + id);
        }
        addressRepository.deleteAddressById(id);
    }

    /**
     * Service method for retrieving an address by id.
     * @param id
     * @return
     */
    @Transactional
    public Address getAddressById(Integer id) {
        Address address = addressRepository.findAddressById(id);
        if (address == null) {
            throw new ApiRequestException("No address exists with id:" + id);
        }
        return address;
    }

    /**
     * Service method for updating an address.
     * Note: Only non-null and non-empty parameters will be updated.
     * @param id id of the address to update.
     * @param streetNumber
     * @param streetName
     * @param province
     * @param city
     * @param country
     * @param postalCode
     * @return updated address
     */
    @Transactional
    public Address updateAddress(Integer id, Integer streetNumber, String streetName, String province, String city, String country, String postalCode) {
        Address address = addressRepository.findAddressById(id);
        if (address == null) {
            throw new ApiRequestException("No address exists with id:" + id);
        }

        if (streetNumber != null) address.setStreetNumber(streetNumber);
        if (!Strings.isNullOrEmpty(streetName)) address.setStreetName(streetName);
        if (!Strings.isNullOrEmpty(province)) address.setProvince(province);
        if (!Strings.isNullOrEmpty(city)) address.setCity(city);
        if (!Strings.isNullOrEmpty(country)) address.setCountry(country);
        if (!Strings.isNullOrEmpty(postalCode)) address.setPostalCode(postalCode);

        return address;
    }

}
