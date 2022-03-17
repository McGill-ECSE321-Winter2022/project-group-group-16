package ca.mcgill.ecse321.GroceryApplicationBackend.controller;

import ca.mcgill.ecse321.GroceryApplicationBackend.dto.CategoryDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Category;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Rest controller method for creating category
     *
     * @param image
     * @param applicationId
     * @param name
     * @param description
     * @return
     * @throws ApiRequestException
     */
    @PostMapping(value = {"/category", "/category/"})
    public CategoryDto createCategory(@RequestParam String image, @RequestParam Integer applicationId, @RequestParam String name, @RequestParam String description) throws ApiRequestException {
        Category category = categoryService.createCategory(image, applicationId, name, description);
        return convertToDto(category);
    }

    /**
     * Rest controller method for updating category
     *
     * @param categoryId
     * @param applicationId
     * @param name
     * @param description
     * @param image
     * @return
     * @throws ApiRequestException
     */
    @PutMapping(value = {"/category/{categoryId}", "/category/{categoryId}/"})
    public CategoryDto updateCategory(@PathVariable("categoryId") Integer categoryId, @RequestParam Integer applicationId, @RequestParam String name, @RequestParam String description, @RequestParam String image) throws ApiRequestException {
        Category category = categoryService.updateCategory(categoryId, applicationId, name, description, image);
        return convertToDto(category);
    }


    /**
     * Rest controller for delete category
     *
     * @param categoryId
     * @return
     * @throws ApiRequestException
     */
    @DeleteMapping(value = {"/deleteCategory/{categoryId}", "deleteCategory/{categoryId}/"})
    public CategoryDto deleteCategory(@PathVariable("categoryId") Integer categoryId) throws ApiRequestException {
        Category category = categoryService.deleteCategory(categoryId);
        return convertToDto(category);
    }

    /**
     * Rest controller for get category by id
     *
     * @param categoryId
     * @return
     * @throws ApiRequestException
     */
    @GetMapping(value = {"/getCategory/{categoryId}", "/getCategory/{categoryId}/"})
    public CategoryDto getCategoryById(@PathVariable("categoryId") Integer categoryId) throws ApiRequestException {
        return convertToDto(categoryService.getCategorybyId(categoryId));
    }

    /**
     * Rest controller for get all category
     *
     * @return
     */
    @GetMapping(value = {"/getAllCategory", "/getAllCategory/"})
    public List<CategoryDto> getAllCategory() {
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : categoryService.getAllCategory()) {
            categoryDtos.add(convertToDto(category));
        }
        return categoryDtos;
    }


    //-------------------------- Helper Methods -----------------------------

    /**
     * Convert to DTO
     *
     * @param category
     * @return
     * @author noahye
     */

    private CategoryDto convertToDto(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("The provided category does not exist.");
        }
        CategoryDto categoryDto = new CategoryDto(category.getId(), category.getName(), category.getDescription(), category.getImage());

        return categoryDto;
    }


}
