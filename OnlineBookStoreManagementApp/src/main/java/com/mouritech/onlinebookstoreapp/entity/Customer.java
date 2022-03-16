package com.mouritech.onlinebookstoreapp.entity;

import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

	private String customerName;

	@Column(name = "customer_Email", nullable = false)
	private String customerEmail;

	@Column(name = "customer_password")
	private String customerPassword;

	@Column(name = "customer_mobileNumber", nullable = false)
	private String customerMobileNumber;

	@OneToOne(mappedBy = "customer", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JsonIgnore
	private Cart cart;

}
