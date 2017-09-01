package com.dkarachurin.trainTickets.model;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
@Entity
public class Wagon extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;
    @Enumerated(EnumType.STRING)
    private WagonType type;
    @Column
    private int seatsCount;
}
