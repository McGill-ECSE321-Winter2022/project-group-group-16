package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product.Availability;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, Integer> {

    /**
     * Find product via their name
     *
     * @param name
     * @return product(Product)
     */
    Product findProductByName(String name);

    /**
     * Find product via their barcode
     *
     * @param barcode
     * @return product (Product)
     */


    Product findProductByBarcode(int barcode);

    /**
     * Find product via availability
     *
     * @param availability
     * @return product (Product)
     */


    Product findProductByAvailability(Availability availability);


}
