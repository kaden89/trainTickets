package com.dkarachurin.trainTickets.service;

import com.dkarachurin.trainTickets.model.Ticket;
import com.dkarachurin.trainTickets.model.TicketStatus;
import com.dkarachurin.trainTickets.model.TicketTransaction;
import com.dkarachurin.trainTickets.model.User;
import com.dkarachurin.trainTickets.repository.TicketTransactionRepository;
import com.dkarachurin.trainTickets.util.exceptions.ReservationException;
import com.dkarachurin.trainTickets.util.exceptions.TicketTransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Denis Karachurin on 04.09.2017.
 */
@Service
@Transactional
public class TicketTransactionServiceImpl extends AbstractCrudServiceImpl<TicketTransaction> implements TicketTransactionService {
    @Autowired
    private TicketTransactionRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private TicketService ticketService;

    @Override
    public TicketTransaction buyTicket(int ticketId, int userId) {
        TicketTransaction ticketTransaction = new TicketTransaction();

        Ticket ticket = ticketService.get(ticketId);
        User user = userService.get(userId);

        if (userCanBuyTicket(user, ticket)){
            user.setBalance(user.getBalance() - ticket.getPrice());
            ticket.setStatus(TicketStatus.SOLD);
            ticketTransaction.setTicket(ticket);
            ticketTransaction.setUser(user);
            ticketTransaction.setAmount(ticket.getPrice());
            ticketTransaction.setDateTime(LocalDateTime.now());

            ticketService.update(ticket);
            userService.update(user);
            save(ticketTransaction);
        } else {
            throw new TicketTransactionException("User can not buy ticket");
        }

        return ticketTransaction;
    }


    private boolean userCanBuyTicket(User user, Ticket ticket){
        return isTicketReservedByUser(ticket.getId(), user.getId()) && isUserHaveEnoughMoney(user, ticket);
    }


    private boolean isTicketReservedByUser(int ticketId, int userId){
        if (reservationService.isTicketReservedByUser(ticketId, userId)){
            return true;
        } else {
            throw new ReservationException("User does not have reservation for ticket");
        }
    }

    private boolean isUserHaveEnoughMoney(User user, Ticket ticket){
        if (user.getBalance() >= ticket.getPrice()){
            return true;
        } else {
            throw new TicketTransactionException("User does not have enough money in the account");
        }
    }

    @Override
    public List<TicketTransaction> getAllUserTransactions(int userId) {
        return repository.getAllByUser_Id(userId);
    }
}
