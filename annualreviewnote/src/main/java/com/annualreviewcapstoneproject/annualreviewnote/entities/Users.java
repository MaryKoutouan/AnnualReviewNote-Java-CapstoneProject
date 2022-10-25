package com.annualreviewcapstoneproject.annualreviewnote.entities;

import com.annualreviewcapstoneproject.annualreviewnote.dtos.UsersDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


//The relationship between the tables in my DB are One-to-Many/ Many-to-One and Many-to-Many.

//One-user can have Many-annual review notes/ Many-annual review notes can belong to One-user/ Many-professional information can belong to Many-annual review notes and One-user.
//let's create a class of each tables in my DB which are (Users/ProfessionalInformation and AnnualReviewNotes.)


@Entity
@Table(name = "Users")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String userEmail;

    @Column
    private String password;

    //Create the relationship between the table with foreign key user to handle the different relationship(One-toMany)
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    //@JsonManagedReference is the forward part of reference – the one that gets serialized.
    //@JsonBackReference is the back part of reference – it will be omitted from serialization
    private Set<ProfessionalInformation> professionalInformationSet = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<AnnualReviewNotes> annualReviewNotesSet = new HashSet<>();

    // Because I created a constructor inside the DTO that accepted an Entity argument,I need to create a constructor inside each Entity that accepts their associated DTO classes as an argument.
    // I haven't added id because is value is being generated for us by Hibernate’s through @Id and @GeneratedValue annotations.
    public Users(UsersDto usersDto) {

        if (usersDto.getUsername() != null) {
            this.username = usersDto.getUsername();
        }
        if (usersDto.getUserEmail() != null) {
            this.userEmail = usersDto.getUserEmail();
        }
        if (usersDto.getPassword() != null) {
            this.password = usersDto.getPassword();
        }
    }
}
