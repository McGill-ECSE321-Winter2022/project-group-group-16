/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStoreApplicationBackend.model;
import java.util.*;
import java.sql.Date;

// line 15 "../../../../../GroceryApplication.ump"
public class User
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, User> usersByEmail = new HashMap<String, User>();
  private static Map<String, User> usersByUsername = new HashMap<String, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String email;
  private String password;
  private String username;
  private String firstName;
  private String lastName;
  private Date dateOfBirth;

  //User Associations
  private List<UserRole> userRoles;
  private GroceryApplication groceryApplication;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aEmail, String aPassword, String aUsername, String aFirstName, String aLastName, Date aDateOfBirth, GroceryApplication aGroceryApplication)
  {
    password = aPassword;
    firstName = aFirstName;
    lastName = aLastName;
    dateOfBirth = aDateOfBirth;
    if (!setEmail(aEmail))
    {
      throw new RuntimeException("Cannot create due to duplicate email. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    if (!setUsername(aUsername))
    {
      throw new RuntimeException("Cannot create due to duplicate username. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    userRoles = new ArrayList<UserRole>();
    boolean didAddGroceryApplication = setGroceryApplication(aGroceryApplication);
    if (!didAddGroceryApplication)
    {
      throw new RuntimeException("Unable to create user due to groceryApplication. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    String anOldEmail = getEmail();
    if (anOldEmail != null && anOldEmail.equals(aEmail)) {
      return true;
    }
    if (hasWithEmail(aEmail)) {
      return wasSet;
    }
    email = aEmail;
    wasSet = true;
    if (anOldEmail != null) {
      usersByEmail.remove(anOldEmail);
    }
    usersByEmail.put(aEmail, this);
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    String anOldUsername = getUsername();
    if (anOldUsername != null && anOldUsername.equals(aUsername)) {
      return true;
    }
    if (hasWithUsername(aUsername)) {
      return wasSet;
    }
    username = aUsername;
    wasSet = true;
    if (anOldUsername != null) {
      usersByUsername.remove(anOldUsername);
    }
    usersByUsername.put(aUsername, this);
    return wasSet;
  }

  public boolean setFirstName(String aFirstName)
  {
    boolean wasSet = false;
    firstName = aFirstName;
    wasSet = true;
    return wasSet;
  }

  public boolean setLastName(String aLastName)
  {
    boolean wasSet = false;
    lastName = aLastName;
    wasSet = true;
    return wasSet;
  }

  public boolean setDateOfBirth(Date aDateOfBirth)
  {
    boolean wasSet = false;
    dateOfBirth = aDateOfBirth;
    wasSet = true;
    return wasSet;
  }

  public String getEmail()
  {
    return email;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithEmail(String aEmail)
  {
    return usersByEmail.get(aEmail);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithEmail(String aEmail)
  {
    return getWithEmail(aEmail) != null;
  }

  public String getPassword()
  {
    return password;
  }

  public String getUsername()
  {
    return username;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithUsername(String aUsername)
  {
    return usersByUsername.get(aUsername);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithUsername(String aUsername)
  {
    return getWithUsername(aUsername) != null;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public Date getDateOfBirth()
  {
    return dateOfBirth;
  }
  /* Code from template association_GetMany */
  public UserRole getUserRole(int index)
  {
    UserRole aUserRole = userRoles.get(index);
    return aUserRole;
  }

  public List<UserRole> getUserRoles()
  {
    List<UserRole> newUserRoles = Collections.unmodifiableList(userRoles);
    return newUserRoles;
  }

  public int numberOfUserRoles()
  {
    int number = userRoles.size();
    return number;
  }

  public boolean hasUserRoles()
  {
    boolean has = userRoles.size() > 0;
    return has;
  }

  public int indexOfUserRole(UserRole aUserRole)
  {
    int index = userRoles.indexOf(aUserRole);
    return index;
  }
  /* Code from template association_GetOne */
  public GroceryApplication getGroceryApplication()
  {
    return groceryApplication;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUserRoles()
  {
    return 0;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfUserRoles()
  {
    return 3;
  }
  /* Code from template association_AddOptionalNToOne */


  public boolean addUserRole(UserRole aUserRole)
  {
    boolean wasAdded = false;
    if (userRoles.contains(aUserRole)) { return false; }
    if (numberOfUserRoles() >= maximumNumberOfUserRoles())
    {
      return wasAdded;
    }

    User existingUser = aUserRole.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);
    if (isNewUser)
    {
      aUserRole.setUser(this);
    }
    else
    {
      userRoles.add(aUserRole);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUserRole(UserRole aUserRole)
  {
    boolean wasRemoved = false;
    //Unable to remove aUserRole, as it must always have a user
    if (!this.equals(aUserRole.getUser()))
    {
      userRoles.remove(aUserRole);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserRoleAt(UserRole aUserRole, int index)
  {  
    boolean wasAdded = false;
    if(addUserRole(aUserRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserRoles()) { index = numberOfUserRoles() - 1; }
      userRoles.remove(aUserRole);
      userRoles.add(index, aUserRole);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserRoleAt(UserRole aUserRole, int index)
  {
    boolean wasAdded = false;
    if(userRoles.contains(aUserRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserRoles()) { index = numberOfUserRoles() - 1; }
      userRoles.remove(aUserRole);
      userRoles.add(index, aUserRole);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserRoleAt(aUserRole, index);
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
      existingGroceryApplication.removeUser(this);
    }
    groceryApplication.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    usersByEmail.remove(getEmail());
    usersByUsername.remove(getUsername());
    for(int i=userRoles.size(); i > 0; i--)
    {
      UserRole aUserRole = userRoles.get(i - 1);
      aUserRole.delete();
    }
    GroceryApplication placeholderGroceryApplication = groceryApplication;
    this.groceryApplication = null;
    if(placeholderGroceryApplication != null)
    {
      placeholderGroceryApplication.removeUser(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "email" + ":" + getEmail()+ "," +
            "password" + ":" + getPassword()+ "," +
            "username" + ":" + getUsername()+ "," +
            "firstName" + ":" + getFirstName()+ "," +
            "lastName" + ":" + getLastName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "dateOfBirth" + "=" + (getDateOfBirth() != null ? !getDateOfBirth().equals(this)  ? getDateOfBirth().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "groceryApplication = "+(getGroceryApplication()!=null?Integer.toHexString(System.identityHashCode(getGroceryApplication())):"null");
  }
}