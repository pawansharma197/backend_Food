package com.mealOrdering.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mealOrdering.model.USER_ROLE;
import com.mealOrdering.model.User;
import com.mealOrdering.repository.UserRepository;
import java.util.List;
import java.util.ArrayList;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Service
public class CustomerUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("User not found with email "+ username);
		}
		
		USER_ROLE role = user.getRole();
		
		
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		authorities.add(new SimpleGrantedAuthority(role.toString()));
		
		return new  org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
	}

}
