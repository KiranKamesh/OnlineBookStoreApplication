package com.mouritech.onlinebookstoreapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mouritech.onlinebookstoreapp.entity.Book;
import com.mouritech.onlinebookstoreapp.entity.Cart;
import com.mouritech.onlinebookstoreapp.entity.Invoice;
import com.mouritech.onlinebookstoreapp.entity.OrderDetails;
import com.mouritech.onlinebookstoreapp.exception.ResourceNotFoundException;
import com.mouritech.onlinebookstoreapp.repository.InvoiceRepository;
import com.mouritech.onlinebookstoreapp.repository.OrderDetailsRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	InvoiceRepository invoiceRepository;

	@Autowired
	OrderDetailsRepository orderRepository;

	@Override
	public Invoice saveInvoice(Invoice invoice) {
		return invoiceRepository.save(invoice);
	}

	@Override
	public List<Invoice> getAllInvoices() {
		return invoiceRepository.findAll();
	}

	@Override
	public ResponseEntity<Invoice> updateBills(Long invoiceId, Invoice invoice) throws ResourceNotFoundException {
		Invoice existingInvoice = invoiceRepository.findById(invoiceId)
				.orElseThrow(() -> new ResourceNotFoundException());
		existingInvoice.setInvoiceDate(invoice.getInvoiceDate());
		existingInvoice.setTotalCost(invoice.getTotalCost());
		existingInvoice.setTotalBooks(invoice.getTotalBooks());
		invoiceRepository.save(existingInvoice);
		return ResponseEntity.ok(existingInvoice);
	}

	@Override
	public ResponseEntity<?> deleteBill(Long invoiceId) throws ResourceNotFoundException {
		Invoice existingInvoice = invoiceRepository.findById(invoiceId)
				.orElseThrow(() -> new ResourceNotFoundException());
		invoiceRepository.delete(existingInvoice);
		return ResponseEntity.ok(existingInvoice);
	}

	@Override
	public ResponseEntity<Invoice> getBillById(Long invoiceId) throws ResourceNotFoundException {
		Invoice existingInvoice = invoiceRepository.findById(invoiceId)
				.orElseThrow(() -> new ResourceNotFoundException());
		return ResponseEntity.ok(existingInvoice);
	}

	@Override
	public Invoice createInvoiceUsingOrderId(Long orderId) throws ResourceNotFoundException {
		OrderDetails existingOrderDetails = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException());
		Cart cart = existingOrderDetails.getCart();
		Invoice invoice = new Invoice();
		int totalBooks = invoice.getTotalBooks();
		double totalCost = invoice.getTotalCost();
		List<Book> books = cart.getBooks();
		for (Book result : books) {
			totalBooks = totalBooks + result.getNoOfCopies();
			totalCost = totalCost + (result.getNoOfCopies() * result.getBookPrice());
		}
		invoice.setInvoiceId(generateInvoiceId());
		invoice.setInvoiceDate(LocalDate.now());
		invoice.setTotalBooks(totalBooks);
		invoice.setTotalCost(totalCost);
		invoice.setOrderDetails(existingOrderDetails);
		invoiceRepository.save(invoice);
		return invoice;
	}

	public long generateInvoiceId() {
		Random rand = new Random(); 
		int upperbound = 255;
		Long cId = (long) rand.nextInt(upperbound);
		return cId;

	}

}
