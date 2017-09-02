package com.dkarachurin.trainTickets.repository;

import com.dkarachurin.trainTickets.model.Ticket;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.List;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Query("SELECT t FROM Ticket t WHERE t.id = :ticketId")
    Ticket getWithVersionIncrement(@Param("ticketId")Integer ticketId);

    @Query("SELECT t FROM Ticket t LEFT JOIN t.reservation r WHERE t.trip.id = :tripId AND (t.reservation IS EMPTY OR r.reservationEndTime < CURRENT_TIMESTAMP) AND t.status <> 'SOLD'")
    List<Ticket> getAllAvailableByTrip(@Param("tripId")Integer tripId);
}
