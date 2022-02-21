package ca.mcgill.ecse321.GroceryApplicationBackend.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Employee;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Shift;


public interface ShiftRepository extends CrudRepository<Shift, Integer>{

    Shift findShiftById(int id);
    Shift findShiftByEmployee(Employee employee);
}