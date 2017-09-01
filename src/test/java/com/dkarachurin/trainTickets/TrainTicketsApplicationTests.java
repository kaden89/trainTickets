package com.dkarachurin.trainTickets;

import com.dkarachurin.trainTickets.model.City;
import com.dkarachurin.trainTickets.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainTicketsApplicationTests {

	@Autowired
	CityService service;

	@Test
	public void contextLoads() {
		City city = new City();
		city.setName("UFA");
		service.save(city);
		List<City> news = service.findAll();
	}


}
