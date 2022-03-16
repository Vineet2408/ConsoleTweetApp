package com.tweetapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.Tweet;

@Repository
public interface TweetRepository extends MongoRepository<Tweet, String>{
	
	Optional<List<Tweet>> findByUserId(String userId);

}
