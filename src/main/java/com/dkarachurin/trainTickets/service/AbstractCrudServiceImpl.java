package com.dkarachurin.trainTickets.service;

import com.dkarachurin.trainTickets.model.BaseEntity;
import com.dkarachurin.trainTickets.util.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
public abstract class AbstractCrudServiceImpl<T extends BaseEntity> implements CrudService<T> {
    @Autowired
    private CrudRepository<T, Integer> repository;

    @Override
    public T save(T obj) {
        return repository.save(obj);
    }

    @Override
    public void update(T obj) throws NotFoundException {
        checkNotFound(obj.getId());
        repository.save(obj);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFound(id);
        repository.delete(id);
    }

    @Override
    public T get(int id) throws NotFoundException {
        checkNotFound(id);
        return repository.findOne(id);
    }

    @Override
    public List<T> findAll() {
        return (List<T>) repository.findAll();
    }

    private void checkNotFound(int id) throws NotFoundException{
        if (!repository.exists(id)){
            throw new NotFoundException(String.format("Entity with id %d not found", id));
        }
    }
}
