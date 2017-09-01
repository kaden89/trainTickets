package com.dkarachurin.trainTickets.repository;

import com.dkarachurin.trainTickets.model.City;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
public interface CityRepository extends CrudRepository<City, Integer> {
}
