package ca.mcgill.ecse321.GroceryApplicationBackend.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Employee;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Shift;


public interface ShiftRepository extends CrudRepository<Shift, Integer>{

	/**
	 * Find shift by id
	 * @param id
	 * @return shift(Shift)
	 *  
	 *  */
    Shift findShiftById(int id);
    
    /**
     * Find shift by employee
     * @param employee
     * @return shift(Shift)
     *  
     *  */
    Shift findShiftByEmployee(Employee employee);
}