package com.dkarachurin.trainTickets.service;

import com.dkarachurin.trainTickets.model.User;
import com.dkarachurin.trainTickets.util.exceptions.NotFoundException;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
public interface UserService extends CrudService<User>{

    User getByUsername(String username) throws NotFoundException;
}
