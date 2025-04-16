package com.example.mb.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.model.CustomerSignup;
import com.example.mb.model.CustomerSignup.Gender;
import com.example.mb.service.CustomerSignupService;

@RestController
public class CustomerSignUpController {

    @Autowired
    private CustomerSignupService customerSignupService;

    @PostMapping("/api/newuser/signup")
    public CustomerSignup signup(
            @RequestParam("aadhaar_card_proof") MultipartFile aadhaarProof,
            @RequestParam("pan_card_proof") MultipartFile panProof,
            @RequestParam("passport_photo") MultipartFile passportPhoto,
            @RequestParam("aadhaar_number") String aadhaarNumber,
            @RequestParam("account_number") String accountNumber,
            @RequestParam("name") String Name,
            @RequestParam("ifsc_code") String ifscCode,
            @RequestParam("email") String email,
            @RequestParam("mobile_no") String mobileNo,
            @RequestParam("dob") String dob,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("gender") String genderStr, // Receive as String
            @RequestParam("address") String address,
            @RequestParam("city") String city,
            @RequestParam("state") String state,
            @RequestParam("pincode") String pincode,
            @RequestParam("pan_number") String panNumber,
            @ModelAttribute CustomerSignup customerSignup) {

        // Convert the gender to uppercase and match with enum
        Gender gender = Gender.valueOf(genderStr.toUpperCase().trim());

        // Date conversion
        LocalDate dateOfBirth = LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Set the uploaded file names
        customerSignup.setAadhaarCardProof(aadhaarProof.getOriginalFilename());
        customerSignup.setPanCardProof(panProof.getOriginalFilename());
        customerSignup.setPassportPhoto(passportPhoto.getOriginalFilename());

        // Set the customer details
        customerSignup.setDob(dateOfBirth);
        customerSignup.setAadhaarNumber(aadhaarNumber);
        customerSignup.setAccountNumber(accountNumber);
        customerSignup.setIfscCode(ifscCode);
        customerSignup.setName(Name);
        customerSignup.setEmail(email);
        customerSignup.setMobileNo(mobileNo);
        customerSignup.setUsername(username);
        customerSignup.setPassword(password);
        customerSignup.setGender(gender); // Use the parsed enum value
        customerSignup.setAddress(address);
        customerSignup.setCity(city);
        customerSignup.setState(state);
        customerSignup.setPincode(pincode);
        customerSignup.setPanNumber(panNumber);

        return customerSignupService.add(customerSignup);
    }
    @GetMapping("/api/getall/{id}")
    public CustomerSignup getDetails(@PathVariable Long id) throws InvalidIdException {
        return customerSignupService.getDetails(id);
    }
    @GetMapping("/api/customer/signup/ifsc/{ifscCode}")
    public CustomerSignup getCustomerSignupByIfsc(@PathVariable String ifscCode) throws InvalidIdException {
        return customerSignupService.getDetailsByIfsc(ifscCode);
    }
}
