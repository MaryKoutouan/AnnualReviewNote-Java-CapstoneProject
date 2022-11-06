package com.annualreviewcapstoneproject.annualreviewnote.services;

//The Service Layer is between the Repository Layer and Controller Layer. The Repository layer sends information to the service and th service passing the information in the controller layer which handle all the request.
//Some things I thought that may be important to do in my application are:

//Register a new User
//Verify User Credentials during Login


import com.annualreviewcapstoneproject.annualreviewnote.dtos.UsersDto;
import com.annualreviewcapstoneproject.annualreviewnote.entities.Users;
import com.annualreviewcapstoneproject.annualreviewnote.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired//Because of the @Repository in our repositories package Spring is able to find the corresponding dependency and inject it where it is needed throughout the application by using the @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional// each time I'm saving something to the database I should include the @Transactional annotation which ensures that the transaction that gets opened with my datasource gets resolved.
    public List<String> addUser(UsersDto usersDto) {

        List<String> response = new ArrayList<>();
        Users users = new Users(usersDto);
        usersRepository.saveAndFlush(users);
        response.add("http://localhost:8080/index.html");
        return response;
    }

    @Override
    public List<String>  loginUser(UsersDto usersDto) {
        List<String> response = new ArrayList<>();
        Optional<Users> usersOptional = usersRepository.findByUsername(usersDto.getUsername());

        //hashes logical to compare the userDto and the UserOptional(DB)
        if (usersOptional.isPresent()) {
            if (passwordEncoder.matches(usersDto.getPassword(), usersOptional.get().getPassword())) {
                response.add("http://localhost:8080/addNote.html");
                response.add(String.valueOf(usersOptional.get().getId()));
            } else {
                response.add("Username or Password incorrect");
            }
        } else {
            response.add("Username or Password incorrect");
        }

        return response;
    }

}
