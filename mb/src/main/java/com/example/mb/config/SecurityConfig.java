package com.example.mb.config;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.mb.service.MyUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private MyUserService myUserService;
	
	@Autowired
	private JwtFilter jwtFilter;
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf(csrf->csrf.disable())
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/api/auth/token/generate").permitAll()	
				.requestMatchers("/api/auth/user/details").authenticated()
				.requestMatchers("/api/auth/signup").permitAll()
				.requestMatchers("/api/newuser/signup").permitAll()
				.requestMatchers("/api/customer/add/{bid}").permitAll()
				.requestMatchers("/api/get/ifsc/{ifsc}").permitAll()
				.requestMatchers("/api/get/{id}").permitAll()
				.requestMatchers("/api/account/add/{branchId}/{customerId}").permitAll()
				.requestMatchers("/api/beneficiary/add/{customerId}").hasAuthority("CUSTOMER")
				.requestMatchers("/api/beneficiaries/customer/{customerId}").hasAnyAuthority("CUSTOMER", "ADMIN")
				.requestMatchers("/api/beneficiary/{beneficiaryId}").hasAnyAuthority("CUSTOMER", "ADMIN")
				.requestMatchers("/api/beneficiary/update/{id}").hasAuthority("CUSTOMER")
				.requestMatchers("/api/beneficiary/delete/{id}").hasAuthority("CUSTOMER")
				.requestMatchers("/api/service-request/raise/{customerId}").permitAll()
				.requestMatchers("/api/service-request/customer/{customerId}").permitAll()
				.requestMatchers("/api/service-request/{requestId}").permitAll()
				.requestMatchers("/api/service-request/delete/{requestId}").permitAll()
				.requestMatchers("/api/transaction-limit/add").permitAll()
				.requestMatchers("/api/transaction-limit/account/{accountNumber}").permitAll()
				.requestMatchers("/api/transaction-limit/update/{accountId}").permitAll()
				.requestMatchers("/api/transactions/upi").hasAuthority("CUSTOMER")  // UPI transaction
                .requestMatchers("/api/transactions/bank-transfer").hasAuthority("CUSTOMER")  // Bank transfer
                .requestMatchers("/api/transactions/history/{accountId}").hasAuthority("CUSTOMER")  // View transaction history for a specific account

				   
				.requestMatchers("/api/employees/**").hasAuthority("ADMIN")
				.requestMatchers("/api/branches/**").permitAll()
				.requestMatchers("/api/departments/**").permitAll()
				
				.anyRequest().authenticated()
			)
			.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		;
		return http.build();
	}
	
	@Bean
	AuthenticationProvider getAuth() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(passEncoder());
		dao.setUserDetailsService(myUserService);	
		return dao;
	}
	
	@Bean
	BCryptPasswordEncoder passEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager getAuthManager(AuthenticationConfiguration auth) throws Exception {
		  return auth.getAuthenticationManager();
	 }
	//RBAC
}
