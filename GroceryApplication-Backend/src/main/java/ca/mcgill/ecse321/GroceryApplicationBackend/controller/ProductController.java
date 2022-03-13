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


	  @PostMapping(value = {"/product", "/product/"})
	  public ProductDto createProduct (@PathVariable("image") String image,  @PathVariable("applicationId") Integer applicationId, @PathVariable("categoryId") Integer categoryId, @PathVariable("name") String name, @PathVariable("description") String description, @PathVariable("price") Float price, @PathVariable("weight") Float weight, @PathVariable("volume") Float volume, @PathVariable("availability") Availability availability,  @PathVariable("isRefundable") boolean isRefundable,  @PathVariable("avaQuantity") Integer avaQuantity)throws ApiRequestException {
		  Product product = productService.createProduct(image, applicationId, categoryId, name, description, price, weight, volume, availability, isRefundable, avaQuantity);
		  return convertToDto(product);
	  }

	  @PutMapping(value = {"/product/{barCode}\", \"/product/{barCode}/"})
	  public ProductDto updateProduct (@RequestParam("barCode") Integer barCode,  @PathVariable("image") String image,  @PathVariable("applicationId") Integer applicationId, @PathVariable("categoryId") Integer categoryId, @PathVariable("name") String name, @PathVariable("description") String description, @PathVariable("price") Float price, @PathVariable("weight") Float weight, @PathVariable("volume") Float volume, @PathVariable("availability") Availability availability,  @PathVariable("isRefundable") boolean isRefundable,  @PathVariable("avaQuantity") Integer avaQuantity  ) {
		  Product product = productService.updateProduct(image, applicationId, categoryId, name, description, price, weight, volume, availability, barCode, isRefundable, avaQuantity);
		  return convertToDto(product);
	  }

	  @DeleteMapping(value = { "/deleteProduct/{barCode}", "deleteProduct/{barCode}/" }) 
	  public ProductDto deleteProduct(@RequestParam("barCode") Integer barCode) {
		  Product product = productService.deletProduct(barCode);
		    return convertToDto(product);
	  }

	  @GetMapping(value = { "/getProductByBarcode/{barCode}", "/getProductByBarcode/{barCode}/" })
	  public ProductDto getProductyById(@RequestParam("barCode") Integer barCode) {
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
	  public ProductDto refundProduct(@RequestParam("barCode") Integer barCode) {
	    return convertToDto(productService.refundProduct(barCode));
	  }



		//-------------------------- Helper Methods -----------------------------

	  /**
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