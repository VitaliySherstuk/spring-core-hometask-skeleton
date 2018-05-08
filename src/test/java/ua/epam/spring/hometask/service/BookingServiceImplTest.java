package ua.epam.spring.hometask.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.dao.TicketDao;
import ua.epam.spring.hometask.domain.*;
import ua.epam.spring.hometask.service.impl.BookingServiceImpl;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class BookingServiceImplTest extends AbstractJUnit4SpringContextTests {

    private BookingServiceImpl bookingServiceImpl;
    private Event event;
    private Auditorium auditorium;
    private TicketDao ticketDao;
    private double regularPrice = 100;
    private LocalDateTime dateTime;
    private Ticket ticketFirst;
    private Ticket ticketSecond;
    private Ticket ticketThird;

    @Before
    public void setUp() throws Exception {

        bookingServiceImpl = (BookingServiceImpl) applicationContext.getBean("bookingService");
        auditorium = (Auditorium) applicationContext.getBean("middleAuditorium");
        ticketDao = (TicketDao) applicationContext.getBean("ticketDao");

        event = new Event().setName("Exhibition");
        event.setBasePrice(regularPrice);
        event.setRating(EventRating.HIGH);

        dateTime = LocalDateTime.now();
        NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();
        auditoriums.put(dateTime, auditorium);
        event.setAuditoriums(auditoriums);

        ticketFirst = new Ticket(new User().setFirstName("Vasy"), event, dateTime, 1);
        ticketSecond = new Ticket(new User().setFirstName("Peter"), event, dateTime, 2);
        ticketThird = new Ticket(new User().setFirstName("Gena"), event, dateTime, 3);
    }

    @Test
    public void getTicketsPrice() {

        Set<Long> seats = new HashSet<>();
        seats.addAll(Arrays.asList(3L, 25L, 58L, 72L));
        double ticketsPrice = bookingServiceImpl.getTicketsPrice(event, dateTime, new User(), seats);
        assertEquals(600, ticketsPrice, 0);

    }

    @Test
    public void bookTickets() {

        Set<Ticket> tickets = new HashSet<>();
        tickets.add(ticketFirst);
        bookingServiceImpl.bookTickets(tickets);
        assertTrue(ticketDao.getAll().size()==1);
    }

    @Test
    public void getPurchasedTicketsForEvent() {

        Set<Ticket> tickets = new HashSet<>();
        tickets.add(ticketSecond);
        tickets.add(ticketThird);
        bookingServiceImpl.bookTickets(tickets);
        Set<Ticket> purchasedTickets = bookingServiceImpl.getPurchasedTicketsForEvent(event, dateTime);
        assertTrue(purchasedTickets.size() == 2);
    }


}