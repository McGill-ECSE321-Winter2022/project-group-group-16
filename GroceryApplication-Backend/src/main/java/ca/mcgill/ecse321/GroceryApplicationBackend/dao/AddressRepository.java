package ca.mcgill.ecse321.GroceryApplicationBackend.dao;


import ca.mcgill.ecse321.GroceryApplicationBackend.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {
    Address findAddressById(Integer id);

    Address findAddressByCity(String city);

    void deleteAddressById(Integer id);

}