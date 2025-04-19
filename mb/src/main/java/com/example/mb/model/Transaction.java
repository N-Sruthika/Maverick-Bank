package com.example.mb.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account fromAccount;

    private Double amount;
    private String transactionType; // UPI or BANK
    private String status;
    private String purpose;
    private String description;
    private String transactionMode;
    private LocalDateTime transactionDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Account getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTransactionMode() {
		return transactionMode;
	}
	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}
	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}
	public Transaction(Long id, Account fromAccount, Double amount, String transactionType, String status,
			String purpose, String description, String transactionMode, LocalDateTime transactionDate) {
		super();
		this.id = id;
		this.fromAccount = fromAccount;
		this.amount = amount;
		this.transactionType = transactionType;
		this.status = status;
		this.purpose = purpose;
		this.description = description;
		this.transactionMode = transactionMode;
		this.transactionDate = transactionDate;
	}
	public Transaction(Account fromAccount, Double amount, String transactionType, String status, String purpose,
			String description, String transactionMode, LocalDateTime transactionDate) {
		super();
		this.fromAccount = fromAccount;
		this.amount = amount;
		this.transactionType = transactionType;
		this.status = status;
		this.purpose = purpose;
		this.description = description;
		this.transactionMode = transactionMode;
		this.transactionDate = transactionDate;
	}
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(amount, description, fromAccount, id, purpose, status, transactionDate, transactionMode,
				transactionType);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(description, other.description)
				&& Objects.equals(fromAccount, other.fromAccount) && Objects.equals(id, other.id)
				&& Objects.equals(purpose, other.purpose) && Objects.equals(status, other.status)
				&& Objects.equals(transactionDate, other.transactionDate)
				&& Objects.equals(transactionMode, other.transactionMode)
				&& Objects.equals(transactionType, other.transactionType);
	}

    // Getters & Setters
}
