package ua.epam.spring.hometask.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.impl.AuditoriumServiceImpl;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class AuditoriumServiceImplTest extends AbstractJUnit4SpringContextTests {

    private AuditoriumServiceImpl auditoriumServiceImpl;
    private Auditorium auditoriumFirst;
    private Auditorium auditoriumSecond;
    private Auditorium auditoriumThird;

    @Before
    public void setUp() throws Exception {

        auditoriumServiceImpl = (AuditoriumServiceImpl) applicationContext.getBean("auditoriumService");
        auditoriumFirst = (Auditorium) applicationContext.getBean("smallAuditorium");
        auditoriumSecond = (Auditorium) applicationContext.getBean("middleAuditorium");
        auditoriumThird = (Auditorium) applicationContext.getBean("bigAuditorium");
    }

    @Test
    public void getAll() {

        Set<Auditorium> auditoriumSet = auditoriumServiceImpl.getAll();
        assertTrue(auditoriumSet.contains(auditoriumFirst));
        assertTrue(auditoriumSet.contains(auditoriumSecond));
        assertTrue(auditoriumSet.contains(auditoriumThird));
    }

    @Test
    public void getByName() {

        assertNotNull(auditoriumServiceImpl.getByName(auditoriumFirst.getName()));
        assertNotNull(auditoriumServiceImpl.getByName(auditoriumSecond.getName()));
        assertNotNull(auditoriumServiceImpl.getByName(auditoriumThird.getName()));
    }
}