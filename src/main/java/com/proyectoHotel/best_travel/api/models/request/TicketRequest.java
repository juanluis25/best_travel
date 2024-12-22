package com.proyectoHotel.best_travel.api.models.request;

import com.proyectoHotel.best_travel.api.models.responses.FlyResponse;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TicketRequest implements Serializable {
    private String idClient;
    private Long idFly;
}
