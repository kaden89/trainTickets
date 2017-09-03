package com.dkarachurin.trainTickets.service;


import com.dkarachurin.trainTickets.model.Trip;
import com.dkarachurin.trainTickets.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Denis Karachurin on 03.09.2017.
 */
@Service
@Transactional
public class TripServiceImpl extends AbstractCrudServiceImpl<Trip> implements TripService{
    @Autowired
    private TripRepository repository;

    @Override
    public List<Trip> getAllOnDateByCities(LocalDate date, int departureId, int destinationId) {
        return repository.getAllOnDateByCities(date, departureId, destinationId);
    }
}
