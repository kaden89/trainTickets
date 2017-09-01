package com.dkarachurin.trainTickets.service;

import com.dkarachurin.trainTickets.util.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
public abstract class CrudServiceImpl<T> implements CrudService<T> {
    @Autowired
    private CrudRepository<T, Integer> repository;

    @Override
    public T save(T obj) {
        return repository.save(obj);
    }

    @Override
    public void update(T obj) throws NotFoundException {
        repository.save(obj);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        repository.delete(id);
    }

    @Override
    public T get(int id) throws NotFoundException {
        return repository.findOne(id);
    }

    @Override
    public List<T> findAll() {
        return (List<T>) repository.findAll();
    }
}
