package com.dkarachurin.trainTickets.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
}
