package com.mealOrdering.controller;

import java.util.Collections;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mealOrdering.config.JwtProvider;
import com.mealOrdering.model.Cart;
import com.mealOrdering.model.USER_ROLE;
import com.mealOrdering.model.User;
import com.mealOrdering.repository.CartRepository;
import com.mealOrdering.repository.UserRepository;
import com.mealOrdering.request.LoginRequest;
import com.mealOrdering.response.AuthResponse;
import com.mealOrdering.service.CustomerUserDetailsService;
import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private CustomerUserDetailsService customerUserDetailsService;	
	@Autowired
	private CartRepository cartRepository;
	
	// Sign Up Method 
	
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception 
	{
		User isEmailExist = userRepository.findByEmail(user.getEmail());
		
		if(isEmailExist!=null)
		{
			throw new Exception("Email is already Used with Another Account");
		}
		
		User createdUser = new User();
		createdUser.setEmail(user.getEmail());
		createdUser.setFullName(user.getFullName());
		createdUser.setRole(user.getRole());
		createdUser.setPassword(passwordEncoder.encode(  user.getPassword()));
		
		
		User savedUser = userRepository.save(createdUser);		
		Cart cart = new Cart();		
		cart.setCustomer(savedUser);
		cartRepository.save(cart);
		
		
		// Authentication
		
		Authentication authentication = new  UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		// Authentication detail pass to Authentication Manager
		// AuthenticationManager pass the detail to AuthenicationProvider
		// Authentication Provider checks the detail , if okay  save it to SecurityContextHolder and generate JWt Token for later use.
		// if not okay , throw an exception.
		
		String jwt = jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(jwt);
		authResponse.setMessage("Register Success");
		authResponse.setRole(user.getRole());		
		
		return new ResponseEntity<>(authResponse,HttpStatus.CREATED);
				
					
	}
	
	
	@PostMapping("/singin")
	
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest)
	{
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		Authentication authentication = authenticate(username,password);
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String role = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();
		
        String jwt = jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(jwt);
		authResponse.setMessage("Register Success");
		
		
		
		authResponse.setRole(USER_ROLE.valueOf(role));				
		
		return new ResponseEntity<>(authResponse,HttpStatus.OK);
		
	}
	
	private Authentication authenticate(String username , String password) 
	{
		UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);
		if(userDetails == null)
		{
			throw new BadCredentialsException("Invalid Username....");
		}
		
		if(!passwordEncoder.matches(password, userDetails.getPassword()))
		{
			throw new BadCredentialsException("Invalid password....");
		}
			
		return new UsernamePasswordAuthenticationToken(
	            userDetails.getUsername(), 
	            null, 
	            userDetails.getAuthorities()
	    );
					
	}
	
	
	
	
	
	
	
	
	

}
