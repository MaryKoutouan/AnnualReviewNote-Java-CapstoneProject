package com.annualreviewcapstoneproject.annualreviewnote.services;

import com.annualreviewcapstoneproject.annualreviewnote.dtos.AnnualReviewNotesDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AnnualReviewNotesService {
    @Transactional
    void addNote(AnnualReviewNotesDto annualReviewNotesDto, Long usersId);

    @Transactional
    void deleteNote(Long annualReviewNotesId);

    @Transactional
    void editNote(AnnualReviewNotesDto annualReviewNotesDto);

    List<AnnualReviewNotesDto> getAllAnnualReviewNotes(Long usersId);
}
