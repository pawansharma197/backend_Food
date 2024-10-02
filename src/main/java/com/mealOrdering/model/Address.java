package com.mealOrdering.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity

public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;
	
	
	
	
	

}
