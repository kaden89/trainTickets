package com.dkarachurin.trainTickets;

import com.dkarachurin.trainTickets.model.*;
import com.dkarachurin.trainTickets.service.*;
import com.dkarachurin.trainTickets.util.exceptions.TicketTransactionException;
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
@TestPropertySource(locations="classpath:application.properties")
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
	@Autowired
	private TicketTransactionService ticketTransactionService;

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
	public void userCanBuyReservedTicketAndBalanceShouldDecreaseAndTicketStatusShouldBeSold() {
		int balanceBefore = userService.get(USER_ID).getBalance();
		reservationService.reserveTicket(USER_ID, TICKET1_ID);
		TicketTransaction ticketTransaction = ticketTransactionService.buyTicket(TICKET1_ID, USER_ID);
		Ticket boughtTicket = ticketService.get(TICKET1_ID);
		User user = userService.get(USER_ID);
		assertThat(user.getBalance(), is(balanceBefore-boughtTicket.getPrice()));
		assertThat(ticketTransaction.getUser(), is(userService.get(USER_ID)));
		assertThat(boughtTicket.getStatus(), is(TicketStatus.SOLD));
	}

	@Test(expected = ReservationException.class)
	public void shouldThrowExceptionWhenTryToBuyTicketWithoutReservation() {
		ticketTransactionService.buyTicket(TICKET2_ID, USER_ID);
	}

	@Test(expected = ReservationException.class)
	public void shouldThrowExceptionWhenTryToReserveAlreadyReservedTicket() {
		reservationService.reserveTicket(USER_ID, TICKET1_ID);
		reservationService.reserveTicket(ADMIN_ID, TICKET1_ID);
	}
	@Test(expected = TicketTransactionException.class)
	public void shouldThrowExceptionWhenTryToBuyWithoutMoney() {
		reservationService.reserveTicket(USER_ID, TICKET1_ID);
		User user = userService.get(USER_ID);
		user.setBalance(0);
		userService.update(user);
		ticketTransactionService.buyTicket(TICKET1_ID, USER_ID);
	}

	@Test
	public void shouldNotReturnReservedAndSoldTickets() {
		List<Ticket> allTickets = ticketService.getAllAvailableByTrip(TRIP1_ID);
		reservationService.reserveTicket(USER_ID, TICKET1_ID);
		ticketTransactionService.buyTicket(TICKET1_ID, USER_ID);
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
