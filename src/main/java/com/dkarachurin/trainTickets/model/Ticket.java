package com.dkarachurin.trainTickets.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
@Entity
public class Ticket extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;
    @ManyToOne
    @JoinColumn(name = "wagon_id", nullable = false)
    private Wagon wagon;
    @Column
    private int placeNumber;
    @Column
    private int price;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "ticket")
    private List<Reservation> reservation;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User boughtUser;
    @Version
    private Integer version;

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Wagon getWagon() {
        return wagon;
    }

    public void setWagon(Wagon wagon) {
        this.wagon = wagon;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Reservation> getReservation() {
        return reservation;
    }

    public void setReservation(List<Reservation> reservation) {
        this.reservation = reservation;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public User getBoughtUser() {
        return boughtUser;
    }

    public void setBoughtUser(User boughtUser) {
        this.boughtUser = boughtUser;
    }

    public Integer getVersion() {
        return version;
    }
}
