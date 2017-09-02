package com.dkarachurin.trainTickets.service;


import com.dkarachurin.trainTickets.model.Reservation;
import com.dkarachurin.trainTickets.repository.ReservationRepository;
import com.dkarachurin.trainTickets.util.exceptions.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${application.reservationMinutesDuration}")
    private int reservationMinutesDuration;

    @Override
    public Reservation reserveTicket(int userId, int ticketId) {
        if (!isTicketReserved(ticketId)){
            Reservation reservation = new Reservation();
            reservation.setUser(userService.get(userId));
            reservation.setTicket(ticketService.getWithVersionIncrement(ticketId));
            reservation.setReservationEndTime(LocalDateTime.now().plusMinutes(reservationMinutesDuration));
            return save(reservation);
        } else {
            throw new ReservationException(String.format("Ticket with id %d already reserved", ticketId));
        }
    }

    @Override
    public boolean isTicketReserved(int ticketId) {
        return reservationRepository.isTicketReserved(ticketId);
    }

    @Override
    public boolean isTicketReservedByUser(int ticketId, int userId) {
        return reservationRepository.isTicketReservedByUser(ticketId, userId);
    }


    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}
