package com.mouritech.onlinebookstoreapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mouritech.onlinebookstoreapp.entity.Cart;
import com.mouritech.onlinebookstoreapp.entity.OrderDetails;
import com.mouritech.onlinebookstoreapp.exception.ResourceNotFoundException;
import com.mouritech.onlinebookstoreapp.repository.CartRepository;
import com.mouritech.onlinebookstoreapp.repository.OrderDetailsRepository;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	OrderDetailsRepository orderRepository;

	@Autowired
	CartRepository cartRepository;

	@Override
	public OrderDetails saveOrder(OrderDetails order) {
		return orderRepository.save(order);
	}

	@Override
	public List<OrderDetails> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public ResponseEntity<OrderDetails> updateOrders(Long orderId, OrderDetails orderdetails)
			throws ResourceNotFoundException {
		OrderDetails existingOrderDetails = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException());
		existingOrderDetails.setOrderDate(LocalDate.now());
		existingOrderDetails.setCart(orderdetails.getCart());
		orderRepository.save(existingOrderDetails);
		return ResponseEntity.ok(existingOrderDetails);
	}

	public long generateOrderId() {
		Random rand = new Random();
		int upperbound = 255;
		Long cId = (long) rand.nextInt(upperbound);
		return cId;

	}

	@Override
	public ResponseEntity<?> deleteOrder(Long orderId) throws ResourceNotFoundException {
		OrderDetails existingOrderDetails = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException());
		if (existingOrderDetails == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("order ID is not present");
		} else {
			orderRepository.delete(existingOrderDetails);
			return ResponseEntity.status(HttpStatus.OK).body(existingOrderDetails);

		}
	}

	@Override
	public ResponseEntity<OrderDetails> getOrderById(long orderId) throws ResourceNotFoundException {
		OrderDetails existingOrderDetails = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException());
		return ResponseEntity.status(HttpStatus.OK).body(existingOrderDetails);
	}

	@Override
	public OrderDetails createOrder(long cartId) throws ResourceNotFoundException {
		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException());
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setOrderId(generateOrderId());
		orderDetails.setOrderDate(LocalDate.now());
		orderDetails.setCart(cart);
		orderRepository.save(orderDetails);
		return orderDetails;
	}

}
