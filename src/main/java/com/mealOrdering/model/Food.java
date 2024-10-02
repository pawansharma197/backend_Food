package com.mealOrdering.model;

import java.util.List;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Locale.Category;

import org.antlr.v4.runtime.misc.TestRig;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Food {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String description;
	
	private Long price;
	
	@ManyToOne
	private FoodCategory foodCategory;
	
	@Column(length = 1000)
	@ElementCollection           // separate table for food images
	private List<String> image;
	
	private boolean available;
	
	@ManyToOne 
	private Restaurant restaurant;
	
	private boolean isVegetarian ; 
	private boolean isSeasonal;
	
	@ManyToMany
	private List<IngredientsItem> ingredientsItems = new ArrayList<>();
	
	private Date creationDate;
	
	
	

}
