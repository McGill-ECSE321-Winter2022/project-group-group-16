/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStoreApplicationBackend.model;
import java.sql.Date;
import java.util.*;

// line 79 "../../../../../GroceryApplication.ump"
public class Order
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum OrderStatus { IN_CART, PLACED, READY_FOR_PICKUP, PICKED_UP, SHIPPED, DELIVERED, CANCELLED, PURCHASED_IN_STORE }
  public enum PurchaseType { ONLINE, IN_STORE }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextOrderID = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private OrderStatus status;
  private Date datePlaced;
  private Date deliveryDate;
  private String customerNote;
  private PurchaseType purchaseType;

  //Autounique Attributes
  private int orderID;

  //Order Associations
  private Customer customer;
  private Address shippingAddress;
  private Address billingAddress;
  private Payment payment;
  private List<CartItem> cartItems;
  private GroceryApplication groceryApplication;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(OrderStatus aStatus, Date aDatePlaced, PurchaseType aPurchaseType, GroceryApplication aGroceryApplication)
  {
    status = aStatus;
    datePlaced = aDatePlaced;
    deliveryDate = null;
    customerNote = null;
    purchaseType = aPurchaseType;
    orderID = nextOrderID++;
    cartItems = new ArrayList<CartItem>();
    boolean didAddGroceryApplication = setGroceryApplication(aGroceryApplication);
    if (!didAddGroceryApplication)
    {
      throw new RuntimeException("Unable to create order due to groceryApplication. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStatus(OrderStatus aStatus)
  {
    boolean wasSet = false;
    status = aStatus;
    wasSet = true;
    return wasSet;
  }

  public boolean setDatePlaced(Date aDatePlaced)
  {
    boolean wasSet = false;
    datePlaced = aDatePlaced;
    wasSet = true;
    return wasSet;
  }

  public boolean setDeliveryDate(Date aDeliveryDate)
  {
    boolean wasSet = false;
    deliveryDate = aDeliveryDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setCustomerNote(String aCustomerNote)
  {
    boolean wasSet = false;
    customerNote = aCustomerNote;
    wasSet = true;
    return wasSet;
  }

  public boolean setPurchaseType(PurchaseType aPurchaseType)
  {
    boolean wasSet = false;
    purchaseType = aPurchaseType;
    wasSet = true;
    return wasSet;
  }

  public OrderStatus getStatus()
  {
    return status;
  }

  public Date getDatePlaced()
  {
    return datePlaced;
  }

  public Date getDeliveryDate()
  {
    return deliveryDate;
  }

  public String getCustomerNote()
  {
    return customerNote;
  }

  public PurchaseType getPurchaseType()
  {
    return purchaseType;
  }

  public int getOrderID()
  {
    return orderID;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }

  public boolean hasCustomer()
  {
    boolean has = customer != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Address getShippingAddress()
  {
    return shippingAddress;
  }

  public boolean hasShippingAddress()
  {
    boolean has = shippingAddress != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Address getBillingAddress()
  {
    return billingAddress;
  }

  public boolean hasBillingAddress()
  {
    boolean has = billingAddress != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Payment getPayment()
  {
    return payment;
  }

  public boolean hasPayment()
  {
    boolean has = payment != null;
    return has;
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
  /* Code from template association_SetOptionalOneToMany */
  public boolean setCustomer(Customer aCustomer)
  {
    boolean wasSet = false;
    Customer existingCustomer = customer;
    customer = aCustomer;
    if (existingCustomer != null && !existingCustomer.equals(aCustomer))
    {
      existingCustomer.removeOrder(this);
    }
    if (aCustomer != null)
    {
      aCustomer.addOrder(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setShippingAddress(Address aNewShippingAddress)
  {
    boolean wasSet = false;
    shippingAddress = aNewShippingAddress;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setBillingAddress(Address aNewBillingAddress)
  {
    boolean wasSet = false;
    billingAddress = aNewBillingAddress;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setPayment(Payment aNewPayment)
  {
    boolean wasSet = false;
    if (payment != null && !payment.equals(aNewPayment) && equals(payment.getOrder()))
    {
      //Unable to setPayment, as existing payment would become an orphan
      return wasSet;
    }

    payment = aNewPayment;
    Order anOldOrder = aNewPayment != null ? aNewPayment.getOrder() : null;

    if (!this.equals(anOldOrder))
    {
      if (anOldOrder != null)
      {
        anOldOrder.payment = null;
      }
      if (payment != null)
      {
        payment.setOrder(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCartItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public CartItem addCartItem(int aCount, Product aProduct)
  {
    return new CartItem(aCount, this, aProduct);
  }

  public boolean addCartItem(CartItem aCartItem)
  {
    boolean wasAdded = false;
    if (cartItems.contains(aCartItem)) { return false; }
    Order existingOrder = aCartItem.getOrder();
    boolean isNewOrder = existingOrder != null && !this.equals(existingOrder);
    if (isNewOrder)
    {
      aCartItem.setOrder(this);
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
    //Unable to remove aCartItem, as it must always have a order
    if (!this.equals(aCartItem.getOrder()))
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
      existingGroceryApplication.removeOrder(this);
    }
    groceryApplication.addOrder(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (customer != null)
    {
      Customer placeholderCustomer = customer;
      this.customer = null;
      placeholderCustomer.removeOrder(this);
    }
    shippingAddress = null;
    billingAddress = null;
    Payment existingPayment = payment;
    payment = null;
    if (existingPayment != null)
    {
      existingPayment.delete();
      existingPayment.setOrder(null);
    }
    for(int i=cartItems.size(); i > 0; i--)
    {
      CartItem aCartItem = cartItems.get(i - 1);
      aCartItem.delete();
    }
    GroceryApplication placeholderGroceryApplication = groceryApplication;
    this.groceryApplication = null;
    if(placeholderGroceryApplication != null)
    {
      placeholderGroceryApplication.removeOrder(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "orderID" + ":" + getOrderID()+ "," +
            "customerNote" + ":" + getCustomerNote()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "status" + "=" + (getStatus() != null ? !getStatus().equals(this)  ? getStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "datePlaced" + "=" + (getDatePlaced() != null ? !getDatePlaced().equals(this)  ? getDatePlaced().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "deliveryDate" + "=" + (getDeliveryDate() != null ? !getDeliveryDate().equals(this)  ? getDeliveryDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "purchaseType" + "=" + (getPurchaseType() != null ? !getPurchaseType().equals(this)  ? getPurchaseType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "shippingAddress = "+(getShippingAddress()!=null?Integer.toHexString(System.identityHashCode(getShippingAddress())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "billingAddress = "+(getBillingAddress()!=null?Integer.toHexString(System.identityHashCode(getBillingAddress())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "payment = "+(getPayment()!=null?Integer.toHexString(System.identityHashCode(getPayment())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "groceryApplication = "+(getGroceryApplication()!=null?Integer.toHexString(System.identityHashCode(getGroceryApplication())):"null");
  }
}