package com.example.mb.model;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private String ifscCode;

    @Column(nullable = false)
    private String accountType;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    private Branch branch;

    @ManyToOne
    private Customer customer;

    // Getters and Setters
    
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(Long id, String accountNumber, String ifscCode, String accountType, BigDecimal balance,
			String status, Branch branch, Customer customer) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.ifscCode = ifscCode;
		this.accountType = accountType;
		this.balance = balance;
		this.status = status;
		this.branch = branch;
		this.customer = customer;
	}

	public Account(String accountNumber, String ifscCode, String accountType, BigDecimal balance, String status,
			Branch branch, Customer customer) {
		super();
		this.accountNumber = accountNumber;
		this.ifscCode = ifscCode;
		this.accountType = accountType;
		this.balance = balance;
		this.status = status;
		this.branch = branch;
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, accountType, balance, branch, customer, id, ifscCode, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(accountNumber, other.accountNumber) && Objects.equals(accountType, other.accountType)
				&& Objects.equals(balance, other.balance) && Objects.equals(branch, other.branch)
				&& Objects.equals(customer, other.customer) && Objects.equals(id, other.id)
				&& Objects.equals(ifscCode, other.ifscCode) && Objects.equals(status, other.status);
	}
}
