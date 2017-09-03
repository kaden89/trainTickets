package com.dkarachurin.trainTickets.model;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
@Entity
public class Wagon extends BaseEntity {
    @Column
    private String number;
    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @Enumerated(EnumType.STRING)
    private WagonType type;

    @Column
    private int seatsCount;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public WagonType getType() {
        return type;
    }

    public void setType(WagonType type) {
        this.type = type;
    }

    public int getSeatsCount() {
        return seatsCount;
    }

    public void setSeatsCount(int seatsCount) {
        this.seatsCount = seatsCount;
    }
}
