package com.dkarachurin.trainTickets.repository;

import com.dkarachurin.trainTickets.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
public interface UserRepository extends CrudRepository<User, Integer>{
    User getByUsername(String username);
}
