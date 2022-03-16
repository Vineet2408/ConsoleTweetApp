package com.tweetapp.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.tweetapp.repository.UserRepository;

public class UserServiceTest {
	
	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	
	
	@Test
	void shouldRegisterUser() {
		
	}

	@Test
	void shouldLoginUserWithCorrectCredentials() {
		
	}
	
	@Test
	void shouldNotLoginUserWithIncorrectCredentials() {
		
	}
	@Test
	void shouldReturnAllUsers() {
		
	}
	
	@Test
	void shouldReturnUserWithProvideEmailId() {
		
	}
	
	@Test
	void shouldNotReturnUserWithNonExistingEmailId() {
		
	}
}
