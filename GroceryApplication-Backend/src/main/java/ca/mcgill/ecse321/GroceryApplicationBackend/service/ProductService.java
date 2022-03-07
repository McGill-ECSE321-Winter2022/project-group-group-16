package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.management.InvalidApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.CategoryRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryStoreApplicationRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.ProductRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Address;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Category;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Customer;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryUser;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product.Availability;

public class ProductService {
    @Autowired
    ProductRepository productRepository;
	@Autowired
	GroceryStoreApplicationRepository groceryStoreApplicationRepository;
	@Autowired
	CategoryRepository categoryRepository;
    
    
	@Transactional
	public Product createProduct(String image, int applicationId, int categoryId, String name, String description, float price, float weight, float volume, Availability availability, int barCode, boolean isRefundable, int avaQuantity) throws InvalidApplicationException {
  
        //create a grocery store application
        //mandatory to create for a store to exist
		GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);
		if (gs == null) {
			throw new InvalidApplicationException("No application associated with this Id.");
		}

        Category category = categoryRepository.findCategoryById(categoryId);
        if (category == null) {
			throw new InvalidApplicationException("No category associated with this Id.");
		}
       

        Product product = new Product();
        product.setGroceryStoreApplication(gs);
        product.setCategory(category);
        product.setBarcode(barCode);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setWeight(weight);
        product.setImage(image);
        product.setIsRefundable(isRefundable);
        product.setVolume(volume);
        product.setAvailableQuantity(avaQuantity);
        product.setAvailability(Availability.DELIVERY);
        productRepository.save(product);

        return product;

	}
	
	public Product updateProduct(String image, int applicationId, int categoryId, String name, String description, float price, float weight, float volume, Availability availability, int barCode, boolean isRefundable, int avaQuantity) throws InvalidApplicationException {
		  
        //create a grocery store application
        //mandatory to create for a store to exist
		GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);
		if (gs == null) {
			throw new InvalidApplicationException("No application associated with this Id.");
		}

        Category category = categoryRepository.findCategoryById(categoryId);
        if (category == null) {
			throw new InvalidApplicationException("No category associated with this Id.");
		}
       

        Product product = productRepository.findProductByBarcode(barCode);
        product.setGroceryStoreApplication(gs);
        product.setCategory(category);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setWeight(weight);
        product.setImage(image);
        product.setIsRefundable(isRefundable);
        product.setVolume(volume);
        product.setAvailableQuantity(avaQuantity);
        product.setAvailability(Availability.DELIVERY);
        productRepository.save(product);

        return product;

	}
	
	@Transactional
	public Product getProduct(int barCode) {
		Product product = productRepository.findProductByBarcode(barCode);
		return product;
	}

	@Transactional
	public List<Product> getAllProduct(Product product) {
		return toList(productRepository.findAll());

	}
	@Transactional
	public Product deletProduct(int barCode) throws InvalidApplicationException {
		if (productRepository.findProductByBarcode(barCode) == null) {
			throw new InvalidApplicationException("Customer account with provided id does not exist.");
		}
		Product product = productRepository.findProductByBarcode(barCode);
		productRepository.delete(product);
		return product;
	}

	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}


}
