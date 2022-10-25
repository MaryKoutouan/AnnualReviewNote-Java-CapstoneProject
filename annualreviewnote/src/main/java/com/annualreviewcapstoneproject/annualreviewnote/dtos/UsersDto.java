package com.annualreviewcapstoneproject.annualreviewnote.dtos;

//Dto is to avoid sending our entities outside. It's a copy of our entities to transfer the data stored within the object.

import com.annualreviewcapstoneproject.annualreviewnote.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto implements Serializable {
    //implement a class with Serializable allows this class to be converted to a Byte stream and send outside the application or store it in a log file
    private Long id;
    private String username;
    private String userEmail;
    private String password;
    private Set<ProfessionalInformationDto> professionalInformationSet = new HashSet<>();
    private Set<AnnualReviewNotesDto> annualReviewNotesSet = new HashSet<>();
    //I have left the members variables "HashSet" here in case I need to use the setter later. It's not important to put them here but just in case I might need them for later

    public UsersDto(Users user) {
        //this constructor helps to put inside some conditional logic for sanity checking that's mean is to prevent null pointer exceptions, it(null pointer exceptions) can be resolved by using a try-catch block or an if-else condition to check if a reference variable is null before obtain from (a pointer) the address of a data item held in another location.

        if (user.getId() != null) {
            this.id = user.getId();
        }
        if (user.getUsername() != null) {
            this.username = user.getUsername();
        }
        if (user.getUserEmail() != null) {
            this.userEmail = user.getUserEmail();
        }
        if (user.getPassword() != null) {
            this.password = user.getPassword();
        }
    }
}
