package com.dkarachurin.trainTickets.service;


import com.dkarachurin.trainTickets.model.Train;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
@Service
@Transactional
public class TrainServiceImpl extends CrudServiceImpl<Train> implements TrainService{
}
