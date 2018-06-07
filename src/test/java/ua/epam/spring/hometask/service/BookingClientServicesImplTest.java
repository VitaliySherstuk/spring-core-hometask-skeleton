package ua.epam.spring.hometask.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.AppConfig;
import ua.epam.spring.hometask.dao.DB.CounterDB;
import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.dao.TicketDao;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class BookingClientServicesImplTest {

    @Autowired
    private BookingService bookingServiceImpl;

    @Autowired
    private EventDao eventDaoDB;

    @Autowired
    private UserDao userDao;

    @Resource
    @Qualifier("getMiddleAuditorium")
    private Auditorium auditorium;

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private CounterDB counterDB;

    private LocalDateTime dateTime = LocalDateTime.now();
    @Test
    public void getTicketsPrice() {

        Set<Long> seats = new HashSet<>();
        seats.addAll(Arrays.asList(3L, 25L, 58L, 72L));
        NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();
        auditoriums.put(dateTime, auditorium);
        Event event = eventDaoDB.getById(1L);
        event.setAuditoriums(auditoriums);
        double ticketsPrice = bookingServiceImpl.getTicketsPrice(event, dateTime, new User(), seats);
        assertEquals(600, ticketsPrice, 0);
        assertTrue(counterDB.getAllPrices().get(event.getName())>0);
    }

    @Test
    public void bookTickets() {
        int count = ticketDao.getAll().size();
        User user = userDao.getById(1L);
        Event event = eventDaoDB.getById(1L);
        Ticket ticket = new Ticket(user, event, dateTime, 150L);
        bookingServiceImpl.bookTickets(new HashSet<>(Arrays.asList(ticket)));
        assertTrue(ticketDao.getAll().size()==count+1);
        assertTrue(counterDB.getCountBooking()>0);
    }

    @Test
    public void getPurchasedTicketsForEvent() {

        int count = ticketDao.getAll().size();
        User user = userDao.getById(1L);
        Event event = eventDaoDB.getById(1L);
        LocalDateTime dateTimeForPurchased = LocalDateTime.now();
        Ticket ticket = new Ticket(user, event, dateTimeForPurchased, 350L);
        bookingServiceImpl.bookTickets(new HashSet<>(Arrays.asList(ticket)));
        Set<Ticket> tickets = bookingServiceImpl.getPurchasedTicketsForEvent(event, dateTimeForPurchased);
        Assert.assertTrue(tickets.size()==1);
    }


}