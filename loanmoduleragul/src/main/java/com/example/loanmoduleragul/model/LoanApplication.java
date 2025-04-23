package com.example.loanmoduleragul.model;

import java.time.LocalDate;
import java.util.List;

import com.example.loanmoduleragul.enums.LoanStatus;
import com.example.loanmoduleragul.enums.LoanType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanType loanType;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus loanStatus;
    
    private LocalDate applicationDate;
    
    @Column(nullable = false)
    private int cibilScore;

    @Column(nullable = true)
    private String remarks;

    @Column(nullable = false)
    private double minLoanAmount;

    @Column(nullable = false)
    private double maxLoanAmount;

    @Column(nullable = false)
    private int minTenure;

    @Column(nullable = false)
    private int maxTenure;

    @Column(nullable = false)
    private double interestRate;

    // Relationship with Applicant (renamed column to applicant_id for clarity)
    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false)
    private Applicant applicant;

    // Relationship with EducationalLoan
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "educational_loan_id", referencedColumnName = "id", nullable = true)
    private EducationalLoan educationalLoan;

    // One-to-many relationship with IncomeDetails
    @OneToMany(mappedBy = "loanApplication")
    private List<IncomeDetails> incomeDetails;
    
    // One-to-one relationship with BusinessLoan
    @OneToOne(mappedBy = "loanApplication")
    private BusinessLoan businessLoan;  // For Business Loan

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public int getCibilScore() {
        return cibilScore;
    }

    public void setCibilScore(int cibilScore) {
        this.cibilScore = cibilScore;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getMinLoanAmount() {
        return minLoanAmount;
    }

    public void setMinLoanAmount(double minLoanAmount) {
        this.minLoanAmount = minLoanAmount;
    }

    public double getMaxLoanAmount() {
        return maxLoanAmount;
    }

    public void setMaxLoanAmount(double maxLoanAmount) {
        this.maxLoanAmount = maxLoanAmount;
    }

    public int getMinTenure() {
        return minTenure;
    }

    public void setMinTenure(int minTenure) {
        this.minTenure = minTenure;
    }

    public int getMaxTenure() {
        return maxTenure;
    }

    public void setMaxTenure(int maxTenure) {
        this.maxTenure = maxTenure;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public EducationalLoan getEducationalLoan() {
        return educationalLoan;
    }

    public void setEducationalLoan(EducationalLoan educationalLoan) {
        this.educationalLoan = educationalLoan;
    }

    public List<IncomeDetails> getIncomeDetails() {
        return incomeDetails;
    }

    public void setIncomeDetails(List<IncomeDetails> incomeDetails) {
        this.incomeDetails = incomeDetails;
    }

    public BusinessLoan getBusinessLoan() {
        return businessLoan;
    }

    public void setBusinessLoan(BusinessLoan businessLoan) {
        this.businessLoan = businessLoan;
    }
}
