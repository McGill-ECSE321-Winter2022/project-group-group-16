package ca.mcgill.ecse321.GroceryApplicationBackend.service;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.EmployeeRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryStoreApplicationRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryUserRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Employee;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;


@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private GroceryUserRepository groceryUserRepository;

    @Autowired
    private GroceryStoreApplicationRepository groceryStoreApplicationRepository;

    /**
     * Service method for creating an employee.
     * @param hiredDate
     * @param employeeStatus
     * @param hourlyPay
     * @param email of the grocery user associated.
     * @param groceryStoreApplicationId
     * @return created employee.
     */
    @Transactional
    public Employee createEmployee(Date hiredDate, Employee.EmployeeStatus employeeStatus, Float hourlyPay,
                                   String email, Integer groceryStoreApplicationId) {
        if (hiredDate == null) throw new ApiRequestException("Date is null.");
        if (employeeStatus == null) throw new ApiRequestException("Employee status is null.");
        if (hourlyPay == null || hourlyPay < 0.0) throw new ApiRequestException("Hourly pay null or negative.");

        GroceryUser groceryUser = groceryUserRepository.findGroceryUserByEmail(email);
        if (groceryUser == null)
            throw new ApiRequestException("The grocery user with email " + email + " does not exist."
                    + "Create a grocery user before creating employee.");

        GroceryStoreApplication groceryStoreApplication = groceryStoreApplicationRepository.findGroceryStoreApplicationById(groceryStoreApplicationId);
        if (groceryStoreApplication == null) {
            throw new ApiRequestException("Application with id " + groceryStoreApplicationId + " does not exist.");
        }

        Employee employee = new Employee();
        employee.setUser(groceryUser);
        employee.setGroceryStoreApplication(groceryStoreApplication);
        employee.setStatus(employeeStatus);
        employee.setHiredDate(hiredDate);
        employee.setHourlyPay(hourlyPay);
        employeeRepository.save(employee);
        return employee;
    }

    /**
     * Service method for getting an employee by id.
     * @param id
     * @return employee with the input id.
     */
    @Transactional
    public Employee getEmployeeById(Integer id) {
        Employee employee = employeeRepository.findEmployeeById(id);
        if (employee == null) throw new ApiRequestException("Employee with id " + id + " does not exist");
        return employee;
    }

    /**
     * Service method for getting an employee by the email
     * of the associated grocery user.
     * @param email of the grocery user.
     * @return employee.
     */
    @Transactional
    public Employee getEmployeeByEmail(String email) {
        GroceryUser user = groceryUserRepository.findGroceryUserByEmail(email);
        if (user == null) throw new ApiRequestException("Grocery user with email " + email + " does not exist");

        Employee employee = employeeRepository.findEmployeeByUser(user);
        if (employee == null)
            throw new ApiRequestException("No employee profile associated with user with email " + email);

        return employee;
    }

    /**
     * Service method for updating an employee.
     * @param id of the employee to update.
     * @param hiredDate
     * @param employeeStatus
     * @param hourlyPay
     * @param email
     * @param groceryStoreApplicationId
     * @return updated employee.
     */
    @Transactional
    public Employee updateEmployee(Integer id, Date hiredDate, Employee.EmployeeStatus employeeStatus,
                                   Float hourlyPay, String email, Integer groceryStoreApplicationId) {
        Employee employee = employeeRepository.findEmployeeById(id);
        if (employee == null) throw new ApiRequestException("Employee with id " + id + "does not exist");

        if (hiredDate != null) employee.setHiredDate(hiredDate);
        if (employeeStatus != null) employee.setStatus(employeeStatus);
        if (hourlyPay != null) {
            if (hourlyPay < 0) throw new ApiRequestException("Hourly pay should be larger than 0");
            employee.setHourlyPay(hourlyPay);
        }

        if (email != null) {
            GroceryUser groceryUser = groceryUserRepository.findGroceryUserByEmail(email);
            if (groceryUser == null)
                throw new ApiRequestException("Grocery user with email " + email + " does not exist");
            employee.setUser(groceryUser);
        }

        if (groceryStoreApplicationId != null) {
            GroceryStoreApplication groceryStoreApplication = groceryStoreApplicationRepository.findGroceryStoreApplicationById(groceryStoreApplicationId);
            if (groceryStoreApplication == null) {
                throw new ApiRequestException("Application with id " + groceryStoreApplicationId + " does not exist.");
            }

            employee.setGroceryStoreApplication(groceryStoreApplication);
        }

        return employee;
    }

    /**
     * Service method for deleting an employee given their id.
     * @param id of the employee to delete.
     */
    @Transactional
    public void deleteEmployeeById(Integer id) {
        Employee employee = employeeRepository.findEmployeeById(id);
        if (employee == null) throw new ApiRequestException("Employee with id " + id + "does not exist");

        employeeRepository.deleteEmployeeById(id);
    }

    /**
     * Service method for deleting an employee given the email of their associated grocery user.
     * @param email of the grocery user for whom to delete the employee role.
     */
    @Transactional
    public void deleteEmployeeByEmail(String email) {
        GroceryUser user = groceryUserRepository.findGroceryUserByEmail(email);
        if (user == null) throw new ApiRequestException("Grocery user with email " + email + " does not exist");


        Employee employee = employeeRepository.findEmployeeByUser(user);
        if (employee == null)
            throw new ApiRequestException("No employee profile associated with user with email " + email);

        employeeRepository.deleteEmployeeByUser(user);
    }

    /**
     * Service method for getting all employees without any ordering.
     * @return all employees.
     */
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Service method for getting all employees sorted by descending pay.
     * @return employees sorted by descending pay.
     */
    @Transactional
    public List<Employee> getAllEmployeesSortByPay() {
        return employeeRepository.findAllByOrderByHourlyPayDesc();
    }

    /**
     * Service method for getting all employees sorted by their descending hired date.
     * @return employees sorted by descending hired date.
     */
    @Transactional
    public List<Employee> getAllEmployeesSortByHiredDate() {
        return employeeRepository.findAllByOrderByHiredDateDesc();
    }

}
