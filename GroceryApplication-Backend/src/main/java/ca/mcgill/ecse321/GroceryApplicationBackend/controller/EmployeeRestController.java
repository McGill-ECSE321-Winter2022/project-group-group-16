package ca.mcgill.ecse321.GroceryApplicationBackend.controller;

import ca.mcgill.ecse321.GroceryApplicationBackend.dto.EmployeeDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Employee;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class EmployeeRestController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * Rest method for creating an employee.
     * Note: A GroceryUser needs to exist with the provided email before
     * creating an employee profile with their email.
     *
     * @param hiredDate
     * @param employeeStatus
     * @param hourlyPay
     * @param email                     of the associated grocery user.
     * @param groceryStoreApplicationId
     * @return created employee
     * @throws ApiRequestException
     */
    @PostMapping(value = {"/employee", "/employee/"})
    public EmployeeDto createEmployee(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date hiredDate,
            @RequestParam Employee.EmployeeStatus employeeStatus,
            @RequestParam Float hourlyPay,
            @RequestParam String email,
            @RequestParam Integer groceryStoreApplicationId
    ) throws ApiRequestException {
        java.sql.Date sqlHiredDate = new java.sql.Date(hiredDate.getTime());
        Employee employee = employeeService.createEmployee(sqlHiredDate, employeeStatus, hourlyPay, email, groceryStoreApplicationId);
        return convertToDto(employee);
    }

    /**
     * Rest method for retrieving an employee by id.
     *
     * @param id of the employee to retrieve.
     * @return request employee.
     * @throws ApiRequestException
     */
    @GetMapping(value = {"/employee/id/{id}", "/employee/id/{id}/"})
    public EmployeeDto getEmployeeById(@PathVariable("id") Integer id) throws ApiRequestException {
        Employee employee = employeeService.getEmployeeById(id);
        return convertToDto(employee);
    }

    /**
     * Rest method for retrieving an employee by email.
     * Note: the email is the email associated with the grocery user.
     *
     * @param email
     * @return employee having the requested email.
     * @throws ApiRequestException
     */
    @GetMapping(value = {"/employee/email/{email}", "/employee/email/{email}/"})
    public EmployeeDto getEmployeeByEmail(@PathVariable("email") String email) throws ApiRequestException {
        Employee employee = employeeService.getEmployeeByEmail(email);
        return convertToDto(employee);
    }

    /**
     * Rest method for retrieving all employees.
     * The returned list is unordered.
     *
     * @return all employees.
     * @throws ApiRequestException
     */
    @GetMapping(value = {"/employee/", "/employee/"})
    public List<EmployeeDto> getAllEmployees() throws ApiRequestException {
        List<Employee> employees = employeeService.getAllEmployees();
        return convertToDto(employees);
    }

    /**
     * Rest method for retrieving all employees ordered by hourly pay.
     * Note: Order is Descending
     *
     * @return All employees ordered by descending hourly pay.
     * @throws ApiRequestException
     */
    @GetMapping(value = {"/employee/order/pay", "/employee/order/pay"})
    public List<EmployeeDto> getAllEmployeesSortedByPay() throws ApiRequestException {
        List<Employee> employees = employeeService.getAllEmployeesSortByPay();
        return convertToDto(employees);
    }

    /**
     * Rest method for retrieving all employees ordered by hired date.
     * Note: Order is Descending (i.e., employees hired more recently are on top).
     *
     * @return all employees ordered by descending hired date.
     * @throws ApiRequestException
     */
    @GetMapping(value = {"/employee/order/hiredDate", "/employee/order/hiredDate"})
    public List<EmployeeDto> getAllEmployeesSortedByHiredDate() throws ApiRequestException {
        List<Employee> employees = employeeService.getAllEmployeesSortByHiredDate();
        return convertToDto(employees);
    }

    /**
     * Rest method for deleting an employee by their id.
     *
     * @param id of the employee to delete.
     * @throws ApiRequestException
     */
    @DeleteMapping(value = {"/employee/id/{id}", "/employee/id/{id}/"})
    public void deleteEmployee(@PathVariable("id") Integer id) throws ApiRequestException {
        employeeService.deleteEmployeeById(id);
    }

    /**
     * Rest method for deleting an employee by their email.
     *
     * @param email of the associated grocery user.
     * @throws ApiRequestException
     */
    @DeleteMapping(value = {"/employee/email/{email}", "/employee/email/{email}/"})
    public void deleteEmployee(@PathVariable("email") String email) throws ApiRequestException {
        employeeService.deleteEmployeeByEmail(email);
    }

    /**
     * Rest method for updating an employee.
     *
     * @param id
     * @param hiredDate
     * @param employeeStatus
     * @param hourlyPay
     * @param email                     of the new grocery user.
     * @param groceryStoreApplicationId
     * @return updated employee.
     * @throws ApiRequestException
     */
    @PutMapping(value = {"/employee/id/{id}", "/employee/id/{id}/"})
    public EmployeeDto updateEmployee(
            @PathVariable("id") Integer id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date hiredDate,
            @RequestParam Employee.EmployeeStatus employeeStatus,
            @RequestParam Float hourlyPay,
            @RequestParam String email,
            @RequestParam Integer groceryStoreApplicationId) throws ApiRequestException {
        java.sql.Date sqlHiredDate = new java.sql.Date(hiredDate.getTime());
        Employee employee = employeeService.updateEmployee(id, sqlHiredDate, employeeStatus, hourlyPay, email, groceryStoreApplicationId);
        return convertToDto(employee);
    }


    /**
     * Converts an employee to a data transfer object.
     *
     * @param employee employee to convert.
     * @return corresponding data transfer object.
     */
    private static EmployeeDto convertToDto(Employee employee) {
        if (employee == null) return null;
        return new EmployeeDto(employee.getHiredDate(), employee.getStatus(), employee.getHourlyPay(), employee.getShift(), employee.getUser(), employee.getGroceryStoreApplication());
    }

    /**
     * Convert a list of employees to a list of data transfer objects.
     *
     * @param employees
     * @return corresponding list of data transfer objects.
     */
    private static List<EmployeeDto> convertToDto(List<Employee> employees) {
        List<EmployeeDto> Dtos = new ArrayList<>();
        for (Employee employee : employees) {
            Dtos.add(convertToDto(employee));
        }
        return Dtos;
    }

}
