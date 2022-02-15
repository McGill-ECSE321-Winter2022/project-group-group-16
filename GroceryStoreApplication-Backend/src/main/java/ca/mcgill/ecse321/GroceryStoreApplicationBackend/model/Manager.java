/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStoreApplicationBackend.model;
import java.sql.Date;
import java.util.*;

// line 44 "../../../../../GroceryApplication.ump"
public class Manager extends Employee
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Manager(User aUser, Date aHiredDate, EmployeeStatus aStatus, float aHourlyPay, GroceryApplication aGroceryApplication)
  {
    super(aUser, aHiredDate, aStatus, aHourlyPay, aGroceryApplication);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}