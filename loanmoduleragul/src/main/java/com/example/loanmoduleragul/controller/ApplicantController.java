package com.example.loanmoduleragul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loanmoduleragul.dto.ApplicantDto;
import com.example.loanmoduleragul.model.Applicant;
import com.example.loanmoduleragul.service.ApplicantService;

@RestController
@RequestMapping("/api/applicants")
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @PostMapping
    public ResponseEntity<Applicant> saveApplicant(@RequestBody ApplicantDto applicantDTO) {
        try {
            Applicant applicant = applicantService.saveApplicant(applicantDTO);
            return ResponseEntity.ok(applicant);
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Handle internal server errors
        }
    }
}