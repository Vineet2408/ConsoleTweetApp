package com.tweetapp.service;

import com.tweetapp.model.Gender;
import com.tweetapp.model.User;
import com.tweetapp.model.UserCredentials;
import org.assertj.core.internal.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.tweetapp.repository.UserRepository;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	private User registerUser(User user) {
		 user.setId("someRandomId");
		 return user;
	}

	private User getDummyUser() {
		User user = new User("firstName", "lastName", "email@gmail.com", Gender.FEMALE, new Date(), "password",
				LocalDateTime.now(), "username", "avatarLink");
		return user;
	}

	private Optional<User> getOptionalUser() {
		Optional<User> optionalUser = Optional.of(getDummyUser());
		return optionalUser;
	}

	private Optional<User> getEmptyOptionalUser() {
		return Optional.empty();
	}

	private List<User> getListOfUser() {
		List<User> list = new ArrayList<>();
		list.add(registerUser(getDummyUser()));
		return list;
	}
	
	@Test
	void shouldRegisterUser() {
		User user = getDummyUser();
		when(userRepository.insert(user)).thenReturn(registerUser(user));
		User registeredUser = userService.registerUser(user);
		assertThat(registeredUser).isNotNull().hasFieldOrProperty("id").hasFieldOrPropertyWithValue("firstName", "firstName");
	}

	@Test
	void shouldLoginUserWithCorrectCredentials() {
		UserCredentials creds = new UserCredentials();
		String email = "email@gmail.com";
		String password = "password";
		creds.setEmail(email);
		creds.setPassword(password);

		when(userRepository.findByEmail(email)).thenReturn(getOptionalUser());

		boolean loggedIn = userService.loginUser(creds);
		assertThat(loggedIn).isTrue();

	}
	
	@Test
	void shouldNotLoginUserWithIncorrectCredentials() {
		UserCredentials creds1 = new UserCredentials();
		String email1 = "email@gmail.com";
		String password1 = "passwo";
		creds1.setEmail(email1);
		creds1.setPassword(password1);

		when(userRepository.findByEmail(email1)).thenReturn(getOptionalUser());

		boolean loggedIn = userService.loginUser(creds1);
		assertThat(loggedIn).isFalse();

		String email2 = "email@il.com";
		String password2 = "password";
		creds1.setEmail(email2);
		creds1.setPassword(password2);

		when(userRepository.findByEmail(email2)).thenReturn(getEmptyOptionalUser());

		loggedIn = userService.loginUser(creds1);
		assertThat(loggedIn).isFalse();

		String email3 = "email@il.com";
		String password3 = "passwo";
		creds1.setEmail(email3);
		creds1.setPassword(password3);

		when(userRepository.findByEmail(email3)).thenReturn(getEmptyOptionalUser());

		loggedIn = userService.loginUser(creds1);
		assertThat(loggedIn).isFalse();

	}
	@Test
	void shouldReturnAllUsers() {
		when(userRepository.findAll()).thenReturn(getListOfUser());
		List<User> userList = userService.getAllUsers();

		assertThat(userList).hasSize(1).isNotNull().isNotEmpty();
		assertThat(userList.get(0)).hasFieldOrProperty("id").hasFieldOrPropertyWithValue("firstName","firstName");
	}
	
	@Test
	void shouldReturnUserWithProvideEmailId() {
		String email = "email@gmail.com";

		when(userRepository.findByEmail(email)).thenReturn(getOptionalUser());
		Optional<User> optionalUser = userService.findUserByEmail(email);

		assertThat(optionalUser.get()).isNotNull().hasFieldOrPropertyWithValue("firstName","firstName");

	}
	
	@Test
	void shouldNotReturnUserWithNonExistingEmailId() {
		String email = "email@gil.com";
		when(userRepository.findByEmail(email)).thenReturn(getEmptyOptionalUser());
		Optional<User> optionalUser = userService.findUserByEmail(email);

		assertThat(optionalUser).isEmpty();
	}
}
