package ua.epam.spring.hometask.util;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.time.MonthDay;

public class BirthdayStrategy implements DiscountService {


    private byte birthdayDiscount;
    private byte airDate;

    public BirthdayStrategy(byte birthdayDiscount, byte airDate)
    {
        this.birthdayDiscount = birthdayDiscount;
        this.airDate = airDate;
    }


    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {

        if(user==null)
        {
            return 0;
        }
        MonthDay birthday = MonthDay.from(user.getBirthday());
        LocalDateTime start = airDateTime.minusDays(airDate);
        LocalDateTime end = airDateTime.plusDays(airDate);
        for (int i = 0; i < 10; i++) {
            MonthDay day = MonthDay.from(start.plusDays(i));

            if (day.equals(birthday)) {

                return birthdayDiscount;
            }
        }

        return 0;
    }
}
