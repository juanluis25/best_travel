package com.proyectoHotel.best_travel.infraestructure.abstract_services;

import com.proyectoHotel.best_travel.api.models.responses.HotelResponse;

import java.util.Set;

public interface IHotelService extends CatalogService<HotelResponse>{
    Set<HotelResponse> findByRatingGreaterThan(Integer rating);
}
