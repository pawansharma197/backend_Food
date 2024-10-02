package com.mealOrdering.model;

import java.util.List;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
	
	@Id      
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	private String fullName;
	private String email;
	private String password;
	private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL , orphanRemoval = true , mappedBy = "customer")
	private List<Order> orders = new ArrayList<>();
	
	@ElementCollection
	private List<RestaurantsDto> favorites = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
	private List<Address> addresses = new ArrayList<>() ;
	private String status;
	
	public USER_ROLE getRole() {
		// TODO Auto-generated method stub
		return role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}	
	
	public String getFullName() {
		return fullName;
	}	
	
	public void setRole(USER_ROLE role)
	{
		this.role = role;
	}
	
	
	
	

}
