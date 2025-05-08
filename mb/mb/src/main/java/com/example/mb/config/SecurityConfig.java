package com.example.mb.config;

 
import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;
import java.util.List;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
		.cors(withDefaults())
		.csrf(csrf->csrf.disable())
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/api/auth/token/generate").permitAll()	
				.requestMatchers("/api/auth/user/details").authenticated()
				.requestMatchers("/api/auth/signup").permitAll()
				.requestMatchers("/api/reset").permitAll()
	              
                //Customersignup				
				.requestMatchers("/api/newuser/signup").permitAll()
				.requestMatchers("/api/getall/{id}").hasAuthority("CUSTOMER")
				
				//customer profile			
				.requestMatchers("/api/get/profile/{id}").hasAuthority("CUSTOMER")
				.requestMatchers("/api/get/details/{username}").hasAuthority("CUSTOMER")
				.requestMatchers("/api/customer/updateProfile/{id}").hasAuthority("CUSTOMER")
				
				//account					
				.requestMatchers("/api/account/get/balance/{accountNumber}").hasAuthority("CUSTOMER")//getting balance
				.requestMatchers("/api/account/get/balance/customer/{cid}").hasAuthority("CUSTOMER")
				
				.requestMatchers("/api/accounts/customer/{customerId}").hasAuthority("CUSTOMER")
				.requestMatchers("/api/account/update/{accountId}").hasAuthority("CUSTOMER")
				.requestMatchers("/api/account/deactivate/{accountId}").hasAuthority("CUSTOMER")
				.requestMatchers("/find/{accountNumber}").hasAuthority("CUSTOMER")
				.requestMatchers("/api/accounts/active/count/{customerId}").hasAuthority("CUSTOMER")
				.requestMatchers("/api/accounts").permitAll()
				
				
				
				//beneficiary 
				.requestMatchers("/api/beneficiary/add/{customerId}").hasAnyAuthority("CUSTOMER")
				.requestMatchers("/api/beneficiaries/customer/{customerId}").hasAnyAuthority("CUSTOMER")
				.requestMatchers("/api/beneficiary/{beneficiaryId}").hasAnyAuthority("CUSTOMER")
				.requestMatchers("/api/beneficiary/update/{beneficiaryid}").hasAnyAuthority("CUSTOMER")
				.requestMatchers("/api/beneficiary/delete/{beneficiaryid}").hasAnyAuthority("CUSTOMER")
				.requestMatchers("/api/beneficiaries/count").hasAuthority("CUSTOMER")
				.requestMatchers("/api/beneficiaries/count/{customerId}").hasAuthority("CUSTOMER")//get balance
				.requestMatchers("/api/beneficiaries/details/{customerId}").hasAuthority("CUSTOMER")
				
				//service request				
				.requestMatchers("/api/service-request/raise/{customerId}").hasAuthority("CUSTOMER")
				.requestMatchers("/api/service-request/customer/{customerId}").hasAuthority("CUSTOMER")
				.requestMatchers("/api/service-request/{requestId}").permitAll()
				.requestMatchers("/api/service-request/delete/{requestId}").permitAll()
				
				.requestMatchers("/api/transactions/upi-transfer/{accountNumber}").hasAuthority("CUSTOMER") // UPI transaction
			    .requestMatchers("/api/transactions/bank-transfer/{accountNumber}").hasAuthority("CUSTOMER") // Bank transfer
			    .requestMatchers("/api/transactions/history/{accountId}").hasAuthority("CUSTOMER")
			    .requestMatchers("/api/transactions/customer/{customerId}").hasAuthority("CUSTOMER")
			    .requestMatchers("/api/transactions/customer/history/{customerId}").hasAuthority("CUSTOMER")
			    .requestMatchers("/swagger-ui/**").permitAll()
				 	
				.anyRequest().permitAll()
				
			)
			.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		;
		return http.build();
	}
	
	@Bean
	UrlBasedCorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
	    configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
	    configuration.setAllowedHeaders(List.of("*"));
	    configuration.setAllowCredentials(true);
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
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
