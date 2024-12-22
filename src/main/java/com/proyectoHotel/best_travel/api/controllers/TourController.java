package com.proyectoHotel.best_travel.api.controllers;

import com.proyectoHotel.best_travel.api.models.request.TourRequest;
import com.proyectoHotel.best_travel.api.models.responses.TourResponse;
import com.proyectoHotel.best_travel.infraestructure.abstract_services.ITourService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "tour")
@AllArgsConstructor
public class TourController {
    private final ITourService tourService;


    @PostMapping
    public ResponseEntity<TourResponse> post (@RequestBody TourRequest request){
        return ResponseEntity.ok(this.tourService.create(request));
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<TourResponse> get (@PathVariable Long id){
        return ResponseEntity.ok(this.tourService.read(id));
    }
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        this.tourService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping(path = "{tourId}/remove_ticket/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long tourId, @PathVariable UUID ticketId){
        this.tourService.removeTicket(tourId, ticketId);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping(path = "{tourId}/add_ticket/{ticketId}")
    public ResponseEntity<Map<String,UUID>> postTicket(@PathVariable Long tourId, @PathVariable Long flyId){
        var response = Collections.singletonMap("ticketId", this.tourService.addTicket(tourId, flyId));
        return ResponseEntity.ok(response);
    }
}