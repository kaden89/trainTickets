package com.dkarachurin.trainTickets.repository;

import com.dkarachurin.trainTickets.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
    Reservation getByTicketId(int ticketId);

    @Query("SELECT CASE WHEN MAX(r.reservationEndTime) >= :dateTime THEN true ELSE false END  FROM Reservation r WHERE r.ticket.id = :id")
    boolean isTicketReserved(@Param("id")Integer ticketId, @Param("dateTime")LocalDateTime dateTime);

    @Query("SELECT CASE WHEN COUNT (r.id) > 0 THEN true ELSE false END FROM Reservation r WHERE r.ticket.id = :ticketId AND r.user.id = :userId AND r.reservationEndTime >= :dateTime")
    boolean isTicketReservedByUser(@Param("ticketId")Integer ticketId, @Param("userId")Integer userId, @Param("dateTime")LocalDateTime dateTime);
}
