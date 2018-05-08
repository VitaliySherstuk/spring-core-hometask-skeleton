package ua.epam.spring.hometask.service.impl;

import ua.epam.spring.hometask.dao.TicketDao;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.*;
import ua.epam.spring.hometask.service.BookingService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService{

    private UserDao userDao;
    private TicketDao ticketDao;
    private double koefVip;
    private double koefRaiting;

    public BookingServiceImpl(UserDao userDao, TicketDao ticketDao, double koefVip, double koefRaiting)
    {
        this.userDao = userDao;
        this.ticketDao = ticketDao;
        this.koefVip = koefVip;
        this.koefRaiting = koefRaiting;
    }

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {

        double regularPrise = event.getBasePrice();
        EventRating raiting = event.getRating();
        Auditorium auditorium = event.getAuditoriums().get(dateTime);

        double result = 0;
        for(Long seat : seats)
        {
            result += auditorium.getVipSeats().contains(seat) ? regularPrise * koefVip : regularPrise;
        }

        return EventRating.HIGH.equals(raiting) ? result * koefRaiting : result;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {

        ticketDao.bookTickets(tickets);

        for(Ticket ticket : tickets)
        {
            User user = ticket.getUser();

            if(user!=null)
            {
                user.getTickets().add(ticket);
                userDao.add(user);
            }
        }
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {

        Set<Ticket> tickets = ticketDao.getTicketsByDateTime(dateTime);
        return tickets.stream().filter(r -> r.getEvent().equals(event)).collect(Collectors.toSet());
    }
}
