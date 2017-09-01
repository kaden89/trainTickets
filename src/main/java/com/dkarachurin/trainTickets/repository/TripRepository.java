package com.dkarachurin.trainTickets.repository;

import com.dkarachurin.trainTickets.model.Trip;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
public interface TripRepository extends CrudRepository<Trip, Integer> {
}
