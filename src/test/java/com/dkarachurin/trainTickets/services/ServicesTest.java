package com.dkarachurin.trainTickets.services;

import com.dkarachurin.trainTickets.model.Reservation;
import com.dkarachurin.trainTickets.model.Ticket;
import com.dkarachurin.trainTickets.model.TicketStatus;
import com.dkarachurin.trainTickets.service.ReservationService;
import com.dkarachurin.trainTickets.service.TicketService;
import com.dkarachurin.trainTickets.service.UserService;
import com.dkarachurin.trainTickets.util.exceptions.ReservationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static com.dkarachurin.trainTickets.testData.TestData.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
@Sql(scripts = {"/db/populateDB"}, config = @SqlConfig(encoding = "UTF-8"))
public class ServicesTest {
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private UserService userService;

	@Test
	public void shouldCreateReservationAndIncrementTicketVersion() {
		Ticket ticket = ticketService.get(TICKET1_ID);
		Reservation reservation = reservationService.reserveTicket(USER_ID, TICKET1_ID);
		assertNotNull(reservation);
		assertThat(reservation.getUser(), is(userService.get(USER_ID)));
		assertTrue(reservationService.isTicketReserved(TICKET1_ID));
		assertTrue(reservation.getTicket().getVersion() == ticket.getVersion()+1);
	}

	@Test
	public void userCanBuyReservedTicket() {
		reservationService.reserveTicket(USER_ID, TICKET1_ID);
		ticketService.buyTicket(TICKET1_ID, USER_ID);
		assertThat(ticketService.get(TICKET1_ID).getBoughtUser(), is(userService.get(USER_ID)));
		assertThat(ticketService.get(TICKET1_ID).getStatus(), is(TicketStatus.SOLD));
	}

	@Test(expected = ReservationException.class)
	public void shouldThrowExceptionWhenTryToBuyTicketWithoutReservation() {
		ticketService.buyTicket(TICKET2_ID, USER_ID);
	}

	@Test(expected = ReservationException.class)
	public void shouldThrowExceptionWhenTryToReserveAlreadyReservedTicket() {
		reservationService.reserveTicket(USER_ID, TICKET1_ID);
		reservationService.reserveTicket(USER_ID, TICKET1_ID);
	}

	@Test
	public void shouldNotReturnReservedAndSoldTickets() {
		List<Ticket> allTickets = ticketService.getAllAvailableByTrip(10101);
		reservationService.reserveTicket(USER_ID, TICKET1_ID);
		ticketService.buyTicket(TICKET1_ID, USER_ID);
		reservationService.reserveTicket(USER_ID, TICKET2_ID);
		List<Ticket> availableTickets = ticketService.getAllAvailableByTrip(10101);
		assertEquals(allTickets.size()-2, availableTickets.size());
	}






}
