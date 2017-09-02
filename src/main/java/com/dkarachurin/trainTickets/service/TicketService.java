package com.dkarachurin.trainTickets.service;

import com.dkarachurin.trainTickets.model.Ticket;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
public interface TicketService extends CrudService<Ticket> {
    void buyTicket(int ticketId, int userId);
}
