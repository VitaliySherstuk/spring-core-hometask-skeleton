package ua.epam.spring.hometask.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.AppConfig;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.impl.AuditoriumServiceImpl;

import javax.annotation.Resource;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)

public class AuditoriumServiceImplTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private AuditoriumServiceImpl auditoriumServiceImpl;
    @Resource
    @Qualifier("getSmallAuditorium")
    private Auditorium auditoriumFirst;
    @Resource
    @Qualifier("getMiddleAuditorium")
    private Auditorium auditoriumSecond;
    @Resource
    @Qualifier("getBigAuditorium")
    private Auditorium auditoriumThird;


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