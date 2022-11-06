package com.annualreviewcapstoneproject.annualreviewnote.entities;


import com.annualreviewcapstoneproject.annualreviewnote.dtos.ProfessionalInformationDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Professional_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalInformation extends ProfessionalInformationDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String companyInfo;
    @Column
    private String companyTitle;
    @ManyToOne
    @JsonBackReference
    private Users user;

    @OneToMany(mappedBy = "professionalInformation", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<AnnualReviewNotes> annualReviewNotesSet = new HashSet<>();

    public ProfessionalInformation(ProfessionalInformationDto professionalInformationDto) {

        if (professionalInformationDto.getId() != null) {
            this.id = professionalInformationDto.getId();
        }

        if (professionalInformationDto.getCompanyInfo() != null) {
            this.companyInfo = professionalInformationDto.getCompanyInfo();
        }
        if (professionalInformationDto.getCompanyTitle() != null) {
            this.companyTitle = professionalInformationDto.getCompanyTitle();
        }
    }
}
