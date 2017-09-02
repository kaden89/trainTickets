package com.dkarachurin.trainTickets.services;

import com.dkarachurin.trainTickets.model.City;
import com.dkarachurin.trainTickets.model.Reservation;
import com.dkarachurin.trainTickets.model.Ticket;
import com.dkarachurin.trainTickets.service.CrudService;
import com.dkarachurin.trainTickets.service.ReservationService;
import com.dkarachurin.trainTickets.service.TicketService;
import com.dkarachurin.trainTickets.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static com.dkarachurin.trainTickets.testData.UserTestData.USER_ID;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {

	@Autowired
	private ReservationService reservationService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private UserService userService;

	@Test
	public void shouldUpdateReservation() {
		Reservation reservation = reservationService.reserveTicket(USER_ID, 1001, LocalDateTime.now());
		assertThat(reservation.getUser(), is(userService.get(USER_ID)));
		assertNotNull(reservation);
	}


}
