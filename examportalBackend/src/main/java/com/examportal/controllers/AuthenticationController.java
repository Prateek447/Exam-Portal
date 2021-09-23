package com.examportal.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.config.JwtUtil;
import com.examportal.models.JwtRequest;
import com.examportal.models.JwtResponse;
import com.examportal.models.User;
import com.examportal.servicesImpl.UserDetailServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PostMapping("/generate-token")
	public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest request) throws Exception{
		
		try {
			
		//	authenticate(request.getUsername(), request.getPassword());
			System.out.println("Try to authenticate");
			Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
	       System.out.println("After authentication-->"+authenticate.isAuthenticated());
			
		} catch (UsernameNotFoundException e) {
		   e.printStackTrace();
		   throw new Exception("user not found");
		}
		catch(DisabledException e) {
			throw new Exception("Disabled user -->"+e.getMessage());
		}
		catch (BadCredentialsException e) {
		  throw new Exception("Invalid Credentials--->" + e.getMessage());
		}
		catch(Exception e) {
			System.out.println("Authentication falied--->");
			e.printStackTrace();
		}
		System.out.println("Authentication Completed");
		UserDetails userDetails = this.userDetailServiceImpl.loadUserByUsername(request.getUsername());
		String token = this.jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
	
	//for getting current login user
		@GetMapping("/current-user")
		public User getCurrentUser(Principal principal){
			 //principal.getName() return the token after removing Bearer
			 String username = this.jwtUtil.extractUsername(principal.getName());
			User user =   (User) this.userDetailServiceImpl.loadUserByUsername(username);
			return user;
		}
		

	
	
//	public void authenticate(String username, String password) throws Exception {
//		
//	
//		
//		try {
//			System.out.println("Try to authenticate");
//		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		System.out.println("After authentication-->"+authenticate.isAuthenticated());
//		
//		if(!authenticate.isAuthenticated()) {
//			throw new Exception("User is not Authenticated !");
//		}
//		}
//		catch(DisabledException e) {
//			throw new Exception("Disabled user"+e.getMessage());
//		}
//		catch (BadCredentialsException e) {
//		  throw new Exception("Invalid Credentials" + e.getMessage());
//		}
//		catch(Exception e) {
//			System.out.println("User Authentication failed");
//			e.printStackTrace();
//		}
//		System.out.println(" authentication complete");
//	}
	
}
