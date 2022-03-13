package ca.mcgill.ecse321.GroceryApplicationBackend.service;

//Project imports
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.*;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.*;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Payment.PaymentType;

//Mockito imports
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;


@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private GroceryOrderRepository groceryOrderRepository;

    @InjectMocks
    private PaymentService paymentService;

    //Initial parameters
    private static final Integer ORDER_ID = 1;
    private static final Integer PAYMENT_ID = 2;
    private static final Float PAYMENT_AMOUNT = (float) 5.99;
    private static final PaymentType PAYMENT_TYPE = PaymentType.DEBIT;
    private static final String PAYMENT_CODE = "23411SX";

    //Updated parameters
    private static final Float PAYMENT_AMOUNT_UPDATED = (float) 11.99;
    private static final PaymentType PAYMENT_TYPE_UPDATED = PaymentType.CREDIT;
    private static final String PAYMENT_CODE_UPDATED = "12345AA";

    //Does not exist parameters
    private static final Integer ORDER_DOES_NOT_EXIST = 9;
    private static final Integer PAYMENT_DOES_NOT_EXIST = 10;


    @BeforeEach
    public void setMockOutput() {
        lenient().when(paymentRepository.findPaymentById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {

            if (invocation.getArgument(0).equals(PAYMENT_ID)) {
                GroceryStoreApplication groceryStoreApplication = new GroceryStoreApplication();
                GroceryOrder groceryOrder = new GroceryOrder();
                Payment payment = new Payment();
                groceryOrder.setGroceryStoreApplication(groceryStoreApplication);
                payment.setOrder(groceryOrder);
                payment.setId(PAYMENT_ID);
                return payment;
            } else {
                return null;
            }

        });

        lenient().when(groceryOrderRepository.findGroceryOrderById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {

            if (invocation.getArgument(0).equals(ORDER_ID)) {
                GroceryStoreApplication groceryStoreApplication = new GroceryStoreApplication();
                GroceryOrder groceryOrder = new GroceryOrder();
                groceryOrder.setGroceryStoreApplication(groceryStoreApplication);
                groceryOrder.setId(ORDER_ID);
                return groceryOrder;
            } else {
                return null;
            }

        });

        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> invocation.getArgument(0);
        lenient().when(paymentRepository.save(any(Payment.class))).thenAnswer(returnParameterAsAnswer);

    }


    //===============
    //createPayment()
    //===============

    @Test
    public void testCreatePayment() {
        Payment payment = null;

        try {
            payment = paymentService.createPayment(ORDER_ID, PAYMENT_AMOUNT, PAYMENT_TYPE, PAYMENT_CODE);
        } catch (ApiRequestException e) {
            fail();
        }

        assertNotNull(payment);
        assertEquals(ORDER_ID, payment.getOrder().getId());
        assertEquals(PAYMENT_AMOUNT, payment.getAmount());
        assertEquals(PAYMENT_TYPE, payment.getPaymentType());
        assertEquals(PAYMENT_CODE, payment.getPaymentCode());
    }

    @Test
    public void testCreatePaymentOrderNotFound() {
        Payment payment = null;
        String error = null;

        try {
            payment = paymentService.createPayment(ORDER_DOES_NOT_EXIST, PAYMENT_AMOUNT, PAYMENT_TYPE, PAYMENT_CODE);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertNull(payment);
        assertEquals("The order doesn't exist!", error);
    }

    //===============
    //getPaymentById
    //===============

    @Test
    public void testFindPayment() {
        Payment payment = null;

        try {
            payment = paymentService.getPaymentById(PAYMENT_ID);
        } catch (ApiRequestException e) {
            fail();
        }

        assertNotNull(payment);
        assertEquals(PAYMENT_ID, payment.getId());
    }

    @Test
    public void testFindPaymentNotFound() {
        Payment payment = null;
        String error = null;

        try {
            payment = paymentService.getPaymentById(PAYMENT_DOES_NOT_EXIST);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertNull(payment);
        assertEquals("Payment doesn't exist!", error);
    }

    @Test
    public void testFindNullPayment() {
        Payment payment = null;
        String error = null;

        try {
            payment = paymentService.getPaymentById(null);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertNull(payment);
        assertEquals("Payment id cannot be null!", error);
    }


    //===============
    //updatePayment()
    //===============

    @Test
    public void testUpdatePayment() {
        Payment payment = null;

        try {
            payment = paymentService.updatePayment(PAYMENT_ID, PAYMENT_AMOUNT_UPDATED, PAYMENT_TYPE_UPDATED, PAYMENT_CODE_UPDATED);
        } catch (ApiRequestException e) {
            fail();
        }

        assertNotNull(payment);
        assertEquals(PAYMENT_AMOUNT_UPDATED, payment.getAmount());
        assertEquals(PAYMENT_TYPE_UPDATED, payment.getPaymentType());
        assertEquals(PAYMENT_CODE_UPDATED, payment.getPaymentCode());
    }

    @Test
    public void testUpdatePaymentNotFound() {
        Payment payment = null;
        String error = null;

        try {
            payment = paymentService.updatePayment(PAYMENT_DOES_NOT_EXIST, PAYMENT_AMOUNT_UPDATED, PAYMENT_TYPE_UPDATED, PAYMENT_CODE_UPDATED);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertNull(payment);
        assertEquals("Payment doesn't exist!", error);
    }

    @Test
    public void testUpdateNullPayment() {
        String error = null;

        try {
            paymentService.updatePayment(null, PAYMENT_AMOUNT_UPDATED, PAYMENT_TYPE_UPDATED, PAYMENT_CODE_UPDATED);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertEquals("Payment id cannot be null!", error);
    }


    //=============
    //deletePayment
    //=============

    @Test
    public void testDeletePayment() {
        try {
            paymentService.deletePayment(PAYMENT_ID);
        } catch (ApiRequestException e) {
            fail();
        }
    }

    @Test
    public void testDeletePaymentNotFound() {
        String error = null;

        try {
            paymentService.deletePayment(PAYMENT_DOES_NOT_EXIST);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertEquals("Payment doesn't exist!", error);
    }

    @Test
    public void testDeleteNullPayment() {
        String error = null;

        try {
            paymentService.deletePayment(null);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertEquals("Payment id cannot be null!", error);
    }

}
