package com.tweetapp.model;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class LoggedInUser {

	static private  User user;
	static private String userId;
	static private boolean loggedIn = false;

	public static User getUser() {
		return LoggedInUser.user;
	}
	public static String getUserId() {
		return LoggedInUser.userId;
	}
	public static boolean isUserLoggedIn() {
		return LoggedInUser.loggedIn;
	}

	public static void setUser(User user) {
		LoggedInUser.user = user;
	}
	public static String etUserId(String userId) {
		return LoggedInUser.userId = userId;
	}

	public static boolean setUserLoggedIn(boolean flag) {
		return LoggedInUser.loggedIn = flag;
	}
}
