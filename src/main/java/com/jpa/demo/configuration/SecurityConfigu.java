package com.jpa.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.jpa.demo.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfigu {

	@Autowired
    GoogleOAuth2SuccessHandle googleOAuth2SuccessHandle;
	@Autowired

	CustomUserDetailsService customUserDetailsService;
	
	

	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/", "/shop/**", "/register", "/h2-console/**").permitAll()
              .requestMatchers("/admin/**").hasRole("ADMIN")
              .anyRequest()
              .authenticated())
		.formLogin(login -> login
              .loginPage("/login")
              .permitAll()
              .failureUrl("/login?error= true")
              .defaultSuccessUrl("/")
              .usernameParameter("email")
              .passwordParameter("password"))
		.oauth2Login(login -> login
				.loginPage("/login")
				.successHandler(googleOAuth2SuccessHandle))
		 .logout(logout -> logout
               .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
               .logoutSuccessUrl("/login"));
//		 .exceptionHandling(excption -> excption
//	             .ex
//		  .sessionManagement(session -> session
//                 .invalidateHttpSession(true)
//			    .deleteCookies("JSESSIONID"));
//              .loginPage("/login")
//              .successHandler(googleOAuth2SuccessHandle))
//				Customizer.withDefaults());

		return http.httpBasic(Customizer.withDefaults())
              .csrf(AbstractHttpConfigurer::disable)
              .build();
	}
	
	
	
    @Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
       
		auth.userDetailsService(customUserDetailsService);

    }

 public void configureGlobal(WebSecurity web) throws Exception {
       web.ignoring().requestMatchers("/resources/**","/images/**", "/productimages/**", "/css/**","/js/**");

    }
}