package com.examportal.services;

import java.util.Set;

import com.examportal.models.User;
import com.examportal.models.UserRole;

public interface UserService {

	public User createUser(User user, Set<UserRole> userRole) throws Exception;
	
	
	public User getUser(String username);
	
	public void deleteUser(long id);
	
	public User updateUser(User user, long id);
	
	public Long getAllUsers();
}
