package com.example.loanmoduleragul.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
public class BusinessLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String businessRegistrationProofUrl; 

    @Column(nullable = false)
    private String gstNumber;

    @Column(nullable = true)  // Made nullable in case it's not mandatory for all business loan applications
    private String profitAndLossStatementUrl; 

    @Column(nullable = true)  // Made nullable in case it's not mandatory for all business loan applications
    private String balanceSheetUrl; 

    @Column(nullable = true)  // Made nullable in case it's not mandatory for all business loan applications
    private String businessPlanUrl; 

    // Relationship with LoanApplication
    @OneToOne
    @JoinColumn(name = "loan_application_id", nullable = false)
    private LoanApplication loanApplication;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessRegistrationProofUrl() {
        return businessRegistrationProofUrl;
    }

    public void setBusinessRegistrationProofUrl(String businessRegistrationProofUrl) {
        this.businessRegistrationProofUrl = businessRegistrationProofUrl;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getProfitAndLossStatementUrl() {
        return profitAndLossStatementUrl;
    }

    public void setProfitAndLossStatementUrl(String profitAndLossStatementUrl) {
        this.profitAndLossStatementUrl = profitAndLossStatementUrl;
    }

    public String getBalanceSheetUrl() {
        return balanceSheetUrl;
    }

    public void setBalanceSheetUrl(String balanceSheetUrl) {
        this.balanceSheetUrl = balanceSheetUrl;
    }

    public String getBusinessPlanUrl() {
        return businessPlanUrl;
    }

    public void setBusinessPlanUrl(String businessPlanUrl) {
        this.businessPlanUrl = businessPlanUrl;
    }

    public LoanApplication getLoanApplication() {
        return loanApplication;
    }

    public void setLoanApplication(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }
}
