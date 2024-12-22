package com.proyectoHotel.best_travel.domain.repositories;

import com.proyectoHotel.best_travel.domain.entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationRepository extends JpaRepository<ReservationEntity, UUID> {

}
