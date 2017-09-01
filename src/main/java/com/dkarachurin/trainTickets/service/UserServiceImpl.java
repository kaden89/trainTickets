package com.dkarachurin.trainTickets.service;


import com.dkarachurin.trainTickets.model.City;
import com.dkarachurin.trainTickets.model.User;
import com.dkarachurin.trainTickets.util.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
@Service
@Transactional
public class UserServiceImpl extends CrudServiceImpl<User> implements UserService{
    @Override
    public User getByUsername(String username) throws NotFoundException {
        return null;
    }

    @Override
    public void enable(int id, boolean enable) {

    }
}
