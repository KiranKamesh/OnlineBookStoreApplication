package com.mouritech.onlinebookstoreapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mouritech.onlinebookstoreapp.entity.Customer;
import com.mouritech.onlinebookstoreapp.exception.ResourceNotFoundException;
import com.mouritech.onlinebookstoreapp.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public void addCustomerInformation(Customer customer) {
		customerRepository.save(customer);

	}

	@Override
	public boolean findCustomerByEmailAndPassword(String customerEmail, String customerPassword) {
		boolean flag = false;

		Customer customer = customerRepository.findByCustomerEmailAndCustomerPassword(customerEmail, customerPassword);
		if (customer == null) {
			return flag;
		} else {
			return flag = true;
		}
	}

	@Override
	public ResponseEntity<?> updatecustomerinfobyname(Customer customer) {
		Customer cust = customerRepository.findByCustomerName(customer.getCustomerName());
		if (cust == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("you cannot update customer");
		} else {
			cust.setCustomerName(customer.getCustomerName());
			cust.setCustomerPassword(customer.getCustomerPassword());
			cust.setCustomerMobileNumber(customer.getCustomerMobileNumber());
			cust.setCustomerEmail(customer.getCustomerEmail());
			final Customer customerfinal = customerRepository.save(cust);

			return ResponseEntity.ok(customerfinal);
		}
	}

	@Override
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}

	@Override
	public Customer deleteCustomer(Long customerId) throws ResourceNotFoundException {
		Customer existingCustomer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException());
		customerRepository.delete(existingCustomer);
		return existingCustomer;
	}

}
