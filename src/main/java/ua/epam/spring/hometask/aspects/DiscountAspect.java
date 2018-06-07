package ua.epam.spring.hometask.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.dao.DB.CounterDB;
import ua.epam.spring.hometask.domain.User;

@Component
@Aspect
public class DiscountAspect {

    @Autowired
    private CounterDB counterDB;

    @Pointcut("execution(* *.getDiscount(..))")
    private void getDiscount(){}

    @After("getDiscount()")
    public void counterDiscount(JoinPoint joinPoint)
    {
        User user = (User) joinPoint.getArgs()[0];
        Long count = counterDB.getCountDiscount(user);
        counterDB.setCountDiscount(user, count.equals(0L) ? 1L : count++);
    }


}
