package ca.mcgill.ecse321.GroceryApplicationBackend.service;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.CategoryRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryStoreApplicationRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.ProductRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Category;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	GroceryStoreApplicationRepository groceryStoreApplicationRepository;

	@Autowired
	ProductRepository productRepository;

	// image??
	/**
	 * 
	 * @param image
	 * @param categoryId
	 * @param applicationId
	 * @param name
	 * @param description
	 * @return
	 */
	@Transactional
	public Category createCategory(String image, Integer applicationId, String name, String description)  {
		if (name == null || name.trim().length() == 0) {
			throw new ApiRequestException("requested name is null or length 0. Please enter valid name.\n");
		}
		if (image == null || image.trim().length() == 0) {
			throw new ApiRequestException(
					"requested image is null or length 0. Please enter valid description.\n");
		}
		
		if (description == null || description.trim().length() == 0) {
			throw new ApiRequestException(
					"requested description is null or length 0. Please enter valid description.\n");
		}

		GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);
		if (gs == null) {
			throw new ApiRequestException("No application associated with this Id.");
		}

		Category category = new Category();
		category.setGroceryStoreApplication(gs);
		category.setName(name);
		category.setDescription(description);
		category.setImage(image);
		categoryRepository.save(category);

		return category;
	}
	
	/**
	 * 
	 * @param categoryId
	 * @return
	 */

	@Transactional
	public Category getCategorybyId(Integer categoryId) {
		if (categoryRepository.findCategoryById(categoryId) == null) {
			throw new ApiRequestException("Category  with provided id does not exist.");
		}
		Category c = categoryRepository.findCategoryById(categoryId);
		return c;
	}
	
	/**
	 * 
	 * @return
	 */
	@Transactional
	public List<Category> getAllCategory() {
		return toList(categoryRepository.findAll());
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public Category deleteCategory(Integer categoryId)  {
		if (categoryRepository.findCategoryById(categoryId) == null) {
			throw new ApiRequestException("Category  with provided id does not exist.");
		}
		Category category = categoryRepository.findCategoryById(categoryId);
		categoryRepository.deleteCategoryById(categoryId);;
		return category;
	}
	/**
	 * 
	 * @param categoryId
	 * @param applicationId
	 * @param name
	 * @param description
	 * @return
	 */
	@Transactional
	public Category updateCategory(Integer categoryId, Integer applicationId, String name, String description, String image) {
		if (name == null || name.trim().length() == 0) {
			throw new ApiRequestException("requested name is null or length 0. Please enter valid namel.\n");
		}
		
		if (image == null || image.trim().length() == 0) {
			throw new ApiRequestException("requested image is null or length 0. Please enter validimagel.\n");
		}
		if (description == null || description.trim().length() == 0) {
			throw new ApiRequestException(
					"requested description is null or length 0. Please enter valid description.\n");
		}

		GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);
		if (gs == null) {
			throw new ApiRequestException("No application associated with this Id.");
		}

		Category category = categoryRepository.findCategoryById(categoryId);
		category.setGroceryStoreApplication(gs);
		category.setName(name);
		category.setDescription(description);
		category.setId(categoryId);
		category.setImage(image);
		categoryRepository.save(category);

		return category;

	}
	
	  // ------------------ Helper Methods ---------------------
	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
