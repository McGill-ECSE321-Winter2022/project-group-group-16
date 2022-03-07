package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.InvalidApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.CategoryRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryStoreApplicationRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryUserRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.ProductRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Category;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Customer;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product;

@Service
public class CategroyService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	GroceryStoreApplicationRepository groceryStoreApplicationRepository;

	@Autowired
	ProductRepository productRepository;

	// image??
	public Category createCategory(int categoryId, int applicationId, String name, String description) throws InvalidApplicationException {
		if (name == null || name.trim().length() == 0) {
			throw new InvalidApplicationException("requested name is null or length 0. Please enter valid namel.\n");
		}
		if (description == null || description.trim().length() == 0) {
			throw new InvalidApplicationException(
					"requested description is null or length 0. Please enter valid description.\n");
		}

		GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);
		if (gs == null) {
			throw new InvalidApplicationException("No application associated with this Id.");
		}

		Category category = new Category();
		category.setGroceryStoreApplication(gs);
		category.setName(name);
		category.setDescription(description);
		category.setId(categoryId);
		categoryRepository.save(category);

		return category;

	}

	@Transactional
	public Category getCategory(int categoryId) {
		Category c = categoryRepository.findCategoryById(categoryId);
		return c;
	}

	@Transactional
	public List<Category> getAllCategory(Category categroy) {
		return toList(categoryRepository.findAll());

	}

	@Transactional
	public Category deleteCategory(int id) throws InvalidApplicationException {
		if (categoryRepository.findCategoryById(id) == null) {
			throw new InvalidApplicationException("Category  with provided id does not exist.");
		}
		Category category = categoryRepository.findCategoryById(id);
		categoryRepository.delete(category);
		return category;
	}

	public Category updateCategory(int categoryId, int applicationId, String name, String description)
			throws InvalidApplicationException {
		if (name == null || name.trim().length() == 0) {
			throw new InvalidApplicationException("requested name is null or length 0. Please enter valid namel.\n");
		}
		if (description == null || description.trim().length() == 0) {
			throw new InvalidApplicationException(
					"requested description is null or length 0. Please enter valid description.\n");
		}

		GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);
		if (gs == null) {
			throw new InvalidApplicationException("No application associated with this Id.");
		}

		Category category = categoryRepository.findCategoryById(categoryId);
		category.setGroceryStoreApplication(gs);
		category.setName(name);
		category.setDescription(description);
		category.setId(categoryId);
		categoryRepository.save(category);

		return category;

	}

	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
