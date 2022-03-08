package ca.mcgill.ecse321.GroceryApplicationBackend.dto;

import java.sql.Date;


public class GroceryUserDto {
    private Date dateOfBirth;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    
    public GroceryUserDto() {
		
	}
	
	public GroceryUserDto(String email) {
		this.email = email;
		this.dateOfBirth = null;
		this.username = null;
		this.password = null;
		this.firstName = null;
		this.lastName = null;
	}
	
	public GroceryUserDto (String email, Date dateOfBirth,String username,String password,String firstName, String lastName ){
		this.email = email;
		this.dateOfBirth =dateOfBirth;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	// ------------------ Getters and Setters ------------------------
	
	
	  public String getUsername() {
	        return this.username;
	    }

	    public void setUsername(String value) {
	        this.username = value;
	    }

	    public String getPassword() {
	        return this.password;
	    }

	    public void setPassword(String value) {
	        this.password = value;
	    }

	    public String getFirstName() {
	        return this.firstName;
	    }

	    public void setFirstName(String value) {
	        this.firstName = value;
	    }

	    public String getLastName() {
	        return this.lastName;
	    }

	    public void setLastName(String value) {
	        this.lastName = value;
	    }

	    public String getEmail() {
	        return this.email;
	    }

	    public void setEmail(String value) {
	        this.email = value;
	    }

	    public Date getDateOfBirth() {
	        return this.dateOfBirth;
	    }

	    public void setDateOfBirth(Date value) {
	        this.dateOfBirth = value;
	    }
	
	
	
    
    
	
	

}
