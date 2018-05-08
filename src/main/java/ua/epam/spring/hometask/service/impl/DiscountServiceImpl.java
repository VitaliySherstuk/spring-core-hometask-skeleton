package ua.epam.spring.hometask.service.impl;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;

public class DiscountServiceImpl implements DiscountService {

    private List<DiscountService> discountServiceList;

    public DiscountServiceImpl(List<DiscountService> discountServiceList)
    {

        this.discountServiceList = discountServiceList;

    }

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {

        byte discountMax = 0;

        for(DiscountService discountService : discountServiceList)
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
