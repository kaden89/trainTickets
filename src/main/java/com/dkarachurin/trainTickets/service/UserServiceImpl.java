package com.dkarachurin.trainTickets.service;


import com.dkarachurin.trainTickets.model.User;
import com.dkarachurin.trainTickets.repository.UserRepository;
import com.dkarachurin.trainTickets.util.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractCrudServiceImpl<User> implements UserService{
    @Autowired
    private UserRepository repository;

    @Override
    public User getByUsername(String username) throws NotFoundException {
        return repository.getByUsername(username);
    }

    @Override
    public void enable(int id, boolean enable) {

    }
}
