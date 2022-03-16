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

import com.mouritech.onlinebookstoreapp.entity.Cart;
import com.mouritech.onlinebookstoreapp.exception.ResourceNotFoundException;
import com.mouritech.onlinebookstoreapp.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService cartService;

	@PostMapping("/add-cart")
	public Cart insertCart(@RequestBody Cart newCart) {
		return cartService.insertCart(newCart);
	}

	@GetMapping("/get-allcarts")
	public List<Cart> showAllCarts() {
		return cartService.showAllCarts();
	}

	@GetMapping("getcart-bycartid/{cartid}")
	public Cart showCartById(@PathVariable("cartid") Long CartId) throws ResourceNotFoundException {
		return cartService.showCartById(CartId);
	}

	@PutMapping("update-cart/{cartid}")
	public Cart updateCartById(@PathVariable("cartd") Long CartId, @RequestBody Cart Cart)
			throws ResourceNotFoundException {
		return cartService.updateCartById(CartId, Cart);
	}

	@DeleteMapping("delete-cart/{cartid}")
	public Cart deleteCartById(@PathVariable("cartid") long CartId) throws ResourceNotFoundException {
		return cartService.deleteCartById(CartId);
	}

	@PostMapping("/create-cart-using-customernameandbook/{customername}/{suppliername}/{bookname}")
	public Cart createCart(@PathVariable("customername") String customerName,
			@PathVariable("suppliername") String supplierName, @PathVariable("bookname") String bookName) {
		return cartService.createCart(customerName, supplierName, bookName);
	}

	@PutMapping("/cart-addnewbooks/{cartid}/{suppliername}/{bookname}")
	public Cart addBooksInCart(@PathVariable("cartid") Long cartId, @PathVariable("suppliername") String supplierName,
			@PathVariable("bookname") String bookName) throws ResourceNotFoundException {
		return cartService.addBooksInCart(cartId, supplierName, bookName);
	}

	@PostMapping("/create-cart-using-customername-books-quantity/{customername}/{suppliername}/{bookname}/{booksquantitycart}")
	public Cart createCartAndAddItemsQuantity(@PathVariable("customername") String customerName,
			@PathVariable("suppliername") String supplierName, @PathVariable("bookname") String bookName,
			@PathVariable("booksquantitycart") int booksQuantityCart) {
		return cartService.createCartAndAddItemsQuantity(customerName, supplierName, bookName, booksQuantityCart);

	}

}
