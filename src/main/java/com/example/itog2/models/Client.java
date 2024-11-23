package com.example.itog2.models;



import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String firstName;

    @NotBlank
    @Size(max = 20)
    private String surname;

    @Size(max = 20)
    private String secondName;

    @Size(max = 20)
    private String passportNumber;

    @Size(max = 20)
    private String country;

    private LocalDateTime birthday;

    @Email
    @Size(max = 50)
    private String email;

    @Size(min = 10, max = 11)
    private String phoneNumber;

    @Size(max = 50)
    private String address;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank @Size(max = 20) String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank @Size(max = 20) String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank @Size(max = 20) String getSurname() {
        return surname;
    }

    public void setSurname(@NotBlank @Size(max = 20) String surname) {
        this.surname = surname;
    }

    public @Size(max = 20) String getSecondName() {
        return secondName;
    }

    public void setSecondName(@Size(max = 20) String secondName) {
        this.secondName = secondName;
    }

    public @Size(max = 20) String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(@Size(max = 20) String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public @Size(max = 20) String getCountry() {
        return country;
    }

    public void setCountry(@Size(max = 20) String country) {
        this.country = country;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public @Email @Size(max = 50) String getEmail() {
        return email;
    }

    public void setEmail(@Email @Size(max = 50) String email) {
        this.email = email;
    }

    public @Size(min = 10, max = 11) String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@Size(min = 10, max = 11) String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @Size(max = 50) String getAddress() {
        return address;
    }

    public void setAddress(@Size(max = 50) String address) {
        this.address = address;
    }
}
