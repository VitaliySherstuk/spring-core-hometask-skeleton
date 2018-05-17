package ua.epam.spring.hometask.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.AppConfig;
import ua.epam.spring.hometask.aspects.CounterAspect;
import ua.epam.spring.hometask.aspects.DiscountAspect;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AspectTest {

    @Autowired
    private CounterAspect counterAspect;

    @Autowired
    private EventService eventServiceImpl;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private DiscountAspect discountAspect;



    @Test
    public void getCountGetEventName()
    {
        System.out.println("getEventName counter:");
       counterAspect.getMapCounterEvent().entrySet().stream().forEach(r -> System.out.println(r.getKey() + ": " + r.getValue()));

    }

    @Test
    public void getCountGetTicketPrice()
    {
        System.out.println("geTicketPrice counter:");
        counterAspect.getMapCounterPricesQuering().entrySet().stream().forEach(r -> System.out.println(r.getKey().getName() + ": " + r.getValue()));

    }

    @Test
    public void getCounterTicketBooking()
    {
        System.out.println("geTicketBooking counter: ");
        counterAspect.getCounterTicketBooking().entrySet().stream().forEach(r -> System.out.println(r.getValue()));
    }

    @Test
    public void counterDiscount()
    {
        System.out.println("getDiscount counter:");
        discountAspect.getCounterDiscountMap().entrySet().stream().forEach(r -> System.out.println(r.getKey() + ": " + r.getValue()));
    }
}
