package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.AddressRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.CustomerRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryStoreApplicationRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryUserRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    GroceryStoreApplicationRepository groceryStoreApplicationRepository;

    @Autowired
    GroceryUserRepository groceryUserRepository;

    /**
     * @param custiomerId
     * @param applicationId
     * @param addressId
     * @param customerId
     * @param userEmail
     * @return
     */
    @Transactional
    public Customer createCustomer(Integer applicationId, Integer addressId, String userEmail) {
        if (userEmail == null || userEmail.trim().length() == 0) {
            throw new ApiRequestException(
                    "requested userEmail is null or length 0. Please enter valid userEmail.\n");
        }

        GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);
        if (gs == null) {
            throw new ApiRequestException("No application associated with this Id.");
        }

        GroceryUser gu = groceryUserRepository.findGroceryUserByEmail(userEmail);
        if (gu == null) {
            throw new ApiRequestException("No user associated with this email");
        }

        Address address = addressRepository.findAddressById(addressId);
        if (address == null) {
            throw new ApiRequestException("No address associated with this Id");
        }

        Customer customer = new Customer();
        customer.setGroceryStoreApplication(gs);
        gu.setEmail(userEmail);
        customer.setAddress(address);
        customer.setUser(gu);
        customer.setAddress(address);
        customerRepository.save(customer);

        return customer;
    }


    public Customer updateCustomer(Integer customerId, Integer addressId) {


        Address address = addressRepository.findAddressById(addressId);
        if (address == null) {
            throw new ApiRequestException("No address associated with this Id");
        }

        Customer customer = customerRepository.findCustomerById(customerId);
        customer.setAddress(address);
        customerRepository.save(customer);

        return customer;
    }

    /**
     * @param Id
     * @return
     */
    @Transactional
    public Customer getCustomerById(Integer Id) {//Test


        Customer customer = customerRepository.findCustomerById(Id);

        if (customer == null) {
            throw new ApiRequestException("Customer account with provided id does not exists");


        }

        return customer;
    }

    /**
     * @param email
     * @return
     */
    @Transactional
    public Customer getCustomerByEmail(String email) {
        GroceryUser user = groceryUserRepository.findGroceryUserByEmail(email);
        if (user == null) throw new ApiRequestException("Grocery user with email " + email + " does not exist");
        Customer customer = customerRepository.findCustomerByUser(user);
        if (customer == null) {
            throw new ApiRequestException("No customer profile associated with user with email " + email);
        }
        return customer;
    }

    /**
     * @return
     */
    @Transactional
    public List<Customer> getAllCustomers() {
        return toList(customerRepository.findAll());

    }

    // update customer through Groceyuser?

    /**
     * @param id
     * @return
     */
    @Transactional
    public Customer deleteCustomer(Integer id) {
        if (customerRepository.findCustomerById(id) == null) {
            throw new ApiRequestException("Customer account with provided id does not exist.");
        }
        Customer customer = customerRepository.findCustomerById(id);
        customerRepository.delete(customer);
        return customer;
    }


    // ------------------ Helper Methods ---------------------
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

}
