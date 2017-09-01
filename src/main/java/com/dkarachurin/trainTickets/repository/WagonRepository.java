package com.dkarachurin.trainTickets.repository;

import com.dkarachurin.trainTickets.model.Wagon;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
public interface WagonRepository extends CrudRepository<Wagon, Integer> {
}
