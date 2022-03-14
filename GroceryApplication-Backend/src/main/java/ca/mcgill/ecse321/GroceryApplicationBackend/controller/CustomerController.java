package ca.mcgill.ecse321.GroceryApplicationBackend.controller;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.GroceryApplicationBackend.dto.CustomerDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Customer;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.CustomerService;


@CrossOrigin(origins = "*")
@RestController
public class CustomerController {
    
      @Autowired
      private CustomerService customerService;
      
      
      
      @PostMapping(value = { "/customer", "/customer/"})
      public CustomerDto createCustomer (@PathVariable("applicationId") Integer applicationId, @PathVariable("addressId") Integer addressId, @PathVariable("userEmail") String userEmail) {
          Customer customer = customerService.createCustomer(applicationId, addressId, userEmail);
            return convertToDto(customer);
      }
      

      @DeleteMapping(value = { "/deleteCustomer/{id}", "deleteCustomer/{id}/" }) 
      public CustomerDto deleteCustomer(@RequestParam("id") Integer id) {
        Customer customer = customerService.deleteCustomer(id);
        return convertToDto(customer);
      }
      
      @GetMapping(value = { "/getCustomerById/{id}", "/getCustomer/{id}/" })
      public CustomerDto getCustomerById(@RequestParam("id") Integer id) {
        return convertToDto(customerService.getCustomerById(id));
      }
      
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
