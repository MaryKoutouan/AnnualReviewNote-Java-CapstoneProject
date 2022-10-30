package com.annualreviewcapstoneproject.annualreviewnote.services;

import com.annualreviewcapstoneproject.annualreviewnote.dtos.AnnualReviewNotesDto;
import com.annualreviewcapstoneproject.annualreviewnote.dtos.ProfessionalInformationDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProfessionalInformationService {

    List<ProfessionalInformationDto> getProfessionalInfoByUser(Long usersId);

    @Transactional
    void addProfessionalInformation(ProfessionalInformationDto professionalInformationDto, Long usersId);

    @Transactional
    void editProfessionalInformation(ProfessionalInformationDto professionalInformationDto);

    @Transactional
    void deleteProfessionInformation(Long professionalInformationId);

}
