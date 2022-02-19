package ca.mcgill.ecse321.GroceryApplicationBackend.dao;
// package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import org.springframework.data.repository.CrudRepository;
// import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product.Availability;


public interface ProductRepository  extends CrudRepository<Product,Integer> {


	Product findProductByName(String name);


	Product findProductByBarcode(int barcode);


	Product findProductByAvailability (Availability availability);



}
