package com.tweetapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;
import com.tweetapp.repository.TweetRepository;

@Service
public class TweetService {
	
	@Autowired
	TweetRepository tweetRepository;
	
	public Tweet getTweetByTweetId(String tweetId) {
		Optional<Tweet> tweet = tweetRepository.findById(tweetId);
		return tweet.get();
	}
	
	public Tweet addTweetForUser(Tweet tweet, User user) {
		tweet.setUserId(user.getId());
		tweet.setHandle(user.getUsername());
		
		Tweet newTweet =  tweetRepository.insert(tweet);
		return newTweet;
	}
	
	public Tweet addReplytoTweet(String tweetId, Tweet newTweet, User user) {
		Tweet existingTweet = this.tweetRepository.findById(tweetId).get();
		
		newTweet = this.addTweetForUser(newTweet, user);
		newTweet.setTweetedTo(tweetId);
		
		Tweet createdTweet = this.tweetRepository.insert(newTweet);
		
		existingTweet.getReplyList().add(createdTweet.getId());
		return this.tweetRepository.save(existingTweet);
	}

	public List<Tweet> getAllTweets() {
		return tweetRepository.findAll();
	}
	
	public List<Tweet> getAllTweetByUserId(String userId) {
		Optional<List<Tweet>> optionalTweets = tweetRepository.findByUserId(userId);
		
		if (optionalTweets.isPresent()) {
			return optionalTweets.get();
		}
		return new ArrayList<Tweet>();
		
	}
	
}
