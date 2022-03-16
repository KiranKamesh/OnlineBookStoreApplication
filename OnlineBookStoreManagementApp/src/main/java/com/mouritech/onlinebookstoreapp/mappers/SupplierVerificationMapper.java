package com.mouritech.onlinebookstoreapp.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mouritech.onlinebookstoreapp.DTO.SupplierVerificationDto;
import com.mouritech.onlinebookstoreapp.entity.Supplier;

@Component
public class SupplierVerificationMapper {
	
	@Autowired
	ModelMapper mappers;
	
	public SupplierVerificationDto supplierToSupplierVerificationDto(Supplier supplier) {
		return mappers.map(supplier, SupplierVerificationDto.class);
	}
	
	public Supplier supplierVerifactionDtoToSupplier(SupplierVerificationDto supplierVerificationDto) {
		return mappers.map(supplierVerificationDto, Supplier.class);
	}

}
