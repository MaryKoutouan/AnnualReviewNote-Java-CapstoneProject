package com.annualreviewcapstoneproject.annualreviewnote.configuration;

//This layers allows me to create my custom Java Bean so that Spring can keep track of it and I can use it for Dependency Injection

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config {
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
        // PasswordEncoder is used to perform a one way transformation of a password to allow the password to be stored securely.
        //The preferred implementation of it is BCryptPasswordEncoder.(strength)
        //Goal: It verifies the encoded password obtained from storage matches the submitted raw password after it too is encoded. Returns true if the passwords match, false if they do not. The stored password itself is never decoded.
    }

}
