package com.tweetapp.bean;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tweetapp.model.LoggedInUser;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;
import com.tweetapp.model.UserCredentials;
import com.tweetapp.service.TweetService;
import com.tweetapp.service.UserService;
import com.tweetapp.util.ConsoleUi;

@Component
public class ConsoleApp {
	
	@Autowired
	ConsoleUi ui;
	
	@Autowired
	TweetService tweetService;
	
	@Autowired
	UserService userService;
	
	
	public void startUi()throws IOException {
		Scanner sc = new Scanner(System.in);
		

		int choice = -1;
		boolean showMenu = true;
		do {
			
			boolean isUserLoggedIn = LoggedInUser.loggedIn;// some function will return if user is loggedIn or not

			if (isUserLoggedIn) {
				ui.printMenuLoggedInUser();
				choice = sc.nextInt();
				switch(choice) 
				{
					case 1:
						Tweet tweet = ui.takeTweetDataFromUser();
						tweetService.addTweetForUser(tweet, LoggedInUser.user);
						break;
						
					case 2:
						List<Tweet> allUserTweets = tweetService.getAllTweetByUserId(LoggedInUser.user.getId());
						ui.displayAllTweets(allUserTweets);
						break;
						
					case 3:
						List<Tweet> allTweets = tweetService.getAllTweets();
						ui.displayAllTweets(allTweets);
						break;
						
					case 4:
						List<User> userList = userService.getAllUsers();
						ui.displayAllUsers(userList);
						break;
						
					case 5:
						
						break;
						
					case 6:
						LoggedInUser.user = null;
						LoggedInUser.loggedIn=false;
						isUserLoggedIn = false;
						break;
						
					default:
						ui.printInvalidOptionEntered();
				}
				
			}
			else {
				ui.printMenuLoggedOutUser();
				choice = sc.nextInt();
				
				switch(choice) 
				{
					case 1:
						User newUser= ui.takeAllUserInfoForRegistration();
						User registeredUser = userService.registerUser(newUser);
						System.out.println("Your Details are Registered \n" +registeredUser);
						LoggedInUser.user=registeredUser;
						LoggedInUser.loggedIn = true;
						
						break;
						
					case 2:
						UserCredentials userCreds =  ui.takeLoginCredentials();
						boolean loggedIn = userService.loginUser(userCreds);
						if (loggedIn) {
							ui.printLoginSuccess();
						}
						else {
							ui.printLoginFailed();
						}
						
						break;
						
					case 3:
						break;
						
					case 4:
						showMenu = false;
						ui.printExitMessage();
						sc.close();
						break;
						
					default:
						ui.printInvalidOptionEntered();
				}
				
			}
			
		}while(showMenu);
	}

}
