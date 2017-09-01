package com.dkarachurin.trainTickets.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
@Entity
public class City extends BaseEntity {
    @Column
    private String name;
}
