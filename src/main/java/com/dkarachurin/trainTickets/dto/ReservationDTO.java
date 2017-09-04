package com.dkarachurin.trainTickets.dto;

import java.time.LocalDateTime;

/**
 * Created by Denis Karachurin on 03.09.2017.
 */
public class ReservationDTO {
    private String userFullName;
    private String ticketNumber;
    private LocalDateTime reservationEndTime;

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public LocalDateTime getReservationEndTime() {
        return reservationEndTime;
    }

    public void setReservationEndTime(LocalDateTime reservationEndTime) {
        this.reservationEndTime = reservationEndTime;
    }
}
