package com.tweetapp.util;

import org.springframework.stereotype.Service;

@Service
public class Validator {

    public boolean validatePassword(String password) {
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

    public boolean validateUsername(String username) {
        // TODO Auto-generated method stub
        if (username.contains(" ")) {
            return false;
        }
        return username.length() >= 10 ? true : false;
    }
    public boolean email(String email) {
        String emailRegEx =	"^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if (email.matches(emailRegEx)) {
            return true;
        }
        return false;
    }
}
