package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;

public interface AuditoriumDao extends AbstractDao<Auditorium>{

    public Auditorium getByName(String name);
    public Auditorium getById(Long id);
}
