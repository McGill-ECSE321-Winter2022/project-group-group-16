package ca.mcgill.ecse321.GroceryApplication.service;

//Project imports
import ca.mcgill.ecse321.GroceryApplicationBackend.model.*;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.*;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder.OrderStatus;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder.PurchaseType;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.OrderService;

//Mockito imports
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

//Java imports
import java.sql.Date;


@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private GroceryOrderRepository orderRepository;

    @Mock
    private GroceryStoreApplicationRepository gsAppRepository;

    @InjectMocks
    private OrderService orderService;

    //Initial parameters
    private static final Integer ORDER_ID = 10;
    private static final Integer APP_ID = 1;
    private static final OrderStatus ORDER_STATUS = OrderStatus.PLACED;
    private static final Date DATE_PLACED = Date.valueOf("2022-01-01");
    private static final Date DATE_DELIVERY = Date.valueOf("2022-02-10");
    private static final String CUSTOMER_NOTE = "Leave at door uwu";
    private static final PurchaseType PURCHASE_TYPE = PurchaseType.ONLINE;

    //Updated parameters
    private static final OrderStatus ORDER_STATUS_UPDATED = OrderStatus.SHIPPED;

    //Does not exist parameters
    private static final Integer ORDER_ID_DOES_NOT_EXIST = 99;
    private static final Integer APP_ID_DOES_NOT_EXIST = 9;


    @BeforeEach
    public void setMockOutput() {

        lenient().when(orderRepository.findGroceryOrderById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {

            if (invocation.getArgument(0).equals(ORDER_ID)) {
                GroceryStoreApplication gsApplication = new GroceryStoreApplication();
                GroceryOrder order = new GroceryOrder();
                order.setGroceryStoreApplication(gsApplication);
                order.setId(ORDER_ID);
                return order;
            } else {
                return null;
            }

        });

        lenient().when(gsAppRepository.findGroceryStoreApplicationById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {

            if (invocation.getArgument(0).equals(APP_ID)) {
                GroceryStoreApplication gsApplication = new GroceryStoreApplication();
                gsApplication.setId(APP_ID);
                return gsApplication;
            } else {
                return null;
            }

        });

        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> invocation.getArgument(0);
        lenient().when(orderRepository.save(any(GroceryOrder.class))).thenAnswer(returnParameterAsAnswer);

    }


    //============
    //placeOrder()
    //============

    @Test
    public void testPlaceOrder() {
        GroceryOrder groceryOrder = null;

        try {
            groceryOrder = orderService.placeOrder(APP_ID, ORDER_STATUS, DATE_PLACED, DATE_DELIVERY, CUSTOMER_NOTE, PURCHASE_TYPE);
        } catch (ApiRequestException e) {
            fail();
        }

        assertNotNull(groceryOrder);
        assertEquals(APP_ID, groceryOrder.getGroceryStoreApplication().getId());
        assertEquals(ORDER_STATUS, groceryOrder.getStatus());
        assertEquals(DATE_PLACED, groceryOrder.getDatePlaced());
        assertEquals(DATE_DELIVERY, groceryOrder.getDeliveryDate());
        assertEquals(CUSTOMER_NOTE, groceryOrder.getCustomerNote());
        assertEquals(PURCHASE_TYPE, groceryOrder.getPurchaseType());
    }

    @Test
    public void testPlaceOrderAppNotFound() {
        GroceryOrder groceryOrder = null;
        String error = null;

        try {
            groceryOrder = orderService.placeOrder(APP_ID_DOES_NOT_EXIST, ORDER_STATUS, DATE_PLACED, DATE_DELIVERY, CUSTOMER_NOTE, PURCHASE_TYPE);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertNull(groceryOrder);
        assertEquals("The application doesn't exist!", error);
    }


    //=============
    //updateOrder()
    //=============

    @Test
    public void testUpdateOrder() {
        GroceryOrder groceryOrder = null;

        try {
            groceryOrder = orderService.updateOrderStatus(ORDER_STATUS_UPDATED, ORDER_ID);
        } catch (ApiRequestException e) {
            fail();
        }

        assertNotNull(groceryOrder);
        assertEquals(ORDER_ID, groceryOrder.getId());
        assertEquals(ORDER_STATUS_UPDATED, groceryOrder.getStatus());
    }

    @Test
    public void testUpdateOrderNotFound() {
        GroceryOrder groceryOrder = null;
        String error = null;

        try {
            groceryOrder = orderService.updateOrderStatus(ORDER_STATUS_UPDATED, ORDER_ID_DOES_NOT_EXIST);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertNull(groceryOrder);
        assertEquals("Order does not exist!", error);
    }


    //=============
    //deleteOrder()
    //=============

    @Test
    public void testDeleteOrder() {

        try {
            orderService.deleteOrder(ORDER_ID);
        } catch (ApiRequestException e) {
            fail();
        }

    }

    @Test
    public void testDeleteOrderNotFound() {
        String error = null;

        try {
            orderService.deleteOrder(ORDER_ID_DOES_NOT_EXIST);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertEquals("Order does not exist!", error);
    }


    //==============
    //getOrderById()
    //==============

    @Test
    public void testGetOrderById() {
        GroceryOrder order = null;

        try {
            order = orderService.getOrderById(ORDER_ID);
        } catch (ApiRequestException e) {
            fail();
        }

        assertNotNull(order);
        assertEquals(ORDER_ID, order.getId());

    }

    @Test
    public void testGetOrderByIdNotFound() {
        GroceryOrder order = null;
        String error = null;

        try {
            order = orderService.getOrderById(ORDER_ID_DOES_NOT_EXIST);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertNull(order);
        assertEquals("Order does not exist!", error);

    }


    //=================
    //refundOrderById()
    //=================

    @Test
    public void testRefundOrderById() {
        GroceryOrder order = null;

        try {
            order = orderService.refundOrderById(ORDER_ID);
        } catch (ApiRequestException e) {
            fail();
        }

        assertNotNull(order);
        assertEquals(ORDER_ID, order.getId());
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }

    @Test
    public void testRefundOrderByIdNotFound() {
        GroceryOrder order = null;
        String error = null;

        try {
            order = orderService.refundOrderById(ORDER_ID_DOES_NOT_EXIST);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertNull(order);
        assertEquals("Order does not exist!", error);
    }


}
