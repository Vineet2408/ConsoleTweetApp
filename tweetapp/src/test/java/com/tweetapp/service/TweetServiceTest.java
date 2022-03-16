package com.tweetapp.service;

import com.tweetapp.model.Gender;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;
import com.tweetapp.repository.TweetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class TweetServiceTest
{

    @InjectMocks
    TweetService tweetService;

    @Mock
    TweetRepository tweetRepository;


    private Tweet getDummyTweet(String message, String userId) {
        Tweet tweet = new Tweet(null, message, new ArrayList<>(), LocalDateTime.now(),userId);
        tweet.setHandle("username");
        return tweet;
    }
    private Tweet getDummyTweet(String userId) {
        Tweet tweet = new Tweet(null, "message", new ArrayList<>(), LocalDateTime.now(),userId);
        tweet.setHandle("username");
        return tweet;
    }
    private Tweet getDummyTweet() {
        Tweet tweet = new Tweet(null, "message", new ArrayList<>(), LocalDateTime.now());
        return tweet;
    }
    private Tweet getDummyTweetWithUserDetails() {
        Tweet tweet = new Tweet(null, "message", new ArrayList<>(), LocalDateTime.now());
        tweet.setHandle("username");
        tweet.setUserId("1");
        return tweet;
    }

    private List<Tweet> getAllTweets(String userId)
    {
        List<Tweet> list = new ArrayList<>();
        if (userId.trim().equals("")){
           list.add(getDummyTweet("1"));
           list.add(getDummyTweet("some message from user 1","1"));
           list.add(getDummyTweet("2"));
           return list;
        }
        else {
            list.add(getDummyTweet(userId));
        }
        return list;
    }

    private Tweet getDbAddedTweet(Tweet tweet) {
        tweet.setId("someRandomId");
        return tweet;
    }
    private User getDummyUser(String userId) {
        User user = new User("firstName", "lastName", "email@gmail.com", Gender.FEMALE, new Date(), "password",
                LocalDateTime.now(), "username", "avatarLink");
        user.setId(userId);
        return user;
    }

    private Optional<List<Tweet>> getOptionalListTweet(String userId) {
        return Optional.of(getAllTweets(userId));
    }

    @Test
    void shouldAllReturnAllTweets(){

        when(tweetRepository.findAll()).thenReturn(getAllTweets(""));
        List<Tweet> tweets = tweetService.getAllTweets();

        assertThat(tweets).isNotNull().isNotEmpty().hasSize(3);
        assertThat(tweets.get(1))
                .isNotNull()
                .hasFieldOrPropertyWithValue("message","some message from user 1");
    }
    @Test
    void shouldAllReturnAllTweetsOfUser(){
        when(tweetRepository.findByUserId("1")).thenReturn(getOptionalListTweet("1"));
        List<Tweet> tweets = tweetService.getAllTweetByUserId("1");

        assertThat(tweets).isNotNull().isNotEmpty().hasSize(1);
        assertThat(tweets.get(0))
                .isNotNull()
                .hasFieldOrPropertyWithValue("message","message")
                .hasFieldOrPropertyWithValue("userId","1");
    }

    @Test
    void shouldAddTweetForUser() {
        Tweet tweetWithUserDetails = getDummyTweetWithUserDetails();
        Tweet dbAddedTweet = getDbAddedTweet(getDummyTweetWithUserDetails());
        Tweet noUserDetailsTweet = getDummyTweet();
        User user = getDummyUser("1");

//        doReturn(dbAddedTweet).when(tweetRepository.insert(tweet));
        when(tweetRepository.insert(tweetWithUserDetails)).thenReturn(dbAddedTweet);
        Tweet addedTweet = tweetService.addTweetForUser(noUserDetailsTweet,user);

        assertThat(addedTweet)
                .isNotNull()
                .hasFieldOrPropertyWithValue("userId","1")
                .hasFieldOrPropertyWithValue("message","message");
    }

    @Test
    void shouldGetTweetForGivedTweetId() {
        Tweet dbAddedTweet = getDbAddedTweet(getDummyTweetWithUserDetails());
        when(tweetRepository.findById("someRandomId")).thenReturn(Optional.of(dbAddedTweet));

        Tweet tweet = tweetService.getTweetByTweetId("someRandomId");
        assertThat(tweet)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id","someRandomId")
                .hasFieldOrPropertyWithValue("message","message")
                .hasFieldOrPropertyWithValue("handle","username");
    }
}
