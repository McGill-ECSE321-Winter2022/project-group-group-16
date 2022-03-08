package ca.mcgill.ecse321.GroceryApplicationBackend.dto;


import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product.Availability;

public class ProductDto {
	// attributes
    private String name;
    private String description;
    private float price;
    private String image;
    private float weight;
    private float volume;
    private int barcode;
    private boolean isRefundable;
    private int availableQuantity;
    private Availability availability;
  
	

	public ProductDto() {
		
	}
	
	public ProductDto(int barcode) {
		this.name = null;
		this.description = null;
		this.price = 0f;
		this.image = null;
		this.weight = 0f;
		this.volume = 0f;
		this.barcode = barcode;
		this.availability = null;
		this.isRefundable = false;
		this.availableQuantity = 0;
	}
	
	public ProductDto(float volume, String name, String description,float price, String image, float weight, int barcode, boolean isRefundable, int availableQuantity, Availability availability) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
		this.weight = weight;
		this.volume = volume;
		this.barcode = barcode;
		this.availability = availability;
		this.isRefundable = isRefundable;
		this.availableQuantity = availableQuantity;
	}
	
	// ------------------ Getters and Setters ------------------------
	
	 public String getName() {
	        return this.name;
	    }

	    public void setName(String value) {
	        this.name = value;
	    }

	    public String getDescription() {
	        return this.description;
	    }

	    public void setDescription(String value) {
	        this.description = value;
	    }

	    public float getPrice() {
	        return this.price;
	    }

	    public void setPrice(float value) {
	        this.price = value;
	    }

	    public String getImage() {
	        return this.image;
	    }

	    public void setImage(String value) {
	        this.image = value;
	    }

	    public float getWeight() {
	        return this.weight;
	    }

	    public void setWeight(float value) {
	        this.weight = value;
	    }

	    public float getVolume() {
	        return this.volume;
	    }

	    public void setVolume(float value) {
	        this.volume = value;
	    }

	    public Availability getAvailability() {
	        return this.availability;
	    }

	    public void setAvailability(Availability value) {
	        this.availability = value;
	    }

	    public int getBarcode() {
	        return this.barcode;
	    }

	    public void setBarcode(int value) {
	        this.barcode = value;
	    }

	    public boolean isRefundable() {
	        return this.isRefundable;
	    }

	    public void setIsRefundable(boolean value) {
	        this.isRefundable = value;
	    }

	    public int getAvailableQuantity() {
	        return this.availableQuantity;
	    }

	    public void setAvailableQuantity(int value) {
	        this.availableQuantity = value;
	    }

	
	

}
