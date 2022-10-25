package com.annualreviewcapstoneproject.annualreviewnote.services;

import com.annualreviewcapstoneproject.annualreviewnote.dtos.ProfessionalInformationDto;
import org.springframework.transaction.annotation.Transactional;

public interface ProfessionalInformationService {
    @Transactional
    void addProfessionalInformation(ProfessionalInformationDto professionalInformationDto, Long usersId);

    @Transactional
    void editProfessionalInformation(ProfessionalInformationDto professionalInformationDto);

    @Transactional
    void deleteProfessionInformation(Long professionalInformationId);
}
