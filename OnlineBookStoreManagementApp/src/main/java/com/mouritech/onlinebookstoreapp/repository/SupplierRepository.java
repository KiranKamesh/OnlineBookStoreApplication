package com.mouritech.onlinebookstoreapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouritech.onlinebookstoreapp.entity.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>{

	Supplier findBySupplierEmailAndSupplierPassword(String supplierEmail, String supplierPassword);


	Supplier findBySupplierName(String supplierName);

}
