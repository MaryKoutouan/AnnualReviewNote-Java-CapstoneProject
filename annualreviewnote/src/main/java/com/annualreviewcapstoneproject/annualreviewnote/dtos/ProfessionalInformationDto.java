package com.annualreviewcapstoneproject.annualreviewnote.dtos;

import com.annualreviewcapstoneproject.annualreviewnote.entities.ProfessionalInformation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalInformationDto implements Serializable {

    private Long id;
    private String companyInfo;
    private String companyTitle;

    public ProfessionalInformationDto(ProfessionalInformation professionalInformation) {

        if (professionalInformation.getId() != null) {
            this.id = professionalInformation.getId();
        }
        if (professionalInformation.getCompanyInfo() != null) {
            this.companyInfo = professionalInformation.getCompanyInfo();
        }
        if (professionalInformation.getCompanyTitle() != null) {
            this.companyTitle = professionalInformation.getCompanyTitle();
        }
    }
}
