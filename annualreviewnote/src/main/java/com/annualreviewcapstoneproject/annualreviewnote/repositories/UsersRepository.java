package com.annualreviewcapstoneproject.annualreviewnote.repositories;

//The repository layer is responsible for interacting with the database. I will use Spring Data JPA to make thing easy for me, because Spring Data JPA improve the implementation of data access. That's mean I can write all my repository interfaces and Spring will provide the implementation automatically.

import com.annualreviewcapstoneproject.annualreviewnote.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository  // The @Repository clues Spring Boot in to keep track of this resource for Dependency Injection
public interface UsersRepository extends JpaRepository<Users, Long> {
    //The arguments inside the JpaRepository is what I want JpaRepository to keep track of for me. In this case is my Users class created in my entities package and the type of the UsersId(<Class or Type, ID_FIELD_TYPE>).
    //Interface allows us to create rules about what code will compile

    Optional<Users> findByUsername(String username);

}
