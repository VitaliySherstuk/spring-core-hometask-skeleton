package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountService;
import ua.epam.spring.hometask.util.DiscountStrategy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {


    @Resource
    @Qualifier("discountServiceList")
    private List<DiscountStrategy> discountServiceList;


    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {

        byte discountMax = 0;

        for(DiscountStrategy discountService : discountServiceList)
        {
           byte discount = discountService.getDiscount(user, event, airDateTime, numberOfTickets);
           if(discount > discountMax)
           {
               discountMax = discount;
           }
        }

        return discountMax;
    }
}
