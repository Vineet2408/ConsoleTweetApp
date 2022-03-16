package com.tweetapp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.model.Gender;
import com.tweetapp.model.LoggedInUser;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;
import com.tweetapp.model.UserCredentials;
import com.tweetapp.service.TweetService;
import com.tweetapp.service.UserService;

@Service
public class ConsoleUi {
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Scanner sc= new Scanner(System.in);
	
	@Autowired
	TweetService tweetService;
	
	@Autowired
	UserService userService;
	
	public ConsoleUi() {
		
	}
	
	public void printMenuLoggedInUser() {
		System.out.println("i. Post a tweet\n"
				+ "ii. View my tweets\n"
				+ "iii. View all tweets\n"
				+ "iv. View All Users\n"
				+ "v. Reset Password\n"
				+ "vi. Logout");
		System.out.print("\t");
	}
	
	public void printMenuLoggedOutUser() {
		System.out.println("1. Register ");
		System.out.println("2. Login ");
		System.out.println("3. Forgot Password ");
		System.out.println("4. Exit ");
		System.out.print("\t");
		
	}
	
	public UserCredentials takeLoginCredentials() throws IOException {
		UserCredentials userCredentials = new UserCredentials();
		System.out.println("Enter Your Email");
		String email = sc.nextLine();
		
		System.out.println("Enter the Password");
		String password = br.readLine();
		
		userCredentials.setEmail(email);
		userCredentials.setPassword(password);
		return userCredentials;
	}

	public Tweet takeTweetDataFromUser() throws IOException {

		System.out.println("Enter the Message");
		String message = br.readLine();
		
		User user= LoggedInUser.user;
		
		System.out.println("LoggedIn User = "+ user.getFirstName() + " " + user.getId());
				
		// create a new Tweet
		Tweet tweet = new Tweet(null, message,new ArrayList<String>(), LocalDateTime.now());
		return tweet;
		
	}

	public void displayAllTweets(List<Tweet> tweets) {
		// TODO Auto-generated method stub
		if (tweets == null ) {
			System.out.println("You dont have any tweets");
			return;
		}
		if (tweets.isEmpty()) {
			System.out.println("You dont have any tweets");
			return;
		}
		System.out.println("\n====================================================================================================\n");
		for(Tweet tweet:tweets) {
			System.out.println(tweet.toString());
		}
		System.out.println("\n====================================================================================================\n");
		
	}
	
	public void displayAllUsers(List<User> users) {
		// TODO Auto-generated method stub
		if (users == null ) {
			System.out.println("We dont have any users");
			return;
		}
		if (users.isEmpty()) {
			System.out.println("We dont have any users");
			return;
		}
		System.out.println("\n====================================================================================================\n");
		for(User user:users) {
			System.out.println(user.toString());
		}
		System.out.println("\n====================================================================================================\n");
		
	}

	public void printExitMessage() {
		// TODO Auto-generated method stub
		System.out.println("\n******************************************************************************************************\n");
		System.out.println("\n Thankyou for using our Portal. Have a Nice Day !!! \n");
		System.out.println("\n*****************************************************************************************************\n");

		
	}

	public void printLoginSuccess() {
		// TODO Auto-generated method stub
		System.out.println("\n******************************************************************************************************\n");
		System.out.println("\n found matching records. Login Successfull. \n");
		System.out.println("\n*****************************************************************************************************\n");

	}

	public void printLoginFailed() {
		// TODO Auto-generated method stub
		System.out.println("\n !!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!! \n");
		System.out.println("\n Not matching records Found. Login failed. Please Try again with correct credentials. \n");
		System.out.println("\n !!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!!@!!! \n");

	}
	
