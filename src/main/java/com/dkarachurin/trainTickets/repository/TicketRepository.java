package com.dkarachurin.trainTickets.repository;

import com.dkarachurin.trainTickets.model.Ticket;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Query("SELECT t FROM Ticket t WHERE t.id = :ticketId")
    Ticket getWithVersionIncrement(@Param("ticketId")Integer ticketId);
}
