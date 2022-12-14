package com.annualreviewcapstoneproject.annualreviewnote.services;

import com.annualreviewcapstoneproject.annualreviewnote.dtos.AnnualReviewNotesDto;
import com.annualreviewcapstoneproject.annualreviewnote.dtos.ProfessionalInformationDto;
import com.annualreviewcapstoneproject.annualreviewnote.entities.AnnualReviewNotes;
import com.annualreviewcapstoneproject.annualreviewnote.entities.ProfessionalInformation;
import com.annualreviewcapstoneproject.annualreviewnote.entities.Users;
import com.annualreviewcapstoneproject.annualreviewnote.repositories.ProfessionalInformationRepository;
import com.annualreviewcapstoneproject.annualreviewnote.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessionalInformationServiceImpl implements ProfessionalInformationService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ProfessionalInformationRepository professionalInformationRepository;

    //allows the user by adding the name and title in the company is working
    //edit
    //delete

    @Override
    public List<ProfessionalInformationDto> getProfessionalInfoByUser(Long usersId) {
        Optional<Users> usersOptional = usersRepository.findById(usersId);

        if (usersOptional.isPresent()) {
            List<ProfessionalInformation> professionalInformationList = professionalInformationRepository.findAllByUserEquals(usersOptional.get());
            return professionalInformationList.stream().map(professionalInformation -> new ProfessionalInformationDto(professionalInformation)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public void addProfessionalInformation(ProfessionalInformationDto professionalInformationDto, Long usersId) {
        Optional<Users> usersOptional = usersRepository.findById(usersId);
        ProfessionalInformation professionalInformation = new ProfessionalInformation(professionalInformationDto);
        usersOptional.ifPresent(professionalInformation::setUser);
        professionalInformationRepository.saveAndFlush(professionalInformation);
    }


    @Override
    @Transactional
    public void editProfessionalInformation(ProfessionalInformationDto professionalInformationDto) {
        Optional<ProfessionalInformation> professionalInformationOptional = professionalInformationRepository.findById(professionalInformationDto.getId());
        professionalInformationOptional.ifPresent(professionalInformation -> {
            professionalInformation.setCompanyInfo(professionalInformationDto.getCompanyInfo());
            professionalInformationDto.setCompanyTitle(professionalInformationDto.getCompanyTitle());
            professionalInformationRepository.saveAndFlush(professionalInformation);
        });
    }

    @Override
    @Transactional
    public void deleteProfessionInformation(Long professionalInformationId) {
        Optional<ProfessionalInformation> professionalInformationOptional = professionalInformationRepository.findById(professionalInformationId);
        professionalInformationOptional.ifPresent(professionalInformation -> professionalInformationRepository.delete(professionalInformation));

    }

}
