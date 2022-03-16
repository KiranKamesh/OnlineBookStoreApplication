package com.mouritech.onlinebookstoreapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mouritech.onlinebookstoreapp.entity.Book;
import com.mouritech.onlinebookstoreapp.exception.ResourceNotFoundException;
import com.mouritech.onlinebookstoreapp.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	BookService bookService;

	@PostMapping("/add-books")
	public Book addBooks(@RequestBody Book book) {
		return bookService.addBooks(book);
	}

	@GetMapping("/getAll")
	public List<Book> getAll() {
		return bookService.getAllItems();
	}

	@GetMapping("/get-by-bookisbn/{bookisbn}")
	public Book getByISBN(@PathVariable(value = "bookisbn") Long bookISBN) throws ResourceNotFoundException {
		return bookService.findByISBN(bookISBN);
	}

	@PutMapping("/update-by-bookisbn/{bookisbn}")
	public Book updateByISBN(@PathVariable(value = "bookisbn") Long bookISBN, @RequestBody Book book)
			throws ResourceNotFoundException {
		return bookService.updateByISBN(book, bookISBN);
	}

	@DeleteMapping("/delete-by-bookisbn/{bookisbn}")
	public Book deleteBookByISBN(@PathVariable("bookisbn") Long bookISBN) throws ResourceNotFoundException {
		return bookService.deleteBookByISBN(bookISBN);
	}

	@PostMapping("/add-books-by-supplierid/{supplierid}")
	public Book addBooks(@PathVariable("supplierid") Long supplierId, @RequestBody Book books)
			throws ResourceNotFoundException {
		return bookService.addBooks(supplierId, books);
	}

	@PostMapping("add-books-by-suppliername/{suppliername}")
	public Book AddBooksBySupplierName(@PathVariable("suppliername") String supplierName,@RequestBody Book books) {
		return bookService.AddBooksBySupplierName(supplierName,books);
	}
	
	@GetMapping("/get-books/{suppliername}")
	public List<Book> getBooksBySupplierName(@PathVariable("suppliername") String supplierName){
		return bookService.getBooksBySupplierName(supplierName);
	}
	
	@DeleteMapping("/deleteby-bookname/{bookname}")
	public Book deleteByName(@PathVariable("bookname") String bookName) {
		return bookService.deleteByName(bookName);
	}

}
