package com.example.loanmoduleragul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loanmoduleragul.dto.ApplicantDto;
import com.example.loanmoduleragul.model.Applicant;
import com.example.loanmoduleragul.repository.ApplicantRepository;
@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    public Applicant saveApplicant(ApplicantDto applicantDto) {
        // Check for duplicate Aadhaar number
        Applicant existingApplicant = applicantRepository.findByAadhaarNumber(applicantDto.getAadhaarNumber());
        if (existingApplicant != null) {
            throw new RuntimeException("Applicant with Aadhaar number " + applicantDto.getAadhaarNumber() + " already exists");
        }

        // Save new applicant
        Applicant applicant = new Applicant();
        applicant.setName(applicantDto.getName());
        applicant.setMobileNo(applicantDto.getMobileNo());
        applicant.setEmail(applicantDto.getEmail());
        applicant.setAddress(applicantDto.getAddress());
        applicant.setDob(applicantDto.getDob());
        applicant.setPanNumber(applicantDto.getPanNumber());
        applicant.setAadhaarNumber(applicantDto.getAadhaarNumber());
        applicant.setPassportPhotoUrl(applicantDto.getPassportPhotoUrl());
        applicant.setPanProofUrl(applicantDto.getPanProofUrl());
        applicant.setAadhaarProofUrl(applicantDto.getAadhaarProofUrl());
        return applicantRepository.save(applicant);
    }
}