package com.dkarachurin.trainTickets.web;


import com.dkarachurin.trainTickets.dto.DTOConverter;
import com.dkarachurin.trainTickets.dto.ReservationDTO;
import com.dkarachurin.trainTickets.dto.TicketDTO;
import com.dkarachurin.trainTickets.dto.TripDTO;
import com.dkarachurin.trainTickets.model.Reservation;
import com.dkarachurin.trainTickets.model.Ticket;
import com.dkarachurin.trainTickets.model.Trip;
import com.dkarachurin.trainTickets.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Collection;

/**
 * Created by Denis Karachurin on 02.09.2017.
 */
@RestController
@RequestMapping("/api/v1/trips")
public class TicketRestController {
    @Autowired
    private DTOConverter<Ticket, TicketDTO> ticketDTOConverter;
    @Autowired
    private DTOConverter<Trip, TripDTO> tripDTOConverter;
    @Autowired
    private DTOConverter<Reservation, ReservationDTO> reservationDTOConverter;
    @Autowired
    private TripService tripService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private UserService userService;
    @Autowired
    private TicketTransactionService ticketTransactionService;

    @GetMapping
    Collection<TripDTO> getTrips(@RequestParam("departureId") int departureId,
                                 @RequestParam("destinationId") int destinationId,
                                 @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date){
        return tripDTOConverter.convertCollection(tripService.getAllOnDateByCities(date, departureId, destinationId));
    }
    @GetMapping(value = "/{tripId}/tickets")
    Collection<TicketDTO> getTickets(@PathVariable("tripId") int tripId){
        return ticketDTOConverter.convertCollection(ticketService.getAllAvailableByTrip(tripId));
    }
    @PostMapping(value = "/{tripId}/tickets/{ticketId}/reserve")
    ReservationDTO reserveTicket(@PathVariable("ticketId") int ticketId, Principal principal){
        return reservationDTOConverter.convert(reservationService.reserveTicket(userService.getByUsername(principal.getName()).getId(), ticketId));
    }
    @PostMapping(value = "/{tripId}/tickets/{ticketId}/buy")
    @ResponseStatus(value = HttpStatus.OK)
    void buyTicket(@PathVariable("ticketId") int ticketId, Principal principal){
        ticketTransactionService.buyTicket(ticketId, userService.getByUsername(principal.getName()).getId());
    }



}
