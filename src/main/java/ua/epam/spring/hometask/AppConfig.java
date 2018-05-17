package ua.epam.spring.hometask;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.util.BirthdayStrategy;
import ua.epam.spring.hometask.util.DiscountStrategy;
import ua.epam.spring.hometask.util.GeneralDiscountStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("ua.epam.spring.hometask")
@PropertySource("file:src/main/resources/properties.properties")
/*@Import({DBconfig.class})*/
public class AppConfig {

    @Value("${amountTicketDiscount}")
    private byte amountTicketDiscount;
    @Value("${numberTicketDiscount}")
    private byte numberTicketDiscount;
    @Value("${birthdayDiscount}")
    private byte birthdayDiscount;
    @Value("${airDate}")
    private byte airDate;

    @Value("${koefVip}")
    private double koefVip;
    @Value("${koefRaiting}")
    private double koefRaiting;

    @Value ("#{'${smallGroup.name}'}")
    private String nameSmallGroup;
    @Value ("#{'${smallGroup.number}'}")
    private long numberSmallGroup;
    @Value("#{'${smallGroup.vip.seats}'.split(',')}")
    private Set<Long> vipSeatsSmallGroup;

    @Value ("#{'${middleGroup.name}'}")
    private String nameMiddleGroup;
    @Value ("#{'${middleGroup.number}'}")
    private long numberMiddleGroup;
    @Value("#{'${middleGroup.vip.seats}'.split(',')}")
    private Set<Long> vipSeatsMiddleGroup;

    @Value ("#{'${bigGroup.name}'}")
    private String nameBigGroup;
    @Value ("#{'${bigGroup.number}'}")
    private long numberBigGroup;
    @Value("#{'${bigGroup.vip.seats}'.split(',')}")
    private Set<Long> vipSeatsBigGroup;

    @Bean
    public double koefVipValue()
    {
        return koefVip;
    }

    @Bean
    public double koefRaitingValue()
    {
        return koefRaiting;
    }

    @Bean
    public Auditorium getSmallAuditorium()
    {
        return new Auditorium(nameSmallGroup, numberSmallGroup, vipSeatsSmallGroup);
    }

    @Bean
    public Auditorium getMiddleAuditorium()
    {
        return new Auditorium(nameMiddleGroup, numberMiddleGroup, vipSeatsMiddleGroup);
    }

    @Bean
    public Auditorium getBigAuditorium()
    {
        return new Auditorium(nameBigGroup, numberBigGroup, vipSeatsBigGroup);
    }

    @Bean
    public Set<Auditorium> auditoriums()
    {
        Set<Auditorium> auditoriumSet = new HashSet<>();
        auditoriumSet.add(getSmallAuditorium());
        auditoriumSet.add(getMiddleAuditorium());
        auditoriumSet.add(getBigAuditorium());
        return auditoriumSet;
    }


    @Bean
    public DiscountStrategy birthdayStrategy()
    {
        return new BirthdayStrategy(birthdayDiscount, airDate);
    }

    @Bean
    public DiscountStrategy discountStrategy()
    {
        return new GeneralDiscountStrategy(amountTicketDiscount, numberTicketDiscount);
    }

    @Bean
    public List<DiscountStrategy> discountServiceList()
    {
        List<DiscountStrategy> listOfdiscountService = new ArrayList<>();
        listOfdiscountService.add(birthdayStrategy());
        listOfdiscountService.add(discountStrategy());
        return listOfdiscountService;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
