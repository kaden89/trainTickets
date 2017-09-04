package com.dkarachurin.trainTickets;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.dkarachurin.trainTickets.TestData.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username=USER_NAME,roles={"USER"})
@Sql(scripts = {"/db/populateDB"}, config = @SqlConfig(encoding = "UTF-8"))
public class RestTest {
	@Autowired
	private MockMvc mockMvc;

	@Before
	public void init(){
	}

	@Test
	public void shouldReturnTripsOnDate() throws Exception {
		this.mockMvc.perform(get("/api/v1/trips")
				.param("departureId", String.valueOf(UFA_ID))
				.param("destinationId", String.valueOf(SPB_ID))
				.param("date",DATE_WITH_TRIPS))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$.size()", greaterThan(0)));
	}
	@Test
	public void shouldReturnTicketsByTrip() throws Exception {
		this.mockMvc.perform(get("/api/v1/trips/"+TRIP1_ID+"/tickets"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$.size()", greaterThan(0)));
	}
	@Test
	public void shouldReturnReservationWithUserAndTicketData() throws Exception {
		this.mockMvc.perform(post("/api/v1/trips/"+TRIP1_ID+"/tickets/"+TICKET1_ID+"/reserve"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.userFullName", equalTo(USER_FIO)))
				.andExpect(jsonPath("$.ticketNumber", equalTo(TICKET1_NUMBER)));
	}
	@Test
	public void shouldReturnOkWhenBuyReservedTicket() throws Exception {
		this.mockMvc.perform(post("/api/v1/trips/"+TRIP1_ID+"/tickets/"+TICKET1_ID+"/reserve"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.userFullName", equalTo(USER_FIO)))
				.andExpect(jsonPath("$.ticketNumber", equalTo(TICKET1_NUMBER)));
		this.mockMvc.perform(post("/api/v1/trips/"+TRIP1_ID+"/tickets/"+TICKET1_ID+"/buy"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void shouldBeConflictWhenTryBoughtNotReservedTicket() throws Exception {
		this.mockMvc.perform(post("/api/v1/trips/"+TRIP1_ID+"/tickets/"+TICKET1_ID+"/buy"))
				.andDo(print())
				.andExpect(status().isConflict());
	}



}
