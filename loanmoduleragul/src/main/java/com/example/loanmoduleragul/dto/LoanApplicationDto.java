package com.example.loanmoduleragul.dto;

import com.example.loanmoduleragul.enums.LoanType;

public class LoanApplicationDto {

    private LoanType loanType; // Enum: EDUCATIONAL, BUSINESS, etc.
    private int cibilScore;
    private double minLoanAmount;
    private double maxLoanAmount;
    private int minTenure;
    private int maxTenure;
    private double interestRate;
	public LoanType getLoanType() {
		return loanType;
	}
	public void setLoanType(LoanType loanType) {
		this.loanType = loanType;
	}
	public int getCibilScore() {
		return cibilScore;
	}
	public void setCibilScore(int cibilScore) {
		this.cibilScore = cibilScore;
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

    // Getters and Setters
    // Add getters and setters for all fields
    
}