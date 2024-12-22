package com.proyectoHotel.best_travel.domain.repositories;

import com.proyectoHotel.best_travel.domain.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
}
