package ca.mcgill.ecse321.GroceryApplicationBackend.dao;


import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Address;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Customer;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Store;

public interface AddressRepository extends CrudRepository<Address, Integer>{

    Address findAddressById(int id);
    Address findAddressByCity(String city);
    // Address findAddressByCustomer(Customer customer);
    // Address findAddressByStore(Store store);
    // boolean existsByStoreAndCustomer(Customer customerName  ,Store Storename);
}