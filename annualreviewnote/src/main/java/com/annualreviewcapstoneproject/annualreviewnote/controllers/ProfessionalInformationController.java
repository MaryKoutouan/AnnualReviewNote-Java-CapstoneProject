package com.annualreviewcapstoneproject.annualreviewnote.controllers;

import com.annualreviewcapstoneproject.annualreviewnote.dtos.AnnualReviewNotesDto;
import com.annualreviewcapstoneproject.annualreviewnote.dtos.ProfessionalInformationDto;
import com.annualreviewcapstoneproject.annualreviewnote.services.ProfessionalInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professionalinformation")
public class ProfessionalInformationController {
    @Autowired
    private ProfessionalInformationService professionalInformationService;

    @GetMapping("/users/{usersId}")
    public List<ProfessionalInformationDto> getProfessionalInfoByUser(@PathVariable Long usersId) {
        return professionalInformationService.getProfessionalInfoByUser(usersId);
    }

    @PostMapping("/users/{userId}")
    public void addProfessionalInformation(@RequestBody ProfessionalInformationDto professionalInformationDto, @PathVariable Long userId) {
        professionalInformationService.addProfessionalInformation(professionalInformationDto, userId);
    }

    @DeleteMapping("/{professionalInfoId}")
    public void deleteProfessionalInformation(@PathVariable Long professionalInformationId) {
        professionalInformationService.deleteProfessionInformation(professionalInformationId);
    }

    @PutMapping
    public void editProfessionalInformation(@RequestBody ProfessionalInformationDto professionalInformationDto) {
        professionalInformationService.editProfessionalInformation(professionalInformationDto);
    }
}