	public Date takeDateOfBirthInputFromUser() {
		System.out.println("\t i.Enter your year of birth in YYYY format (numbers only).");
		int year = sc.nextInt();
		System.out.println("\t ii.Enter your month of birth in MM format.");
		int month = sc.nextInt();
		System.out.println("\t iii.Enter your day date of birth in DD format.");
		int dayDate = sc.nextInt();
		
		String dob = String.valueOf(dayDate)+"/"+String.valueOf(month)+"/"+String.valueOf(year);

		Date dobDate = null;
		try {
			dobDate = new SimpleDateFormat("dd/mm/yyyy")
			        .parse(dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("Date is not entered correctly");
			
		}
		System.out.println("your Date of birth is "+ dobDate.toString());
		return dobDate;
	}

	public User takeAllUserInfoForRegistration() {
		// TODO Auto-generated method stub
		System.out.println("\n Enter your Details for registration. \n");
		
		System.out.println("\t 1.Enter your first name.");
		String firstName = sc.next();
		
		System.out.println("\t 2.Enter your last name.");
		String lastName = sc.next();
		
		System.out.println("\t 3.Enter your Gender as MALE OR FEMALE.");
		String gender = sc.next();
		Gender newGender = Gender.valueOf(gender.toUpperCase());
		
		System.out.println("\t 4.Enter your Email.");
		String email = sc.next();
		
		System.out.println("\t 5.Enter your Date of birth.");
		Date dobDate = takeDateOfBirthInputFromUser();
		
		System.out.println("\t 6.Enter your  username. It should contain 10 or more letter and no spaces allowed. \n");
		String username = takeUsernameInputFromUser();
		
		System.out.println(" 7.Enter your Password");
		String password = takePasswordInputFromUser();
		String avatarLink = "https://thumbs.dreamstime.com/b/avatar-faceless-male-profile-vector-illustration-graphic-design-avatar-faceless-male-profile-138082635.jpg";
		
		User newUser = new User(firstName,lastName,email, newGender, dobDate, password, LocalDateTime.now(), username, avatarLink);
		System.out.println("\n Your Details \n"+newUser.toString());
		
		return newUser;
	}

	private String takeUsernameInputFromUser() {
		// TODO Auto-generated method stub
		String username = "";
		int i=0;
		while (true) {
			if (validateUsername(username)) {
				return username;
			}
			else if (i > 0) {
				System.out.println("please enter characters more than 9");
			}
			username = sc.nextLine();
			i++;
		}
	}

	private boolean validateUsername(String username) {
		// TODO Auto-generated method stub
		if (username.contains(" ")) {
			return false;
		}
		return username.length() >= 10 ? true : false;
	}
	
	private void printPasswordCriteria() {
		System.out.println("Password must contain at least one digit [0-9].");
		System.out.println("Password must contain at least one lowercase Latin character [a-z]. \n"
				+ "Password must contain at least one uppercase Latin character [A-Z]. \n"
				+ "Password must contain at least one special character like ! @ # & ( ). \n"
				+ "Password must contain a length of at least 8 characters and a maximum of 30 characters.");
	}
	
	private String takePasswordInputFromUser() {
		// TODO Auto-generated method stub
		int i=0;
		while (true) {
			String password = "";
			System.out.println("Enter New Password");
			printPasswordCriteria();
			while (true) {
				if (validatePassword(password)) {
					break;
				}
				else if (i > 0) {
					printPasswordCriteria();
				}
				password = sc.next();
				i++;
			}
			
			System.out.println("Confirm Your Password");
			String confirmPass = sc.next();
			if (confirmPass.equals(password)) {
				return password;
			}
			else {
				System.out.println("Password and Confirm-Password do not match. Please try again");
			}
		}
	}
	
	
	private boolean validatePassword(String password) {
		// TODO Auto-generated method stub
		String passwordRegex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,30}";
		 if (password.length() < 8 ) {
			 return false;
		 }
		 if (password.matches(passwordRegex)) {
			 return true;
		 }
		 return false;
	}

	public void printInvalidOptionEntered() {
		// TODO Auto-generated method stub
		System.out.println("invalid option Entered. Try Again");
		
	}

}
