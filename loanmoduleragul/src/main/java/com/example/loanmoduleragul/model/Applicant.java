package com.example.loanmoduleragul.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "applicant")
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long mobileNo;  

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private LocalDate dob; 

    @Column(nullable = false)
    private String panNumber;  

    @Column(nullable = false)
    private String aadhaarNumber;  

    @Column(nullable = false)
    private String passportPhotoUrl;  // URL for passport photo

    @Column(nullable = false)
    private String panProofUrl;  // URL for PAN proof

    @Column(nullable = false)
    private String aadhaarProofUrl;  // URL for Aadhaar proof

    // Relationship with LoanApplication
    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoanApplication> loanApplications = new ArrayList<>();

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public String getPassportPhotoUrl() {
        return passportPhotoUrl;
    }

    public void setPassportPhotoUrl(String passportPhotoUrl) {
        this.passportPhotoUrl = passportPhotoUrl;
    }

    public String getPanProofUrl() {
        return panProofUrl;
    }

    public void setPanProofUrl(String panProofUrl) {
        this.panProofUrl = panProofUrl;
    }

    public String getAadhaarProofUrl() {
        return aadhaarProofUrl;
    }

    public void setAadhaarProofUrl(String aadhaarProofUrl) {
        this.aadhaarProofUrl = aadhaarProofUrl;
    }

    public List<LoanApplication> getLoanApplications() {
        return loanApplications;
    }

    public void setLoanApplications(List<LoanApplication> loanApplications) {
        this.loanApplications = loanApplications;
    }
}
