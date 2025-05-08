package com.example.mb.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "transaction_limit")
public class TransactionLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal dailyLimit;

    @Column(nullable = false)
    private BigDecimal monthlyLimit;

    @OneToOne
    private Account account;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(BigDecimal dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public BigDecimal getMonthlyLimit() {
        return monthlyLimit;
    }

    public void setMonthlyLimit(BigDecimal monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

	public TransactionLimit(Long id, BigDecimal dailyLimit, BigDecimal monthlyLimit, Account account) {
		super();
		this.id = id;
		this.dailyLimit = dailyLimit;
		this.monthlyLimit = monthlyLimit;
		this.account = account;
	}

	public TransactionLimit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionLimit(BigDecimal dailyLimit, BigDecimal monthlyLimit, Account account) {
		super();
		this.dailyLimit = dailyLimit;
		this.monthlyLimit = monthlyLimit;
		this.account = account;
	}

	@Override
	public int hashCode() {
		return Objects.hash(account, dailyLimit, id, monthlyLimit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionLimit other = (TransactionLimit) obj;
		return Objects.equals(account, other.account) && Objects.equals(dailyLimit, other.dailyLimit)
				&& Objects.equals(id, other.id) && Objects.equals(monthlyLimit, other.monthlyLimit);
	}
}
