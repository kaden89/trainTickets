package com.dkarachurin.trainTickets.model;



import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
@Entity
public class Trip extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;
    @ManyToOne
    @JoinColumn(name = "departure_id", nullable = false)
    private City departure;
    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private City destination;
    @Column
    private LocalDateTime departureTime;
    @Column
    private LocalDateTime destinationTime;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "trip")
    private List<Ticket> tickets;
}
