package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Category;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {


    /**
     * Find category by id
     *
     * @param id
     * @return category(Category)
     */
    Category findCategoryById(int id);

    /**
     * Find category by the name
     *
     * @param name
     * @return category(Category)
     */

    Category findCategoryByname(String name);

    /**
     * Find category by the product
     *
     * @param product
     * @return category(Category)
     */


    Category findCategoryByProduct(Product product);
    
    void deleteCategoryById(Integer id);
}
