/*
package ua.epam.spring.hometask.dao.impl;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.dao.TicketDao;
import ua.epam.spring.hometask.domain.Ticket;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class TicketDaoImplimentation implements TicketDao {


    private static Set<Ticket> tickets = new HashSet<>();

    @Override
    public void bookTickets(Set<Ticket> OredrTickets) {

        tickets.addAll(OredrTickets);
    }

    @Override
    public Set<Ticket> getTicketsByDateTime(LocalDateTime dateTime) {

        Set<Ticket> ticketsDateTime = new HashSet<>();
        for(Ticket ticket : tickets)
        {
            if(ticket.getDateTime().equals(dateTime))
                ticketsDateTime.add(ticket);
        }
        return ticketsDateTime;
    }

    @Override
    public Ticket add(Ticket ticket) {

        tickets.add(ticket);
        return ticket;
    }

    @Override
    public void remove(Ticket ticket) {

        tickets.remove(ticket);
    }

    @Override
    public Collection<Ticket> getAll() {

        return tickets;
    }
}
*/
