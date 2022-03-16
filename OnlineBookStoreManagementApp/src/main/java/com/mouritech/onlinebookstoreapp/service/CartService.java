package com.mouritech.onlinebookstoreapp.service;

import java.util.List;

import com.mouritech.onlinebookstoreapp.entity.Cart;
import com.mouritech.onlinebookstoreapp.exception.ResourceNotFoundException;

public interface CartService {

	Cart insertCart(Cart newCart);

	List<Cart> showAllCarts();

	Cart showCartById(Long cartId) throws ResourceNotFoundException;

	Cart updateCartById(Long cartId, Cart cart) throws ResourceNotFoundException;

	Cart deleteCartById(long cartId) throws ResourceNotFoundException;

	Cart createCart(String customerName, String supplierName, String bookName);

	Cart addBooksInCart(Long cartId, String supplierName, String bookName) throws ResourceNotFoundException;

	Cart createCartAndAddItemsQuantity(String customerName, String supplierName, String bookName,
			int booksQuantityCart);

}
