package ca.mcgill.ecse321.GroceryApplicationBackend.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    Category findCategoryById(int id);
}
