
package ua.epam.spring.hometask.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Event;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class CounterAspect {

    private Map<String, Integer> counterGetEventName = new HashMap<>();
    private Map<Event, Integer> counterPricesQueringMap = new HashMap<>();
    private Map<String, Integer> counterTicketBookingMap = new HashMap<>();

    @Pointcut("execution(* ua.epam.spring.hometask.dao.impl.EventDaoImpl.getByName(..))")
    private void getByName(){}

    @Pointcut("execution(* *.getTicketsPrice(..))")
    private void getTicketsPrice(){}

    @Pointcut("execution(* *.bookTickets(..))")
    private void bookTickets(){}

    @After("getByName()")
    public void counterForGetNameEvent(JoinPoint joinPoint)
    {
        String eventName = (String) joinPoint.getArgs()[0];
        counterGetEventName.put(eventName, counterGetEventName.get(eventName)==null ? 1 : counterGetEventName.get(eventName)+1);
    }

    @After("getTicketsPrice()")
    public void counterPricesQuering(JoinPoint joinPoint)
    {
        Event event = (Event)joinPoint.getArgs()[0];
        counterPricesQueringMap.put(event, counterPricesQueringMap.get(event)==null ? 1: counterPricesQueringMap.get(event)+1);
    }

    @After("bookTickets()")
    public void  counterBookingTickets()
    {
        counterTicketBookingMap.put("counter", counterTicketBookingMap.get("counter")==null ? 1: counterTicketBookingMap.get("counter")+1);
    }

    public Map<String, Integer> getMapCounterEvent()
    {
        return counterGetEventName;
    }

    public Map<Event, Integer> getMapCounterPricesQuering()
    {
        return counterPricesQueringMap;
    }

    public Map<String, Integer> getCounterTicketBooking()
    {
        return counterTicketBookingMap;
    }
}

