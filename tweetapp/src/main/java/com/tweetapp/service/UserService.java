package com.tweetapp.service;

import java.util.List;
import java.util.Optional;

import com.tweetapp.repository.UserCredentialsRepository;
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

	@Autowired
	UserCredentialsRepository credentialsRepository;
	
	public User registerUser(User user) {
		User savedUser = this.userRepository.insert(user);
		System.out.println(" \n User Registered Sucessfully\n " + savedUser.toString());
		UserCredentials credentials = new UserCredentials();
		credentials.setPassword(savedUser.getPassword());
		credentials.setUsername(savedUser.getUsername());
		credentials.setUserId(savedUser.getId());
		credentials.setId(savedUser.getEmail());
		credentials.setLoggedIn(true);
		credentialsRepository.save(credentials);
		return savedUser;
	}
	
	public boolean loginUser(UserCredentials userCreds) {
		Optional<User> optionalUser = this.userRepository.findByEmail(userCreds.getId());
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			// use bcrypt to encrypt the password before checking
			if (user.getPassword().equals(userCreds.getPassword())) {
				LoggedInUser.setUser(user);
				LoggedInUser.setUserLoggedIn(true);
				// save login info in db also
				userCreds.setLoggedIn(true);
				userCreds.setUserId(user.getId());
				userCreds.setUsername(user.getUsername());
				userCreds.setId(user.getEmail());
				credentialsRepository.save(userCreds);
				return true;
			}
		}
		return false;
	}

	public boolean logoutUser(UserCredentials creds) {
		creds.setLoggedIn(false);
		creds.setUsername(LoggedInUser.getUser().getUsername());
		creds.setPassword(LoggedInUser.getUser().getPassword());
		creds.setId(LoggedInUser.getUser().getEmail());
		try {
			UserCredentials loggedOutCreds = credentialsRepository.save(creds);
			LoggedInUser.setUser(null);
			LoggedInUser.setUserLoggedIn(false);
			return loggedOutCreds.isLoggedIn();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
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
