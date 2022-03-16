package com.mouritech.onlinebookstoreapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouritech.onlinebookstoreapp.entity.Book;
import com.mouritech.onlinebookstoreapp.entity.Cart;
import com.mouritech.onlinebookstoreapp.entity.Customer;
import com.mouritech.onlinebookstoreapp.entity.Supplier;
import com.mouritech.onlinebookstoreapp.exception.ResourceNotFoundException;
import com.mouritech.onlinebookstoreapp.repository.CartRepository;
import com.mouritech.onlinebookstoreapp.repository.CustomerRepository;
import com.mouritech.onlinebookstoreapp.repository.SupplierRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	@Override
	public Cart insertCart(Cart newCart) {
		return cartRepository.save(newCart);
	}

	@Override
	public List<Cart> showAllCarts() {
		return cartRepository.findAll();
	}

	@Override
	public Cart showCartById(Long cartId) throws ResourceNotFoundException {
		return cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException());
	}

	@Override
	public Cart updateCartById(Long cartId, Cart cart) throws ResourceNotFoundException {
		Cart existingCart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException());
		existingCart.setBooks(cart.getBooks());
		cartRepository.save(existingCart);
		return existingCart;
	}

	@Override
	public Cart deleteCartById(long cartId) throws ResourceNotFoundException {
		Cart existingCart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException());
		cartRepository.delete(existingCart);
		return existingCart;
	}

	@Override
	public Cart createCart(String customerName, String supplierName, String bookName) {

		List<Book> myBooks = new ArrayList<>();

		Customer existingcustomer = customerRepository.findByCustomerName(customerName);
		Supplier supplier = supplierRepository.findBySupplierName(supplierName);
		List<Book> supplierBooks = supplier.getBooks();
		for (Book result : supplierBooks) {
			if (result.getBookName().equals(bookName)) {
				Cart cart = new Cart();
				myBooks.add(result);
				cart.setCartId(generateCartId());
				cart.setBooks(myBooks);
				cart.setCustomer(existingcustomer);
				cartRepository.save(cart);
				return cart;
			}

		}
		return null;
	}

	public long generateCartId() {
		Random rand = new Random();
		int upperbound = 255;
		Long cId = (long) rand.nextInt(upperbound);
		return cId;

	}

	@Override
	public Cart addBooksInCart(Long cartId, String supplierName, String bookName) throws ResourceNotFoundException {
		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException());
		Supplier supplier = supplierRepository.findBySupplierName(supplierName);
		List<Book> supplierBooks = supplier.getBooks();
		for (Book result : supplierBooks) {
			if (result.getBookName().equals(bookName)) {
				List<Book> books = cart.getBooks();
				books.add(result);
				cartRepository.save(cart);
				return cart;
			}
		}
		return null;
	}

	@Override
	public Cart createCartAndAddItemsQuantity(String customerName, String supplierName, String bookName,
			int booksQuantityCart) {
		List<Book> myBooks = new ArrayList<>();

		Customer existingcustomer = customerRepository.findByCustomerName(customerName);
		Supplier supplier = supplierRepository.findBySupplierName(supplierName);
		List<Book> supplierBooks = supplier.getBooks();
		for (Book result : supplierBooks) {
			if (result.getBookName().equals(bookName)) {
				Cart cart = new Cart();
				myBooks.add(result);
				cart.setCartId(generateCartId());
				cart.setBooks(myBooks);
				cart.setCustomer(existingcustomer);
				// foodCart.setItemsQuantityForCart(itemsQuantityForCart);

				cartRepository.save(cart);

				return cart;
			}

		}
		return null;
	}

}
