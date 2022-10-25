package com.annualreviewcapstoneproject.annualreviewnote.controllers;

import com.annualreviewcapstoneproject.annualreviewnote.dtos.UsersDto;
import com.annualreviewcapstoneproject.annualreviewnote.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public List<String> addUser(@RequestBody UsersDto usersDto) {
        String passHash = passwordEncoder.encode(usersDto.getPassword());
        usersDto.setPassword(passHash);
        return usersService.addUser(usersDto);
    }

    @PostMapping("/login")
    public List<String> loginUser(@RequestBody UsersDto usersDto) {
        return usersService.loginUser(usersDto);
    }
}
