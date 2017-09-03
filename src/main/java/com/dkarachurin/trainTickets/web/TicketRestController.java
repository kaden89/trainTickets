package com.dkarachurin.trainTickets.web;


import com.dkarachurin.trainTickets.dto.DTOConverter;
import com.dkarachurin.trainTickets.dto.TicketDTO;
import com.dkarachurin.trainTickets.dto.TripDTO;
import com.dkarachurin.trainTickets.model.Ticket;
import com.dkarachurin.trainTickets.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Denis Karachurin on 02.09.2017.
 */
@RestController
@RequestMapping("/api/v1/trips/id/tickets/id")
public class TicketRestController {
    @Autowired
    DTOConverter<Ticket, TicketDTO> ticketDTOConverter;
    @Autowired
    DTOConverter<Trip, TripDTO> tripDTOConverter;

    List<TripDTO> getTrips(int departureId, int destinationId, LocalDateTime dateTime){
        return null;
    }

    List<TicketDTO> getTickets(int tripId){
        return null;
    }



}
