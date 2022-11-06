package com.annualreviewcapstoneproject.annualreviewnote.services;

//Some things I thought that may be important to do in my application are:
//find All Notes By User
//Add a Note
//Delete a Note
//Update a Note
//Find a Note by ID
//Attach Photos & Videos**
//Organize With Folders**
//Make Notes Password-Protected**
//Possibility to add a calendar**
//Change color of your notes**
//share your notes by email

import com.annualreviewcapstoneproject.annualreviewnote.dtos.AnnualReviewNotesDto;
import com.annualreviewcapstoneproject.annualreviewnote.entities.AnnualReviewNotes;
import com.annualreviewcapstoneproject.annualreviewnote.entities.ProfessionalInformation;
import com.annualreviewcapstoneproject.annualreviewnote.entities.Users;
import com.annualreviewcapstoneproject.annualreviewnote.repositories.AnnualReviewNotesRepository;
import com.annualreviewcapstoneproject.annualreviewnote.repositories.ProfessionalInformationRepository;
import com.annualreviewcapstoneproject.annualreviewnote.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnnualReviewNotesServiceImpl implements AnnualReviewNotesService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ProfessionalInformationRepository professionalInformationRepository;
    @Autowired
    private AnnualReviewNotesRepository annualReviewNotesRepository;

    @Override
    @Transactional
    public void addNote(AnnualReviewNotesDto annualReviewNotesDto, Long usersId, Long professionalInfoId) {
        Optional<Users> usersOptional = usersRepository.findById(usersId);
        Optional<ProfessionalInformation> optionalProfessionalInformation = professionalInformationRepository.findById(professionalInfoId);
        AnnualReviewNotes annualReviewNotes = new AnnualReviewNotes(annualReviewNotesDto);
        usersOptional.ifPresent(annualReviewNotes::setUser);
        optionalProfessionalInformation.ifPresent(annualReviewNotes::setProfessionalInformation);
        annualReviewNotesRepository.saveAndFlush(annualReviewNotes);
    }

    @Override
    @Transactional
    public void deleteNote(Long annualReviewNotesId) {
        //Delete note by Id
        Optional<AnnualReviewNotes> annualReviewNotesOptional = annualReviewNotesRepository.findById(annualReviewNotesId);
        annualReviewNotesOptional.ifPresent(annualReviewNotes -> annualReviewNotesRepository.delete(annualReviewNotes));
    }

    @Override
    @Transactional
    public void editNote(AnnualReviewNotesDto annualReviewNotesDto) {
        //allows the user to edit or update a note by ID
        Optional<AnnualReviewNotes> annualReviewNotesOptional = annualReviewNotesRepository.findById(annualReviewNotesDto.getId());
        annualReviewNotesOptional.ifPresent(annualReviewNotes -> {
            annualReviewNotes.setAnnualNote(annualReviewNotesDto.getAnnualNote());
            annualReviewNotes.setGithubLink(annualReviewNotesDto.getGithubLink());
            annualReviewNotes.setJiraTicket(annualReviewNotesDto.getJiraTicket());
            annualReviewNotesRepository.saveAndFlush(annualReviewNotes);
        });
    }

    @Override
    public List<AnnualReviewNotesDto> getAllAnnualReviewNotes(Long usersId) {
        //allows the user to see all their annual review notes by ID
        Optional<Users> usersOptional = usersRepository.findById(usersId);

        if (usersOptional.isPresent()) {
            List<AnnualReviewNotes> annualReviewNotesList = annualReviewNotesRepository.findAllByUserEquals(usersOptional.get());
            return annualReviewNotesList.stream().map(annualReviewNotes -> new AnnualReviewNotesDto(annualReviewNotes)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
    @Override
    public Optional<AnnualReviewNotesDto> getAnnualReviewNotesById( Long annualReviewNotesId){
        Optional<AnnualReviewNotes> annualReviewNotesOptional = annualReviewNotesRepository.findById(annualReviewNotesId);
        if (annualReviewNotesOptional.isPresent()){
            return Optional.of(new AnnualReviewNotesDto(annualReviewNotesOptional.get()));
        }
        return Optional.empty();
    }


}
