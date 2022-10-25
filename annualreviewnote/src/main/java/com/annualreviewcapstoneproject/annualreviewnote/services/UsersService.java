package com.annualreviewcapstoneproject.annualreviewnote.services;

import com.annualreviewcapstoneproject.annualreviewnote.dtos.UsersDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UsersService {
    @Transactional//each time I'm saving something to the database I should include the @Transactional annotation which ensures that the transaction that gets opened with my datasource gets resolved.
    List<String> addUser(UsersDto usersDto);
    List<String> loginUser(UsersDto usersDto);
}
