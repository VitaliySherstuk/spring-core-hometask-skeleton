package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.dao.TicketDao;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.*;
import ua.epam.spring.hometask.service.BookingService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private UserDao userDao;
    @Autowired
    private TicketDao ticketDao;

    @Resource
    @Qualifier("koefVipValue")
    private double koefVip;

    @Resource
    @Qualifier("koefRaitingValue")
    private double koefRaiting;



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
                //userDao.add(user);
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
