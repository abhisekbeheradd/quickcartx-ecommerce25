package com.jpa.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jpa.demo.modelentity.CustomUserDetails;
import com.jpa.demo.modelentity.User;
import com.jpa.demo.repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService{
  @Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user = userRepository.findUserByEmail(email);
		user.orElseThrow(() -> new UsernameNotFoundException("User to nehin mila"));
		return user.map(CustomUserDetails::new).get();
	}

}
