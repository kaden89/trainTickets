package com.dkarachurin.trainTickets.service;

import com.dkarachurin.trainTickets.model.TicketTransaction;

import java.util.List;

/**
 * Created by Denis Karachurin on 04.09.2017.
 */
public interface TicketTransactionService extends CrudService<TicketTransaction> {
    TicketTransaction buyTicket(int ticketId, int userId);
    List<TicketTransaction> getAllUserTransactions(int userId);
}
