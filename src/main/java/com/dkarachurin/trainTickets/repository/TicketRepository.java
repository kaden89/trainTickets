package com.dkarachurin.trainTickets.repository;

import com.dkarachurin.trainTickets.model.Ticket;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
public interface TicketRepository extends CrudRepository<Ticket, Integer> {
}
