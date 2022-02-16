/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStoreApplicationBackend.model;
import java.util.*;

// line 106 "../../../../../GroceryApplication.ump"
public class Product
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Availability { PICK_UP, IN_STORE, DELIVERY, UNRESTRICTED }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextBarcode = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Product Attributes
  private String name;
  private String description;
  private float price;
  private String image;
  private float weight;
  private float volume;
  private Availability availability;
  private boolean isRefundable;
  private int availableQuantity;

  //Autounique Attributes
  private int barcode;

  //Product Associations
  private List<CartItem> cartItems;
  private GroceryApplication groceryApplication;
  private Category category;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Product(String aName, float aPrice, String aImage, Availability aAvailability, boolean aIsRefundable, int aAvailableQuantity, GroceryApplication aGroceryApplication, Category aCategory)
  {
    name = aName;
    description = " ";
    price = aPrice;
    image = aImage;
    weight = 0.0f;
    volume = 0.0f;
    availability = aAvailability;
    isRefundable = aIsRefundable;
    availableQuantity = aAvailableQuantity;
    barcode = nextBarcode++;
    cartItems = new ArrayList<CartItem>();
    boolean didAddGroceryApplication = setGroceryApplication(aGroceryApplication);
    if (!didAddGroceryApplication)
    {
      throw new RuntimeException("Unable to create product due to groceryApplication. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddCategory = setCategory(aCategory);
    if (!didAddCategory)
    {
      throw new RuntimeException("Unable to create product due to category. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(float aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
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

  public boolean setWeight(float aWeight)
  {
    boolean wasSet = false;
    weight = aWeight;
    wasSet = true;
    return wasSet;
  }

  public boolean setVolume(float aVolume)
  {
    boolean wasSet = false;
    volume = aVolume;
    wasSet = true;
    return wasSet;
  }

  public boolean setAvailability(Availability aAvailability)
  {
    boolean wasSet = false;
    availability = aAvailability;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsRefundable(boolean aIsRefundable)
  {
    boolean wasSet = false;
    isRefundable = aIsRefundable;
    wasSet = true;
    return wasSet;
  }

  public boolean setAvailableQuantity(int aAvailableQuantity)
  {
    boolean wasSet = false;
    availableQuantity = aAvailableQuantity;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getDescription()
  {
    return description;
  }

  public float getPrice()
  {
    return price;
  }

  public String getImage()
  {
    return image;
  }

  public float getWeight()
  {
    return weight;
  }

  public float getVolume()
  {
    return volume;
  }

  public Availability getAvailability()
  {
    return availability;
  }

  public boolean getIsRefundable()
  {
    return isRefundable;
  }

  public int getAvailableQuantity()
  {
    return availableQuantity;
  }

  public int getBarcode()
  {
    return barcode;
  }
  /* Code from template association_GetMany */
  public CartItem getCartItem(int index)
  {
    CartItem aCartItem = cartItems.get(index);
    return aCartItem;
  }

  public List<CartItem> getCartItems()
  {
    List<CartItem> newCartItems = Collections.unmodifiableList(cartItems);
    return newCartItems;
  }

  public int numberOfCartItems()
  {
    int number = cartItems.size();
    return number;
  }

  public boolean hasCartItems()
  {
    boolean has = cartItems.size() > 0;
    return has;
  }

  public int indexOfCartItem(CartItem aCartItem)
  {
    int index = cartItems.indexOf(aCartItem);
    return index;
  }
  /* Code from template association_GetOne */
  public GroceryApplication getGroceryApplication()
  {
    return groceryApplication;
  }
  /* Code from template association_GetOne */
  public Category getCategory()
  {
    return category;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCartItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public CartItem addCartItem(int aCount, Order aOrder)
  {
    return new CartItem(aCount, aOrder, this);
  }

  public boolean addCartItem(CartItem aCartItem)
  {
    boolean wasAdded = false;
    if (cartItems.contains(aCartItem)) { return false; }
    Product existingProduct = aCartItem.getProduct();
    boolean isNewProduct = existingProduct != null && !this.equals(existingProduct);
    if (isNewProduct)
    {
      aCartItem.setProduct(this);
    }
    else
    {
      cartItems.add(aCartItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCartItem(CartItem aCartItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aCartItem, as it must always have a product
    if (!this.equals(aCartItem.getProduct()))
    {
      cartItems.remove(aCartItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCartItemAt(CartItem aCartItem, int index)
  {  
    boolean wasAdded = false;
    if(addCartItem(aCartItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCartItems()) { index = numberOfCartItems() - 1; }
      cartItems.remove(aCartItem);
      cartItems.add(index, aCartItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCartItemAt(CartItem aCartItem, int index)
  {
    boolean wasAdded = false;
    if(cartItems.contains(aCartItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCartItems()) { index = numberOfCartItems() - 1; }
      cartItems.remove(aCartItem);
      cartItems.add(index, aCartItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCartItemAt(aCartItem, index);
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
      existingGroceryApplication.removeProduct(this);
    }
    groceryApplication.addProduct(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCategory(Category aCategory)
  {
    boolean wasSet = false;
    if (aCategory == null)
    {
      return wasSet;
    }

    Category existingCategory = category;
    category = aCategory;
    if (existingCategory != null && !existingCategory.equals(aCategory))
    {
      existingCategory.removeProduct(this);
    }
    category.addProduct(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (cartItems.size() > 0)
    {
      CartItem aCartItem = cartItems.get(cartItems.size() - 1);
      aCartItem.delete();
      cartItems.remove(aCartItem);
    }
    
    GroceryApplication placeholderGroceryApplication = groceryApplication;
    this.groceryApplication = null;
    if(placeholderGroceryApplication != null)
    {
      placeholderGroceryApplication.removeProduct(this);
    }
    Category placeholderCategory = category;
    this.category = null;
    if(placeholderCategory != null)
    {
      placeholderCategory.removeProduct(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "barcode" + ":" + getBarcode()+ "," +
            "name" + ":" + getName()+ "," +
            "description" + ":" + getDescription()+ "," +
            "price" + ":" + getPrice()+ "," +
            "image" + ":" + getImage()+ "," +
            "weight" + ":" + getWeight()+ "," +
            "volume" + ":" + getVolume()+ "," +
            "isRefundable" + ":" + getIsRefundable()+ "," +
            "availableQuantity" + ":" + getAvailableQuantity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "availability" + "=" + (getAvailability() != null ? !getAvailability().equals(this)  ? getAvailability().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "groceryApplication = "+(getGroceryApplication()!=null?Integer.toHexString(System.identityHashCode(getGroceryApplication())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "category = "+(getCategory()!=null?Integer.toHexString(System.identityHashCode(getCategory())):"null");
  }
}