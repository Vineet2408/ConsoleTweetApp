package com.tweetapp.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	Optional<User> findByEmail(String email);

}
