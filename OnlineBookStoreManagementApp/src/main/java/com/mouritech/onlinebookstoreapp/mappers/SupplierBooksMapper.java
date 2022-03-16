package com.mouritech.onlinebookstoreapp.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mouritech.onlinebookstoreapp.DTO.SupplierBooksDto;
import com.mouritech.onlinebookstoreapp.entity.Supplier;

@Component
public class SupplierBooksMapper {

	@Autowired
	ModelMapper mappers;
	
	public SupplierBooksDto supplierToSupplierBooksDto(Supplier supplier) {
		return mappers.map(supplier, SupplierBooksDto.class);
		
	}
	
	public Supplier supplierBooksDtoToSupplier(SupplierBooksDto supplierBooksDto) {
		return mappers.map(supplierBooksDto, Supplier.class);
	}
}
