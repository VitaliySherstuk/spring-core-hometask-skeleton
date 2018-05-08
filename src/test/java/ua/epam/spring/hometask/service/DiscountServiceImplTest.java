package ua.epam.spring.hometask.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class DiscountServiceImplTest extends AbstractJUnit4SpringContextTests {

    private DiscountService discountService;
    private DiscountService birthdayDiscountService;
    private DiscountService discountStrategyService;
    private Event event;
    private LocalDateTime time;


    @Before
    public void setUp() throws Exception {

        discountService = (DiscountService) applicationContext.getBean("discountService");
        birthdayDiscountService = (DiscountService) applicationContext.getBean("birthdayStrategy");
        discountStrategyService = (DiscountService) applicationContext.getBean("discountStrategy");
        event = new Event().setName("Circus");
        time = LocalDateTime.now();
    }

    @Test
    public void getDiscount() {

        byte discontTest = discountService.getDiscount(new User().setFirstName("Vasy").setBirthday(LocalDate.of(2000, 11, 15)), event, time, 20);
        assertEquals(5, discontTest);
        byte discontTestBirthdayStrategy = birthdayDiscountService.getDiscount(new User().setFirstName("Peter").setBirthday(time.toLocalDate()), event, time, 1);
        assertEquals(5, discontTestBirthdayStrategy);
        byte discontTestWithoutDiscount = birthdayDiscountService.getDiscount(new User().setFirstName("Peter").setBirthday(LocalDate.of(2000, 11, 15)), event, time, 1);
        assertEquals(0, discontTestWithoutDiscount);
        byte discontTestDiscountStrategy = discountStrategyService.getDiscount(new User().setFirstName("Gena").setBirthday(LocalDate.of(2000, 11, 15)), event, time, 30);
        assertEquals(5, discontTestDiscountStrategy);
        byte discontTestUserNotRegistrate = discountStrategyService.getDiscount(null, event, time, 30);
        assertEquals(5, discontTestUserNotRegistrate);

    }
}