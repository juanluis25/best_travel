package com.proyectoHotel.best_travel.infraestructure.abstract_services;

import com.proyectoHotel.best_travel.api.models.request.TicketRequest;
import com.proyectoHotel.best_travel.api.models.responses.TicketResponse;

import java.math.BigDecimal;
import java.util.UUID;

public interface ITicketService extends CrudService<TicketRequest, TicketResponse, UUID>{
    BigDecimal findPrice(Long flyId);
}
