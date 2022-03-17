package ca.mcgill.ecse321.GroceryApplicationBackend.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.GroceryApplicationBackend.dto.OrderDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder.OrderStatus;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder.PurchaseType;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.OrderService;

@CrossOrigin(origins = "*")
@RestController
public class OrderRestController {

    @Autowired
    OrderService orderService;

    /**
     * Rest method for creating an order
     * 
     * @param applicationId
     * @param status
     * @param datePlaced
     * @param deliveryDate
     * @param customerNote
     * @param purchaseType
     * @return
     * @throws ApiRequestException
     */
    @PostMapping(value = {"/groceryOrder", "/groceryOrder/"})
    public OrderDto placeOrder(
        @RequestParam Integer applicationId,
        @RequestParam OrderStatus status,
        @RequestParam String datePlaced,
        @RequestParam  String deliveryDate,
        @RequestParam String customerNote,
        @RequestParam PurchaseType purchaseType) throws ApiRequestException{
    	 Date convertDeliveryDate = Date.valueOf(deliveryDate);
    	 Date convertDatePlaced = Date.valueOf(datePlaced);
         return convertToDto(orderService.placeOrder(applicationId, status, convertDatePlaced, convertDeliveryDate, customerNote, purchaseType));
    }

    
    /**
     * Rest method for updating an order status
     * 
     * @param status
     * @param id
     * @return updated status
     * @throws ApiRequestException
     */
    @PutMapping(value = {"/groceryOrder/{id}", "/gorceryOrder/{id}/"})
    public OrderDto updateOrderStatus(
        @RequestParam OrderStatus status,
        @PathVariable("id") Integer id) throws ApiRequestException{
        return convertToDto(orderService.updateOrderStatus(status, id));
    }

    /**
     * Rest method for marking an order as refunded
     *
     * @param id
     * @return requested order
     */
    @PutMapping(value= {"/order/refund/{id}", "/order/refund/{id}/"})
    public OrderDto refundOrderById(
        @PathVariable("id") Integer id) throws ApiRequestException{
            return convertToDto(orderService.refundOrderById(id));
    }

    
    /**
     * Rest method for retrieving an order by id
     * 
     * @param id
     * @return requested order
     * @throws ApiRequestException
     */
    @GetMapping(value = {"/groceryOrder/{id}", "/groceryOrder/{id}/"})
    public OrderDto getOrderById(@PathVariable("id") Integer id) throws ApiRequestException{
        return convertToDto(orderService.getOrderById(id));
    }

    
    /**
     * Rest method to retrieve all orders in the database
     * 
     * @return all orders
     * @throws ApiRequestException
     */
    @GetMapping(value = {"/groceryOrder", "/groceryOrder/"})
    public List<OrderDto> getAllOrders() throws ApiRequestException {
        List<OrderDto> orderDtos = new ArrayList<>();
	    for (GroceryOrder order : orderService.getAllOrders()) {
	      orderDtos.add(convertToDto(order));
	    }
	    return orderDtos;
    }

    /**
     * Rest method to delete an order
     * 
     * @param id
     * @throws ApiRequestException
     */
    @DeleteMapping(value = {"/groceryOrder/{id}", "/groceryOrder/{id}/"})
    public void deleteOrder(@PathVariable("id") Integer id) throws ApiRequestException{
        if(id == null) {
            throw new ApiRequestException("The id is not valid.");
        }
        orderService.deleteOrder(id);
    }
    

    public static OrderDto convertToDto(GroceryOrder order) {
        if (order == null){
            throw new ApiRequestException("Order does not exist");
        }

        return new OrderDto(order.getStatus(), order.getId(), order.getDatePlaced(), order.getDeliveryDate(), order.getCustomerNote(), order.getPurchaseType(), 
        order.getProduct(), order.getBillingAddress(), order.getCustomer(), order.getShippingAddress(), order.getPayment());
    }
    
}
