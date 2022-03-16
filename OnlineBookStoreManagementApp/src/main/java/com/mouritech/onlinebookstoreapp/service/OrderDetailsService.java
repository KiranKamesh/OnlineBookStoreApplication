package com.mouritech.onlinebookstoreapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mouritech.onlinebookstoreapp.entity.OrderDetails;
import com.mouritech.onlinebookstoreapp.exception.ResourceNotFoundException;

public interface OrderDetailsService {

	OrderDetails saveOrder(OrderDetails order);

	List<OrderDetails> getAllOrders();

	ResponseEntity<OrderDetails> updateOrders(Long orderId, OrderDetails orderdetails) throws ResourceNotFoundException;

	ResponseEntity<?> deleteOrder(Long orderId) throws ResourceNotFoundException;

	ResponseEntity<OrderDetails> getOrderById(long orderId) throws ResourceNotFoundException;

	OrderDetails createOrder(long cartId) throws ResourceNotFoundException;

}
