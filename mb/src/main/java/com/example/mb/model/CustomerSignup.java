package com.example.mb.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_signup")
public class CustomerSignup {

    public enum Gender {
        MALE,
        FEMALE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate dob;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String mobileNo;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String pincode;

    @Column(nullable = false, unique = true)
    private String panNumber;

    @Column(nullable = false, unique = true)
    private String aadhaarNumber;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private String ifscCode;

    @Column(nullable = false)
    private String panCardProof;

    public String getPanCardProof() {
		return panCardProof;
	}

	public void setPanCardProof(String panCardProof) {
		this.panCardProof = panCardProof;
	}

	public String getAadhaarCardProof() {
		return aadhaarCardProof;
	}

	public void setAadhaarCardProof(String aadhaarCardProof) {
		this.aadhaarCardProof = aadhaarCardProof;
	}

	public String getPassportPhoto() {
		return passportPhoto;
	}

	public void setPassportPhoto(String passportPhoto) {
		this.passportPhoto = passportPhoto;
	}

	@Column(nullable = false)
    private String aadhaarCardProof;

    @Column(nullable = false)
    private String passportPhoto;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getPanCardProofUrl() {
        return panCardProof;
    }

    public void setPanCardProofUrl(String panCardProofUrl) {
        this.panCardProof = panCardProofUrl;
    }

    public String getAadhaarCardProofUrl() {
        return aadhaarCardProof;
    }

    public void setAadhaarCardProofUrl(String aadhaarCardProofUrl) {
        this.aadhaarCardProof = aadhaarCardProofUrl;
    }

    public String getPassportPhotoUrl() {
        return passportPhoto;
    }

    public void setPassportPhotoUrl(String passportPhotoUrl) {
        this.passportPhoto = passportPhotoUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
	public CustomerSignup() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerSignup(Long id, String name, LocalDate dob, Gender gender, String email, String mobileNo,
			String address, String city, String state, String pincode, String panNumber, String aadhaarNumber,
			String accountNumber, String ifscCode, String panCardProof, String aadhaarCardProof, String passportPhoto,
			String username, String password) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.mobileNo = mobileNo;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.panNumber = panNumber;
		this.aadhaarNumber = aadhaarNumber;
		this.accountNumber = accountNumber;
		this.ifscCode = ifscCode;
		this.panCardProof = panCardProof;
		this.aadhaarCardProof = aadhaarCardProof;
		this.passportPhoto = passportPhoto;
		this.username = username;
		this.password = password;
	}

	public CustomerSignup(String name, LocalDate dob, Gender gender, String email, String mobileNo, String address,
			String city, String state, String pincode, String panNumber, String aadhaarNumber, String accountNumber,
			String ifscCode, String panCardProof, String aadhaarCardProof, String passportPhoto, String username,
			String password) {
		super();
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.mobileNo = mobileNo;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.panNumber = panNumber;
		this.aadhaarNumber = aadhaarNumber;
		this.accountNumber = accountNumber;
		this.ifscCode = ifscCode;
		this.panCardProof = panCardProof;
		this.aadhaarCardProof = aadhaarCardProof;
		this.passportPhoto = passportPhoto;
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "CustomerSignup [id=" + id + ", name=" + name + ", dob=" + dob + ", gender=" + gender + ", email="
				+ email + ", mobileNo=" + mobileNo + ", address=" + address + ", city=" + city + ", state=" + state
				+ ", pincode=" + pincode + ", panNumber=" + panNumber + ", aadhaarNumber=" + aadhaarNumber
				+ ", accountNumber=" + accountNumber + ", ifscCode=" + ifscCode + ", panCardProof=" + panCardProof
				+ ", aadhaarCardProof=" + aadhaarCardProof + ", passportPhoto=" + passportPhoto + ", username="
				+ username + ", password=" + password + "]";
	}
}
