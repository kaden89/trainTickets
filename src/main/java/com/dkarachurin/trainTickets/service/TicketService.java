package com.dkarachurin.trainTickets.service;

import com.dkarachurin.trainTickets.model.Ticket;

import java.util.List;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
public interface TicketService extends CrudService<Ticket> {
    Ticket buyTicket(int ticketId, int userId);
    Ticket getWithVersionIncrement(int ticketId);
    List<Ticket> getAllAvailableByTrip(int tripId);
}
