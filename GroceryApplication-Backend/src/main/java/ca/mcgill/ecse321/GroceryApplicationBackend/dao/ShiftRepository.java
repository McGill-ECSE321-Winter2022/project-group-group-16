package ca.mcgill.ecse321.GroceryApplicationBackend.dao;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Shift;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Shift.Day;

public interface ShiftRepository extends CrudRepository<ShiftRepository,Integer> {
	
	Shift findByWeekDay(Day weekDay);
	
	Shift findByShift(Shift shift);
	
	boolean existsByWeekDay(Day weekDay);
	
	boolean existsByShift(Shift shift);
	
	
}
