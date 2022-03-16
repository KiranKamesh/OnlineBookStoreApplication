package com.mouritech.onlinebookstoreapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouritech.onlinebookstoreapp.entity.Book;
import com.mouritech.onlinebookstoreapp.entity.Supplier;
import com.mouritech.onlinebookstoreapp.exception.ResourceNotFoundException;
import com.mouritech.onlinebookstoreapp.repository.BookRepository;
import com.mouritech.onlinebookstoreapp.repository.SupplierRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	SupplierRepository supplierRepository;

	@Override
	public Book addBooks(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public List<Book> getAllItems() {
		return bookRepository.findAll();
	}

	@Override
	public Book findByISBN(Long bookISBN) throws ResourceNotFoundException {
		Book existingBook = bookRepository.findById(bookISBN).orElseThrow(() -> new ResourceNotFoundException());
		return existingBook;
	}

	@Override
	public Book updateByISBN(Book book, Long bookISBN) throws ResourceNotFoundException {
		Book existingBook = bookRepository.findById(bookISBN).orElseThrow(() -> new ResourceNotFoundException());
		existingBook.setBookAuthor(book.getBookAuthor());
		existingBook.setBookName(book.getBookAuthor());
		existingBook.setBookPrice(book.getBookPrice());
		existingBook.setNoOfCopies(book.getNoOfCopies());
		bookRepository.save(existingBook);
		return existingBook;

	}

	@Override
	public Book deleteBookByISBN(Long bookISBN) throws ResourceNotFoundException {
		Book existingBook = bookRepository.findById(bookISBN).orElseThrow(() -> new ResourceNotFoundException());
		bookRepository.delete(existingBook);
		return existingBook;
	}

	@Override
	public Book addBooks(Long supplierId, Book books) throws ResourceNotFoundException {
		Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(() -> new ResourceNotFoundException());
		books.setSupplier(supplier);
		Book finalBooks = bookRepository.save(books);
		return finalBooks;
	}

	@Override
	public Book AddBooksBySupplierName(String supplierName, Book books) {
		Supplier supplier = supplierRepository.findBySupplierName(supplierName);
		books.setSupplier(supplier);
		Book finalBooks = bookRepository.save(books);
		return finalBooks;
	}

	@Override
	public List<Book> getBooksBySupplierName(String supplierName) {
		Supplier supplier = supplierRepository.findBySupplierName(supplierName);
		List<Book> books = supplier.getBooks();
		return books;
	}

	@Override
	public Book deleteByName(String bookName) {
		Book exisitingBook = bookRepository.findByBookName(bookName);
		bookRepository.delete(exisitingBook);
		return exisitingBook;
	}

}
