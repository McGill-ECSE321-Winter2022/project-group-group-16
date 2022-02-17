package ca.mcgill.ecse321.GroceryApplicationBackend.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Address;

public interface AddressRepository extends CrudRepository<Address, Integer>{

    Address findAddressById(int id);
    Address findAddressByCity(String city);
}