package com.example.loanmoduleragul.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class EducationalLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String markSheet10;

    @Column(nullable = false)
    private String markSheet12;

    @Column(nullable = false)
    private String certificateUG;

    @Column(nullable = false)
    private String admissionLetter;

    @Column(nullable = false)
    private String feeStructure;

    @Column(nullable = false)
    private String entranceExamScorecard;

    // Relationship with LoanApplication
    @OneToOne
    @JoinColumn(name = "loan_application_id", nullable = false)
    private LoanApplication loanApplication;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarkSheet10() {
        return markSheet10;
    }

    public void setMarkSheet10(String markSheet10) {
        this.markSheet10 = markSheet10;
    }

    public String getMarkSheet12() {
        return markSheet12;
    }

    public void setMarkSheet12(String markSheet12) {
        this.markSheet12 = markSheet12;
    }

    public String getCertificateUG() {
        return certificateUG;
    }

    public void setCertificateUG(String certificateUG) {
        this.certificateUG = certificateUG;
    }

    public String getAdmissionLetter() {
        return admissionLetter;
    }

    public void setAdmissionLetter(String admissionLetter) {
        this.admissionLetter = admissionLetter;
    }

    public String getFeeStructure() {
        return feeStructure;
    }

    public void setFeeStructure(String feeStructure) {
        this.feeStructure = feeStructure;
    }

    public String getEntranceExamScorecard() {
        return entranceExamScorecard;
    }

    public void setEntranceExamScorecard(String entranceExamScorecard) {
        this.entranceExamScorecard = entranceExamScorecard;
    }

    public LoanApplication getLoanApplication() {
        return loanApplication;
    }

    public void setLoanApplication(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }
}
