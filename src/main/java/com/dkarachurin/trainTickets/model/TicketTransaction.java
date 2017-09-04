package com.dkarachurin.trainTickets.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Denis Karachurin on 04.09.2017.
 */
@Entity
public class TicketTransaction extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
    @Column
    private int amount;
    @Column
    private LocalDateTime dateTime;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
