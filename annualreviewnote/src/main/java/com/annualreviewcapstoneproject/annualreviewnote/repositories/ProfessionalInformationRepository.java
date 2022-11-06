package com.annualreviewcapstoneproject.annualreviewnote.repositories;

import com.annualreviewcapstoneproject.annualreviewnote.entities.AnnualReviewNotes;
import com.annualreviewcapstoneproject.annualreviewnote.entities.ProfessionalInformation;
import com.annualreviewcapstoneproject.annualreviewnote.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessionalInformationRepository extends JpaRepository<ProfessionalInformation, Long> {
    List<ProfessionalInformation> findAllByUserEquals(Users users);

}
