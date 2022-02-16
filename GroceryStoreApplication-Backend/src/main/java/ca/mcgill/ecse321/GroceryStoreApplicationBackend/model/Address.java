/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStoreApplicationBackend.model;
import java.util.*;
import java.sql.Time;

// line 58 "../../../../../GroceryApplication.ump"
public class Address
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Address Attributes
  private int streetNumber;
  private String streetName;
  private String city;
  private String province;
  private String country;
  private String postalCode;

  //Autounique Attributes
  private int id;

  //Address Associations
  private List<Customer> customers;
  private Store store;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Address(int aStreetNumber, String aStreetName, String aCity, String aProvince, String aCountry, String aPostalCode, Store aStore)
  {
    streetNumber = aStreetNumber;
    streetName = aStreetName;
    city = aCity;
    province = aProvince;
    country = aCountry;
    postalCode = aPostalCode;
    id = nextId++;
    customers = new ArrayList<Customer>();
    if (aStore == null || aStore.getAddress() != null)
    {
      throw new RuntimeException("Unable to create Address due to aStore. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    store = aStore;
  }

  public Address(int aStreetNumber, String aStreetName, String aCity, String aProvince, String aCountry, String aPostalCode, String aNameForStore, GroceryApplication aGroceryApplicationForStore)
  {
    streetNumber = aStreetNumber;
    streetName = aStreetName;
    city = aCity;
    province = aProvince;
    country = aCountry;
    postalCode = aPostalCode;
    id = nextId++;
    customers = new ArrayList<Customer>();
    store = new Store(aNameForStore, this, aGroceryApplicationForStore);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStreetNumber(int aStreetNumber)
  {
    boolean wasSet = false;
    streetNumber = aStreetNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setStreetName(String aStreetName)
  {
    boolean wasSet = false;
    streetName = aStreetName;
    wasSet = true;
    return wasSet;
  }

  public boolean setCity(String aCity)
  {
    boolean wasSet = false;
    city = aCity;
    wasSet = true;
    return wasSet;
  }

  public boolean setProvince(String aProvince)
  {
    boolean wasSet = false;
    province = aProvince;
    wasSet = true;
    return wasSet;
  }

  public boolean setCountry(String aCountry)
  {
    boolean wasSet = false;
    country = aCountry;
    wasSet = true;
    return wasSet;
  }

  public boolean setPostalCode(String aPostalCode)
  {
    boolean wasSet = false;
    postalCode = aPostalCode;
    wasSet = true;
    return wasSet;
  }

  public int getStreetNumber()
  {
    return streetNumber;
  }

  public String getStreetName()
  {
    return streetName;
  }

  public String getCity()
  {
    return city;
  }

  public String getProvince()
  {
    return province;
  }

  public String getCountry()
  {
    return country;
  }

  public String getPostalCode()
  {
    return postalCode;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template association_GetMany */
  public Customer getCustomer(int index)
  {
    Customer aCustomer = customers.get(index);
    return aCustomer;
  }

  public List<Customer> getCustomers()
  {
    List<Customer> newCustomers = Collections.unmodifiableList(customers);
    return newCustomers;
  }

  public int numberOfCustomers()
  {
    int number = customers.size();
    return number;
  }

  public boolean hasCustomers()
  {
    boolean has = customers.size() > 0;
    return has;
  }

  public int indexOfCustomer(Customer aCustomer)
  {
    int index = customers.indexOf(aCustomer);
    return index;
  }
  /* Code from template association_GetOne */
  public Store getStore()
  {
    return store;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCustomers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Customer addCustomer(User aUser, GroceryApplication aGroceryApplication)
  {
    return new Customer(aUser, this, aGroceryApplication);
  }

  public boolean addCustomer(Customer aCustomer)
  {
    boolean wasAdded = false;
    if (customers.contains(aCustomer)) { return false; }
    Address existingAddress = aCustomer.getAddress();
    boolean isNewAddress = existingAddress != null && !this.equals(existingAddress);
    if (isNewAddress)
    {
      aCustomer.setAddress(this);
    }
    else
    {
      customers.add(aCustomer);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCustomer(Customer aCustomer)
  {
    boolean wasRemoved = false;
    //Unable to remove aCustomer, as it must always have a address
    if (!this.equals(aCustomer.getAddress()))
    {
      customers.remove(aCustomer);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCustomerAt(Customer aCustomer, int index)
  {  
    boolean wasAdded = false;
    if(addCustomer(aCustomer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCustomers()) { index = numberOfCustomers() - 1; }
      customers.remove(aCustomer);
      customers.add(index, aCustomer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCustomerAt(Customer aCustomer, int index)
  {
    boolean wasAdded = false;
    if(customers.contains(aCustomer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCustomers()) { index = numberOfCustomers() - 1; }
      customers.remove(aCustomer);
      customers.add(index, aCustomer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCustomerAt(aCustomer, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (customers.size() > 0)
    {
      Customer aCustomer = customers.get(customers.size() - 1);
      aCustomer.delete();
      customers.remove(aCustomer);
    }
    
    Store existingStore = store;
    store = null;
    if (existingStore != null)
    {
      existingStore.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "streetNumber" + ":" + getStreetNumber()+ "," +
            "streetName" + ":" + getStreetName()+ "," +
            "city" + ":" + getCity()+ "," +
            "province" + ":" + getProvince()+ "," +
            "country" + ":" + getCountry()+ "," +
            "postalCode" + ":" + getPostalCode()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "store = "+(getStore()!=null?Integer.toHexString(System.identityHashCode(getStore())):"null");
  }
}