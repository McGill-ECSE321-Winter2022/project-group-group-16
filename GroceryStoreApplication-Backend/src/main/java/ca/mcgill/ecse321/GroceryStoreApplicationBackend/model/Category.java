/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStoreApplicationBackend.model;
import java.util.*;

// line 125 "../../../../../GroceryApplication.ump"
public class Category
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Category Attributes
  private String description;
  private String name;
  private String image;

  //Autounique Attributes
  private int id;

  //Category Associations
  private List<Product> products;
  private GroceryApplication groceryApplication;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Category(String aName, String aImage, GroceryApplication aGroceryApplication)
  {
    description = " ";
    name = aName;
    image = aImage;
    id = nextId++;
    products = new ArrayList<Product>();
    boolean didAddGroceryApplication = setGroceryApplication(aGroceryApplication);
    if (!didAddGroceryApplication)
    {
      throw new RuntimeException("Unable to create category due to groceryApplication. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setImage(String aImage)
  {
    boolean wasSet = false;
    image = aImage;
    wasSet = true;
    return wasSet;
  }

  public String getDescription()
  {
    return description;
  }

  public String getName()
  {
    return name;
  }

  public String getImage()
  {
    return image;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template association_GetMany */
  public Product getProduct(int index)
  {
    Product aProduct = products.get(index);
    return aProduct;
  }

  public List<Product> getProducts()
  {
    List<Product> newProducts = Collections.unmodifiableList(products);
    return newProducts;
  }

  public int numberOfProducts()
  {
    int number = products.size();
    return number;
  }

  public boolean hasProducts()
  {
    boolean has = products.size() > 0;
    return has;
  }

  public int indexOfProduct(Product aProduct)
  {
    int index = products.indexOf(aProduct);
    return index;
  }
  /* Code from template association_GetOne */
  public GroceryApplication getGroceryApplication()
  {
    return groceryApplication;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfProducts()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Product addProduct(String aName, float aPrice, String aImage, Product.Availability aAvailability, boolean aIsRefundable, int aAvailableQuantity, GroceryApplication aGroceryApplication)
  {
    return new Product(aName, aPrice, aImage, aAvailability, aIsRefundable, aAvailableQuantity, aGroceryApplication, this);
  }

  public boolean addProduct(Product aProduct)
  {
    boolean wasAdded = false;
    if (products.contains(aProduct)) { return false; }
    Category existingCategory = aProduct.getCategory();
    boolean isNewCategory = existingCategory != null && !this.equals(existingCategory);
    if (isNewCategory)
    {
      aProduct.setCategory(this);
    }
    else
    {
      products.add(aProduct);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeProduct(Product aProduct)
  {
    boolean wasRemoved = false;
    //Unable to remove aProduct, as it must always have a category
    if (!this.equals(aProduct.getCategory()))
    {
      products.remove(aProduct);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addProductAt(Product aProduct, int index)
  {  
    boolean wasAdded = false;
    if(addProduct(aProduct))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfProducts()) { index = numberOfProducts() - 1; }
      products.remove(aProduct);
      products.add(index, aProduct);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveProductAt(Product aProduct, int index)
  {
    boolean wasAdded = false;
    if(products.contains(aProduct))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfProducts()) { index = numberOfProducts() - 1; }
      products.remove(aProduct);
      products.add(index, aProduct);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addProductAt(aProduct, index);
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
      existingGroceryApplication.removeCategory(this);
    }
    groceryApplication.addCategory(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=products.size(); i > 0; i--)
    {
      Product aProduct = products.get(i - 1);
      aProduct.delete();
    }
    GroceryApplication placeholderGroceryApplication = groceryApplication;
    this.groceryApplication = null;
    if(placeholderGroceryApplication != null)
    {
      placeholderGroceryApplication.removeCategory(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "description" + ":" + getDescription()+ "," +
            "name" + ":" + getName()+ "," +
            "image" + ":" + getImage()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "groceryApplication = "+(getGroceryApplication()!=null?Integer.toHexString(System.identityHashCode(getGroceryApplication())):"null");
  }
}