package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Ticket;

import java.time.LocalDateTime;
import java.util.Set;

public interface TicketDao extends AbstractDao<Ticket>{

    void bookTickets(Set<Ticket> tickets);
    Set<Ticket> getTicketsByDateTime(LocalDateTime dateTime);

}
