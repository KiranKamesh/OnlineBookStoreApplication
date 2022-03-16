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

import com.mouritech.onlinebookstoreapp.entity.Invoice;
import com.mouritech.onlinebookstoreapp.exception.ResourceNotFoundException;
import com.mouritech.onlinebookstoreapp.service.InvoiceService;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;

	@PostMapping("/create-invoice")
	public Invoice saveInvoice(@RequestBody Invoice invoice) {
		return invoiceService.saveInvoice(invoice);
	}

	@GetMapping("/get-invoices")
	public List<Invoice> getAllInvoices() {
		return invoiceService.getAllInvoices();
	}

	@PutMapping("/update-invoice/{invoiceid}")
	public ResponseEntity<Invoice> updateInvoice(@PathVariable(value = "invoiceid") Long invoiceId,
			@RequestBody Invoice invoice) throws ResourceNotFoundException {
		return invoiceService.updateBills(invoiceId, invoice);
	}

	@DeleteMapping("/delete-invoice{invoiceid}")
	public ResponseEntity<?> deleteInvoice(@PathVariable(value = "invoiceid") Long invoiceId)
			throws ResourceNotFoundException {
		return invoiceService.deleteBill(invoiceId);

	}

	@GetMapping("/get-invoices/{invoiceid}")
	public ResponseEntity<Invoice> getInvoiceById(@PathVariable(value = "invoiceid") Long invoiceId)
			throws ResourceNotFoundException {
		return invoiceService.getBillById(invoiceId);

	}

	@PostMapping("/create-invoice-usingorderid/{orderid}")
	public Invoice createInvoiceUsingOrderId(@PathVariable(value = "orderid") Long orderId) throws ResourceNotFoundException {
		return invoiceService.createInvoiceUsingOrderId(orderId);
	}

}
