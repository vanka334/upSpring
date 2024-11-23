package com.example.itog2.models;



import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class TypePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @Size(max = 50)
    private String description;

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

    public @Size(max = 50) String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 50) String description) {
        this.description = description;
    }
}

