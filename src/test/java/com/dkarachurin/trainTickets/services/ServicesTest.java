package com.dkarachurin.trainTickets.services;

import com.dkarachurin.trainTickets.model.Reservation;
import com.dkarachurin.trainTickets.model.Ticket;
import com.dkarachurin.trainTickets.model.TicketStatus;
import com.dkarachurin.trainTickets.service.ReservationService;
import com.dkarachurin.trainTickets.service.TicketService;
import com.dkarachurin.trainTickets.service.UserService;
import com.dkarachurin.trainTickets.util.exceptions.ReservationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static com.dkarachurin.trainTickets.testData.TestData.TICKET1_ID;
import static com.dkarachurin.trainTickets.testData.TestData.TICKET2_ID;
import static com.dkarachurin.trainTickets.testData.TestData.USER_ID;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
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
		Reservation reservation = reservationService.reserveTicket(USER_ID, TICKET1_ID, LocalDateTime.now());
		assertNotNull(reservation);
		assertThat(reservation.getUser(), is(userService.get(USER_ID)));
		assertTrue(reservation.getTicket().getVersion() == ticket.getVersion()+1);
	}

	@Test
	public void ticketShouldBeReserved() {
		assertTrue(reservationService.isTicketReserved(TICKET1_ID));
	}

	@Test
	public void userCanBuyReservedTicket() {
		ticketService.buyTicket(TICKET1_ID, USER_ID);
		assertThat(ticketService.get(TICKET1_ID).getBoughtUser(), is(userService.get(USER_ID)));
		assertThat(ticketService.get(TICKET1_ID).getStatus(), is(TicketStatus.SOLD));
	}

	@Test(expected = ReservationException.class)
	public void shouldThrowExceptionWhenTryToBuyTicketWithoutReservation() {
		ticketService.buyTicket(TICKET2_ID, USER_ID);
	}




}
