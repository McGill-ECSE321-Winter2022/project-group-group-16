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

import ca.mcgill.ecse321.GroceryApplicationBackend.dto.ProductDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product.Availability;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.ProductService;

@CrossOrigin(origins = "*")
@RestController
public class ProductController {
	   @Autowired
	    ProductService productService;

	   /**
	    * Rest controller to create product
	    * 
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
	    * @param isRefundable
	    * @param avaQuantity
	    * @return
	    * @throws ApiRequestException
	    */
	  @PostMapping(value = {"/product", "/product/"})
	  public ProductDto createProduct (@RequestParam  String image,  @RequestParam Integer applicationId, @RequestParam Integer categoryId, @RequestParam String name, @RequestParam String description, @RequestParam Float price, @RequestParam Float weight, @RequestParam Float volume, @RequestParam Availability availability,  @RequestParam  boolean isRefundable,  @RequestParam Integer avaQuantity)throws ApiRequestException {
		  Product product = productService.createProduct(image, applicationId, categoryId, name, description, price, weight, volume, availability, isRefundable, avaQuantity);
		  return convertToDto(product);
	  }
	  
	  /**
	   * Rest controller to update product
	   * 
	   * 
	   * @param barCode
	   * @param image
	   * @param applicationId
	   * @param categoryId
	   * @param name
	   * @param description
	   * @param price
	   * @param weight
	   * @param volume
	   * @param availability
	   * @param isRefundable
	   * @param avaQuantity
	   * @return
	   */
	  @PutMapping(value = {"/product/{barCode}", "/product/{barCode}/"})
	  public ProductDto updateProduct (@PathVariable("barCode") Integer barCode,  @RequestParam String image,  @RequestParam Integer applicationId, @RequestParam Integer categoryId,  @RequestParam String name,  @RequestParam String description,  @RequestParam Float price,  @RequestParam Float weight,  @RequestParam Float volume,  @RequestParam Availability availability,   @RequestParam boolean isRefundable,   @RequestParam Integer avaQuantity  ) {
		  Product product = productService.updateProduct(image, applicationId, categoryId, name, description, price, weight, volume, availability, barCode, isRefundable, avaQuantity);
		  return convertToDto(product);
	  }
	  /**
	   * Rest controller to delete controller 
	   * 
	   * 
	   * @param barCode
	   * @return
	   */
	  @DeleteMapping(value = { "/deleteProduct/{barCode}", "deleteProduct/{barCode}/" }) 
	  public ProductDto deleteProduct(@PathVariable("barCode") Integer barCode) {
		  Product product = productService.deleteProduct(barCode);
		    return convertToDto(product);
	  }
	  /**
	   * Rest controller to get product by id
	   * 
	   * 
	   * @param barCode
	   * @return
	   */
	  @GetMapping(value = { "/getProductByBarcode/{barCode}", "/getProductByBarcode/{barCode}/" })
	  public ProductDto getProductyById(@PathVariable("barCode") Integer barCode) {
	    return convertToDto(productService.getProductByBarcode(barCode));
	  }
	  /**
	   * Rest controller to get all product
	   * 
	   * 
	   * @return
	   */
	  @GetMapping(value = { "/getAllProduct", "/getAllProduct/" })
	  public List<ProductDto> getAllProduct() {
	    List<ProductDto> productDtos = new ArrayList<>();
	    for (Product product : productService.getAllProduct()) {
	      productDtos.add(convertToDto(product));
	    }
	    return productDtos;
	  }
	  /**
	   * Rest controller refund product by barcode
	   * 
	   * 
	   * @param barCode
	   * @return
	   */
	  @GetMapping(value = { "/refundProduct/{barCode}", "/refundProduct/{barCode}/" })
	  public ProductDto refundProduct(@PathVariable("barCode") Integer barCode) {
	    return convertToDto(productService.refundProduct(barCode));
	  }



		//-------------------------- Helper Methods -----------------------------

	  /**
	   * Convert to DTO
	   * 
	   * @author noahye
	   * @param product
	   * @return
	   */
		private ProductDto convertToDto(Product product) {
			if (product == null) {
				throw new IllegalArgumentException("The provided product does not exist.");
			}
			ProductDto productDto = new ProductDto(product.getVolume(),product.getName(),product.getDescription(), product.getPrice(), product.getImage(),product.getWeight(), product.getBarcode(),product.getIsRefundable(), product.getAvailableQuantity(), product.getAvailability());
			return productDto;
		}

}