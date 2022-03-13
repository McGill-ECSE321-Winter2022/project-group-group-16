package ca.mcgill.ecse321.GroceryApplicationBackend.service;


import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.CategoryRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.CustomerRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryStoreApplicationRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.ProductRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
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
	public Product createProduct(String image, Integer applicationId, Integer categoryId, String name, String description, Float price, Float weight, Float volume, Availability availability, boolean isRefundable, Integer avaQuantity)  {
  
		   //create a grocery store application
        //mandatory to create for a store to exist
		GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);
		if (gs == null) {
			throw new ApiRequestException("No application associated with this Id.");
		}

        Category category = categoryRepository.findCategoryById(categoryId);
        if (category == null) {
			throw new ApiRequestException("No category associated with this Id.");
		}
        
        if (name == null || name.trim().length() == 0) {
			throw new ApiRequestException("Requested name is null or length 0. Please enter valid namel.\n");
		}
        
        if (description == null || description.trim().length() == 0) {
			throw new ApiRequestException("Requested description is null or length 0. Please enter valid descriptionl.\n");
		}
       

        Product product = new Product();
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
	public Product updateProduct(String image, Integer applicationId, Integer categoryId, String name, String description, Float price, Float weight, Float volume, Availability availability, Integer barCode, boolean isRefundable, Integer avaQuantity)  {
		  
        //create a grocery store application
        //mandatory to create for a store to exist
		GroceryStoreApplication gs = groceryStoreApplicationRepository.findGroceryStoreApplicationById(applicationId);
		if (gs == null) {
			throw new ApiRequestException("No application associated with this Id.");
		}

        Category category = categoryRepository.findCategoryById(categoryId);
        if (category == null) {
			throw new ApiRequestException("No category associated with this Id.");
		}
        
        if (name == null || name.trim().length() == 0) {
			throw new ApiRequestException("Requested name is null or length 0. Please enter valid namel.\n");
		}
        
        if (description == null || description.trim().length() == 0) {
			throw new ApiRequestException("Requested description is null or length 0. Please enter valid descriptionl.\n");
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
	public Product getProductByBarcode(Integer barCode) {
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
	public Product deletProduct(Integer barCode)  {
		if (productRepository.findProductByBarcode(barCode) == null) {
			throw new ApiRequestException("Product with provided barcode does not exist.");
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
	public Product refundProduct(Integer barCode)  {
		Product product = productRepository.findProductByBarcode(barCode);
		if(!product.getIsRefundable()) {
			throw new ApiRequestException("Product is not refundable.");
		}
		
		product.setAvailableQuantity(product.getAvailableQuantity()+1);
		productRepository.save(product);
		return product;
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
