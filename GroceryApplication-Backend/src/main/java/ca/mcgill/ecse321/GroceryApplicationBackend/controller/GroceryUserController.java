package ca.mcgill.ecse321.GroceryApplicationBackend.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


import ca.mcgill.ecse321.GroceryApplicationBackend.dto.GroceryUserDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryUser;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.GroceryUserService;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.InvalidInputException;

@CrossOrigin(origins = "*")
@RestController
public class GroceryUserController {

	  @Autowired
	  private GroceryUserService groceryUserService;
	  
	  
	  
	  @PostMapping(value = { "/createGroceryUser/{username}/{password}/{firstName}/{lastName}/(email}/{date}", "/createGroceryUser/{username}/{password}/{firstName}/{lastName}/(email}/{date}/" })
	  public GroceryUserDto createGroceryUser (@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName, @PathVariable("email") String email, @PathVariable("date") Date date) {
		  GroceryUser user =  groceryUserService.createGroceryUser(username, password, firstName, lastName, email, date);
		    return convertToDto(user);
	  }
	  
	  
	  @PutMapping(value = { "/updateGroceryUser/{username}/{password}/{firstName}/{lastName}/(email}/{date}", "/updateGroceryUser/{username}/{password}/{firstName}/{lastName}/(email}/{date}/" })
	  public GroceryUserDto updateGroceryUser (@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName, @PathVariable("email") String email, @PathVariable("date") Date date) {
		 List <GroceryUser> users =  groceryUserService.getAllGroceryUser();
		 for(int i = 0; i<users.size(); i++) {
			if (users.get(i).getEmail().equals(email)){
				throw new InvalidInputException(
						"User with this email already exists.\n");
			}
			 
		 }
		  GroceryUser user =  groceryUserService.createGroceryUser(username, password, firstName, lastName, email, date);
		    return convertToDto(user);
	  }
	  
	  @DeleteMapping(value = { "/deleteGroceryUser/{email}", "deleteGroceryUser/{email}/" }) 
	  public GroceryUserDto deleteProduct(@PathVariable("email") String email) {
		  GroceryUser user = groceryUserService.deleteGroceryUser(email);
		    return convertToDto(user);
	  }
	  
	  @GetMapping(value = { "/getGroceryUserbyId/{email}", "/getGroceryUserbyId/{email}/" })
	  public GroceryUserDto getGroceryByEmail(@PathVariable("email") String email) {
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
	  public GroceryUserDto loginGroceryUser(@PathVariable("email") String email, @PathVariable("password") String password) {
		  
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
