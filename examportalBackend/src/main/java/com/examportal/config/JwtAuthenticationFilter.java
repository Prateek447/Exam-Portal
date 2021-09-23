package com.examportal.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
 
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
	       final   String header = request.getHeader("Authorization");
	       System.out.println("header-->"+header);
	       
	       String username = null;
	       
	       String jwtToken =null;
	       if(header!=null && header.startsWith("Bearer")) {
	    	   jwtToken =  header.substring(7);
	    	   System.out.println("after removing Bearer-->"+jwtToken);
	    	   try {
	    		   
				username =  jwtUtil.extractUsername(jwtToken);
				System.out.println("username from token-->"+username);
				
			} catch (ExpiredJwtException e) {
				System.out.println("Token is expired");
				e.printStackTrace();
			}
	    	   catch(Exception e) {
	    		   System.out.println("Something went wrong");
	    	   }
	       }
	       else {
	    	   System.out.println("Token is invalid");
	       }
	       
	       
	       //validate the user
	       if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	    	   System.out.println("validating the user");
	    	   final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
	    	   System.out.println("userdetails-->"+userDetails);
	    	   //if token is valid then
	    	   if(this.jwtUtil.validateToken(jwtToken, userDetails)) {
	    		   UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(jwtToken,null, userDetails.getAuthorities());
	    		   usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	    		   SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	    	   }
	    	   else {
	    		   System.out.println("we dont have user with that token");
	    	   }
	       }
	       else {
	    	   System.out.println("No Username with that token");
	       }
	 filterChain.doFilter(request, response);
	 
	}

}
