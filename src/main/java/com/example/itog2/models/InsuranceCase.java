package com.example.itog2.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class InsuranceCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinTable(name = "Cases_Policies",
    joinColumns = @JoinColumn(name ="insurance_product_id"),
            inverseJoinColumns = @JoinColumn(name = "insurance_policy_id")
    )
    private InsurancePolicy insurancePolicy;

    @NotNull
    private LocalDateTime issueDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InsurancePolicy getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }

    public @NotNull LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(@NotNull LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public @Size(max = 50) String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 50) String description) {
        this.description = description;
    }

    public @NotBlank @Size(max = 15) String getStatus() {
        return status;
    }

    public void setStatus(@NotBlank @Size(max = 15) String status) {
        this.status = status;
    }

    @Size(max = 50)
    private String description;

    @NotBlank
    @Size(max = 15)
    private String status;

    // Getters and Setters
}