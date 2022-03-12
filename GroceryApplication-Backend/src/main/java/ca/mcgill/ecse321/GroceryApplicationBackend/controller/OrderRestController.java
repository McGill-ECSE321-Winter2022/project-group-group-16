package ca.mcgill.ecse321.GroceryApplicationBackend.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.GroceryApplicationBackend.dto.OrderDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Address;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Customer;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder.OrderStatus;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder.PurchaseType;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.OrderService;

@CrossOrigin(origins = "*")
@RestController
public class OrderRestController {

    @Autowired
    OrderService orderService;

    
    
    
    /** 
     * @param placeOrder(
     * @return OrderDto
     */
    //place order
    @PostMapping(value = {"/orders/placeOrder", "/orders/placeOrder/"})
    public OrderDto placeOrder(
        @RequestParam Integer barcode,
        @RequestParam Integer applicationId,
        @RequestParam Integer addressId,
        @RequestParam OrderStatus status,
        @RequestParam Date datePlaced,
        @RequestParam Date deliveryDate,
        @RequestParam String customerNote,
        @RequestParam PurchaseType purchaseType,
        @RequestParam Product product,
        @RequestParam Address billingAddress,
        @RequestParam Customer customer,
        @RequestParam Address shippingAddress) throws ApiRequestException{
        return convertToDto(orderService.placeOrder(barcode, applicationId, addressId, status, datePlaced, deliveryDate, customerNote, purchaseType, billingAddress, customer, shippingAddress));
    }

    
    /** 
     * @param updateOrderStatus(
     * @return OrderDto
     */
    //update status
    @PutMapping(value = {"/orders/updateOrderStatus/{id}", "/orders/updateOrderStatus/{id}/"})
    public OrderDto updateOrderStatus(
        @RequestParam OrderStatus status,
        @PathVariable("id") Integer id) throws ApiRequestException{
        return convertToDto(orderService.updateOrderStatus(status, id));
    }

    
    /** 
     * @param updateOrderAddress(
     * @return OrderDto
     */
    @PutMapping(value = {"/orders/updateOrderStatus/{id}]", "/orders/updateOrderStatus/{id}/"})
    public OrderDto updateOrderAddress(
        @RequestParam Address bAddress,
        @RequestParam Address sAddress,
        @PathVariable("id") Integer id) throws ApiRequestException{
        return convertToDto(orderService.updateAddress(bAddress, sAddress, id));
    }

    
    /** 
     * @param id
     * @return OrderDto
     * @throws Exception
     */
    @GetMapping(value = {"/groceryOrders/{id}", "/groceryOrders/{id}/"})
    public OrderDto getOrderById(@PathVariable("id") Integer id) throws ApiRequestException{
        return convertToDto(orderService.getOrderById(id));
    }

    
    /** 
     * @return List<OrderDto>
     */
    @GetMapping(value = {"/groceryOrders/allOrders", "/groceryOrders/allOrders/"})
    public List<OrderDto> getAllOrders() throws ApiRequestException {
        List<OrderDto> orderDtos = new ArrayList<>();
	    for (GroceryOrder order : orderService.getAllOrders()) {
	      orderDtos.add(convertToDto(order));
	    }
	    return orderDtos;
    }

    
    /** 
     * @param id
     * @return boolean
     */
    @DeleteMapping(value = {"/groceryOrders/{id}", "/groceryOrders/{id}/"})
    public boolean deleteOrder(@PathVariable("id") Integer id) throws ApiRequestException{
        if(id == 0) {
            throw new ApiRequestException("The id is not valid.");
        }
        return orderService.deleteOrder(id);
    }

    
    /** 
     * @param orderDto
     * @return boolean
     */
    @DeleteMapping(value = {"/groceryOrders", "/groceryOrders/"})
    public boolean deleteOrder(OrderDto orderDto){
        if(orderDto == null) {
            throw new ApiRequestException("The order is not valid.");
        }
        return orderService.deleteOrder(orderDto.getOrderId());
    }

    
    /** 
     * @param order
     * @return OrderDto
     */
    //helper

    public static OrderDto convertToDto(GroceryOrder order) {
        if (order == null){
            throw new ApiRequestException("Order does not exist");
        }

        return new OrderDto(order.getStatus(), order.getId(), order.getDatePlaced(), order.getDeliveryDate(), order.getCustomerNote(), order.getPurchaseType(), 
        order.getProduct(), order.getBillingAddress(), order.getCustomer(), order.getShippingAddress(), order.getPayment());
    }
    
}
