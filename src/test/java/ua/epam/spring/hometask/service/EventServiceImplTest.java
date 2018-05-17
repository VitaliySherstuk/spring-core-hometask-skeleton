package ua.epam.spring.hometask.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.AppConfig;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.service.impl.EventServiceImpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class EventServiceImplTest {

    private Event eventConcert;
    private Event eventCinema;
    @Autowired
    private EventServiceImpl eventService;
    private long idCinema = 20l;


    @Before
    public void setUp() throws Exception {

        //eventService = (EventServiceImpl) applicationContext.getBean("eventService");
        eventConcert = new Event().setName("Concert");
        eventCinema = new Event().setName("Cinema");
        eventCinema.setId(idCinema);
    }

    @Test
    public void getByName() {

        Event eventTest = eventService.getByName("Concert");
        assertNotNull(eventTest);
    }

    @Test
    public void save() {
        eventService.getAll().clear();
        eventService.save(eventConcert);
        assertTrue(eventService.getAll().size()==1);
    }

    @Test
    public void remove(){

        eventService.getAll().clear();
        eventService.save(eventConcert);
        eventService.remove(eventConcert);
        assertTrue(eventService.getAll().size()==0);
    }

    @Test
    public void getById() {
        eventService.save(eventCinema);
        Event eventTest = eventService.getById(idCinema);
        assertTrue(eventTest.equals(eventCinema));
    }

    @Test
    public void getAll() {
        assertNotNull(eventService.getAll());
    }

}