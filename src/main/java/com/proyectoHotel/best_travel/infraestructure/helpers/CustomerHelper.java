package com.proyectoHotel.best_travel.infraestructure.helpers;

import com.proyectoHotel.best_travel.domain.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@AllArgsConstructor
public class CustomerHelper {

    public final CustomerRepository customerRepository;

    public void increase(String CustomerId, Class<?> type){
        var customerToUpdate = this.customerRepository.findById(CustomerId).orElseThrow();
        switch (type.getSimpleName()){
            case "TourService" -> customerToUpdate.setTotalTours(customerToUpdate.getTotalTours()+1);
            case "TicketService" -> customerToUpdate.setTotalFlights(customerToUpdate.getTotalFlights()+1);
            case "ReservationService" -> customerToUpdate.setTotalLodgings(customerToUpdate.getTotalLodgings()+1);
        }
        this.customerRepository.save(customerToUpdate);
    }

    public void decrease(String CustomerId, Class<?> type){
        var customerToUpdate = this.customerRepository.findById(CustomerId).orElseThrow();
        switch (type.getSimpleName()){
            case "TourService" -> customerToUpdate.setTotalTours(customerToUpdate.getTotalTours()-1);
            case "TicketService" -> customerToUpdate.setTotalFlights(customerToUpdate.getTotalFlights()-1);
            case "ReservationService" -> customerToUpdate.setTotalLodgings(customerToUpdate.getTotalLodgings()-1);
        }
        this.customerRepository.save(customerToUpdate);
    }
}
