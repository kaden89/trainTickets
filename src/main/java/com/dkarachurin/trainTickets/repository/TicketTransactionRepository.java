package com.dkarachurin.trainTickets.repository;

import com.dkarachurin.trainTickets.model.TicketTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Denis Karachurin on 04.09.2017.
 */
public interface TicketTransactionRepository extends CrudRepository<TicketTransaction, Integer> {
    List<TicketTransaction> getAllByUser_Id(int userId);
}
