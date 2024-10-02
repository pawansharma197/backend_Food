package com.mealOrdering.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name ="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ; 
	
	@ManyToOne
	private User customer;
	
	@JsonIgnore
	@ManyToOne
	private Restaurant restaurant;
	
	private Long totalAmount;
	private String orderStatuString;
	private Date createdAt ;
	
	@ManyToOne
	private Address deliverAddress;
	
	@OneToMany
	private List<OrderItem> items;
	
	//private Payment payment   1:008
	private int TotalItem;
	private int TotalPrice ;
	
	

}
