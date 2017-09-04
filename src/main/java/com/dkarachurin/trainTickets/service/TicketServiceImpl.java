package com.dkarachurin.trainTickets.service;


import com.dkarachurin.trainTickets.model.Ticket;
import com.dkarachurin.trainTickets.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
@Service
@Transactional
public class TicketServiceImpl extends AbstractCrudServiceImpl<Ticket> implements TicketService{
    @Autowired
    private TicketRepository repository;

    @Override
    public Ticket getWithVersionIncrement(int ticketId) {
        return repository.getWithVersionIncrement(ticketId);
    }

    @Override
    public List<Ticket> getAllAvailableByTrip(int tripId) {
        return repository.getAllAvailableByTrip(tripId);
    }
}
