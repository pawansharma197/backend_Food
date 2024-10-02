package com.mealOrdering.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Restaurant {
	
	@Id      
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	
	@OneToOne
	private User owner;
	private String name;
	
	private String description;
	
	private String cuisineType;
	
	@OneToOne
	private Address address;
	
	@Embedded
	private ContactInformation contactInformation;
	
	private String openingHour;
	
	@OneToMany(mappedBy = "restaurant" , cascade = CascadeType.ALL , orphanRemoval = true )
	private List<Order> orders = new ArrayList<>();
	
	@ElementCollection
	@Column(length = 1000)
	private List<String> image;
	private LocalDateTime registrationDate;
	private boolean open ;
	
	@OneToMany(mappedBy = "restaurant" , cascade = CascadeType.ALL , orphanRemoval = true)
	@JsonIgnore
	private List<Food> foods = new ArrayList<>();
	
	
	
	

}
