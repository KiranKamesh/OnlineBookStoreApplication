package com.mouritech.onlinebookstoreapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
	
	private String bookName;
	
	private Double bookPrice;
	
	private String bookAuthor;
	
	private int noOfCopies;
	
	private String supplierName;

}
