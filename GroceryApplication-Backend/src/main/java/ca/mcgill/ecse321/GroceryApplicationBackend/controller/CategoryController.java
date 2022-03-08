package ca.mcgill.ecse321.GroceryApplicationBackend.controller;

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

import ca.mcgill.ecse321.GroceryApplicationBackend.dto.CategoryDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Category;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.CategroyService;


@CrossOrigin(origins = "*")
@RestController
public class CategoryController {
	
	  @Autowired
	  private CategroyService categoryService;
	  
	  @PostMapping(value = { "/createCategroy/{image}/{categoryId}/{applicationId}/{name}/{description}", "/createCustomer/{image}/{categoryId}/{applicationId}/{name}/{description}/" })
	  public CategoryDto createCategory (@PathVariable("image") String image, @PathVariable("categoryId") int categoryId, @PathVariable("applicationId") int applicationId, @PathVariable("name") String name, @PathVariable("description") String description) {
		  Category category = categoryService.createCategory(image, categoryId, applicationId, name, description);
		    return convertToDto(category);
	  }
	  
	  @PutMapping(value = { "/updateCategory/{categoryId}/{applicationId}/{name}/{description}/{image}", "/updateCategory/{categoryId}/{applicationId}/{name}/{description}/{image}/" })
	  public CategoryDto updateCategory(@PathVariable("categoryid") int categoryId, @PathVariable("applicationId")int applicationId, @PathVariable("name") String name, @PathVariable("description") String description, @PathVariable("image") String image) {
		  Category category = categoryService.updateCategory(categoryId, applicationId, name, description,image);
		    return convertToDto(category);
	  }
	  
	  
	  
	  @DeleteMapping(value = { "/deleteCategory/{categoryId}", "deleteCategory/{categoryId}/" }) 
	  public CategoryDto deleteCategory(@PathVariable("categoryId") int categoryId) {
		  Category category = categoryService.deleteCategory(categoryId);
		    return convertToDto(category);
	  }
	  
	  @GetMapping(value = { "/getCategory/{categoryId}", "/getCategory/{categoryId}/" })
	  public CategoryDto getCategoryById(@PathVariable("categoryId") int categoryId) {
	    return convertToDto(categoryService.getCategorybyId(categoryId));
	  }
	  
	  @GetMapping(value = { "/getAllCategory", "/getAllCategory/" })
	  public List<CategoryDto> getAllCategory() {
	    List<CategoryDto> categoryDtos = new ArrayList<>();
	    for (Category category : categoryService.getAllCategory()) {
	      categoryDtos.add(convertToDto(category));
	    }
	    return categoryDtos;
	  }
	  
	  
	  
		//-------------------------- Helper Methods -----------------------------
	  
	  /**
	   * @author noahye
	   * @param category
	   * @return
	   */
	  
		private CategoryDto convertToDto(Category category) {
			if (category == null) {
				throw new IllegalArgumentException("The provided category does not exist.");
			}
			CategoryDto categoryDto = new CategoryDto(category.getId(), category.getName(), category.getDescription(), category.getImage());

			return categoryDto;
		}
		
	  

}
