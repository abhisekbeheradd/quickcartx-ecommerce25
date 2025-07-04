package com.jpa.demo.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.jpa.demo.modelentity.Role;
import com.jpa.demo.modelentity.User;
import com.jpa.demo.repository.RoleRepository;
import com.jpa.demo.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class GoogleOAuth2SuccessHandle implements AuthenticationSuccessHandler {
@Autowired
RoleRepository roleRepository;
@Autowired
UserRepository userRepository;


private RedirectStrategy redirectStrategy;
@Override
public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {

	OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
	String email = token.getPrincipal().getAttributes().get("email").toString();
	if(userRepository.findUserByEmail(email).isPresent()) {
		
	} else 
	{
		User user = new User();
		user.setFirstName(token.getPrincipal().getAttributes().get("give_name").toString());
		user.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());
		user.setEmail(email);
		List<Role> roles = new ArrayList<>();
		roles.add(roleRepository.findById(2).get());
		
		user.setRoles(roles);
		userRepository.save(user);
	}
	
	redirectStrategy.sendRedirect(request, response, "/");
	
	
}

}



