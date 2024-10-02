package com.mealOrdering.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class RestaurantsDto {
	
	private String title ;
	@Column(length = 1000)
	private List<String>  image;
	
	private String  description;
	private Long id;
	
	

}
