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
				.requestMatchers("/api/auth/user/details").permitAll()
				.requestMatchers("/api/auth/signup").permitAll()
				.requestMatchers("/api/reset").permitAll()
	              
                //Customersignup				
				.requestMatchers("/api/newuser/signup").permitAll()
				.requestMatchers("api/customer/signup/ifsc/{ifscCode}").hasAuthority("CUSTOMER")			
				.requestMatchers("/api/getall/{id}").hasAuthority("CUSTOMER")
				
				//customer profile			
				.requestMatchers("/api/customer/add/{bid}").hasAnyAuthority("ADMIN")
				.requestMatchers("/api/get/profile/{id}").hasAuthority("CUSTOMER")
				.requestMatchers("/api/get/details/{username}").hasAuthority("CUSTOMER")
				.requestMatchers("/api/getall/customer/{bid}").hasAuthority("CUSTOMER")
				
				//account					
				.requestMatchers("/api/account/add/{branchId}/{customerId}").hasAuthority("ADMIN")
				.requestMatchers("/api/account/get/balance/{accountNumber}").permitAll()
				.requestMatchers("/api/account/{accountId}").permitAll()
				.requestMatchers("/api/accounts/customer/{customerId}").hasAuthority("CUSTOMER")
				.requestMatchers("/api/account/update/{accountId}").hasAuthority("CUSTOMER")
				.requestMatchers("/api/account/deactivate/{accountId}").hasAuthority("CUSTOMER")
				.requestMatchers("/find/{accountNumber}").hasAuthority("CUSTOMER")
				.requestMatchers("/api/accounts/active/count/{customerId}").permitAll()
				
				
				
				//beneficiary 
				.requestMatchers("/api/beneficiary/add/{customerId}").permitAll()
				.requestMatchers("/api/beneficiaries/customer/{customerId}").permitAll()
				.requestMatchers("/api/beneficiary/{beneficiaryId}").hasAnyAuthority("CUSTOMER")
				.requestMatchers("/api/beneficiary/update/{beneficiaryid}").hasAuthority("CUSTOMER")
				.requestMatchers("/api/beneficiary/delete/{beneficiaryid}").permitAll()
				.requestMatchers("/api/beneficiaries/count").hasAuthority("CUSTOMER")
				.requestMatchers("/api/beneficiaries/count/{customerId}").permitAll()
				.requestMatchers("/api/beneficiaries/details/{customerId}").hasAuthority("CUSTOMER")
				
				//service request				
				.requestMatchers("/api/service-request/raise/{customerId}").hasAuthority("CUSTOMER")
				.requestMatchers("/api/service-request/customer/{customerId}").hasAnyAuthority("CUSTOMER", "ADMIN")
				.requestMatchers("/api/service-request/{requestId}").permitAll()
				.requestMatchers("/api/service-request/delete/{requestId}").permitAll()
				
				//transaction limit
				.requestMatchers("/api/transaction-limit/add/{accountId}").hasAnyAuthority("EMPLOYEE", "ADMIN")
				.requestMatchers("/api/transaction-limit/account/{accountNumber}").permitAll()
				.requestMatchers("/api/transaction-limit/update/{accountId}").hasAnyAuthority("EMPLOYEE", "ADMIN")
				
				.requestMatchers("/api/transactions/upi-transfer/{accountNumber}").hasAuthority("CUSTOMER") // UPI transaction
			    .requestMatchers("/api/transactions/bank-transfer/{accountNumber}").hasAuthority("CUSTOMER") // Bank transfer
			    .requestMatchers("/api/transactions/history/{accountId}").hasAuthority("CUSTOMER")
			    .requestMatchers("/api/transactions/account/history/{aid}").permitAll()
			    // View transaction history for a specific account				/account/history/{aid}   
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
