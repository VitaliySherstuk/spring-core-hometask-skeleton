package ua.epam.spring.hometask.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.AppConfig;
import ua.epam.spring.hometask.dao.DB.CounterDB;
import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class EventClientServicesImplTest {

    @Autowired
    private EventDao eventDaoDb;

    @Autowired
    private CounterDB counterDB;

    @Test
    public void getByName() {

        Assert.assertTrue(eventDaoDb.getByName("Cinema").getId().equals(1L));
        Assert.assertTrue(counterDB.getAllEventByName().get("Cinema")==1);
    }

    @Test
    public void save() {
        int count = eventDaoDb.getAll().size();
        Event event = new Event();
        event.setName("Exhebition").setBasePrice(50.0).setRating(EventRating.HIGH);
        eventDaoDb.add(event);
        Assert.assertTrue(eventDaoDb.getAll().size()==count+1);
    }

    @Test
    public void remove(){

        int count = eventDaoDb.getAll().size();
        eventDaoDb.remove(eventDaoDb.getByName("Competition"));
        Assert.assertTrue(eventDaoDb.getAll().size()==count-1);
    }

    @Test
    public void getById() {

        Assert.assertTrue(eventDaoDb.getById(1L).getName().equals("Cinema"));
    }

    @Test
    public void getAll() {

        Assert.assertTrue(eventDaoDb.getAll().size()>0);
    }

}
