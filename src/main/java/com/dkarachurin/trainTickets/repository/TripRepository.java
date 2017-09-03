package com.dkarachurin.trainTickets.repository;

import com.dkarachurin.trainTickets.model.Trip;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
public interface TripRepository extends CrudRepository<Trip, Integer> {
    @Query("SELECT t FROM Trip t WHERE t.departureTime >= :time AND t.departure.id = :departureId AND t.destination.id = :destinationId")
    List<Trip> getAllOnDateByCities(@Param("time")LocalDateTime time, @Param("departureId")Integer departureId, @Param("destinationId")Integer destinationId);
}
