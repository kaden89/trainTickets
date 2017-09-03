package com.dkarachurin.trainTickets.dto;

import com.dkarachurin.trainTickets.model.Ticket;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by Denis Karachurin on 03.09.2017.
 */
@Component
public class TicketDTOConverter implements DTOConverter<Ticket, TicketDTO> {
    @Override
    public TicketDTO convert(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTicketNumber(ticket.getNumber());
        ticketDTO.setTrainNumber(ticket.getTrip().getTrain().getNumber());
        ticketDTO.setWagonNumber(ticket.getWagon().getNumber());
        ticketDTO.setPlaceNumber(ticket.getPlaceNumber());
        ticketDTO.setPlaceType(ticket.getWagon().getType().toString());
        ticketDTO.setPrice(ticket.getPrice());
        ticketDTO.setDeparture(ticket.getTrip().getDeparture().getName());
        ticketDTO.setDepartureTime(LocalDateTime.of(ticket.getTrip().getDepartureDate(),ticket.getTrip().getDepartureTime()));
        ticketDTO.setDestination(ticket.getTrip().getDestination().getName());
        ticketDTO.setDestinationTime(ticket.getTrip().getDestinationTime());
        return ticketDTO;
    }
}
