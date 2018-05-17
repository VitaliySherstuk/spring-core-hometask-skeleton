package ua.epam.spring.hometask.service;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({UserServiceImplTest.class, EventServiceImplTest.class, DiscountServiceImplTest.class,
        BookingServiceImplTest.class, AuditoriumServiceImplTest.class, AspectTest.class})
public class SuteTest {
}
