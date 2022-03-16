package com.tweetapp;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.tweetapp.bean.ConsoleApp;

import com.tweetapp.util.ConsoleUi;
@SpringBootApplication
@ComponentScan("com.tweetapp")
public class TweetappApplication {
	
	static
	ConsoleUi ui;
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args)throws IOException {
		ApplicationContext context =SpringApplication.run(TweetappApplication.class, args);
		
		ConsoleApp consoleApp = context.getBean(ConsoleApp.class);
		
		consoleApp.startUi();
//		startUi();

	}

	
}
