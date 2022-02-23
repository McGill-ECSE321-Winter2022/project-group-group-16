package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, String> {


    /**
     * Find an employee via id
     *
     * @param id
     * @return employee(Employee)
     */
    Employee findEmployeeById(int id);

}