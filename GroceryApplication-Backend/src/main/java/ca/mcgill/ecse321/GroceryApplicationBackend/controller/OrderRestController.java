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
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.GroceryApplicationBackend.dto.OrderDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Address;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Customer;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder.OrderStatus;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryOrder.PurchaseType;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.InvalidInputException;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.OrderService;

@CrossOrigin(origins = "*")
@RestController
public class OrderRestController {

    @Autowired
    OrderService orderService;

    //place order
    @PostMapping(value = {"/orders/placeOrder", "/orders/placeOrder/"})
    public OrderDto placeOrder(
        @PathVariable("barcode") int barcode,
        @PathVariable("applicationId") int applicationId,
        @PathVariable("addressId") int addressId,
        @PathVariable("status") OrderStatus status,
        @PathVariable("id") int id,
        @PathVariable("datePlaced") Date datePlaced,
        @PathVariable("deliveryDate") Date deliveryDate,
        @PathVariable("customerNote") String customerNote,
        @PathVariable("purchaseType") PurchaseType purchaseType,
        @PathVariable("product") Product product,
        @PathVariable("billingAddress") Address billingAddress,
        @PathVariable("customer") Customer customer,
        @PathVariable("shippingAddress") Address shippingAddress) throws Exception{
        return convertToDto(orderService.placeOrder(barcode, applicationId, addressId, status, id, datePlaced, deliveryDate, customerNote, purchaseType, billingAddress, customer, shippingAddress));
    }

    //update status
    @PutMapping(value = {"/orders/updateOrderStatus/{status}", "/orders/updateOrderStatus/{status}/"})
    public OrderDto updateOrderStatus(
        @PathVariable("status") OrderStatus status,
        @PathVariable("id") int id){
        return convertToDto(orderService.updateOrderStatus(status, id));
    }

    @PutMapping(value = {"/orders/updateOrderStatus/{bAdd}/{sAdd}", "/orders/updateOrderStatus/{bAdd}/{sAdd}/"})
    public OrderDto updateOrderAddress(
        @PathVariable("bAdd") Address bAddress,
        @PathVariable("sAdd") Address sAddress,
        @PathVariable("id") int id){
        return convertToDto(orderService.updateAddress(bAddress, sAddress, id));
    }

    @GetMapping(value = {"/groceryOrders/{id}", "/groceryOrders/{id}/"})
    public OrderDto getOrderById(@PathVariable("id") int id) throws Exception{
        return convertToDto(orderService.getOrderById(id));
    }

    @GetMapping(value = {"/groceryOrders/allOrders", "groceryOrders/allOrders/"})
    public List<OrderDto> getAllOrders() {
        List<OrderDto> orderDtos = new ArrayList<>();
	    for (GroceryOrder order : orderService.getAllOrders()) {
	      orderDtos.add(convertToDto(order));
	    }
	    return orderDtos;
    }

    @DeleteMapping(value = {"/groceryOrders/{id}", "/groceryOrders/{id}/"})
    public boolean deleteOrder(@PathVariable("id") int id){
        if(id == 0) {
            throw new InvalidInputException("The id is not valid.");
        }
        return orderService.deleteOrder(id);
    }

    @DeleteMapping(value = {"/groceryOrders", "/groceryOrders/"})
    public boolean deleteOrder(OrderDto orderDto){
        if(orderDto == null) {
            throw new InvalidInputException("The order is not valid.");
        }
        return orderService.deleteOrder(orderDto.getOrderId());
    }

    //helper

    public static OrderDto convertToDto(GroceryOrder order) {
        if (order == null){
            throw new IllegalArgumentException("Order does not exist");
        }

        return new OrderDto(order.getStatus(), order.getId(), order.getDatePlaced(), order.getDeliveryDate(), order.getCustomerNote(), order.getPurchaseType(), 
        order.getProduct(), order.getBillingAddress(), order.getCustomer(), order.getShippingAddress(), order.getPayment());
    }
    
}
