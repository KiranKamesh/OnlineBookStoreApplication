package com.mouritech.onlinebookstoreapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mouritech.onlinebookstoreapp.DTO.SupplierVerificationDto;
import com.mouritech.onlinebookstoreapp.entity.Book;
import com.mouritech.onlinebookstoreapp.entity.Supplier;
import com.mouritech.onlinebookstoreapp.exception.ResourceNotFoundException;
import com.mouritech.onlinebookstoreapp.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	SupplierService supplierService;

	@GetMapping("/get-supplier/{supplieremail}/{supplierpassword}")
	public ResponseEntity<?> findSupplierByEmailAndPassword(@PathVariable(value = "supplieremail") String supplierEmail,
			@PathVariable(value = "supplierpassword") String supplierPassword) {

		Supplier result = supplierService.findSupplierByEmailAndPassword(supplierEmail, supplierPassword);
		if (result != null) {
			return ResponseEntity.ok().body("login sucessfull , Welcome  " + result.getSupplierName());
		}

		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("supplier not found please sign in first");

		}

	}

	@PutMapping("/update-supplierinfo-by-name")
	public ResponseEntity<?> updateSupplierinfoByName(@RequestBody Supplier supplier) {
		ResponseEntity<?> result = supplierService.updateSupplierinfoByName(supplier);
		return result;
	}

	@GetMapping("/get-supplier-byemailandpassword-check")
	public ResponseEntity<?> checkSupplierEmailAndPassword(
			@RequestBody SupplierVerificationDto supplierVerificationDto) {
		if (supplierVerificationDto == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please Enter Email and password");
		} else {
			ResponseEntity<?> checkLogin = supplierService.checkSupplierEmailAndPassword(supplierVerificationDto);
			return checkLogin;
		}
	}
//
//	@PostMapping("/insertitemsByrestaurantemaiandpassword")
//	public ResponseEntity<?> insertItems(@RequestBody RestaurantItemsDto restaurantItemsDto) {
//
//		if (restaurantItemsDto == null) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please Enter Email and password, items");
//		} else {
//
//			ResponseEntity<?> itemsInsert = restaurantService.insertItems(restaurantItemsDto);
//			return itemsInsert;
//
//		}
//
//	}
//	

	@PostMapping("/add-supplier")
	public Supplier addSupplier(@RequestBody Supplier supplier) {
		return supplierService.addSupplier(supplier);
	}

	@GetMapping("/get-allsuppliers")
	public List<Supplier> getAllSuppliers() {
		return supplierService.getAllSuppliers();

	}

	@GetMapping("/get-by-supplierid/{supplierid}")
	public Supplier getSupplierById(@PathVariable(value = "supplierid") Long supplierId)
			throws ResourceNotFoundException {
		return supplierService.getSupplierById(supplierId);
	}

	@PutMapping("/update-supplier/{supplierid}")
	public Supplier updateSupplierById(@PathVariable(value = "supplierid") Long supplierId,
			@RequestBody Supplier supplier) throws ResourceNotFoundException {
		return supplierService.updateSupplierById(supplierId, supplier);
	}

	@DeleteMapping("/delete-supplier/{supplierid}")
	public Supplier deleteSupplier(@PathVariable(value = "supplierid") Long supplierId)
			throws ResourceNotFoundException {
		return supplierService.deleteSupplier(supplierId);
	}

	@PostMapping("/add-books/{supplierid}")
	public Book addBooksBySupplier(@PathVariable(value = "supplierid") Long supplierId, @RequestBody Book book)
			throws ResourceNotFoundException {
		return supplierService.addBooksBySupplier(supplierId, book);
	}

	@GetMapping("/get-supplier-books/{suppliername}")
	public ResponseEntity<Supplier> getSupplierInfoAndBooks(@PathVariable(value = "suppliername") String supplierName) {
		return supplierService.getSupplierInfoAndBooks(supplierName);

	}

	@DeleteMapping("/delete-books/{suppliername}/{bookname}")
	public ResponseEntity<Supplier> deleteByBooksusingSupplierName(
			@PathVariable(value = "suppliername") String supplierName,
			@PathVariable(value = "bookname") String bookName) throws ResourceNotFoundException {

		return supplierService.deleteByBooksusingSupplierName(supplierName, bookName);
	}

	@PutMapping("update-books/{suppliername}/{bookname}")
	public ResponseEntity<Supplier> updateByBooksusingSupplierName(
			@PathVariable(value = "suppliername") String supplierName,
			@PathVariable(value = "bookname") String bookName, @RequestBody Book books)
			throws ResourceNotFoundException {

		return supplierService.updateByBooksusingSupplierName(supplierName, bookName, books);
	}

}
