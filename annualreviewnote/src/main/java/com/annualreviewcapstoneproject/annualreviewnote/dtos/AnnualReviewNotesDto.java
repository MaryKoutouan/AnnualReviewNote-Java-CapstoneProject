package com.annualreviewcapstoneproject.annualreviewnote.dtos;

import com.annualreviewcapstoneproject.annualreviewnote.entities.AnnualReviewNotes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Calendar;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnualReviewNotesDto implements Serializable {

    private Long id;
    private String annualNote;
    private String githubLink;
    private String jiraTicket;
    private Calendar dateNote;

    private UsersDto usersDto;
    private ProfessionalInformationDto professionalInformationDto;

    public AnnualReviewNotesDto(AnnualReviewNotes annualReviewNotes) {

        if (annualReviewNotes.getId() != null) {
            this.id = annualReviewNotes.getId();
        }
        if (annualReviewNotes.getAnnualNote() != null) {
            this.annualNote = annualReviewNotes.getAnnualNote();
        }
        if (annualReviewNotes.getGithubLink() != null) {
            this.githubLink = annualReviewNotes.getGithubLink();
        }
        if (annualReviewNotes.getJiraTicket() != null) {
            this.jiraTicket = annualReviewNotes.getJiraTicket();
        }
        if (annualReviewNotes.getDateNote() != null) {
            this.dateNote = annualReviewNotes.getDateNote();
        }
    }
}
