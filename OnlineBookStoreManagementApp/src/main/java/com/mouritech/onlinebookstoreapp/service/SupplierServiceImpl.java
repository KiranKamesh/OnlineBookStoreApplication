package com.mouritech.onlinebookstoreapp.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mouritech.onlinebookstoreapp.DTO.SupplierVerificationDto;
import com.mouritech.onlinebookstoreapp.entity.Book;
import com.mouritech.onlinebookstoreapp.entity.Supplier;
import com.mouritech.onlinebookstoreapp.exception.ResourceNotFoundException;
import com.mouritech.onlinebookstoreapp.repository.BookRepository;
import com.mouritech.onlinebookstoreapp.repository.SupplierRepository;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	SupplierRepository supplierRepository;

	@Autowired
	BookRepository bookRepository;

	@Override
	public Supplier findSupplierByEmailAndPassword(String supplierEmail, String supplierPassword) {
		Supplier supplier = supplierRepository.findBySupplierEmailAndSupplierPassword(supplierEmail,
				supplierPassword);
		if (supplier == null) {
			return null;
		} else {
			return supplier;
		}
	}

	@Override
	public Supplier addSupplier(Supplier supplier) {
		return supplierRepository.save(supplier);
	}

	@Override
	public List<Supplier> getAllSuppliers() {
		return supplierRepository.findAll();
	}

	@Override
	public Supplier getSupplierById(Long supplierId) throws ResourceNotFoundException {
		Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(() -> new ResourceNotFoundException());
		return supplier;
	}

	@Override
	public Supplier updateSupplierById(Long supplierId, Supplier supplier) throws ResourceNotFoundException {
		Supplier existingSupplier = supplierRepository.findById(supplierId)
				.orElseThrow(() -> new ResourceNotFoundException());
		existingSupplier.setSupplierName(supplier.getSupplierName());
		existingSupplier.setSupplierEmail(supplier.getSupplierEmail());
		existingSupplier.setSupplierContactNumber(supplier.getSupplierContactNumber());
		supplierRepository.save(existingSupplier);
		return existingSupplier;
	}

	@Override
	public Supplier deleteSupplier(Long supplierId) throws ResourceNotFoundException {
		Supplier existingSupplier = supplierRepository.findById(supplierId)
				.orElseThrow(() -> new ResourceNotFoundException());
		supplierRepository.delete(existingSupplier);
		return existingSupplier;
	}

	@Override
	public Book addBooksBySupplier(Long supplierId, Book book) throws ResourceNotFoundException {
		Supplier existingSupplier = supplierRepository.findById(supplierId)
				.orElseThrow(() -> new ResourceNotFoundException());
		book.setSupplier(existingSupplier);
		Book books = bookRepository.save(book);
		return books;

	}

	@Override
	public ResponseEntity<Supplier> getSupplierInfoAndBooks(String supplierName) {
		Supplier supplier = supplierRepository.findBySupplierName(supplierName);
		return ResponseEntity.ok(supplier);
	}

	@Override
	public ResponseEntity<Supplier> deleteByBooksusingSupplierName(String supplierName, String bookName)
			throws ResourceNotFoundException {
		Supplier supplier = supplierRepository.findBySupplierName(supplierName);

		List<Book> bookList = supplier.getBooks();
		for (Book result : bookList) {
			if (result.getBookName().equals(bookName)) {
				System.out.println(result.getBookName());

				long existingBookISBN = result.getBookISBN();

				System.out.println(existingBookISBN);

				Book existingBooks = bookRepository.findById(existingBookISBN)
						.orElseThrow(() -> new ResourceNotFoundException());
				bookRepository.delete(existingBooks);

				return getSupplierInfoAndBooks(supplierName);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Supplier> updateByBooksusingSupplierName(String supplierName, String bookName, Book books)
			throws ResourceNotFoundException {
		Supplier supplier = supplierRepository.findBySupplierName(supplierName);

		List<Book> bookList= supplier.getBooks();
		for (Book result : bookList) {
			if(result.getBookName().equals(bookName)) {
				long existingBookISBN = result.getBookISBN();
				Book existingBooks = bookRepository.findById(existingBookISBN).orElseThrow(() -> new ResourceNotFoundException());
				existingBooks.setBookName(books.getBookName());
				existingBooks.setBookAuthor(books.getBookAuthor());
				existingBooks.setBookPrice(books.getBookPrice());
				existingBooks.setNoOfCopies(books.getNoOfCopies());
				
				bookRepository.save(existingBooks);
				
				return getSupplierInfoAndBooks(supplierName);
			
			}
			
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<?> updateSupplierinfoByName(Supplier supplier) {
		Supplier exisitingSupplier = supplierRepository.findBySupplierName(supplier.getSupplierName());
		if(exisitingSupplier==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("you cannot update the resource");
		}
		else {
			exisitingSupplier.setSupplierName(supplier.getSupplierName());
			exisitingSupplier.setSupplierEmail(supplier.getSupplierEmail());
			exisitingSupplier.setSupplierPassword(supplier.getSupplierPassword());
			final Supplier supplierFinal = supplierRepository.save(exisitingSupplier);
		    return ResponseEntity.ok(supplierFinal);
		}
	}

	@Override
	public ResponseEntity<?> checkSupplierEmailAndPassword(SupplierVerificationDto supplierVerificationDto) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
