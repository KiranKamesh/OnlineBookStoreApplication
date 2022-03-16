package com.mouritech.onlinebookstoreapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mouritech.onlinebookstoreapp.entity.Customer;
import com.mouritech.onlinebookstoreapp.exception.ResourceNotFoundException;

public interface CustomerService {

	void addCustomerInformation(Customer customer);

	boolean findCustomerByEmailAndPassword(String customerEmail, String customerPassword);

	ResponseEntity<?> updatecustomerinfobyname(Customer customer);

	List<Customer> getAllCustomer();

	Customer deleteCustomer(Long customerId) throws ResourceNotFoundException;

}
