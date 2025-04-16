package com.example.mb.dto;

import java.math.BigDecimal;

public class UPITransactionRequest {
    private Long fromAccountId;
    private String upiId;
    private BigDecimal amount;
	public Long getFromAccountId() {
		return fromAccountId;
	}
	public void setFromAccountId(Long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	public String getUpiId() {
		return upiId;
	}
	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

    // Getters and setters
}
