package com.example.itog2.repositories;

import com.example.itog2.models.Branch;
import com.example.itog2.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
