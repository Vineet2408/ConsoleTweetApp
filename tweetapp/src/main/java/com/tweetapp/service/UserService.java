package com.tweetapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.model.LoggedInUser;
import com.tweetapp.model.User;
import com.tweetapp.model.UserCredentials;
import com.tweetapp.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User registerUser(User user) {
		User savedUser = this.userRepository.insert(user);
		System.out.println(" \n User Registered Sucessfully\n " + savedUser.toString());
		return savedUser;
	}
	
	public boolean loginUser(UserCredentials userCreds) {
		Optional<User> optionalUser = this.userRepository.findByEmail(userCreds.getEmail());
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			// use bcrypt to encrypt the password before checking
			if (user.getPassword().equals(userCreds.getPassword())) {
				LoggedInUser.user = user;
				LoggedInUser.loggedIn = true;
				// save login info in db also
				return true;
			}
		}
		return false;
	}
	
	public void resetPassword(User user, String newPassword) {
		
	}

	public Optional<User> findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
