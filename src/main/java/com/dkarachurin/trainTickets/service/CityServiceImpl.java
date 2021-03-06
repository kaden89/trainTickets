package com.dkarachurin.trainTickets.service;


import com.dkarachurin.trainTickets.model.City;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
@Service
@Transactional
public class CityServiceImpl extends AbstractCrudServiceImpl<City> implements CityService{
}
