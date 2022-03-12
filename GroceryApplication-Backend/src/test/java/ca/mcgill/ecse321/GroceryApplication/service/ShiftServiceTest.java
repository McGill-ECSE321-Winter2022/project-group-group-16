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

import ca.mcgill.ecse321.GroceryApplicationBackend.dao.EmployeeRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.ShiftRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Employee;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Shift;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.ShiftService;

@ExtendWith(MockitoExtension.class)
public class ShiftServiceTest {
	
	@Mock
	private ShiftRepository shiftRepository;
	
	@Mock 
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private ShiftService shiftService;
	
	private static final int SHIFTID = 180;
	private static final Shift.Day DAY= Shift.Day.FRIDAY;
	private static final Shift.ShiftType SHIFTTYPE = Shift.ShiftType.CLOSING;
	private static final Integer EMPLOYEEID = 6969;
	
	
	private static final int SHIFTID2 =181;
	
	
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(shiftRepository.findShiftById(anyInt())).thenAnswer((InvocationOnMock invocation ) -> {
			if(invocation.getArgument(0).equals(SHIFTID)) {
				Shift shift = new Shift();
				Employee employee = new Employee();
				employee.setId(EMPLOYEEID);
				shift.setEmployee(employee);
				shift.setId(SHIFTID);
				shift.setDay(DAY);
				shift.setShift(SHIFTTYPE);
				//need to set employee as well
				return shift;
				
			}
			
			else if(invocation.getArgument(0).equals(SHIFTID2)) {
				
				Shift shift = new Shift();
				shift.setId(SHIFTID2);
				shift.setDay(Shift.Day.THURSDAY);
				shift.setShift(Shift.ShiftType.OPENING);
				return shift;
			}else {
	
			return null;
			}
			
		});
		
	}
	
	
	//Test for creating a shift
	/*@Test
	public void testCreateShift() {
		
		Shift shift = null;
		
		try {
			shift = shiftService.createShift(DAY, SHIFTTYPE, EMPLOYEEID);
			
			
		}catch(ApiRequestException e) {
			
			fail();
			
		}
		
		assertNotNull(shift);
		assertEquals(shift.getDay(), Shift.Day.MONDAY);
		assertEquals(shift.getShift(),Shift.ShiftType.OPENING);
		assertEquals(shift.getEmployee().getId(), EMPLOYEEID);
		
		
		
		
	}*/
	
	//Test creating shift without a day
	@Test
	public void testCreateShiftWithoutDay() {

		Shift shift = null;
		String error = null;
		
		try {
			
			shift = shiftService.createShift(null, SHIFTTYPE, EMPLOYEEID);
			
		}catch(ApiRequestException e) {
			error = e.getMessage();

		}
		
		assertNull(shift);
		assertEquals("Day is null",error);
	}
	//Test for creating shift without shift type
	@Test 
	public void testCreateShiftWithoutShiftType() {
		Shift shift = null;
		String error = null;
		
		try {
			
			shift=shiftService.createShift(DAY, null, EMPLOYEEID);
			
			
		}catch(ApiRequestException e) {
			error = e.getMessage();

		}
		assertNull(shift);
		assertEquals("Shift type is null",error);
		
		
	}
	
	//Test for creating shift with wrong employeeID	
	@Test
	public void testCreateShiftWithWrongEmployeeId() {
		Shift shift = null;
		String error = null;
		try {
			
			shift=shiftService.createShift(DAY, SHIFTTYPE, 123);
			
		}catch(ApiRequestException e) {
			error = e.getMessage();
			
			
		}		
		assertNull(shift);
		assertEquals("Employee with id " + 123 + " does not exist",error);
				
	}
	
	
	//Test for creating shift without employee ID
	@Test
	public void testCreateShiftWithoutEmployeeId() {
		Shift shift = null;
		String error = null;
		
		try {
			
			shift=shiftService.createShift(DAY, SHIFTTYPE, null);
			
		}catch(ApiRequestException e) {
			error = e.getMessage();
			
			
		}		
		assertNull(shift);
		assertEquals("Employee Id is null",error);
	
	}
	
	
	//Test for getting shift with Id
	
	@Test
	public void testGetShiftWithId() {
		Shift shift = null;
		try {
			
			shift = shiftService.getShift(SHIFTID);
		} catch(ApiRequestException e) {
			
			fail();
			
		}
		
		assertNotNull(shift);
		assertEquals(SHIFTID, shift.getId());
		
	}
	
	//Test for getting shift with nonexistant Id
	@Test
	public void testGetShiftWithNonexistantId() {
		Shift shift = null;
		String error = null;
		
		try {
			shift= shiftService.getShift(456);
			
		}catch(ApiRequestException e) {
			
			error = e.getMessage();
			
			
		}		
		assertNull(shift);
		assertEquals("Shift with id " + 456 + " does not exist",error);
		
		
		
	}
	
	
	

	//Test for updating shift with an Id that doesn't exists
	
	@Test
	public void testUpdateShiftWithWrongId() {
		Shift shift = null;
		String error = null;
		
		try {
			shift= shiftService.updateShift(23, DAY, SHIFTTYPE, EMPLOYEEID);
			
		}catch(ApiRequestException e) {
			
			error = e.getMessage();
			
			
		}
		
		assertNull(shift);
		assertEquals("Shift with id " + 23 + " does not exist",error);
		
	}
	
	//Test for updating shift with an employeeId that doesn't exists
	@Test
	public void testUpdateShiftWithWrongEmployeeId() {
		
		Shift shift = null;
		String error = null;
		
		try {
			shift= shiftService.updateShift(SHIFTID, DAY, SHIFTTYPE, 45);
			
		}catch(ApiRequestException e) {
			
			error = e.getMessage();
			
			
		}
		assertNull(shift);
		assertEquals("Employee with id " + 45 + " does not exist",error);

	}
	
	
	
	
	
	
	
	//Test for  finding all shift	
	@Test
	public void testGetAllShift() {
		List<Shift> shifts = null;
		shifts = shiftService.getAllShifts();
		assertNotNull(shifts);
		
		
	}
	
	
	

	
	
	
	
	
	
	
	
	
	

}
