package com.proyectoHotel.best_travel.domain.repositories;

import com.proyectoHotel.best_travel.domain.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<TicketEntity, UUID> {
    Optional<TicketEntity> findByTourId(Long id);
}
