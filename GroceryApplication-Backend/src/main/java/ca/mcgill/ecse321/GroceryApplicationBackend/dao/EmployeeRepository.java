package ca.mcgill.ecse321.GroceryApplicationBackend.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Employee;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Shift;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

    Employee findEmployeeById(int id);
    Employee findEmployeeByShift(Shift shift);
   
}
