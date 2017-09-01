package com.dkarachurin.trainTickets.service;


import com.dkarachurin.trainTickets.model.Reservation;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
@Service
@Transactional
public class ReservationServiceImpl extends CrudServiceImpl<Reservation> implements ReservationService{
}
