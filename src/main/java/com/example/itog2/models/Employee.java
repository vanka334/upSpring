package com.example.itog2.models;



import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Employee {
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


    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)

    private Role post;

    @ManyToOne
    @JoinColumn(name = "branch", nullable = false)
    private Branch branch;

    @Size(max = 20)
    private String phoneNumber;

    @Email
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(max = 256)
    private String hashPassword;

    @NotBlank
    @Size(max = 50)
    private String salt;

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

    public  Role getPost() {
        return post;
    }

    public void setPost( Role post) {
        this.post = post;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public @Size(max = 20) String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@Size(max = 20) String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @Email @Size(max = 50) String getEmail() {
        return email;
    }

    public void setEmail(@Email @Size(max = 50) String email) {
        this.email = email;
    }

    public @NotBlank @Size(max = 256) String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(@NotBlank @Size(max = 256) String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public @NotBlank @Size(max = 50) String getSalt() {
        return salt;
    }

    public void setSalt(@NotBlank @Size(max = 50) String salt) {
        this.salt = salt;
    }
// Getters and Setters
}
