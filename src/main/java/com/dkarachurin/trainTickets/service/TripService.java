package com.dkarachurin.trainTickets.service;

import com.dkarachurin.trainTickets.model.Trip;
import com.dkarachurin.trainTickets.model.User;
import com.dkarachurin.trainTickets.util.exceptions.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Denis Karachurin on 03.09.2017.
 */
public interface TripService extends CrudService<Trip>{
    List<Trip> getAllOnDateByCities(LocalDateTime time, int departureId, int destinationId);
}
