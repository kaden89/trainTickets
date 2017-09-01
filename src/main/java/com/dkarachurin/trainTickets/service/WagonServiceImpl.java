package com.dkarachurin.trainTickets.service;


import com.dkarachurin.trainTickets.model.Wagon;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
@Service
@Transactional
public class WagonServiceImpl extends CrudServiceImpl<Wagon> implements WagonService{
}
