package com.examportal;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.examportal.models.Role;
import com.examportal.models.User;
import com.examportal.models.UserRole;
import com.examportal.servicesImpl.UserServiceImpl;

@SpringBootApplication
public class ExamportalBackendApplication implements CommandLineRunner {
	
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamportalBackendApplication.class, args);
		
		
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		
//		User user = new User();
//		user.setUsername("yprateek");
//		user.setFirstname("prateek");
//		user.setLastname("Yadav");
//		user.setEmail("prateek@gmail.com");
//		user.setPhone("999999999");
//		user.setPassword(bCryptPasswordEncoder.encode("1234"));
//		user.setProfile("default.png");
//		
//		Role role =  new Role();
//		role.setId(44L);
//		role.setRoleName("ADMIN");
//		
//		Set<UserRole> set = new  HashSet<>();
//		
//		UserRole userRole = new UserRole();
//		userRole.setRole(role);
//		userRole.setUser(user);
//		
//		set.add(userRole);
//		
//		User createUser = this.userServiceImpl.createUser(user, set);
//		System.out.println(createUser);
		
		
	}
	
	

}
