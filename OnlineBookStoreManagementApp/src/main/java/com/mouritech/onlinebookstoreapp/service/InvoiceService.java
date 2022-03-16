package com.mouritech.onlinebookstoreapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mouritech.onlinebookstoreapp.entity.Invoice;
import com.mouritech.onlinebookstoreapp.exception.ResourceNotFoundException;

public interface InvoiceService {

	Invoice saveInvoice(Invoice invoice);

	List<Invoice> getAllInvoices();

	ResponseEntity<Invoice> updateBills(Long invoiceId, Invoice invoice) throws ResourceNotFoundException;

	ResponseEntity<?> deleteBill(Long invoiceId) throws ResourceNotFoundException;

	ResponseEntity<Invoice> getBillById(Long invoiceId) throws ResourceNotFoundException;

	Invoice createInvoiceUsingOrderId(Long orderId) throws ResourceNotFoundException;

}
