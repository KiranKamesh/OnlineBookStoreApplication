package com.mouritech.onlinebookstoreapp.entity;

import java.time.LocalDate;



import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "invoice_table")
public class Invoice {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long invoiceId;
	
	private LocalDate invoiceDate;
	
	private int totalBooks;
	
	private double totalCost;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="order_id" )
    private OrderDetails orderDetails;
	
	

}
