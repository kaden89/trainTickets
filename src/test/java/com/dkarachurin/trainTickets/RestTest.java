package com.dkarachurin.trainTickets;


import com.dkarachurin.trainTickets.service.TripService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username="User",roles={"USER"})
@Sql(scripts = {"/db/populateDB"}, config = @SqlConfig(encoding = "UTF-8"))
public class RestTest {
	@MockBean
	TripService tripService;
//	@MockBean
//	TicketService ticketService;
	@Autowired
	private MockMvc mockMvc;

	@Before
	public void init(){
//		given(tripService.getAllOnDateByCities(LocalDate.now(), 1, 2)).willReturn(1);
//		given(ticketService.getAllAvailableByTrip(10101)).willReturn(Arrays.asList(new Ticket()));

	}

	@Test
	public void shouldCreateReservationAndIncrementTicketVersion() throws Exception {
		this.mockMvc.perform(get("/api/v1/trips")
				.param("departureId", "10003")
				.param("destinationId", "10002")
				.param("dateTime", LocalDateTime.now().minusYears(1).toString()))
				.andDo(print())
				.andExpect(status().isOk());
//		mockMvc.perform(get("/api/v1/trips")
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$", hasSize(3)));
	}
	@Test
	public void shouldReturnTickets() throws Exception {
		this.mockMvc.perform(get("/api/v1/trips/10101/tickets"))
				.andDo(print())
				.andExpect(status().isOk());
	}



}
