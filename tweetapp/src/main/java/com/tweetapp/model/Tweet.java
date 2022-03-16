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
	private String handle;
	private List<String> replyList;
	private LocalDateTime date; // automatic generated

	private String userId;

	public Tweet(String tweetedTo, String message, List<String> replyList, LocalDateTime date) {
		super();
		this.tweetedTo = tweetedTo;
		this.message = message;
		this.replyList = replyList;
		this.date = date;
	}
	
	

}
