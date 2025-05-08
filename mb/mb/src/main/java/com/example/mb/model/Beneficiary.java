package com.example.mb.model;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "beneficiary")
public class Beneficiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private String ifsc;

    @Column(nullable = false)
    private String bankName;

    @ManyToOne
    private Customer customer;

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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

	public Beneficiary(Long id, String name, String accountNumber, String ifsc, String bankName, Customer customer) {
		super();
		this.id = id;
		this.name = name;
		this.accountNumber = accountNumber;
		this.ifsc = ifsc;
		this.bankName = bankName;
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, bankName, customer, id, ifsc, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Beneficiary other = (Beneficiary) obj;
		return Objects.equals(accountNumber, other.accountNumber) && Objects.equals(bankName, other.bankName)
				&& Objects.equals(customer, other.customer) && Objects.equals(id, other.id)
				&& Objects.equals(ifsc, other.ifsc) && Objects.equals(name, other.name);
	}

	public Beneficiary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Beneficiary(String name, String accountNumber, String ifsc, String bankName, Customer customer) {
		super();
		this.name = name;
		this.accountNumber = accountNumber;
		this.ifsc = ifsc;
		this.bankName = bankName;
		this.customer = customer;
	}
}
