package com.example.mb.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "bank_transfer")
public class BankTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String beneficiaryName;

    @Column(nullable = false)
    private String beneficiaryAccountNumber;

    @Column(nullable = false)
    private String beneficiaryIfsc;

    @Column(nullable = false)
    private String beneficiaryBankName;

    @Column(nullable = false)
    private String beneficiaryAccountType;

    @Column(nullable = false)
    private BigDecimal amount;

    @OneToOne
    private Transaction transaction;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getBeneficiaryAccountNumber() {
        return beneficiaryAccountNumber;
    }

    public void setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
    }

    public String getBeneficiaryIfsc() {
        return beneficiaryIfsc;
    }

    public void setBeneficiaryIfsc(String beneficiaryIfsc) {
        this.beneficiaryIfsc = beneficiaryIfsc;
    }

    public String getBeneficiaryBankName() {
        return beneficiaryBankName;
    }

    public void setBeneficiaryBankName(String beneficiaryBankName) {
        this.beneficiaryBankName = beneficiaryBankName;
    }

    public String getBeneficiaryAccountType() {
        return beneficiaryAccountType;
    }

    public void setBeneficiaryAccountType(String beneficiaryAccountType) {
        this.beneficiaryAccountType = beneficiaryAccountType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

	public BankTransfer(Long id, String beneficiaryName, String beneficiaryAccountNumber, String beneficiaryIfsc,
			String beneficiaryBankName, String beneficiaryAccountType, BigDecimal amount, Transaction transaction) {
		super();
		this.id = id;
		this.beneficiaryName = beneficiaryName;
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
		this.beneficiaryIfsc = beneficiaryIfsc;
		this.beneficiaryBankName = beneficiaryBankName;
		this.beneficiaryAccountType = beneficiaryAccountType;
		this.amount = amount;
		this.transaction = transaction;
	}

	public BankTransfer(String beneficiaryName, String beneficiaryAccountNumber, String beneficiaryIfsc,
			String beneficiaryBankName, String beneficiaryAccountType, BigDecimal amount, Transaction transaction) {
		super();
		this.beneficiaryName = beneficiaryName;
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
		this.beneficiaryIfsc = beneficiaryIfsc;
		this.beneficiaryBankName = beneficiaryBankName;
		this.beneficiaryAccountType = beneficiaryAccountType;
		this.amount = amount;
		this.transaction = transaction;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, beneficiaryAccountNumber, beneficiaryAccountType, beneficiaryBankName,
				beneficiaryIfsc, beneficiaryName, id, transaction);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankTransfer other = (BankTransfer) obj;
		return Objects.equals(amount, other.amount)
				&& Objects.equals(beneficiaryAccountNumber, other.beneficiaryAccountNumber)
				&& Objects.equals(beneficiaryAccountType, other.beneficiaryAccountType)
				&& Objects.equals(beneficiaryBankName, other.beneficiaryBankName)
				&& Objects.equals(beneficiaryIfsc, other.beneficiaryIfsc)
				&& Objects.equals(beneficiaryName, other.beneficiaryName) && Objects.equals(id, other.id)
				&& Objects.equals(transaction, other.transaction);
	}

	public BankTransfer() {
		super();
		// TODO Auto-generated constructor stub
	}
}
