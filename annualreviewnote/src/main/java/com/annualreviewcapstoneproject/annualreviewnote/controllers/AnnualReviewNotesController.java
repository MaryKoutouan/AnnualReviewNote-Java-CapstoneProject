package com.annualreviewcapstoneproject.annualreviewnote.controllers;

import com.annualreviewcapstoneproject.annualreviewnote.dtos.AnnualReviewNotesDto;
import com.annualreviewcapstoneproject.annualreviewnote.services.AnnualReviewNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/annualreviewnotes")
public class AnnualReviewNotesController {
    @Autowired
    private AnnualReviewNotesService annualReviewNotesService;

    @GetMapping("/users/{usersId}")
    public List<AnnualReviewNotesDto> getAnnualNoteByUser(@PathVariable Long usersId) {
        return annualReviewNotesService.getAllAnnualReviewNotes(usersId);
    }

    @PostMapping("/users/{usersId}")
    public void addNote(@RequestBody AnnualReviewNotesDto annualReviewNotesDto, @PathVariable Long usersId) {
        annualReviewNotesService.addNote(annualReviewNotesDto, usersId);
    }

    @DeleteMapping("/{annualreviewnotesId}")
    public void deleteAnnualNoteById(@PathVariable Long annualReviewNotesId) {
        annualReviewNotesService.deleteNote(annualReviewNotesId);
    }

    @PutMapping
    public void editNote(@RequestBody AnnualReviewNotesDto annualReviewNotesDto) {
        annualReviewNotesService.editNote(annualReviewNotesDto);
    }

}
