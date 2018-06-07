/*
package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.dao.AuditoriumDao;
import ua.epam.spring.hometask.domain.Auditorium;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

@Component
public class AuditoriumDaoImplementation implements AuditoriumDao {

    @Resource
    @Qualifier("auditoriums")
    private Set<Auditorium> auditoriums;


    @Override
    public Auditorium getByName(String name) {

        for(Auditorium auditorium : auditoriums)
        {
            if(auditorium.getName().equals(name))
                return auditorium;
        }
        return null;
    }

    @Override
    public Auditorium getById(Long id) {

        for(Auditorium auditorium : auditoriums)
        {
            if(auditorium.getId().equals(id))
                return auditorium;
        }
        return null;
    }

    @Override
    public Auditorium add(Auditorium auditorium) {

        auditoriums.add(auditorium);
        return auditorium;
    }

    @Override
    public void remove(Auditorium auditorium) {

        auditoriums.remove(auditorium);
    }

    @Override
    public Collection<Auditorium> getAll() {

        return auditoriums;
    }
}
*/
