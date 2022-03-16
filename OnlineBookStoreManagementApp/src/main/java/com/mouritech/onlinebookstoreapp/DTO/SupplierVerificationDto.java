package com.mouritech.onlinebookstoreapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierVerificationDto {
	
	private String supplierEmail;
	
	private String supplierPassword;

}
