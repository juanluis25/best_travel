package com.proyectoHotel.best_travel.infraestructure.services;

import com.proyectoHotel.best_travel.api.models.request.TicketRequest;
import com.proyectoHotel.best_travel.api.models.responses.FlyResponse;
import com.proyectoHotel.best_travel.api.models.responses.TicketResponse;
import com.proyectoHotel.best_travel.domain.entities.TicketEntity;
import com.proyectoHotel.best_travel.domain.repositories.CustomerRepository;
import com.proyectoHotel.best_travel.domain.repositories.FlyRepository;
import com.proyectoHotel.best_travel.domain.repositories.TicketRepository;
import com.proyectoHotel.best_travel.infraestructure.abstract_services.ITicketService;
import com.proyectoHotel.best_travel.util.BestTravelUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.apache.logging.log4j.LogManager.getLogger;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class TicketService implements ITicketService {

    private static final Logger LOGGER = getLogger(TicketService.class);

    private final FlyRepository flyRepository;
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;
    @Override
    public TicketResponse create(TicketRequest request) {
        var fly = flyRepository.findById(request.getIdFly()).orElseThrow();
        var customer = customerRepository.findById(request.getIdClient()).orElseThrow();

        var ticketToPersist = TicketEntity.builder()
                .id(UUID.randomUUID())
                .fly(fly)
                .customer(customer)
                .price(fly.getPrice().add((fly.getPrice().multiply(charges_price_percentage))))
                .purchaseDate(LocalDate.now())
                .arrivalDate(BestTravelUtil.getRandomSoon())
                .departureDate(BestTravelUtil.getRandomLater())
                .build();

        var ticketPersisted = this.ticketRepository.save(ticketToPersist);

        LOGGER.error("Ticket saved with id: {}",ticketPersisted.getId());
        log.info("Ticket saved with id: {}",ticketPersisted.getId());

        return this.entityToResponse(ticketPersisted);
    }

    @Override
    public TicketResponse read(UUID id) {

        var ticketFromDB = this.ticketRepository.findById(id).orElseThrow();
        return this.entityToResponse(ticketFromDB);
    }

    @Override
    public TicketResponse update(TicketRequest request, UUID id) {
        var ticketToUpdate = ticketRepository.findById(id).orElseThrow();
        var fly  = flyRepository.findById(request.getIdFly()).orElseThrow();

        ticketToUpdate.setFly(fly);
        ticketToUpdate.setPrice(fly.getPrice().add((fly.getPrice().multiply(charges_price_percentage))));
        ticketToUpdate.setDepartureDate(BestTravelUtil.getRandomSoon());
        ticketToUpdate.setArrivalDate(BestTravelUtil.getRandomLater());


        var ticketUpdated = this.ticketRepository.save(ticketToUpdate);

        log.info("Ticket updated with id {}", ticketUpdated.getId());

        return this.entityToResponse(ticketToUpdate);

    }

    @Override
    public void delete(UUID id) {
        var ticketToDelete = ticketRepository.findById(id).orElseThrow();
        this.ticketRepository.delete(ticketToDelete);
    }
    @Override
    public BigDecimal findPrice(Long flyId) {
        var fly = this.flyRepository.findById(flyId).orElseThrow();
        return fly.getPrice().add(fly.getPrice().multiply(charges_price_percentage));
    }
    private TicketResponse entityToResponse(TicketEntity entity){
        var response = new TicketResponse();
        BeanUtils.copyProperties(entity, response);
        var flyResponse = new FlyResponse();
        BeanUtils.copyProperties(entity.getFly(), flyResponse);
        response.setFly(flyResponse);
        return response;
    }

    public static final BigDecimal charges_price_percentage = BigDecimal.valueOf(0.25);
}