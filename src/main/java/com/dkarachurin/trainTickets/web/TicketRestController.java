package com.dkarachurin.trainTickets.web;

import com.dkarachurin.trainTickets.model.City;
import com.dkarachurin.trainTickets.model.Ticket;
import com.dkarachurin.trainTickets.model.Trip;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Denis Karachurin on 02.09.2017.
 */
@RestController
@RequestMapping("/api/v1/trips/id/tickets/id")
public class TicketRestController {
    List<Trip> getTrips(City detour, City destination, LocalDate date){
        return null;
    }

    List<Ticket> getTickets(int tripId){
        return null;
    }



}
