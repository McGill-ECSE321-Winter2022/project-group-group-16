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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.GroceryApplicationBackend.dto.CategoryDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Category;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.CategoryService;


@CrossOrigin(origins = "*")
@RestController
public class CategoryController {
	
	  @Autowired
	  private CategoryService categoryService;
	  
	  @PostMapping(value = {"/category", "/category/"})
	  public CategoryDto createCategory (@RequestParam String image, @RequestParam Integer applicationId, @RequestParam String name, @RequestParam String description) throws ApiRequestException {
		  Category category = categoryService.createCategory(image, applicationId, name, description);
		   return convertToDto(category);
	  }
	  
	  @PutMapping(value = {"/category/{categoryId}", "/category/{categoryId}/"})
	  public CategoryDto updateCategory(@PathVariable("categoryId") Integer categoryId, @RequestParam Integer applicationId, @RequestParam String name, @RequestParam String description, @RequestParam String image) throws ApiRequestException {
		  Category category = categoryService.updateCategory(categoryId, applicationId, name, description,image);
		    return convertToDto(category);
	  }
	  
	  
	  
	  @DeleteMapping(value = { "/deleteCategory/{categoryId}", "deleteCategory/{categoryId}/" }) 
	  public CategoryDto deleteCategory(@PathVariable("categoryId") Integer categoryId) throws ApiRequestException {
		  Category category = categoryService.deleteCategory(categoryId);
		    return convertToDto(category);
	  }
	  
	  @GetMapping(value = { "/getCategory/{categoryId}", "/getCategory/{categoryId}/" })
	  public CategoryDto getCategoryById(@PathVariable("categoryId") Integer categoryId) throws ApiRequestException {
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
