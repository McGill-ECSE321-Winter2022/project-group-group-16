package ca.mcgill.ecse321.GroceryApplicationBackend.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.GroceryApplicationBackend.dto.CategoryDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.dto.GroceryUserDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Category;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryUser;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.GroceryUserService;


@CrossOrigin(origins = "*")
@RestController
public class GroceryUserController {

      @Autowired
      private GroceryUserService groceryUserService;



      @PostMapping(value = { "/gorceryUser", "/gorceryUser/"})
      public GroceryUserDto createGroceryUser (@RequestParam String username,@RequestParam String password, @RequestParam String firstName, @RequestParam String lastName,@RequestParam String email, @RequestParam String date) throws ApiRequestException { 
    	  Date convertDate = Date.valueOf(date);
          GroceryUser user =  groceryUserService.createGroceryUser(username, password, firstName, lastName, email, convertDate);
            return convertToDto(user);
      }


      @PutMapping(value = { "/gorceryUser/{email}", "/gorceryUser/{email}/" })
      public GroceryUserDto updateGroceryUser ( @PathVariable("email") String email, @RequestParam String username, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName,  @RequestParam String date) throws ApiRequestException  {
    	  Date convertDate = Date.valueOf(date);
          GroceryUser user =  groceryUserService.updateGroceryUser(username, password, firstName, lastName, email, convertDate);
            return convertToDto(user);
      }
	
      @DeleteMapping(value = { "/deleteGroceryUser/{email}", "deleteGroceryUser/{email}/" }) 
      public GroceryUserDto deleteGeoceryUser(@PathVariable("email") String email) throws ApiRequestException {
          GroceryUser user = groceryUserService.deleteGroceryUser(email);
            return convertToDto(user);
      }

      @GetMapping(value = { "/getGroceryUserbyEmail/{email}", "/getGroceryUserbyEmail/{email}/" })
      public GroceryUserDto getGroceryByEmail(@PathVariable ("email") String email) throws ApiRequestException {
        return convertToDto(groceryUserService.getGroceryUserByEmail(email));
      }

      @GetMapping(value = { "/getAllGroceryUser", "/getAllGroceryUser/" })
      public List<GroceryUserDto> getAllGroceryUser() {
        List<GroceryUserDto> groceryUserDtos = new ArrayList<>();
        for (GroceryUser user : groceryUserService.getAllGroceryUser()) {
          groceryUserDtos.add(convertToDto(user));
        }
        return groceryUserDtos;
      }

      @GetMapping(value = { "/loginGroceryUser/{email}/{password}", "/loginGroceryUser/{email}/{password}/" })
      public GroceryUserDto loginGroceryUser(@PathVariable("email") String email, @PathVariable("password") String password) throws ApiRequestException {

          GroceryUser user = groceryUserService.loginGroceryUser(email,password);


        return convertToDto(user);
      }


    //-------------------------- Helper Methods -----------------------------

      /**
       * @author noahye
       * @param user
       * @return
       */
            private GroceryUserDto convertToDto(GroceryUser user) {
                if (user == null) {
                    throw new IllegalArgumentException("The provided user does not exist.");
                }
                GroceryUserDto userDto = new GroceryUserDto(user.getEmail(),user.getDateOfBirth(),user.getUsername(),user.getPassword(),user.getFirstName(),user.getLastName());

                return userDto;
            }

}