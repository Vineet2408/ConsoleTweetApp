package com.tweetapp.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class LoggedInUser {
	
	static public  User user;
	static public boolean loggedIn = false;

}
