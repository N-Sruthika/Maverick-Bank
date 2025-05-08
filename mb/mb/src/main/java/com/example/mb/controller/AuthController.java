package com.example.mb.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mb.config.JwtUtil;
import com.example.mb.dto.TokenDto;
import com.example.mb.exception.InvalidUsernameException;
import com.example.mb.model.User;
import com.example.mb.service.AuthService;
import com.example.mb.service.MyUserService;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = {"http://localhost:5173"})
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthService authService;
	@Autowired
	private MyUserService myUserService;
	Logger logger =  LoggerFactory.getLogger("AuthController"); 
	@PostMapping("/signup")
	public User signUp(@RequestBody User user) throws InvalidUsernameException {
		logger.info("Sign up in progress for User " + user.getUsername()); 
		return authService.signUp(user);
	}
	
	@GetMapping("/login")
	public UserDetails login(Principal principal) {
		
		String username = principal.getName();
		logger.debug("Username given " + username);
		return myUserService.loadUserByUsername(username);
	}
	@PostMapping("/token/generate")
	public TokenDto generateToken(@RequestBody User user,TokenDto dto) {
		/*Step 1. Build authentication ref based on username,passord given*/
		Authentication auth = 
		new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
	
		authenticationManager.authenticate(auth);
		
		/*Step 2: Generate the token since we know that credentials are correct */
		String token =  jwtUtil.generateToken(user.getUsername()); 
		dto.setToken(token);
		dto.setUsername(user.getUsername());
		dto.setExpiry(jwtUtil.extractExpiration(token).toString());
		logger.info("Token generated for User " + user.getUsername()); 
 		logger.warn("Token will expiry On " + jwtUtil.extractExpiration(token).toString());
		return dto; 
	}
	
	@GetMapping("/user/details")
	public UserDetails getUserDetails(Principal principal) {
		String username = principal.getName();
		return myUserService.loadUserByUsername(username);
	}
	@PostMapping("/api/reset")
	public ResponseEntity<?> reset(@RequestBody User user,Principal principal) {
		//get the username from principal and fetch the info from db
		String username = principal.getName();
 		authService.reset(username,user);
 		return ResponseEntity.ok("Password reseted successfully...");
	}

	
}