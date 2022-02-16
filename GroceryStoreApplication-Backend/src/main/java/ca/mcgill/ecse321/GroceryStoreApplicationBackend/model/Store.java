/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStoreApplicationBackend.model;
import java.sql.Time;

// line 68 "../../../../../GroceryApplication.ump"
public class Store
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Store Attributes
  private String name;
  private Time weekDayOpening;
  private Time weekDayClosing;
  private Time weekendOpening;
  private Time weekendClosing;

  //Store Associations
  private Address address;
  private GroceryApplication groceryApplication;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Store(String aName, Address aAddress, GroceryApplication aGroceryApplication)
  {
    name = aName;
    weekDayOpening = null;
    weekDayClosing = null;
    weekendOpening = null;
    weekendClosing = null;
    boolean didAddAddress = setAddress(aAddress);
    if (!didAddAddress)
    {
      throw new RuntimeException("Unable to create store due to address. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aGroceryApplication == null || aGroceryApplication.getStore() != null)
    {
      throw new RuntimeException("Unable to create Store due to aGroceryApplication. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    groceryApplication = aGroceryApplication;
  }

  public Store(String aName, Address aAddress)
  {
    name = aName;
    weekDayOpening = null;
    weekDayClosing = null;
    weekendOpening = null;
    weekendClosing = null;
    boolean didAddAddress = setAddress(aAddress);
    if (!didAddAddress)
    {
      throw new RuntimeException("Unable to create store due to address. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    groceryApplication = new GroceryApplication(this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeekDayOpening(Time aWeekDayOpening)
  {
    boolean wasSet = false;
    weekDayOpening = aWeekDayOpening;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeekDayClosing(Time aWeekDayClosing)
  {
    boolean wasSet = false;
    weekDayClosing = aWeekDayClosing;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeekendOpening(Time aWeekendOpening)
  {
    boolean wasSet = false;
    weekendOpening = aWeekendOpening;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeekendClosing(Time aWeekendClosing)
  {
    boolean wasSet = false;
    weekendClosing = aWeekendClosing;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public Time getWeekDayOpening()
  {
    return weekDayOpening;
  }

  public Time getWeekDayClosing()
  {
    return weekDayClosing;
  }

  public Time getWeekendOpening()
  {
    return weekendOpening;
  }

  public Time getWeekendClosing()
  {
    return weekendClosing;
  }
  /* Code from template association_GetOne */
  public Address getAddress()
  {
    return address;
  }
  /* Code from template association_GetOne */
  public GroceryApplication getGroceryApplication()
  {
    return groceryApplication;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setAddress(Address aNewAddress)
  {
    boolean wasSet = false;
    if (aNewAddress == null)
    {
      //Unable to setAddress to null, as store must always be associated to a address
      return wasSet;
    }
    
    Store existingStore = aNewAddress.getStore();
    if (existingStore != null && !equals(existingStore))
    {
      //Unable to setAddress, the current address already has a store, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Address anOldAddress = address;
    address = aNewAddress;
    address.setStore(this);

    if (anOldAddress != null)
    {
      anOldAddress.setStore(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Address existingAddress = address;
    address = null;
    if (existingAddress != null)
    {
      existingAddress.delete();
    }
    GroceryApplication existingGroceryApplication = groceryApplication;
    groceryApplication = null;
    if (existingGroceryApplication != null)
    {
      existingGroceryApplication.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "weekDayOpening" + "=" + (getWeekDayOpening() != null ? !getWeekDayOpening().equals(this)  ? getWeekDayOpening().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "weekDayClosing" + "=" + (getWeekDayClosing() != null ? !getWeekDayClosing().equals(this)  ? getWeekDayClosing().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "weekendOpening" + "=" + (getWeekendOpening() != null ? !getWeekendOpening().equals(this)  ? getWeekendOpening().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "weekendClosing" + "=" + (getWeekendClosing() != null ? !getWeekendClosing().equals(this)  ? getWeekendClosing().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "address = "+(getAddress()!=null?Integer.toHexString(System.identityHashCode(getAddress())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "groceryApplication = "+(getGroceryApplication()!=null?Integer.toHexString(System.identityHashCode(getGroceryApplication())):"null");
  }
}