/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStoreApplicationBackend.model;

// line 93 "../../../../../GroceryApplication.ump"
public class Payment
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum PaymentType { DEBIT, CREDIT, GIFTCARD }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextPaymentID = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Payment Attributes
  private float amount;
  private String paymentCode;
  private PaymentType paymentType;

  //Autounique Attributes
  private int paymentID;

  //Payment Associations
  private Order order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Payment(float aAmount, String aPaymentCode, PaymentType aPaymentType, Order aOrder)
  {
    amount = aAmount;
    paymentCode = aPaymentCode;
    paymentType = aPaymentType;
    paymentID = nextPaymentID++;
    boolean didAddOrder = setOrder(aOrder);
    if (!didAddOrder)
    {
      throw new RuntimeException("Unable to create payment due to order. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAmount(float aAmount)
  {
    boolean wasSet = false;
    amount = aAmount;
    wasSet = true;
    return wasSet;
  }

  public boolean setPaymentCode(String aPaymentCode)
  {
    boolean wasSet = false;
    paymentCode = aPaymentCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setPaymentType(PaymentType aPaymentType)
  {
    boolean wasSet = false;
    paymentType = aPaymentType;
    wasSet = true;
    return wasSet;
  }

  public float getAmount()
  {
    return amount;
  }

  public String getPaymentCode()
  {
    return paymentCode;
  }

  public PaymentType getPaymentType()
  {
    return paymentType;
  }

  public int getPaymentID()
  {
    return paymentID;
  }
  /* Code from template association_GetOne */
  public Order getOrder()
  {
    return order;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setOrder(Order aNewOrder)
  {
    boolean wasSet = false;
    if (aNewOrder == null)
    {
      //Unable to setOrder to null, as payment must always be associated to a order
      return wasSet;
    }
    
    Payment existingPayment = aNewOrder.getPayment();
    if (existingPayment != null && !equals(existingPayment))
    {
      //Unable to setOrder, the current order already has a payment, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Order anOldOrder = order;
    order = aNewOrder;
    order.setPayment(this);

    if (anOldOrder != null)
    {
      anOldOrder.setPayment(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Order existingOrder = order;
    order = null;
    if (existingOrder != null)
    {
      existingOrder.setPayment(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "paymentID" + ":" + getPaymentID()+ "," +
            "amount" + ":" + getAmount()+ "," +
            "paymentCode" + ":" + getPaymentCode()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "paymentType" + "=" + (getPaymentType() != null ? !getPaymentType().equals(this)  ? getPaymentType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null");
  }
}