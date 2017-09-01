package com.dkarachurin.trainTickets.service;

import com.dkarachurin.trainTickets.util.exceptions.NotFoundException;

import java.util.List;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
public interface CrudService<T> {
    T save(T obj);
    void update(T obj) throws NotFoundException;
    void delete(int id) throws NotFoundException;
    T get(int id) throws NotFoundException;
    List<T> findAll();
}
