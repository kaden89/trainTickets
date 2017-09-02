package com.dkarachurin.trainTickets.service;


import com.dkarachurin.trainTickets.model.Reservation;
import com.dkarachurin.trainTickets.model.Ticket;
import com.dkarachurin.trainTickets.model.TicketStatus;
import com.dkarachurin.trainTickets.model.User;
import com.dkarachurin.trainTickets.repository.TicketRepository;
import com.dkarachurin.trainTickets.util.exceptions.BuyingProcessException;
import com.dkarachurin.trainTickets.util.exceptions.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
@Service
@Transactional
public class TicketServiceImpl extends AbstractCrudServiceImpl<Ticket> implements TicketService{
    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;
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

    @Override
    public void buyTicket(int ticketId, int userId) {
        Ticket ticket = get(ticketId);
        User user = userService.get(userId);

        if (userCanBuyTicket(user, ticket)){
            user.setBalance(user.getBalance() - ticket.getPrice());
            ticket.setBoughtUser(user);
            ticket.setStatus(TicketStatus.SOLD);
        }

        userService.update(user);
        update(ticket);
    }


    private boolean userCanBuyTicket(User user, Ticket ticket){
        return isTicketReservedByUser(ticket.getId(), user.getId()) && userHaveEnoughMoney(user, ticket);
    }


    private boolean isTicketReservedByUser(int ticketId, int userId){
        if (reservationService.isTicketReservedByUser(ticketId, userId)){
            return true;
        } else {
            throw new ReservationException("User does not have reservation for ticket");
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
