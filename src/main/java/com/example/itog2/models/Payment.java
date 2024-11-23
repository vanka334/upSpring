package com.example.itog2.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "insurancePolicyId", nullable = false)
    private InsurancePolicy insurancePolicy;

    @ManyToOne
    @JoinColumn(name = "insuranceCaseId", nullable = false)
    private InsuranceCase insuranceCase;

    @NotNull
    private LocalDateTime paymentDate;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal summary;

    @Size(max = 50)
    private String description;

    @ManyToOne
    @JoinColumn(name = "typePaymentId", nullable = false)
    private TypePayment typePayment;

    // Getters and Setters

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

    public InsuranceCase getInsuranceCase() {
        return insuranceCase;
    }

    public void setInsuranceCase(InsuranceCase insuranceCase) {
        this.insuranceCase = insuranceCase;
    }

    public @NotNull LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(@NotNull LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public @NotNull @DecimalMin("0.00") BigDecimal getSummary() {
        return summary;
    }

    public void setSummary(@NotNull @DecimalMin("0.00") BigDecimal summary) {
        this.summary = summary;
    }

    public @Size(max = 50) String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 50) String description) {
        this.description = description;
    }

    public TypePayment getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(TypePayment typePayment) {
        this.typePayment = typePayment;
    }
}
