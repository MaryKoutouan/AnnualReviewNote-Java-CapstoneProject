package com.annualreviewcapstoneproject.annualreviewnote.controllers;

import com.annualreviewcapstoneproject.annualreviewnote.dtos.AnnualReviewNotesDto;
import com.annualreviewcapstoneproject.annualreviewnote.entities.AnnualReviewNotes;
import com.annualreviewcapstoneproject.annualreviewnote.services.AnnualReviewNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/annualreviewnotes")
public class AnnualReviewNotesController {
    @Autowired
    private AnnualReviewNotesService annualReviewNotesService;

    @GetMapping("/users/{usersId}")
    public List<AnnualReviewNotesDto> getAnnualNoteByUser(@PathVariable Long usersId) {
        return annualReviewNotesService.getAllAnnualReviewNotes(usersId);
    }

    @GetMapping("/{annualreviewnotesId}")
    public Optional<AnnualReviewNotesDto> getAnnualReviewNotesById(@PathVariable Long annualreviewnotesId){
        return annualReviewNotesService.getAnnualReviewNotesById(annualreviewnotesId);
    }

    @PostMapping("/users/{usersId}/{professionalInfoId}")
    public void addNote(@RequestBody AnnualReviewNotesDto annualReviewNotesDto, @PathVariable Long usersId, @PathVariable Long professionalInfoId) {
        annualReviewNotesService.addNote(annualReviewNotesDto, usersId, professionalInfoId);
    }

    @DeleteMapping("/{annualreviewnotesId}")
    public void deleteAnnualNoteById(@PathVariable Long annualreviewnotesId) {
        annualReviewNotesService.deleteNote(annualreviewnotesId);
    }

    @PutMapping
    public void editNote(@RequestBody AnnualReviewNotesDto annualReviewNotesDto) {
        annualReviewNotesService.editNote(annualReviewNotesDto);
    }

}
