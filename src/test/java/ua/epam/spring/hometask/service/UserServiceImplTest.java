package ua.epam.spring.hometask.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.dao.impl.UserDaoImplimentation;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDate;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class UserServiceImplTest extends AbstractJUnit4SpringContextTests {

    private UserService userService;
    private User user;


    @Before
    public void setUp() throws Exception {

        userService = (UserService) applicationContext.getBean("userService");
        user = new User().setFirstName("Peter").setLastName("Ivanov")
                .setBirthday(LocalDate.of(2000, 11, 15))
                .setEmail("Peter_Ivanov.epan.com");

        userService.save(user);
    }

    @Test
    public void getUserByEmail() {

        User userByEmail = userService.getUserByEmail("Peter_Ivanov.epan.com");
        assertNotNull(userByEmail);
    }

    @Test
    public void save() {
        userService.getAll().clear();
        User userTest = new User().setEmail("Vasy_Petrov@epam.com")
                .setBirthday(LocalDate.of(1990, 05, 10))
                .setFirstName("Vasy").setLastName("Petrov");
        userService.save(userTest);
        assertTrue(userService.getAll().size()==1);
    }

    @Test
    public void remove() {
        userService.getAll().clear();
        User userTest = new User().setFirstName("Kate").setLastName("Gukova")
                .setBirthday(LocalDate.of(1993, 10, 12))
                .setEmail("Kate_Gukova@epam.com");
        userService.remove(userTest);
        assertTrue(userService.getAll().size()==0);
    }

    @Test
    public void getById() {
        userService.getAll().clear();
        long check = 2L;
        user.setId(check);
        userService.save(user);
        User userTest = userService.getById(check);
        assertTrue(user.equals(userTest));
    }

    @Test
    public void getAll() {
        userService.getAll().clear();
        userService.save(user);
        assertTrue(userService.getAll().size()==1);
    }
}