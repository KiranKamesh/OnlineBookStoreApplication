package com.mouritech.onlinebookstoreapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mouritech.onlinebookstoreapp.entity.Customer;
import com.mouritech.onlinebookstoreapp.exception.ResourceNotFoundException;
import com.mouritech.onlinebookstoreapp.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/add-customer")
	public ResponseEntity<?> addCustomerInformation(@RequestBody Customer customer) {
		customerService.addCustomerInformation(customer);
		return ResponseEntity.ok().body("all customer information inserted");
	}

	@GetMapping("/get-customer-byemailandpassword/{customeremail}/{customerpassword}")
	public ResponseEntity<?> findCustomerByEmailAndPassword(@PathVariable(value = "customeremail") String customerEmail,
			@PathVariable(value = "customerpassword") String customerPassword) {

		boolean result = customerService.findCustomerByEmailAndPassword(customerEmail, customerPassword);
		if (result == true) {
			return ResponseEntity.ok().body("login successful");
		}

		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("resourse not found please sign in first");

		}

	}

	@PutMapping("/update-customerinfo-byname")
	public ResponseEntity<?> updatecustomerinfobyname(@RequestBody Customer customer) {
		ResponseEntity<?> result = customerService.updatecustomerinfobyname(customer);
		return result;
	}

	@GetMapping("/getall")
	public List<Customer> getAllCustomer() {
		List<Customer> customerList = customerService.getAllCustomer();
		return customerList;
	}

	@DeleteMapping("/deletecustomer/{customerid}")
	public Customer deleteCustomer(@PathVariable(value = "customerid") Long customerId)
			throws ResourceNotFoundException {
		return customerService.deleteCustomer(customerId);
	}

}
