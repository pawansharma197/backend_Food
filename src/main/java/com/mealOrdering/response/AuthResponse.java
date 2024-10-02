package com.mealOrdering.response;

import com.mealOrdering.model.USER_ROLE;

import lombok.Data;

@Data
public class AuthResponse {
	
	private String jwt;
	private String message ;
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public USER_ROLE getRole() {
		return role;
	}
	public void setRole(USER_ROLE role) {
		this.role = role;
	}
	private USER_ROLE  role ;

}
