package com.dkarachurin.trainTickets;

import com.dkarachurin.trainTickets.model.Reservation;
import com.dkarachurin.trainTickets.model.Ticket;
import com.dkarachurin.trainTickets.model.Trip;
import com.dkarachurin.trainTickets.model.User;
import com.dkarachurin.trainTickets.service.ReservationService;
import com.dkarachurin.trainTickets.service.TicketService;
import com.dkarachurin.trainTickets.service.TripService;
import com.dkarachurin.trainTickets.service.UserService;
import com.dkarachurin.trainTickets.util.exceptions.BalanceException;
import com.dkarachurin.trainTickets.util.exceptions.ReservationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static com.dkarachurin.trainTickets.TestData.*;
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
	@Autowired
	private TripService tripService;

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
	}

	@Test(expected = ReservationException.class)
	public void shouldThrowExceptionWhenTryToBuyTicketWithoutReservation() {
		ticketService.buyTicket(TICKET2_ID, USER_ID);
	}

	@Test(expected = ReservationException.class)
	public void shouldThrowExceptionWhenTryToReserveAlreadyReservedTicket() {
		reservationService.reserveTicket(USER_ID, TICKET1_ID);
		reservationService.reserveTicket(ADMIN_ID, TICKET1_ID);
	}
	@Test(expected = BalanceException.class)
	public void shouldThrowExceptionWhenTryToBuyWithoutMoney() {
		reservationService.reserveTicket(USER_ID, TICKET1_ID);
		User user = userService.get(USER_ID);
		user.setBalance(0);
		userService.update(user);
		ticketService.buyTicket(TICKET1_ID, USER_ID);
	}

	@Test
	public void shouldNotReturnReservedAndSoldTickets() {
		List<Ticket> allTickets = ticketService.getAllAvailableByTrip(TRIP1_ID);
		reservationService.reserveTicket(USER_ID, TICKET1_ID);
		ticketService.buyTicket(TICKET1_ID, USER_ID);
		reservationService.reserveTicket(USER_ID, TICKET2_ID);
		List<Ticket> availableTickets = ticketService.getAllAvailableByTrip(TRIP1_ID);
		assertEquals(allTickets.size()-2, availableTickets.size());
	}

	@Test
	public void shouldReturnTicketsOnDate() {
		List<Trip> trips = tripService.getAllOnDateByCities(LocalDate.parse(DATE_WITH_TRIPS), UFA_ID, SPB_ID);
		assertTrue(!trips.isEmpty());
	}





}
