package com.dkarachurin.trainTickets.model;



import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
@Entity
@Table(name = "trip", indexes = {@Index(name = "departureDate_idx",  columnList="departureDate", unique = false)})
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
    private LocalDate departureDate;
    @Column
    private LocalTime departureTime;
    @Column
    private LocalDateTime destinationTime;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "trip")
    private List<Ticket> tickets;

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public City getDeparture() {
        return departure;
    }

    public void setDeparture(City departure) {
        this.departure = departure;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(LocalDateTime destinationTime) {
        this.destinationTime = destinationTime;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
