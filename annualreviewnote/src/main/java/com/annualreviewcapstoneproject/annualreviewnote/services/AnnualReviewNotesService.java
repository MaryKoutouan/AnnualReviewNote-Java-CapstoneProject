package com.annualreviewcapstoneproject.annualreviewnote.services;

import com.annualreviewcapstoneproject.annualreviewnote.dtos.AnnualReviewNotesDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AnnualReviewNotesService {
    @Transactional
    void addNote(AnnualReviewNotesDto annualReviewNotesDto, Long usersId, Long professionalInfoId);

    @Transactional
    void deleteNote(Long annualReviewNotesId);

    @Transactional
    void editNote(AnnualReviewNotesDto annualReviewNotesDto);

    List<AnnualReviewNotesDto> getAllAnnualReviewNotes(Long usersId);

    Optional<AnnualReviewNotesDto> getAnnualReviewNotesById(Long annualReviewNotesId);
}
