package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.EmployeeRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.ShiftRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Employee;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShiftService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    @Transactional
    public Shift createShift(Shift.Day day, Shift.ShiftType shiftType, Integer employeeId) {
        if (day == null) throw new ApiRequestException("Day is null");
        if (shiftType == null) throw new ApiRequestException("Shift type is null");
        if (employeeId == null) throw new ApiRequestException("Employee Id is null");

        Employee employee = employeeRepository.findEmployeeById(employeeId);
        if (employee == null) throw new ApiRequestException("Employee with id " + employeeId + " does not exist");

        Shift shift = new Shift();
        shift.setDay(day);
        shift.setEmployee(employee);
        shift.setShift(shiftType);

        shiftRepository.save(shift);
        return shift;
    }

    @Transactional
    public Shift updateShift(Integer id, Shift.Day day, Shift.ShiftType shiftType, Integer employeeId) {
        Shift shift = shiftRepository.findShiftById(id);

        if (shift == null) throw new ApiRequestException("Shift with id " + id + " does not exist");

        if (day != null) shift.setDay(day);
        if (shiftType != null) shift.setShift(shiftType);
        if (employeeId != null) {
            Employee employee = employeeRepository.findEmployeeById(employeeId);
            if (employee == null) throw new ApiRequestException("Employee with id " + employeeId + " does not exist");
            shift.setEmployee(employee);
        }

        return shift;
    }

    @Transactional
    public void deleteShift(Integer id) {
        Shift shift = shiftRepository.findShiftById(id);
        if (shift == null) throw new ApiRequestException("Shift with id " + id + " does not exist");

        shiftRepository.delete(shift);
    }

    @Transactional
    public Shift getShift(Integer id) {
        Shift shift = shiftRepository.findShiftById(id);
        if (shift == null) throw new ApiRequestException("Shift with id " + id + " does not exist");

        return shift;
    }

    @Transactional
    public List<Shift> getAllShifts() {
        return shiftRepository.findAll();
    }
}
