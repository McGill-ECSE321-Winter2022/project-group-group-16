package ca.mcgill.ecse321.GroceryApplicationBackend.service;


import java.util.ArrayList;


import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryStoreApplicationRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryUserRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryUser;

@Service
public class GroceryUserService {
	 @Autowired
	 GroceryStoreApplicationRepository groceryStoreApplicationRepository;
		
	  @Autowired
	  GroceryUserRepository groceryUserRepository;

	/**
	 * 
	 * @param username
	 * @param password
	 * @param firstName
	 * @param Lastname
	 * @param email
	 * @param date
	 * @return
	 */
	@Transactional
	public GroceryUser createGroceryUser(String username, String password, String firstName, String lastName, String email, Date date)  {
		//add security to name 
		
		if(date == null) {
			throw new ApiRequestException("A date is null and has to be created.\n");
		}
		if (email == null || email.trim().length() == 0) {
			throw new ApiRequestException("Requested email is null or length 0. Please enter valid email.\n");
		}
		
		if (validEmail(email)) {
			throw new ApiRequestException("Requested email is not valid.\n");
		}
		
		if (password.length()<5 || !password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*")) {
			throw new ApiRequestException("Your password must be at least 5 characters long and contian a uppercasecase and lowercase character" );
		}
		if (username == null || username.trim().length() == 0) {
			throw new ApiRequestException("Requested username is null or length 0. Please enter valid username.\n");
		}
		
		if (firstName == null || firstName.trim().length() == 0) {
			throw new ApiRequestException("Requested first name is null or length 0. Please enter valid first namel.\n");
		}
		
		if(containsSpecialCharacter(firstName) || firstName.matches(".*\\d.*") ) {
			throw new ApiRequestException("Your first name cannot contain a number or a special character.");
		}
		
		if (lastName == null || lastName.trim().length() == 0) {
			throw new ApiRequestException("Requested last name is null or length 0. Please enter valid last namel.\n");
		}
		
		if(containsSpecialCharacter(lastName) || lastName.matches(".*\\d.*") ) {
			throw new ApiRequestException("Your last name cannot contain a number or a special character.");
		}
		
		
        GroceryUser gu = new GroceryUser();
        gu.setDateOfBirth(date);
        gu.setEmail(email);
        gu.setFirstName(firstName);
        gu.setPassword(password);
        gu.setUsername(username);
        gu.setLastName(lastName);
        
  
        groceryUserRepository.save(gu);

   
        return gu;
	}
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	
	@Transactional
	public GroceryUser getGroceryUserByEmail(String email) {
		GroceryUser user = groceryUserRepository.findGroceryUserByEmail(email);
		if(user == null) {
			throw new ApiRequestException ("Invalid email, could not find associated user with email" + email);
			
		}
		return user;
	}
	/**
	 * 
	 * @return
	 */
	@Transactional
	public List<GroceryUser> getAllGroceryUser() {
		return toList(groceryUserRepository.findAll());

	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @param firstName
	 * @param Lastname
	 * @param email
	 * @param date
	 * @return
	 */
	@Transactional
	public GroceryUser updateGroceryUser(String username, String password, String firstName, String lastName, String email, Date date)  {
		if(date == null) {
			throw new ApiRequestException("A date is null and has to be created.\n");
		}
		if (email == null || email.trim().length() == 0) {
			throw new ApiRequestException("Requested email is null or length 0. Please enter valid email.\n");
		}
		
		if (password.length()<5 || !password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*")) {
			throw new ApiRequestException("Your password must be at least 5 characters long and contian a uppercasecase and lowercase character" );
		}
		if (username == null || username.trim().length() == 0) {
			throw new ApiRequestException("Requested username is null or length 0. Please enter valid username.\n");
		}
		
		if (firstName == null || firstName.trim().length() == 0) {
			throw new ApiRequestException("Requested first name is null or length 0. Please enter valid first namel.\n");
		}
		
		if(containsSpecialCharacter(firstName) || firstName.matches(".*\\d.*") ) {
			throw new ApiRequestException("Your first name cannot contain a number or a special character.");
		}
		
		if (lastName == null || lastName.trim().length() == 0) {
			throw new ApiRequestException("Requested last name is null or length 0. Please enter valid last namel.\n");
		}
		
		if(containsSpecialCharacter(lastName) || lastName.matches(".*\\d.*") ) {
			throw new ApiRequestException("Your last name cannot contain a number or a special character.");
		}
        GroceryUser gu = groceryUserRepository.findGroceryUserByEmail(email);
        gu.setDateOfBirth(date);
        gu.setFirstName(firstName);
        gu.setPassword(password);
        gu.setUsername(username);
        gu.setLastName(lastName);        
  
        groceryUserRepository.save(gu); 
        return gu;
	}
	
	  /**
	   * 
	   * @param email
	   * @param password
	   * @return
	   */
	  @Transactional
	  public GroceryUser loginGroceryUser(String email, String password)  {
	    GroceryUser user = groceryUserRepository.findGroceryUserByEmail(email);
	    if(user == null){
	      throw new ApiRequestException("No user exists with email "+ email);
	    }
	    if(!user.getPassword().equals(password)){
	      throw new ApiRequestException("incorrect password : " + password + " ,  db password : " + user.getPassword());
	    }
	    return user;
	  }
	  	
	  /**
	   * 
	   * @param email
	   * @return
	   */
		@Transactional
		public GroceryUser deleteGroceryUser(String email) {
			if (groceryUserRepository.findGroceryUserByEmail(email) == null) {
				throw new ApiRequestException("Grocery user with provided email does not exist.");
			}
			GroceryUser user = groceryUserRepository.findGroceryUserByEmail(email) ;
			groceryUserRepository.delete(user);
			return user;
		}
		
		  // ------------------ Helper Methods ---------------------
		/**
		 * @author noahye
		 * @param str
		 * @return
		 */
		 private boolean containsSpecialCharacter(String str) {
			    String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
			    for (int i=0; i<str.length(); i++) {
			      if (specialChars.contains(Character.toString(str.charAt(i)))) {
			        return true;
			      }
			    }
			    return false;
			  }
		 /**
		  * @author noahye
		  * @param email
		  * @return
		  */
		 private boolean validEmail(String email) {

		 	  if (email.contains(" ")) {
			 return true;
		 	  }
		 
		 	  if (!email.contains(".")) {
		 		 return true;
		 	  }

		 	  if(!(email.indexOf("@") >0)) {
		 		 return true;
		 	  }
		 	  
		 	  if(!(email.indexOf("@") == email.lastIndexOf("@"))) {
		 		 return true;  
		 	  }
		 	  
		 	  if(!(email.indexOf("@") < email.lastIndexOf(".") - 1)) {
		 		 return true;	  
		 	  }
		 	  
		 	  if(!(email.lastIndexOf(".") < email.length() - 1)) {
		 		 return true;
		 	  }
			    return false;
			  }
	
	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
