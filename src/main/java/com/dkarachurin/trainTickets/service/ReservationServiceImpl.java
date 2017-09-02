package com.dkarachurin.trainTickets.service;


import com.dkarachurin.trainTickets.model.Reservation;
import com.dkarachurin.trainTickets.model.Ticket;
import com.dkarachurin.trainTickets.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
@Service
@Transactional
public class ReservationServiceImpl implements ReservationService{
    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Reservation reserveTicket(int userId, int ticketId, LocalDateTime reserveTime) {
        Reservation reservation = getReservationByTicketId(ticketId);
        reservation.setUser(userService.get(userId));
        reservation.setTicket(ticketService.get(ticketId));
        reservation.setReservationEndTime(reserveTime.plusMinutes(10));
        return save(reservation);
    }

    public Reservation getReservationByTicketId(int ticketId) {
        return reservationRepository.getReservationByTicketId(ticketId);
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}
