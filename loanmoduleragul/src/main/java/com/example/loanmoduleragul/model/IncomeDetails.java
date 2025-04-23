package com.example.loanmoduleragul.model;

import com.example.loanmoduleragul.enums.EmploymentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class IncomeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmploymentType employmentType;

    @Column(nullable = false)  // Ensure income is mandatory
    private Double annualIncome;

    // Salaried Specific Fields
    @Column(nullable = true)
    private String employerName;

    @Column(nullable = true)
    private String designation;

    @Column(nullable = true)
    private String employeeId;

    @Column(nullable = true)
    private String form16Url;

    @Column(nullable = true)
    private String salarySlipUrl;

    @Column(nullable = true)
    private String salaryBankStatementUrl;

    // Self-Employed Fields
    @Column(nullable = true)
    private String profession;

    @Column(nullable = true)
    private String businessRegistrationProofUrl;

    @Column(nullable = true)
    private String selfEmployedBankStatementUrl;

    @Column(nullable = true)
    private String selfEmployedItrUrl;

    // Business Owner Fields
    @Column(nullable = true)
    private String businessName;

    @Column(nullable = true)
    private String businessType; // Proprietorship, LLP, Pvt Ltd, etc.

    @Column(nullable = true)
    private String gstNumber;

    @Column(nullable = true)
    private String businessStartYear;

    @Column(nullable = true)
    private String auditedFinancialsUrl;

    @Column(nullable = true)
    private String businessBankStatementUrl;

    // Relationship with LoanApplication
    @ManyToOne
    @JoinColumn(name = "loan_application_id", nullable = false)
    private LoanApplication loanApplication;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public Double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getForm16Url() {
        return form16Url;
    }

    public void setForm16Url(String form16Url) {
        this.form16Url = form16Url;
    }

    public String getSalarySlipUrl() {
        return salarySlipUrl;
    }

    public void setSalarySlipUrl(String salarySlipUrl) {
        this.salarySlipUrl = salarySlipUrl;
    }

    public String getSalaryBankStatementUrl() {
        return salaryBankStatementUrl;
    }

    public void setSalaryBankStatementUrl(String salaryBankStatementUrl) {
        this.salaryBankStatementUrl = salaryBankStatementUrl;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getBusinessRegistrationProofUrl() {
        return businessRegistrationProofUrl;
    }

    public void setBusinessRegistrationProofUrl(String businessRegistrationProofUrl) {
        this.businessRegistrationProofUrl = businessRegistrationProofUrl;
    }

    public String getSelfEmployedBankStatementUrl() {
        return selfEmployedBankStatementUrl;
    }

    public void setSelfEmployedBankStatementUrl(String selfEmployedBankStatementUrl) {
        this.selfEmployedBankStatementUrl = selfEmployedBankStatementUrl;
    }

    public String getSelfEmployedItrUrl() {
        return selfEmployedItrUrl;
    }

    public void setSelfEmployedItrUrl(String selfEmployedItrUrl) {
        this.selfEmployedItrUrl = selfEmployedItrUrl;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getBusinessStartYear() {
        return businessStartYear;
    }

    public void setBusinessStartYear(String businessStartYear) {
        this.businessStartYear = businessStartYear;
    }

    public String getAuditedFinancialsUrl() {
        return auditedFinancialsUrl;
    }

    public void setAuditedFinancialsUrl(String auditedFinancialsUrl) {
        this.auditedFinancialsUrl = auditedFinancialsUrl;
    }

    public String getBusinessBankStatementUrl() {
        return businessBankStatementUrl;
    }

    public void setBusinessBankStatementUrl(String businessBankStatementUrl) {
        this.businessBankStatementUrl = businessBankStatementUrl;
    }

    public LoanApplication getLoanApplication() {
        return loanApplication;
    }

    public void setLoanApplication(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }
}
