/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStoreApplicationBackend.model;

// line 101 "../../../../../GroceryApplication.ump"
public class CartItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CartItem Attributes
  private int count;

  //CartItem Associations
  private Order order;
  private Product product;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CartItem(int aCount, Order aOrder, Product aProduct)
  {
    count = aCount;
    boolean didAddOrder = setOrder(aOrder);
    if (!didAddOrder)
    {
      throw new RuntimeException("Unable to create cartItem due to order. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddProduct = setProduct(aProduct);
    if (!didAddProduct)
    {
      throw new RuntimeException("Unable to create cartItem due to product. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCount(int aCount)
  {
    boolean wasSet = false;
    count = aCount;
    wasSet = true;
    return wasSet;
  }

  public int getCount()
  {
    return count;
  }
  /* Code from template association_GetOne */
  public Order getOrder()
  {
    return order;
  }
  /* Code from template association_GetOne */
  public Product getProduct()
  {
    return product;
  }
  /* Code from template association_SetOneToMany */
  public boolean setOrder(Order aOrder)
  {
    boolean wasSet = false;
    if (aOrder == null)
    {
      return wasSet;
    }

    Order existingOrder = order;
    order = aOrder;
    if (existingOrder != null && !existingOrder.equals(aOrder))
    {
      existingOrder.removeCartItem(this);
    }
    order.addCartItem(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setProduct(Product aProduct)
  {
    boolean wasSet = false;
    if (aProduct == null)
    {
      return wasSet;
    }

    Product existingProduct = product;
    product = aProduct;
    if (existingProduct != null && !existingProduct.equals(aProduct))
    {
      existingProduct.removeCartItem(this);
    }
    product.addCartItem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Order placeholderOrder = order;
    this.order = null;
    if(placeholderOrder != null)
    {
      placeholderOrder.removeCartItem(this);
    }
    Product placeholderProduct = product;
    this.product = null;
    if(placeholderProduct != null)
    {
      placeholderProduct.removeCartItem(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "count" + ":" + getCount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "product = "+(getProduct()!=null?Integer.toHexString(System.identityHashCode(getProduct())):"null");
  }
}