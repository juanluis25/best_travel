package com.proyectoHotel.best_travel.api.controllers;

import com.proyectoHotel.best_travel.api.models.request.ReservationRequest;
import com.proyectoHotel.best_travel.api.models.responses.ReservationResponse;
import com.proyectoHotel.best_travel.infraestructure.abstract_services.IReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "reservation")
@AllArgsConstructor
public class ReservationController {

    private final IReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse>post(@RequestBody ReservationRequest request){
        return ResponseEntity.ok(reservationService.create(request));
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<ReservationResponse> get(@PathVariable UUID id){
        return ResponseEntity.ok(reservationService.read(id));
    }
    @PutMapping(path = "{id}")
    public ResponseEntity<ReservationResponse> update(@PathVariable UUID id, @RequestBody ReservationRequest request){
        return ResponseEntity.ok(this.reservationService.update(request, id));
    }
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        this.reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<Map<String, BigDecimal>> getReservationPrice(@RequestParam Long reservationId){
        return ResponseEntity.ok(Collections.singletonMap("reservationPrice", this.reservationService.findPrice(reservationId)));
    }
}
