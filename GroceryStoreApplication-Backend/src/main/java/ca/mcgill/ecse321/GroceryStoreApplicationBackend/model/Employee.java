/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStoreApplicationBackend.model;
import java.sql.Date;
import java.util.*;

// line 36 "../../../../../GroceryApplication.ump"
public class Employee extends UserRole
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum EmployeeStatus { ACTIVE, BANNED, QUIT }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Employee Attributes
  private Date hiredDate;
  private EmployeeStatus status;
  private float hourlyPay;

  //Employee Associations
  private List<Shift> shifts;
  private GroceryApplication groceryApplication;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Employee(User aUser, Date aHiredDate, EmployeeStatus aStatus, float aHourlyPay, GroceryApplication aGroceryApplication)
  {
    super(aUser);
    hiredDate = aHiredDate;
    status = aStatus;
    hourlyPay = aHourlyPay;
    shifts = new ArrayList<Shift>();
    boolean didAddGroceryApplication = setGroceryApplication(aGroceryApplication);
    if (!didAddGroceryApplication)
    {
      throw new RuntimeException("Unable to create employee due to groceryApplication. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setHiredDate(Date aHiredDate)
  {
    boolean wasSet = false;
    hiredDate = aHiredDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setStatus(EmployeeStatus aStatus)
  {
    boolean wasSet = false;
    status = aStatus;
    wasSet = true;
    return wasSet;
  }

  public boolean setHourlyPay(float aHourlyPay)
  {
    boolean wasSet = false;
    hourlyPay = aHourlyPay;
    wasSet = true;
    return wasSet;
  }

  public Date getHiredDate()
  {
    return hiredDate;
  }

  public EmployeeStatus getStatus()
  {
    return status;
  }

  public float getHourlyPay()
  {
    return hourlyPay;
  }
  /* Code from template association_GetMany */
  public Shift getShift(int index)
  {
    Shift aShift = shifts.get(index);
    return aShift;
  }

  public List<Shift> getShifts()
  {
    List<Shift> newShifts = Collections.unmodifiableList(shifts);
    return newShifts;
  }

  public int numberOfShifts()
  {
    int number = shifts.size();
    return number;
  }

  public boolean hasShifts()
  {
    boolean has = shifts.size() > 0;
    return has;
  }

  public int indexOfShift(Shift aShift)
  {
    int index = shifts.indexOf(aShift);
    return index;
  }
  /* Code from template association_GetOne */
  public GroceryApplication getGroceryApplication()
  {
    return groceryApplication;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfShifts()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Shift addShift(Shift.WeekDay aDay, Shift.ShiftType aShiftType)
  {
    return new Shift(aDay, aShiftType, this);
  }

  public boolean addShift(Shift aShift)
  {
    boolean wasAdded = false;
    if (shifts.contains(aShift)) { return false; }
    Employee existingEmployee = aShift.getEmployee();
    boolean isNewEmployee = existingEmployee != null && !this.equals(existingEmployee);
    if (isNewEmployee)
    {
      aShift.setEmployee(this);
    }
    else
    {
      shifts.add(aShift);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeShift(Shift aShift)
  {
    boolean wasRemoved = false;
    //Unable to remove aShift, as it must always have a employee
    if (!this.equals(aShift.getEmployee()))
    {
      shifts.remove(aShift);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addShiftAt(Shift aShift, int index)
  {  
    boolean wasAdded = false;
    if(addShift(aShift))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShifts()) { index = numberOfShifts() - 1; }
      shifts.remove(aShift);
      shifts.add(index, aShift);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveShiftAt(Shift aShift, int index)
  {
    boolean wasAdded = false;
    if(shifts.contains(aShift))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShifts()) { index = numberOfShifts() - 1; }
      shifts.remove(aShift);
      shifts.add(index, aShift);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addShiftAt(aShift, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGroceryApplication(GroceryApplication aGroceryApplication)
  {
    boolean wasSet = false;
    if (aGroceryApplication == null)
    {
      return wasSet;
    }

    GroceryApplication existingGroceryApplication = groceryApplication;
    groceryApplication = aGroceryApplication;
    if (existingGroceryApplication != null && !existingGroceryApplication.equals(aGroceryApplication))
    {
      existingGroceryApplication.removeEmployee(this);
    }
    groceryApplication.addEmployee(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (shifts.size() > 0)
    {
      Shift aShift = shifts.get(shifts.size() - 1);
      aShift.delete();
      shifts.remove(aShift);
    }
    
    GroceryApplication placeholderGroceryApplication = groceryApplication;
    this.groceryApplication = null;
    if(placeholderGroceryApplication != null)
    {
      placeholderGroceryApplication.removeEmployee(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "hourlyPay" + ":" + getHourlyPay()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "hiredDate" + "=" + (getHiredDate() != null ? !getHiredDate().equals(this)  ? getHiredDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "status" + "=" + (getStatus() != null ? !getStatus().equals(this)  ? getStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "groceryApplication = "+(getGroceryApplication()!=null?Integer.toHexString(System.identityHashCode(getGroceryApplication())):"null");
  }
}