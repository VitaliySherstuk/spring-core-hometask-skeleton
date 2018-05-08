package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Event;

public interface EventDao extends AbstractDao<Event>{

    public Event getByName(String name);
    public Event getById(Long id);
}
