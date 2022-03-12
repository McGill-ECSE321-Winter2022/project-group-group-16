package ca.mcgill.ecse321.GroceryApplication.service;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.management.InvalidApplicationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.AddressRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.EmployeeRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryUserRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Address;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Employee;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Employee.EmployeeStatus;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryUser;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.AddressService;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;
	
	@Mock
	 private GroceryUserRepository groceryUserRepository;
	
	@InjectMocks
	private EmployeeService employeeService;
	
	private static final int EMPLOYEEID = 789;
	private static final float HOURLYPAY = 18;
	private static final String EMAIL ="john@gmail.com";
	private static final Integer GROCERYAPPID=453;
	private static final String INEXISTANTEMAIL ="danny@hotmail.com";
	
	private static final int EMPLOYEEID2= 697;
	private static final float HOURLYPAY2 = 19;
	
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(employeeRepository.findEmployeeById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(EMPLOYEEID)) {
				

				Employee employee = new Employee();
				GroceryUser user = new GroceryUser();
				user.setEmail(EMAIL);
				employee.setUser(user);
				employee.setId(EMPLOYEEID);
				employee.setHourlyPay(HOURLYPAY);
				employee.setStatus(Employee.EmployeeStatus.ACTIVE);
				
				
				
				return employee;
				
			}
			
			else if (invocation.getArgument(0).equals(EMPLOYEEID2)) {
				Employee employee = new Employee();
				employee.setId(EMPLOYEEID2);
				employee.setHourlyPay(HOURLYPAY2);
				employee.setStatus(Employee.EmployeeStatus.ACTIVE);
				
				return employee;
				
			}
			
			else {
			return null;
			}
			
			
		});
		
		
		lenient().when(employeeRepository.findEmployeeByUser(any(GroceryUser.class))).thenAnswer((InvocationOnMock invocation) -> {
			Employee employee = new Employee();
			employee.setId(EMPLOYEEID);
			employee.setHourlyPay(HOURLYPAY);
			employee.setStatus(Employee.EmployeeStatus.ACTIVE);
			GroceryUser user = invocation.getArgument(0);
			
			employee.setUser(user);
			return employee;

		});
		
		
		lenient().when(employeeRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			
			Employee employee1 = new Employee();
			Employee employee2 = new Employee();
			ArrayList<Employee> employees = new ArrayList<Employee>();
			employees.add(employee1);
			employees.add(employee2);
			return employees;
			
		});
		
		//find by order hourly pay, from greatest to least
		lenient().when(employeeRepository.findAllByOrderByHourlyPayDesc()).thenAnswer((InvocationOnMock invocation) -> {
			
			
			
			Employee employee1 = new Employee();
			Employee employee2 = new Employee();
			ArrayList<Employee> employees = new ArrayList<Employee>();
			employees.add(employee1);
			employees.add(employee2);
			return employees;
		
		});

		//Sorting by greatest to least, hired date
		lenient().when(employeeRepository.findAllByOrderByHiredDateDesc()).thenAnswer((InvocationOnMock invocation) -> {
			
			Calendar c1 = Calendar.getInstance();
			c1.set(2021, Calendar.DECEMBER, 24,0,0,0);
			Date date1 = new Date(c1.getTimeInMillis());

			Calendar c2 = Calendar.getInstance();
			c2.set(2021, Calendar.DECEMBER, 26,0,0,0);
			Date date2 = new Date(c2.getTimeInMillis());
			
			Calendar c3 = Calendar.getInstance();
			c3.set(2021, Calendar.DECEMBER, 20,0,0,0);
			Date date3 = new Date(c3.getTimeInMillis());
			
			
			Employee employee1 = new Employee();			
			Employee employee2 = new Employee();			
			Employee employee3 = new Employee();
			
			employee1.setHiredDate(date1);
			employee2.setHiredDate(date2);
			employee3.setHiredDate(date3);
			
			ArrayList<Employee> employees = new ArrayList<Employee>();
			employees.add(employee1);
			employees.add(employee2);
			employees.add(employee3);
			
			
			Collections.reverse(employees);
			
			return employees;
			
		});
		
	
		
		//When anything is saved, return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			
			return invocation.getArgument(0);
			
		};
		lenient().when(employeeRepository.save(any(Employee.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	//Test to create employee
	/*@Test
	public void testCreateEmployee() {
		Employee employee = null;
		
		lenient().when(groceryUserRepository.findGroceryUserByEmail(anyString())).thenReturn(true);
		
		Calendar c1 = Calendar.getInstance();
		c1.set(2021, Calendar.DECEMBER, 10,0,0,0);
		Date date1 = new Date(c1.getTimeInMillis());
		
		try {
			//Date hiredDate, Employee.EmployeeStatus employeeStatus, Float hourlyPay,  String email, Integer groceryStoreApplicationId         
			employee = employeeService.createEmployee(date1, Employee.EmployeeStatus.ACTIVE, HOURLYPAY,EMAIL, GROCERYAPPID);
			
		}catch (Exception e) {
			
			fail();			
		}
		
		assertNotNull(employee);
		assertEquals(employee.getHiredDate(),date1);
		assertEquals(employee.getStatus(), Employee.EmployeeStatus.ACTIVE);
		assertEquals(employee.getHourlyPay(), HOURLYPAY);
		assertEquals(employee.getGroceryStoreApplication().getId(),GROCERYAPPID);
		assertEquals(employee.getUser().getEmail(),EMAIL);

		
	}*/
	//Test to create employee without date
	@Test
	public void testCreateEmployeeWithoutDate() {
		Employee employee = null;
		String error = null;

		
		try {
			//Date hiredDate, Employee.EmployeeStatus employeeStatus, Float hourlyPay,  String email, Integer groceryStoreApplicationId         
			employee = employeeService.createEmployee(null, Employee.EmployeeStatus.ACTIVE, HOURLYPAY,EMAIL, GROCERYAPPID);
			
		}catch (ApiRequestException e) {
			error = e.getMessage();		
		}
		
		assertNull(employee);
		assertEquals("Date is null.",error);		
	}
	
	
	//Test to create employee without employee status
	
	@Test
	public void testCreateEmployeeWithoutStatus() {
		Employee employee = null;
		String error = null;
		Calendar c1 = Calendar.getInstance();
		c1.set(2021, Calendar.DECEMBER, 10,0,0,0);
		Date date1 = new Date(c1.getTimeInMillis());
		
		try {
			employee = employeeService.createEmployee(date1, null, HOURLYPAY,EMAIL, GROCERYAPPID);
			
		}catch (ApiRequestException e) {
			error = e.getMessage();		
		}
		
		assertNull(employee);
		assertEquals("Employee status is null.",error);		
	}
	
	//Test to create employee without hourly pay
	@Test
	public void testCreateEmployeeWithoutHourlyPay() {
		Employee employee = null;
		String error = null;
		Calendar c1 = Calendar.getInstance();
		c1.set(2021, Calendar.DECEMBER, 10,0,0,0);
		Date date1 = new Date(c1.getTimeInMillis());
		
		try {
			employee = employeeService.createEmployee(date1, EmployeeStatus.ACTIVE, null,EMAIL, GROCERYAPPID);
			
		}catch (ApiRequestException e) {
			error = e.getMessage();		
		}
		
		assertNull(employee);
		assertEquals("Hourly pay null or negative.",error);		
	}
	//Test to create employee with negative hourly pay
	@Test
	public void testCreateEmployeeWithNegativeHourlyPay() {
		
		Employee employee = null;
		String error = null;
		Calendar c1 = Calendar.getInstance();
		c1.set(2021, Calendar.DECEMBER, 10,0,0,0);
		Date date1 = new Date(c1.getTimeInMillis());
		
		try {
			employee = employeeService.createEmployee(date1, EmployeeStatus.ACTIVE, (float) -3,EMAIL, GROCERYAPPID);
			
		}catch (ApiRequestException e) {
			error = e.getMessage();		
		}
		
		assertNull(employee);
		assertEquals("Hourly pay null or negative.",error);		
	}
	
	//Test to create employee with inexistant email
	@Test
	public void testCreateEmployeeWithInexistantEmail() {
		Employee employee = null;
		String error = null;
		Calendar c1 = Calendar.getInstance();
		c1.set(2021, Calendar.DECEMBER, 10,0,0,0);
		Date date1 = new Date(c1.getTimeInMillis());
		try {
			employee = employeeService.createEmployee(date1, EmployeeStatus.ACTIVE, HOURLYPAY, INEXISTANTEMAIL  , GROCERYAPPID);
			
		}catch (ApiRequestException e) {
			error = e.getMessage();		
		}
		
		assertNull(employee);
		assertEquals("The grocery user with email " + INEXISTANTEMAIL + " does not exist."
                + "Create a grocery user before creating employee.",error);		

	}
	
	//Test to get employee with invalid id 
	@Test
	public void testGetEmployeeWithInvalidId() {
		Employee employee = null;
		String error = null;
		try {
			employee = employeeService.getEmployeeById(5489);
					
			
		}catch (ApiRequestException e) {
			error = e.getMessage();		
		}
		assertNull(employee);
		assertEquals("Employee with id " + 5489 + " does not exist",error);
		
		
		
	}
	
	//Test delete employee by id
	@Test
	public void testDeleteEmployeeByiId() {
		
		try{
			employeeService.deleteEmployeeById(EMPLOYEEID);
			
		} catch(ApiRequestException e) {
			fail();	
		}

	}
	
	//Test delete employee with a nonexistant id
	@Test
	public void testDeleteEmployeeWithNonExistantId() {
		String error = null;
		
		try {
			
			employeeService.deleteEmployeeById(696969);
			
		} catch(ApiRequestException e) {
			error = e.getMessage();
			
		}
		assertEquals(error,"Employee with id " + 696969 + "does not exist");
		
		
	}
	
	/*@Test
	public void testDeleteEmployeeWithEmail() {
		try {
			
			employeeService.deleteEmployeeByEmail("johnny@hotmail.com");
		} catch(ApiRequestException e) {
			
			fail();
			
		}
		
		
	}*/
	
	//Test delete employee with invalid email, grocery user
	@Test
	public void testDeleteEmployeeWithInvalidEmail() {
		String error = null;
		try {
			
			employeeService.deleteEmployeeByEmail("johnnysins@gmail.com");
			
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
		}
		
		assertEquals(error, "Grocery user with email " + "johnnysins@gmail.com" + " does not exist" );
		
		
		
	}
	


	
	//Test for getting all the employees
	@Test
	public void testGetAllEmployees() {
		List<Employee> employees = null;
		employees = employeeService.getAllEmployees();
		assertNotNull(employees);
		
		
		
	}
	
	
	
	
	
	
	

	
	
	

}
