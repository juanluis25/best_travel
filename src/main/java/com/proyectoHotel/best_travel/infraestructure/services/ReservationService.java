package com.proyectoHotel.best_travel.infraestructure.services;

import com.proyectoHotel.best_travel.api.models.request.ReservationRequest;
import com.proyectoHotel.best_travel.api.models.responses.HotelResponse;
import com.proyectoHotel.best_travel.api.models.responses.ReservationResponse;
import com.proyectoHotel.best_travel.domain.entities.ReservationEntity;
import com.proyectoHotel.best_travel.domain.repositories.CustomerRepository;
import com.proyectoHotel.best_travel.domain.repositories.HotelRepository;
import com.proyectoHotel.best_travel.domain.repositories.ReservationRepository;
import com.proyectoHotel.best_travel.infraestructure.abstract_services.IReservationService;
import com.proyectoHotel.best_travel.infraestructure.helpers.CustomerHelper;
import com.proyectoHotel.best_travel.util.BestTravelUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class ReservationService implements IReservationService {
    private  final HotelRepository hotelRepository;
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;
    private final CustomerHelper customerHelper;

    @Override
    public ReservationResponse create(ReservationRequest request) {
        var hotel = hotelRepository.findById(request.getIdHotel()).orElseThrow();
        var customer = customerRepository.findById(request.getIdClient()).orElseThrow();

        var reservationToPersist = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .hotel(hotel)
                .customer(customer)
                .totalDays(request.getTotalDays())
                .dateTimeReservation(LocalDateTime.now())
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(request.getTotalDays()))
                .price(hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage)))
                .build();

        var reservationPersisted = reservationRepository.save(reservationToPersist);
        this.customerHelper.increase(customer.getDni(), ReservationService.class);
        return this.entityToResponse(reservationPersisted);
    }

    @Override
    public ReservationResponse read(UUID id) {
        var ReservationFromDB = this.reservationRepository.findById(id).orElseThrow();
        return this.entityToResponse(ReservationFromDB);
    }

    @Override
    public ReservationResponse update(ReservationRequest request, UUID id) {

        var hotel = hotelRepository.findById(request.getIdHotel()).orElseThrow();

        var reservationToUpdate = this.reservationRepository.findById(id).orElseThrow();

        reservationToUpdate.setHotel(hotel);
        reservationToUpdate.setTotalDays(request.getTotalDays());
        reservationToUpdate.setDateTimeReservation(LocalDateTime.now());
        reservationToUpdate.setDateStart(LocalDate.now());
        reservationToUpdate.setDateEnd(LocalDate.now());
        reservationToUpdate.setPrice(hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage)));

        var reservationUpadated = this.reservationRepository.save(reservationToUpdate);
        log.info("Reservation updated with Id {}", reservationUpadated.getId());
        return this.entityToResponse(reservationUpadated);
    }

    @Override
    public void delete(UUID id) {
        var reservationToDelete = reservationRepository.findById(id).orElseThrow();
        var dni = reservationToDelete.getCustomer().getDni();
        this.customerHelper.decrease(dni, ReservationService.class);
        this.reservationRepository.delete(reservationToDelete);
    }
    private ReservationResponse entityToResponse (ReservationEntity entity){
        var response = new ReservationResponse();
        BeanUtils.copyProperties(entity, response);
        var hotelResponse = new HotelResponse();
        BeanUtils.copyProperties(entity.getHotel(), hotelResponse);
        response.setHotel(hotelResponse);
        return response;
    }
    @Override
    public BigDecimal findPrice(Long hotelId){
        var hotel = hotelRepository.findById(hotelId).orElseThrow();
        return this.findPrice(hotel.getId()).add(hotel.getPrice().multiply(charges_price_percentage));
    }
    public static final BigDecimal charges_price_percentage = BigDecimal.valueOf(0.20);

}
