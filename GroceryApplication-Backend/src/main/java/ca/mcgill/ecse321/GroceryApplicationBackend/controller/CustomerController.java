package ca.mcgill.ecse321.GroceryApplicationBackend.controller;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.GroceryApplicationBackend.dto.CategoryDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.dto.CustomerDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Category;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Customer;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.CustomerService;


@CrossOrigin(origins = "*")
@RestController
public class CustomerController {
    
      @Autowired
      private CustomerService customerService;
      
      
      /**
       * Rest controller to create customer
       * 
       * 
       * @param applicationId
       * @param addressId
       * @param userEmail
       * @return
       */
      @PostMapping(value = { "/customer", "/customer/"})
      public CustomerDto createCustomer (@RequestParam Integer applicationId, @RequestParam  Integer addressId, @RequestParam String userEmail) {
          Customer customer = customerService.createCustomer(0, addressId, userEmail);
            return convertToDto(customer);
      }
      /**
       * Rest controller to update controller
       * 
       * @param Id
       * @param addressId
       * @return
       * @throws ApiRequestException
       */
      @PutMapping(value = {"/customer/{Id}", "/customer/{Id}/"})
	  public CustomerDto updateCategory(@PathVariable("Id") Integer Id, @RequestParam Integer addressId) throws ApiRequestException {
		  Customer customer = customerService.updateCustomer(Id,addressId);
		    return convertToDto(customer);
	  }
      
      /**
       * Rest controller to delete customer
       * 
       * 
       * @param id
       * @return
       */
      @DeleteMapping(value = { "/deleteCustomer/{id}", "deleteCustomer/{id}/" }) 
      public CustomerDto deleteCustomer(@PathVariable("id") Integer id) {
        Customer customer = customerService.deleteCustomer(id);
        return convertToDto(customer);
      }
      
      /**
       * Rest controller to get customer by id
       * 
       * 
       * @param id
       * @return
       */
      @GetMapping(value = { "/getCustomerById/{id}", "/getCustomer/{id}/" })
      public CustomerDto getCustomerById(@PathVariable("id") Integer id) {
        return convertToDto(customerService.getCustomerById(id));
      }
      
      /**
       * Rest controller to get all customer 
       * 
       * @return
       */
      @GetMapping(value = { "/getAllCustomers", "/getAllCustomers/" })
      public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customerService.getAllCustomers()) {
          customerDtos.add(convertToDto(customer));
        }
        return customerDtos;
      }
  
    //-------------------------- Helper Methods -----------------------------
      
      /**
       * Convert to DTO
       * 
       * @author noahye
       * @param customer
       * @return
       */
        private CustomerDto convertToDto(Customer customer) {
            if (customer == null) {
                throw new IllegalArgumentException("The provided customer does not exist.");
            }
            CustomerDto customerDto = new CustomerDto(customer.getId());

            return customerDto;
        }
        


}
