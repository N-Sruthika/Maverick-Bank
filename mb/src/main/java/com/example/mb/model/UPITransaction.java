package com.example.mb.model;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class UPITransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String upiId;
    private String amount;

    @OneToOne
    private Transaction transaction;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUpiId() {
		return upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public UPITransaction(Long id, String upiId, String amount, Transaction transaction) {
		super();
		this.id = id;
		this.upiId = upiId;
		this.amount = amount;
		this.transaction = transaction;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, id, transaction, upiId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UPITransaction other = (UPITransaction) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(id, other.id)
				&& Objects.equals(transaction, other.transaction) && Objects.equals(upiId, other.upiId);
	}

	public UPITransaction(String upiId, String amount, Transaction transaction) {
		super();
		this.upiId = upiId;
		this.amount = amount;
		this.transaction = transaction;
	}

	public UPITransaction() {
		super();
		// TODO Auto-generated constructor stub
	}

    // Getters & Setters
}
