package com.example.mb.dto;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
@Component
public class UPITransferDTO {
    private String upiId;
    private BigDecimal amount;
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
