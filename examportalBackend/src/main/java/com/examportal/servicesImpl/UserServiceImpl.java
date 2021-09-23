package com.examportal.servicesImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.dao.RoleRepository;
import com.examportal.dao.UserRepository;
import com.examportal.models.User;
import com.examportal.models.UserRole;
import com.examportal.services.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	//for creating new user
	@Override
	public User createUser(User user, Set<UserRole> userRole) throws Exception {
      User local =  this.userRepository.findByUsername(user.getUsername());
      
      if(local!=null) {
    	  throw new Exception("This Username is not available !");
      }
      else {
    	  for(UserRole ur : userRole) {
    		  this.roleRepository.save(ur.getRole());
    	  }
    	  user.getUserRoles().addAll(userRole);
    	local =   this.userRepository.save(user);
      }
      return local;
	}

	
	
	//for getting user from the database
	@Override
	public User getUser(String username) {
		return this.userRepository.findByUsername(username);
	}



	//for deleting the user
	@Override
	public void deleteUser(long id) {
	 this.userRepository.deleteByid(id);
	}


	//for updating the user
	@Override
	public User updateUser(User user, long id) {
		user.setId(id);
		return this.userRepository.save(user);
	}



	@Override
	public Long getAllUsers() {
	return (long) this.userRepository.findAll().size();
	}

}
