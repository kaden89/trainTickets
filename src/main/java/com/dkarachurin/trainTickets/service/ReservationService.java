package com.dkarachurin.trainTickets.service;

import com.dkarachurin.trainTickets.model.Reservation;
import com.dkarachurin.trainTickets.model.Ticket;

import java.time.LocalDateTime;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
public interface ReservationService {
    Reservation reserveTicket(int userId , int ticketId);
    boolean isTicketReserved(int ticketId);
    boolean isTicketReservedByUser(int ticketId, int userId);
    Reservation save(Reservation reservation);
}
