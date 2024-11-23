package com.example.itog2.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String address;

    @NotBlank
    @Size(min = 10, max = 11)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank @Size(max = 50) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(max = 50) String name) {
        this.name = name;
    }

    public @NotBlank @Size(max = 50) String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank @Size(max = 50) String address) {
        this.address = address;
    }

    public @NotBlank @Size(min = 10, max = 11) String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank @Size(min = 10, max = 11) String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
