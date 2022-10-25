package com.annualreviewcapstoneproject.annualreviewnote.entities;


import com.annualreviewcapstoneproject.annualreviewnote.dtos.ProfessionalInformationDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Professional_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalInformation {
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

    public ProfessionalInformation(ProfessionalInformationDto professionalInformationDto) {

        if (professionalInformationDto.getCompanyInfo() != null) {
            this.companyInfo = professionalInformationDto.getCompanyInfo();
        }
        if (professionalInformationDto.getCompanyTitle() != null) {
            this.companyTitle = professionalInformationDto.getCompanyTitle();
        }
    }
}
