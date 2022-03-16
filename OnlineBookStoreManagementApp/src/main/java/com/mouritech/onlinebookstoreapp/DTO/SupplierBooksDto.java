package com.mouritech.onlinebookstoreapp.DTO;

import java.util.List;

import com.mouritech.onlinebookstoreapp.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierBooksDto {
	
	private String supplierName;
	
	private String supplierEmail;
	
	private List<Book> books;

}
