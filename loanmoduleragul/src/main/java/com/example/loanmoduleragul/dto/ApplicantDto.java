package com.example.loanmoduleragul.dto;

import java.time.LocalDate;

public class ApplicantDto {

    private String name;
    private Long mobileNo;
    private String email;
    private String address;
    private LocalDate dob;
    private String panNumber;
    private String aadhaarNumber;
    private String passportPhotoUrl;
    private String panProofUrl;
    private String aadhaarProofUrl;
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

    // Getters and Setters
    // Add getters and setters for all fields
    
}
