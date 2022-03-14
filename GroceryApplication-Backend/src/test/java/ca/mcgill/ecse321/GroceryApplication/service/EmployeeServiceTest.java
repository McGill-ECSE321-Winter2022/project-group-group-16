package ca.mcgill.ecse321.GroceryApplication.service;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;



import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.EmployeeRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryStoreApplicationRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryUserRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Employee;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Employee.EmployeeStatus;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryUser;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;
	
	@Mock
	 private GroceryUserRepository groceryUserRepository;
	
    @Mock
    private GroceryStoreApplicationRepository gsRepository;
	
	@InjectMocks
	private EmployeeService employeeService;
	
	private static final int EMPLOYEEID = 789;
	private static final float HOURLYPAY = 18;
	private static final String EMAIL ="john@gmail.com";
	private static final Integer GROCERYAPPID=453;
	private static final Date DATE = Date.valueOf("2001-03-18");
	private static final EmployeeStatus STATUS = EmployeeStatus.ACTIVE;
	
	
	private static final float NEWHOURLYPAY = 19;
	private static final Date NEWDATE = Date.valueOf("2001-03-16");

	private static final EmployeeStatus NEWSTATUS = EmployeeStatus.BANNED;
	
	private static final float INVALIDHOURLYPAY = -3;
	private static final String INVALIDEMAIL = "danny@hotmail.com";
	private static final Integer INVALIDGROCERYAPPID =42069;
	private static final Integer INVALIDEMPLOYEEID = 420420;
	
	
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
				employee.setStatus(STATUS);
				
				
				
				return employee;
				
			}
			
			
			else {
			return null;
			}
			
			
		});
		
		 lenient().when(groceryUserRepository.findGroceryUserByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {

	            if (invocation.getArgument(0).equals(EMAIL)) {
	            	GroceryUser user = new GroceryUser();
	            	user.setEmail(EMAIL);
	            	return user;          	
	            }	            
	            return null;
	        });
		 
		 lenient().when(gsRepository.findGroceryStoreApplicationById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {

	            if (invocation.getArgument(0).equals(GROCERYAPPID)) {
	                GroceryStoreApplication gsApplication = new GroceryStoreApplication();
	                gsApplication.setId(GROCERYAPPID);
	                return gsApplication;
	                
	            }
	                else {
	                return null;
	            }

	        });		 
		
		
		lenient().when(employeeRepository.findEmployeeByUser(any())).thenAnswer((InvocationOnMock invocation) -> {
			Employee employee = new Employee();
			employee.setId(EMPLOYEEID);
			employee.setHourlyPay(HOURLYPAY);
			employee.setStatus(STATUS);
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
	
	@Test
	public void testCreateEmployee() {
		Employee employee = null;
		
		try {       
			employee = employeeService.createEmployee(DATE, STATUS, HOURLYPAY,EMAIL, GROCERYAPPID);
			
		}catch (Exception e) {
			
			fail();			
		}
		
		assertNotNull(employee);
		assertEquals(employee.getHiredDate(),DATE);
		assertEquals(employee.getStatus(), STATUS);
		assertEquals(employee.getHourlyPay(), HOURLYPAY);
		assertEquals(employee.getGroceryStoreApplication().getId(),GROCERYAPPID);
		assertEquals(employee.getUser().getEmail(),EMAIL);

		
	}
	
	//Test to create employee without date
	@Test
	public void testCreateEmployeeWithoutDate() {
		Employee employee = null;
		String error = null;

		
		try {
			//Date hiredDate, Employee.EmployeeStatus employeeStatus, Float hourlyPay,  String email, Integer groceryStoreApplicationId         
			employee = employeeService.createEmployee(null, STATUS, HOURLYPAY,EMAIL, GROCERYAPPID);
			
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

		try {
			employee = employeeService.createEmployee(DATE, null, HOURLYPAY,EMAIL, GROCERYAPPID);
			
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

		try {
			employee = employeeService.createEmployee(DATE, STATUS, null,EMAIL, GROCERYAPPID);
			
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
		
		try {
			employee = employeeService.createEmployee(DATE, STATUS, INVALIDHOURLYPAY,EMAIL, GROCERYAPPID);
			
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

		try {
			employee = employeeService.createEmployee(DATE, STATUS, HOURLYPAY, INVALIDEMAIL  , GROCERYAPPID);
			
		}catch (ApiRequestException e) {
			error = e.getMessage();		
		}
		
		assertNull(employee);
		assertEquals("The grocery user with email " + INVALIDEMAIL + " does not exist."
                + "Create a grocery user before creating employee.",error);		

	}
	
	//Test to create employee with wrong application id
	@Test
	public void testCreateEmployeeWithWrongAppId() {
		Employee employee = null;
		String error = null;

		try {
			
			employee = employeeService.createEmployee(DATE, STATUS, HOURLYPAY, EMAIL, INVALIDGROCERYAPPID);
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
		}
		
		assertNull(employee);
		assertEquals("Application with id " + INVALIDGROCERYAPPID + " does not exist.",error);
		
		
		
	}
	
	//Test update employee 
	@Test
	public void testUpdateEmployee() {
		Employee employee = null;

		try {
			
			employee = employeeService.updateEmployee(EMPLOYEEID, NEWDATE,  NEWSTATUS, NEWHOURLYPAY , EMAIL, GROCERYAPPID);
			
		} catch(ApiRequestException e) {
			
			fail();
			
			
		}
		assertNotNull(employee);
		assertEquals(employee.getHiredDate(),NEWDATE);
		assertEquals(employee.getStatus(),NEWSTATUS);
		assertEquals(employee.getHourlyPay(),NEWHOURLYPAY);

	}
	
	//Test update employee with wrong id	
	@Test
	public void testUpdateEmployeeWithWrongId() {
		Employee employee = null;
		String error = null;
		
		try {
			
			employee = employeeService.updateEmployee(INVALIDEMPLOYEEID,NEWDATE,NEWSTATUS,NEWHOURLYPAY,EMAIL,GROCERYAPPID);
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
			
		}
		assertNull(employee);
		assertEquals("Employee with id " + INVALIDEMPLOYEEID + "does not exist",error);
	}
	//Test for updating employee with invalid hourly pay
	@Test
	public void testUpdateEmployeeWithInvalidHourlyPay() {
		Employee employee = null;
		String error = null;

		try {
			
			employee = employeeService.updateEmployee(EMPLOYEEID,NEWDATE,NEWSTATUS,INVALIDHOURLYPAY,EMAIL,GROCERYAPPID);
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
			
		}
		assertNull(employee);
		assertEquals("Hourly pay should be larger than 0",error);

		
	}
	
	//Test for updating employee with invalid email
	
	@Test 
	public void testUpdateEmployeeWithInvalidEmail() {
		Employee employee = null;
		String error = null;

		try {
			
			employee = employeeService.updateEmployee(EMPLOYEEID,NEWDATE,NEWSTATUS,HOURLYPAY,INVALIDEMAIL,GROCERYAPPID);
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
			
		}
		assertNull(employee);
		assertEquals("Grocery user with email " + INVALIDEMAIL + " does not exist",error);

	}
	//Test for updating employee with invalid App id
	@Test 
	public void testUpdateEmployeeWithInvalidAppId() {
		Employee employee = null;
		String error = null;

		try {
			
			employee = employeeService.updateEmployee(EMPLOYEEID,NEWDATE,NEWSTATUS,HOURLYPAY,EMAIL,INVALIDGROCERYAPPID);
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
			
		}
		assertNull(employee);
		assertEquals("Application with id " + INVALIDGROCERYAPPID + " does not exist.",error);
		
		
	}

	//Test get employee with id
	@Test
	public void testGetEmployeeWithId() {
		Employee employee = null;
		try {
			employee = employeeService.getEmployeeById(EMPLOYEEID);
			
		} catch(ApiRequestException e) {
			fail();
			
			
		}
		
		assertNotNull(employee);
		assertEquals(EMPLOYEEID,employee.getId());
		
		
	}
	
	//Test to get employee with invalid id 
	@Test
	public void testGetEmployeeWithInvalidId() {
		Employee employee = null;
		String error = null;
		try {
			employee = employeeService.getEmployeeById(INVALIDEMPLOYEEID);
					
			
		}catch (ApiRequestException e) {
			error = e.getMessage();		
		}
		assertNull(employee);
		assertEquals("Employee with id " + INVALIDEMPLOYEEID + " does not exist",error);
		
		
		
	}
	
	//Test get employee with email
	@Test
	public void testGetEmployeeWithEmail() {
		Employee employee = null;
		try {
			
			employee = employeeService.getEmployeeByEmail(EMAIL);
		} catch(ApiRequestException e) {
			
			fail();
			
		}
		assertNotNull(employee);
		assertEquals(EMAIL,employee.getUser().getEmail());
		
		
	}
	
	//Test get employee with invalid email
	@Test
	public void testGetEmployeeWithInexistantEmail() {
		Employee employee = null;
		String error = null;
		try {
			employee = employeeService.getEmployeeByEmail(INVALIDEMAIL);
					
			
		}catch (ApiRequestException e) {
			error = e.getMessage();		
		}
		assertNull(employee);
		assertEquals("Grocery user with email " + INVALIDEMAIL + " does not exist",error);

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
			
			employeeService.deleteEmployeeById(INVALIDEMPLOYEEID );
			
		} catch(ApiRequestException e) {
			error = e.getMessage();
			
		}
		assertEquals(error,"Employee with id " + INVALIDEMPLOYEEID  + "does not exist");
		
		
	}
	
	//Test deleting email
	@Test
	public void testDeleteEmployeeWithEmail() {
		try {
			
			employeeService.deleteEmployeeByEmail(EMAIL);
		} catch(ApiRequestException e) {
			
			fail();
			
		}
		
		
	}
	
	//Test delete employee with invalid email, grocery user
	@Test
	public void testDeleteEmployeeWithInvalidEmail() {
		String error = null;
		try {
			
			employeeService.deleteEmployeeByEmail(INVALIDEMAIL);
			
		} catch(ApiRequestException e) {
			
			error = e.getMessage();
			
		}
		
		assertEquals(error, "Grocery user with email " + INVALIDEMAIL + " does not exist" );
		
		
		
	}
	


	
	//Test for getting all the employees
	@Test
	public void testGetAllEmployees() {
		List<Employee> employees = null;
		employees = employeeService.getAllEmployees();
		assertNotNull(employees);
		
		
		
	}
	
	
	
	
	
	
	

	
	
	

}
