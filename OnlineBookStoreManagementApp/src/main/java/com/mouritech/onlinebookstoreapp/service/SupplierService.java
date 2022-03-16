package com.mouritech.onlinebookstoreapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mouritech.onlinebookstoreapp.DTO.SupplierVerificationDto;
import com.mouritech.onlinebookstoreapp.entity.Book;
import com.mouritech.onlinebookstoreapp.entity.Supplier;
import com.mouritech.onlinebookstoreapp.exception.ResourceNotFoundException;

public interface SupplierService {

	Supplier addSupplier(Supplier supplier);

	Supplier findSupplierByEmailAndPassword(String supplierEmail, String supplierPassword);

	List<Supplier> getAllSuppliers();

	Supplier getSupplierById(Long supplierId) throws ResourceNotFoundException;

	Supplier updateSupplierById(Long supplierId, Supplier supplier) throws ResourceNotFoundException;

	Supplier deleteSupplier(Long supplierId) throws ResourceNotFoundException;

	Book addBooksBySupplier(Long supplierId, Book book) throws ResourceNotFoundException;

	ResponseEntity<Supplier> getSupplierInfoAndBooks(String supplierName);

	ResponseEntity<Supplier> deleteByBooksusingSupplierName(String supplierName, String bookName) throws ResourceNotFoundException;

	ResponseEntity<Supplier> updateByBooksusingSupplierName(String supplierName, String bookName, Book books) throws ResourceNotFoundException;

	ResponseEntity<?> updateSupplierinfoByName(Supplier supplier);

	ResponseEntity<?> checkSupplierEmailAndPassword(SupplierVerificationDto supplierVerificationDto);


}
