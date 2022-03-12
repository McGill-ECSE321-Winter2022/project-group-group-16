package ca.mcgill.ecse321.GroceryApplicationBackend.dto;

public class CategoryDto {
	private String name;
	private String description;
	private String image;
	private Integer categoryId;
	
	public CategoryDto() {
		
	}
	
	public CategoryDto(Integer categoryId) {
		this.name = null;
		this.description = null;
		this.image = null;
		this.categoryId = categoryId;
	}
	
	public CategoryDto(Integer categoryId, String name, String description, String image) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.categoryId = categoryId;
	}
	
	// ------------------ Getters and Setters ------------------------
	

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    public Integer getId() {
        return this.categoryId;
    }

    public void setId(Integer categoryId) {
    	this.categoryId = categoryId;
    }


	
	

}
