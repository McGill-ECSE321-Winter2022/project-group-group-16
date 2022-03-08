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

import ca.mcgill.ecse321.GroceryApplicationBackend.dto.ProductDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product.Availability;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.ProductService;

@CrossOrigin(origins = "*")
@RestController
public class ProductController {
	   @Autowired
	    ProductService productService;
	
	
	  @PostMapping(value = { "/createProduct/{image}/{applicationId}/{categoryId}/{name}/{description}/{price}/{weight}/{volume}/{availability}/{barCode}/{isRefundable}/{avaQuantity}", "/createProduct/{image}/{applicationId}/{categoryId}/{name}/{description}/{price}/{weight}/{volume}/{availability}/{barCode}/{isRefundable}/{avaQuantity}/" })
	  public ProductDto createProduct (@PathVariable("image") String image,  @PathVariable("applicationId") int applicationId, @PathVariable("categoryId") int categoryId, @PathVariable("name") String name, @PathVariable("description") String description, @PathVariable("price") float price, @PathVariable("weight") float weight, @PathVariable("volume") float volume, @PathVariable("availability") Availability availability,  @PathVariable("barCode") int barCode,  @PathVariable("isRefundable") boolean isRefundable,  @PathVariable("avaQuantity") int avaQuantity  ) {
		  Product product = productService.createProduct(image, applicationId, categoryId, name, description, price, weight, volume, availability, barCode, isRefundable, avaQuantity);
		  return convertToDto(product);
	  }
	  
	  @PutMapping(value = {"/updateProduct/{image}/{applicationId}/{categoryId}/{name}/{description}/{price}/{weight}/{volume}/{availability}/{barCode}/{isRefundable}/{avaQuantity}", "/updateProduct/{image}/{applicationId}/{categoryId}/{name}/{description}/{price}/{weight}/{volume}/{availability}/{barCode}/{isRefundable}/{avaQuantity}/" })
	  public ProductDto updateProduct (@PathVariable("image") String image,  @PathVariable("applicationId") int applicationId, @PathVariable("categoryId") int categoryId, @PathVariable("name") String name, @PathVariable("description") String description, @PathVariable("price") float price, @PathVariable("weight") float weight, @PathVariable("volume") float volume, @PathVariable("availability") Availability availability,  @PathVariable("barCode") int barCode,  @PathVariable("isRefundable") boolean isRefundable,  @PathVariable("avaQuantity") int avaQuantity  ) {
		  Product product = productService.updateProduct(image, applicationId, categoryId, name, description, price, weight, volume, availability, barCode, isRefundable, avaQuantity);
		  return convertToDto(product);
	  }
	  
	  @DeleteMapping(value = { "/deleteProduct/{barCode}", "deleteProduct/{barCode}/" }) 
	  public ProductDto deleteProduct(@PathVariable("barCode") int barCode) {
		  Product product = productService.deletProduct(barCode);
		    return convertToDto(product);
	  }
	  
	  @GetMapping(value = { "/getProductByBarcode/{barCode}", "/getProductByBarcode/{barCode}/" })
	  public ProductDto getProductyById(@PathVariable("barCode") int barCode) {
	    return convertToDto(productService.getProductByBarcode(barCode));
	  }
	  
	  @GetMapping(value = { "/getAllProduct", "/getAllProduct/" })
	  public List<ProductDto> getAllProduct() {
	    List<ProductDto> productDtos = new ArrayList<>();
	    for (Product product : productService.getAllProduct()) {
	      productDtos.add(convertToDto(product));
	    }
	    return productDtos;
	  }
	  
	  @GetMapping(value = { "/refundProduct/{barCode}", "/refundProduct/{barCode}/" })
	  public ProductDto refundProduct(@PathVariable("barCode") int barCode) {
	    return convertToDto(productService.refundProduct(barCode));
	  }

	  
	  
		//-------------------------- Helper Methods -----------------------------
	  
	  
		private ProductDto convertToDto(Product product) {
			if (product == null) {
				throw new IllegalArgumentException("The provided product does not exist.");
			}
			ProductDto productDto = new ProductDto(product.getVolume(),product.getName(),product.getDescription(), product.getPrice(), product.getImage(),product.getWeight(), product.getBarcode(),product.getIsRefundable(), product.getAvailableQuantity(), product.getAvailability());
			return productDto;
		}

}
