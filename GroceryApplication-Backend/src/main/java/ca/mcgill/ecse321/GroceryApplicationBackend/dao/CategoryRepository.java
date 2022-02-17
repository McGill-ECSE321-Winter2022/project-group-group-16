package ca.mcgill.ecse321.GroceryApplicationBackend.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Category;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    Category findCategoryById(int id);
    Category findCategoryByname(String name);
    Category findCategoryByProduct(Product product);
}
