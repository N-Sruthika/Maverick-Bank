package com.example.mb.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer {

    public enum Gender {
        MALE,
        FEMALE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String mobileNo;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private LocalDate dob;

    @Column
    private String education;

    @Column
    private String occupation;

    @Column
    private String maritalStatus;

    @Column(nullable = false, unique = true)
    private String panNumber;

    @Column(nullable = false, unique = true)
    private String aadhaarNumber;

    @ManyToOne
    private Branch branch;
    
    // Getters and Setters

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
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

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

	

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Long id, String name, String username, String mobileNo, String email, String address, LocalDate dob,
			String education, String occupation, String maritalStatus, String panNumber, String aadhaarNumber,
			Branch branch) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.mobileNo = mobileNo;
		this.email = email;
		this.address = address;
		this.dob = dob;
		this.education = education;
		this.occupation = occupation;
		this.maritalStatus = maritalStatus;
		this.panNumber = panNumber;
		this.aadhaarNumber = aadhaarNumber;
		this.branch = branch;
	}

	public Customer(String name, String username, String mobileNo, String email, String address, LocalDate dob,
			String education, String occupation, String maritalStatus, String panNumber, String aadhaarNumber,
			Branch branch) {
		super();
		this.name = name;
		this.username = username;
		this.mobileNo = mobileNo;
		this.email = email;
		this.address = address;
		this.dob = dob;
		this.education = education;
		this.occupation = occupation;
		this.maritalStatus = maritalStatus;
		this.panNumber = panNumber;
		this.aadhaarNumber = aadhaarNumber;
		this.branch = branch;
	}

	@Override
	public int hashCode() {
		return Objects.hash(aadhaarNumber, address, branch, dob, education, email, id, maritalStatus, mobileNo, name,
				occupation, panNumber, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(aadhaarNumber, other.aadhaarNumber) && Objects.equals(address, other.address)
				&& Objects.equals(branch, other.branch) && Objects.equals(dob, other.dob)
				&& Objects.equals(education, other.education) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(maritalStatus, other.maritalStatus)
				&& Objects.equals(mobileNo, other.mobileNo) && Objects.equals(name, other.name)
				&& Objects.equals(occupation, other.occupation) && Objects.equals(panNumber, other.panNumber)
				&& Objects.equals(username, other.username);
	}
}
