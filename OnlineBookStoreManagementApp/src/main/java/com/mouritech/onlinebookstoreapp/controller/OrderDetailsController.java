package com.mouritech.onlinebookstoreapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mouritech.onlinebookstoreapp.entity.OrderDetails;
import com.mouritech.onlinebookstoreapp.exception.ResourceNotFoundException;
import com.mouritech.onlinebookstoreapp.service.OrderDetailsService;

@RestController
@RequestMapping("/orderdetails")
public class OrderDetailsController {

	@Autowired
	OrderDetailsService orderDetailsService;

	@PostMapping("/add-orders")
	public OrderDetails saveOrder(@RequestBody OrderDetails order) {
		return orderDetailsService.saveOrder(order);
	}

	@GetMapping("/get-orders")
	public List<OrderDetails> getAllOrders() {
		return orderDetailsService.getAllOrders();
	}

	@PutMapping("/update-orders/{orderid}")
	public ResponseEntity<OrderDetails> updateOrder(@PathVariable(value = "orderid") Long orderId,
			@RequestBody OrderDetails orderdetails) throws ResourceNotFoundException {
		return orderDetailsService.updateOrders(orderId, orderdetails);

	}

	@DeleteMapping("/delete-orders/{orderid}")
	public ResponseEntity<?> deleteOrder(@PathVariable(value = "orderid") Long orderId)
			throws ResourceNotFoundException {
		return orderDetailsService.deleteOrder(orderId);

	}

	@GetMapping("/get-orders/{orderid}")
	public ResponseEntity<OrderDetails> getOrderById(@PathVariable(value = "orderid") long orderId)
			throws ResourceNotFoundException {
		return orderDetailsService.getOrderById(orderId);
		}
	
	@PostMapping("/create-order-using-cartid/{cartid}")
	public OrderDetails createOrder(@PathVariable(value = "cartid") long cartId) throws ResourceNotFoundException {
		return orderDetailsService.createOrder(cartId);
	}

}
