package com.examportal.controllers;


import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.models.Role;
import com.examportal.models.User;
import com.examportal.models.UserRole;
import com.examportal.servicesImpl.UserServiceImpl;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	
	//handler for creating users
	@PostMapping("/")
	public User createUser(@RequestBody User  user) throws Exception {
		user.setProfile("default.png");
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Set<UserRole> set =  new HashSet<>();
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(new Role(45L,"NORMAL"));
		set.add(userRole);
		User createUser = this.userServiceImpl.createUser(user, set);
		return createUser;
	}

	
	//handler for getting user
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.userServiceImpl.getUser(username);
	}
	
	
	//for deleting user
	@Transactional
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId")long id) {
		this.userServiceImpl.deleteUser(id);
	}
	
	
	@GetMapping("/")
	public Long getAllUsers() {
		return this.userServiceImpl.getAllUsers();
	}
	
	
	//for updating the user
	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable("userId") long id, @RequestBody User user) {
		User user2 = this.getUser(user.getUsername());
		if(user2 != null && user2.getId() != id) {
			return ResponseEntity.badRequest().build();
		}
	     User updateUser = this.userServiceImpl.updateUser(user,id);
	     return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateUser);
	}
	
	

}
