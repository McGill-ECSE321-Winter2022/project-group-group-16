package ca.mcgill.ecse321.GroceryApplicationBackend.dto;

public class CustomerDto {
	
	private Integer customerId;
	
	public CustomerDto() {
		
	}
	
	public CustomerDto(int customerId) {
		this.customerId = customerId;
	}
	
	
	// ------------------ Getters and Setters ------------------------
	
	public Integer getCustomerId(){
		return this.customerId;
	}
	
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	

}
