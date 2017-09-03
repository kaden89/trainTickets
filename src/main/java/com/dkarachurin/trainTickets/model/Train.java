package com.dkarachurin.trainTickets.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
@Entity
public class Train extends BaseEntity {
    @Column
    private String number;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "train")
    private List<Wagon> wagons;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Wagon> getWagons() {
        return wagons;
    }

    public void setWagons(List<Wagon> wagons) {
        this.wagons = wagons;
    }
}
