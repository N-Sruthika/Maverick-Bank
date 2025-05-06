package com.example.mb.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "service_request")
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public enum Category {
        ACCOUNT,
        LOAN,
        TECHNICAL
    }

    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String message;

    @Column
    private String attachmentUrl; // JPG or DOC file path

    @Column(nullable = false)
    private String status;

    @ManyToOne
    private Customer customer;

    @Column
    private LocalDate createdDate;

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

	public ServiceRequest(Long id, Category category, String subject, String message, String attachmentUrl,
			String status, Customer customer, LocalDate createdDate) {
		super();
		this.id = id;
		this.category = category;
		this.subject = subject;
		this.message = message;
		this.attachmentUrl = attachmentUrl;
		this.status = status;
		this.customer = customer;
		this.createdDate = createdDate;
	}

	public ServiceRequest(Category category, String subject, String message, String attachmentUrl, String status,
			Customer customer, LocalDate createdDate) {
		super();
		this.category = category;
		this.subject = subject;
		this.message = message;
		this.attachmentUrl = attachmentUrl;
		this.status = status;
		this.customer = customer;
		this.createdDate = createdDate;
	}

	public ServiceRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceRequest(Long id, Category category, String subject, String message, String status, Customer customer,
			LocalDate createdDate) {
		super();
		this.id = id;
		this.category = category;
		this.subject = subject;
		this.message = message;
		this.status = status;
		this.customer = customer;
		this.createdDate = createdDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(attachmentUrl, category, createdDate, customer, id, message, status, subject);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceRequest other = (ServiceRequest) obj;
		return Objects.equals(attachmentUrl, other.attachmentUrl) && category == other.category
				&& Objects.equals(createdDate, other.createdDate) && Objects.equals(customer, other.customer)
				&& Objects.equals(id, other.id) && Objects.equals(message, other.message)
				&& Objects.equals(status, other.status) && Objects.equals(subject, other.subject);
	}
}
