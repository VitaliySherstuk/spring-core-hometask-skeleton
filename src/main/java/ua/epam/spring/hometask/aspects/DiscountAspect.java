package ua.epam.spring.hometask.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.User;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class DiscountAspect {

    private Map<User, Long> counterDiscountMap = new HashMap<>();

    @Pointcut("execution(* *.getDiscount(..))")
    private void getDiscount(){}

    @After("getDiscount()")
    public void counterDiscount(JoinPoint joinPoint)
    {
        User user = (User) joinPoint.getArgs()[0];
        counterDiscountMap.put(user, counterDiscountMap.get(user)==null ? 1 : counterDiscountMap.get(user)+1);
    }

    public Map<User, Long> getCounterDiscountMap()
    {
        return counterDiscountMap;
    }
}
