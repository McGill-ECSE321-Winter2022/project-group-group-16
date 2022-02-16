/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStoreApplicationBackend.model;

// line 50 "../../../../../GroceryApplication.ump"
public class Shift
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum WeekDay { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }
  public enum ShiftType { OPENING, CLOSING }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Shift Attributes
  private WeekDay day;
  private ShiftType shiftType;

  //Autounique Attributes
  private int id;

  //Shift Associations
  private Employee employee;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Shift(WeekDay aDay, ShiftType aShiftType, Employee aEmployee)
  {
    day = aDay;
    shiftType = aShiftType;
    id = nextId++;
    boolean didAddEmployee = setEmployee(aEmployee);
    if (!didAddEmployee)
    {
      throw new RuntimeException("Unable to create shift due to employee. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDay(WeekDay aDay)
  {
    boolean wasSet = false;
    day = aDay;
    wasSet = true;
    return wasSet;
  }

  public boolean setShiftType(ShiftType aShiftType)
  {
    boolean wasSet = false;
    shiftType = aShiftType;
    wasSet = true;
    return wasSet;
  }

  public WeekDay getDay()
  {
    return day;
  }

  public ShiftType getShiftType()
  {
    return shiftType;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template association_GetOne */
  public Employee getEmployee()
  {
    return employee;
  }
  /* Code from template association_SetOneToMany */
  public boolean setEmployee(Employee aEmployee)
  {
    boolean wasSet = false;
    if (aEmployee == null)
    {
      return wasSet;
    }

    Employee existingEmployee = employee;
    employee = aEmployee;
    if (existingEmployee != null && !existingEmployee.equals(aEmployee))
    {
      existingEmployee.removeShift(this);
    }
    employee.addShift(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Employee placeholderEmployee = employee;
    this.employee = null;
    if(placeholderEmployee != null)
    {
      placeholderEmployee.removeShift(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "day" + "=" + (getDay() != null ? !getDay().equals(this)  ? getDay().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "shiftType" + "=" + (getShiftType() != null ? !getShiftType().equals(this)  ? getShiftType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "employee = "+(getEmployee()!=null?Integer.toHexString(System.identityHashCode(getEmployee())):"null");
  }
}