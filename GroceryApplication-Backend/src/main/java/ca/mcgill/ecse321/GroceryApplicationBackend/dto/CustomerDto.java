package ca.mcgill.ecse321.GroceryApplicationBackend.dto;

public class CustomerDto {
	
	private int customerId;
	
	public CustomerDto() {
		
	}
	
	public CustomerDto(int customerId) {
		this.customerId = customerId;
	}
	
	public int getCustomerId(){
		return this.customerId;
	}
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	

}
