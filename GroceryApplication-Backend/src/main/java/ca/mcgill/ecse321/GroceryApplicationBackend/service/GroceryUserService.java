package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import java.sql.Date;
import java.sql.Time;
import java.util.List;


import javax.management.InvalidApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryStoreApplicationRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryUserRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Address;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Category;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Customer;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Employee;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryUser;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.UserRole;

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
	 * @throws InvalidApplicationException
	 */
	@Transactional
	public GroceryUser createGroceryUser(String username, String password, String firstName, String Lastname, String email, Date date) throws InvalidApplicationException {
	
        GroceryUser gu = new GroceryUser();
        gu.setDateOfBirth(date);
        gu.setEmail(email);
        gu.setFirstName(firstName);
        gu.setPassword(password);
        gu.setUsername(username);
        gu.setLastName(Lastname);
        
  
        groceryUserRepository.save(gu);

   
        return gu;
	}
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	
	@Transactional
	public GroceryUser getGroceryUser(String email) {
		GroceryUser user = groceryUserRepository.findGroceryUserByEmail(email);
		return user;
	}
	/**
	 * 
	 * @return
	 */
	@Transactional
	public List<GroceryUser> getGroceryUser() {
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
	 * @throws InvalidApplicationException
	 */
	@Transactional
	public GroceryUser updateGroceryUser(String username, String password, String firstName, String Lastname, String email, Date date) throws InvalidApplicationException {
	
        GroceryUser gu = groceryUserRepository.findGroceryUserByEmail(email);
        gu.setDateOfBirth(date);
        gu.setEmail(email);
        gu.setFirstName(firstName);
        gu.setPassword(password);
        gu.setUsername(username);
        gu.setLastName(Lastname);        
  
        groceryUserRepository.save(gu); 
        return gu;
	}
	
	  /**
	   * 
	   * @param email
	   * @param password
	   * @return
	   * @throws InvalidApplicationException
	   */
	  @Transactional
	  public GroceryUser loginGroceryUser(String email, String password) throws InvalidApplicationException {
	    GroceryUser user = groceryUserRepository.findGroceryUserByEmail(email);
	    if(user == null){
	      throw new InvalidApplicationException("no Customer exists with email "+ email);
	    }
	    if(!user.getPassword().equals(password)){
	      throw new InvalidApplicationException("incorrect password : " + password + " ,  db password : " + user.getPassword());
	    }
	    return user;
	  }
	  	
	  /**
	   * 
	   * @param email
	   * @return
	   * @throws InvalidApplicationException
	   */
		@Transactional
		public GroceryUser deleteGroceryUser(String email) throws InvalidApplicationException {
			if (groceryUserRepository.findGroceryUserByEmail(email) == null) {
				throw new InvalidApplicationException("Product with provided barcode does not exist.");
			}
			GroceryUser user = groceryUserRepository.findGroceryUserByEmail(email) ;
			groceryUserRepository.delete(user);
			return user;
		}
	
	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
