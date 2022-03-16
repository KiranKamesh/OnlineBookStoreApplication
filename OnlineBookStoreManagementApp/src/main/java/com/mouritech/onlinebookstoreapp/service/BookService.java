package com.mouritech.onlinebookstoreapp.service;

import java.util.List;

import com.mouritech.onlinebookstoreapp.entity.Book;
import com.mouritech.onlinebookstoreapp.exception.ResourceNotFoundException;

public interface BookService {

	Book addBooks(Book book);

	List<Book> getAllItems();

	Book findByISBN(Long bookISBN) throws ResourceNotFoundException;

	Book updateByISBN(Book book, Long bookISBN) throws ResourceNotFoundException;

	Book deleteBookByISBN(Long bookISBN) throws ResourceNotFoundException;

	Book addBooks(Long supplierId, Book books) throws ResourceNotFoundException;

	Book AddBooksBySupplierName(String supplierName, Book books);

	List<Book> getBooksBySupplierName(String supplierName);

	Book deleteByName(String bookName);

}
