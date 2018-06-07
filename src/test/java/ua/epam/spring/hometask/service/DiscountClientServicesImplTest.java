package ua.epam.spring.hometask.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.AppConfig;
import ua.epam.spring.hometask.dao.DB.CounterDB;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.util.DiscountStrategy;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class DiscountClientServicesImplTest {

    @Autowired
    private DiscountService discountService;
    @Resource
    @Qualifier("birthdayStrategy")
    private DiscountStrategy birthdayDiscountService;
    @Resource
    @Qualifier("discountStrategy")
    private DiscountStrategy discountStrategyService;
    private Event event;
    private LocalDateTime time;

    @Autowired
    private CounterDB counterDB;


    @Before
    public void setUp() throws Exception {

        event = new Event().setName("Circus");
        time = LocalDateTime.now();
    }

    @Test
    public void getDiscount() {

        User user = new User().setFirstName("Vasy").setBirthday(LocalDate.of(2000, 11, 15));
        byte discontTest = discountService.getDiscount(user, event, time, 20);
        assertEquals(5, discontTest);
        byte discontTestBirthdayStrategy = birthdayDiscountService.getDiscount(new User().setFirstName("Peter").setBirthday(time.toLocalDate()), event, time, 1);
        assertEquals(5, discontTestBirthdayStrategy);
        byte discontTestWithoutDiscount = birthdayDiscountService.getDiscount(new User().setFirstName("Peter").setBirthday(LocalDate.of(2000, 11, 15)), event, time, 1);
        assertEquals(0, discontTestWithoutDiscount);
        byte discontTestDiscountStrategy = discountStrategyService.getDiscount(new User().setFirstName("Gena").setBirthday(LocalDate.of(2000, 11, 15)), event, time, 30);
        assertEquals(5, discontTestDiscountStrategy);
        Assert.assertTrue(counterDB.getAllCounterDiscount().get(user.getFirstName())>0);
        byte discontTestUserNotRegistrate = discountStrategyService.getDiscount(null, event, time, 30);
        assertEquals(5, discontTestUserNotRegistrate);
    }
}