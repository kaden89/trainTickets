package com.dkarachurin.trainTickets.dto;

import com.dkarachurin.trainTickets.model.Reservation;
import org.springframework.stereotype.Component;

/**
 * Created by Denis Karachurin on 03.09.2017.
 */
@Component
public class ReservationDTOConverter implements DTOConverter<Reservation, ReservationDTO> {
    @Override
    public ReservationDTO convert(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setUserFullName(reservation.getUser().getFullName());
        reservationDTO.setTicketNumber(reservation.getTicket().getNumber());
        reservationDTO.setReservationEndTime(reservation.getReservationEndTime());
        return reservationDTO;
    }
}
