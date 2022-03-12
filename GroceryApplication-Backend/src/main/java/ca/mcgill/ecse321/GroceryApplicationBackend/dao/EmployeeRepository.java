package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Employee;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, String> {


    /**
     * Find an employee via id
     *
     * @param id
     * @return employee(Employee)
     */
    Employee findEmployeeById(int id);

    Employee findEmployeeByUser(GroceryUser user);

    void deleteEmployeeById(Integer id);

    void deleteEmployeeByUser(GroceryUser user);

    List<Employee> findAll();

    List<Employee> findAllByOrderByHourlyPayDesc();

    List<Employee> findAllByOrderByHiredDateDesc();
}