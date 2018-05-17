package ua.epam.spring.hometask.util;


import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import java.time.LocalDateTime;
import javax.annotation.Nullable;


public interface DiscountStrategy {

    public @Nullable byte getDiscount(@Nullable User user, @Nullable Event event, @Nullable LocalDateTime airDateTime, @Nullable long numberOfTickets);

}
