package com.examportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.models.User;

public interface UserRepository  extends JpaRepository<User, Integer>{

	public User findByUsername(String username);
	
	public void deleteByid(long id);
}
