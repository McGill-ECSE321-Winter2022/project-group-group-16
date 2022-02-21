package ca.mcgill.ecse321.GroceryApplicationBackend.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String>{

    Employee findEmployeeById(int id);
    
}