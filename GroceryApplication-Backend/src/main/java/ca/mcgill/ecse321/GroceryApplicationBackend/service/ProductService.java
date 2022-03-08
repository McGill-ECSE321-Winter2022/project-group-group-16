package ca.mcgill.ecse321.GroceryApplicationBackend.service;


import java.util.ArrayList;
import java.util.List;

import javax.management.InvalidApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.CategoryRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.CustomerRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryStoreApplicationRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.ProductRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Category;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product.Availability;

public class ProductService {
    @Autowired
    ProductRepository productRepository;
	@Autowired
	GroceryStoreApplicationRepository groceryStoreApplicationRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	CustomerRepository customerRepository;
    
    
	/**
	 * 
	 * @param image
	 * @param applicationId
	 * @param categoryId
	 * @param name
	 * @param description
	 * @param price
	 * @param weight
	 * @param volume
	 * @param availability
	 * @param barCode
	 * @param isRefundable
	 * @param avaQuantity
	 * @return
	 */
	@Transactional
	public Product createProduct(String image, int applicationId, int categoryId, String name, String description, float price, float weight, float volume, Availability availability, int barCode, boolean isRefundable, int avaQuantity)  {
  
        //create a grocery store application
        //mandatory to create for a store to exist
		GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);
		if (gs == null) {
			throw new InvalidInputException("No application associated with this Id.");
		}

        Category category = categoryRepository.findCategoryById(categoryId);
        if (category == null) {
			throw new InvalidInputException("No category associated with this Id.");
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
	/**
	 * 
	 * @param image
	 * @param applicationId
	 * @param categoryId
	 * @param name
	 * @param description
	 * @param price
	 * @param weight
	 * @param volume
	 * @param availability
	 * @param barCode
	 * @param isRefundable
	 * @param avaQuantity
	 * @return
	 */
	@Transactional
	public Product updateProduct(String image, int applicationId, int categoryId, String name, String description, float price, float weight, float volume, Availability availability, int barCode, boolean isRefundable, int avaQuantity)  {
		  
        //create a grocery store application
        //mandatory to create for a store to exist
		GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);
		if (gs == null) {
			throw new InvalidInputException("No application associated with this Id.");
		}

        Category category = categoryRepository.findCategoryById(categoryId);
        if (category == null) {
			throw new InvalidInputException("No category associated with this Id.");
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
	/**
	 * 
	 * @param barCode
	 * @return
	 */
	@Transactional
	public Product getProductByBarcode(int barCode) {
		Product product = productRepository.findProductByBarcode(barCode);
		return product;
	}
	/**
	 * 
	 * @return
	 */
	@Transactional
	public List<Product> getAllProduct() {
		return toList(productRepository.findAll());
	}
	/**
	 * 
	 * @param barCode
	 * @return
	 */
	@Transactional
	public Product deletProduct(int barCode)  {
		if (productRepository.findProductByBarcode(barCode) == null) {
			throw new InvalidInputException("Product with provided barcode does not exist.");
		}
		Product product = productRepository.findProductByBarcode(barCode);
		productRepository.delete(product);
		return product;
	}
	/**
	 * 
	 * @param barCode
	 * @return
	 */
	@Transactional
	public Product refundProduct(int barCode)  {
		Product product = productRepository.findProductByBarcode(barCode);
		if(!product.getIsRefundable()) {
			throw new InvalidInputException("Product is not refundable.");
		}
		
		product.setAvailableQuantity(product.getAvailableQuantity()+1);
		productRepository.save(product);
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
