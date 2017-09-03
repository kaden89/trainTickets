package com.dkarachurin.trainTickets.service;

import com.dkarachurin.trainTickets.model.Trip;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Denis Karachurin on 03.09.2017.
 */
public interface TripService extends CrudService<Trip>{
    List<Trip> getAllOnDateByCities(LocalDate date, int departureId, int destinationId);
}
