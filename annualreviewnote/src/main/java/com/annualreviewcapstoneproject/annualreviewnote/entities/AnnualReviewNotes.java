package com.annualreviewcapstoneproject.annualreviewnote.entities;

import com.annualreviewcapstoneproject.annualreviewnote.dtos.AnnualReviewNotesDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "Annual_notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnualReviewNotes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(columnDefinition = "text")
    private String annualNote;
    @Column
    private String githubLink;
    @Column
    private String jiraTicket;
    @Column
    private Calendar dateNote;

    @ManyToOne//An annualNote can have many professional info
    @JsonBackReference
    //@JsonBackReference is the back part of reference – it will be omitted from serialization
    private ProfessionalInformation professionalInformation;
    @ManyToOne
    @JsonBackReference
    private Users user;

    public AnnualReviewNotes(AnnualReviewNotesDto annualReviewNotesDto) {

        if (annualReviewNotesDto.getAnnualNote() != null) {
            this.annualNote = annualReviewNotesDto.getAnnualNote();
        }
        if (annualReviewNotesDto.getGithubLink() != null) {
            this.githubLink = annualReviewNotesDto.getGithubLink();
        }
        if (annualReviewNotesDto.getJiraTicket() != null) {
            this.jiraTicket = annualReviewNotesDto.getJiraTicket();
        }
        if (annualReviewNotesDto.getDateNote() != null) {
            this.dateNote = annualReviewNotesDto.getDateNote();
        }
    }
}
