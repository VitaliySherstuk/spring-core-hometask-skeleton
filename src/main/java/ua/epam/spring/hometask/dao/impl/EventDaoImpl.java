package ua.epam.spring.hometask.dao.impl;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.domain.Event;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class EventDaoImpl implements EventDao{

    private static Set<Event> events = new HashSet<>();

    @Override
    public Event getByName(String name) {

        for(Event event : events)
        {
            if(event.getName().equals(name))
                return event;
        }
        return null;
    }

    @Override
    public Event getById(Long id) {

        for(Event event : events)
        {

            if(event.getId().equals(id))
                return event;
        }
        return null;
    }

    @Override
    public Event add(Event event) {

        events.add(event);
        return event;
    }

    @Override
    public void remove(Event event) {

        events.remove(event);
    }

    @Override
    public Collection<Event> getAll() {
        return events;
    }
}
