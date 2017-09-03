package com.dkarachurin.trainTickets.dto;

import com.dkarachurin.trainTickets.model.Trip;
import org.springframework.stereotype.Component;

/**
 * Created by Denis Karachurin on 03.09.2017.
 */
@Component
public class TripDTOConverter implements DTOConverter<Trip, TripDTO> {
    @Override
    public TripDTO convert(Trip trip) {
        TripDTO tripDTO = new TripDTO();
        tripDTO.setTrainNumber(trip.getTrain().getNumber());
        tripDTO.setDeparture(trip.getDeparture().getName());
        tripDTO.setDepartureDate(trip.getDepartureDate());
        tripDTO.setDepartureTime(trip.getDepartureTime());
        tripDTO.setDestination(trip.getDestination().getName());
        tripDTO.setDestinationTime(trip.getDestinationTime());
        return tripDTO;
    }
}
