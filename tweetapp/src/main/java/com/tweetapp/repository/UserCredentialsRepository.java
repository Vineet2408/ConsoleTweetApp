package com.tweetapp.repository;

import com.tweetapp.model.User;
import com.tweetapp.model.UserCredentials;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserCredentialsRepository extends MongoRepository<UserCredentials, String> {

    Optional<List<UserCredentials>> findByUserId(String userId);
}
