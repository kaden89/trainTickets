package com.dkarachurin.trainTickets.service;


import com.dkarachurin.trainTickets.model.Reservation;
import com.dkarachurin.trainTickets.model.Ticket;
import com.dkarachurin.trainTickets.model.TicketStatus;
import com.dkarachurin.trainTickets.model.User;
import com.dkarachurin.trainTickets.util.exceptions.BuyingProcessException;
import com.dkarachurin.trainTickets.util.exceptions.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
@Service
@Transactional
public class TicketServiceImpl extends AbstractCrudServiceImpl<Ticket> implements TicketService{
    @Autowired
    private UserService userService;

    @Override
    public void buyTicket(int ticketId, int userId) {
        Ticket ticket = get(ticketId);
        User user = userService.get(userId);

        if (userCanBuyTicket(user, ticket)){
            user.setBalance(user.getBalance() - ticket.getPrice());
            ticket.setBoughtUser(user);
            ticket.setStatus(TicketStatus.SOLD);
        }
    }

    private boolean userCanBuyTicket(User user, Ticket ticket){
        Reservation reservation = ticket.getReservation();
        return userHaveReservation(user, reservation) && reservationHasNotExpired(reservation) && userHaveEnoughMoney(user, ticket);
    }

    private boolean userHaveReservation(User user, Reservation reservation){
        User reservationUser = reservation.getUser();

        if (reservationUser != null && reservationUser.equals(user)){
            return true;
        } else {
            throw new ReservationException("The user has not booked a ticket");
        }
    }

    private boolean reservationHasNotExpired(Reservation reservation){
        LocalDateTime reservationTime = reservation.getReservationEndTime();

        if (reservationTime != null && reservationTime.isAfter(LocalDateTime.now())){
            return true;
        } else {
            throw new ReservationException("Reservation time expired");
        }
    }

    private boolean userHaveEnoughMoney(User user, Ticket ticket){
        if (user.getBalance() >= ticket.getPrice()){
            return true;
        } else {
            throw new BuyingProcessException("User does not have enough money in the account");
        }
    }
}
