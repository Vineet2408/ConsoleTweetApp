package com.tweetapp.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Document
public class Tweet {

	@Id
	private String id;
	private String tweetedTo;
	private String message;
	private LocalDateTime date; // automatic generated

	private String userId;
	private String handle;
	private List<String> replyList;
	
	
	public Tweet(String tweetedTo, String message, LocalDateTime date, String avatarLink, String username, String userId,
			String handle, List<String> replyList) {
		super();
		this.tweetedTo = tweetedTo;
		this.message = message;
		this.date = date;
		this.userId = userId;
		this.handle = handle;
		this.replyList = replyList;
	}
	
	

}
