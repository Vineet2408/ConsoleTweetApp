package com.tweetapp.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document
public class User {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	@Indexed(unique = true)
	private String email;
	private Gender gender;
	private Date dob;
	private String password;

	private LocalDateTime createdAt; // automatic generated
	private String username;
	private String avatarLink;
	
	public User(String firstName, String lastName, String email, Gender gender, Date dob, String password,
			LocalDateTime createdAt, String username, String avatarLink) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.dob = dob;
		this.password = password;
		this.createdAt = createdAt;
		this.username = username;
		this.avatarLink = avatarLink;
	}
	
	
}
