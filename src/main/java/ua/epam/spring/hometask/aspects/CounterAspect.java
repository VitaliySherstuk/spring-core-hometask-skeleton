
package ua.epam.spring.hometask.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.dao.DB.CounterDB;
import ua.epam.spring.hometask.domain.Event;

@Component
@Aspect
public class CounterAspect {


    @Autowired
    private CounterDB counterDB;

    @Pointcut("execution(* ua.epam.spring.hometask.dao.EventDao.getByName(..))")
    private void getByName(){}

    @Pointcut("execution(* *.getTicketsPrice(..))")
    private void getTicketsPrice(){}

    @Pointcut("execution(* *.bookTickets(..))")
    private void bookTickets(){}

    @After("getByName()")
    public void counterForGetNameEvent(JoinPoint joinPoint)
    {
        String eventName = (String) joinPoint.getArgs()[0];
        Long count = counterDB.getCountByName(eventName);
        counterDB.setEventByName(eventName, count.equals(0L) ? 1L : count+1L);
    }

    @After("getTicketsPrice()")
    public void counterPricesQuering(JoinPoint joinPoint)
    {
        Event event = (Event)joinPoint.getArgs()[0];
        Long count = counterDB.getCountPrices(event.getName());
        counterDB.setPrices(event.getName(), count.equals(0L) ? 1L : count+1L);
    }

    @After("bookTickets()")
    public void  counterBookingTickets()
    {
        Long count = counterDB.getCountBooking();
        counterDB.setBooking(count.equals(0L) ? 1L : count+1L);
   }

}

