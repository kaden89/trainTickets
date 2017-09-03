package com.dkarachurin.trainTickets.dto;

import com.dkarachurin.trainTickets.model.Ticket;
import org.springframework.stereotype.Component;

/**
 * Created by Denis Karachurin on 03.09.2017.
 */
@Component
public class BoughtTicketDTOConverter implements DTOConverter<Ticket, BoughtTicketDTO> {
    @Override
    public BoughtTicketDTO convert(Ticket entity) {
        return null;
    }
}
