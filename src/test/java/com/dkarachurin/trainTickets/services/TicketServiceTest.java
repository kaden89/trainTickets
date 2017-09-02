package com.dkarachurin.trainTickets.services;

import com.dkarachurin.trainTickets.model.City;
import com.dkarachurin.trainTickets.model.Train;
import com.dkarachurin.trainTickets.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceTest {

	@Autowired
	private CrudService<City> crudService;

	@Test
	public void contextLoads() {
		City city = new City();
		city.setName("UFA");
		crudService.save(city);
		List<City> news = crudService.findAll();
	}


}
