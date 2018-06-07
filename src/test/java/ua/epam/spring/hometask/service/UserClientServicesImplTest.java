package ua.epam.spring.hometask.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.AppConfig;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserClientServicesImplTest {

    @Autowired
    private UserService userService;


    @Test
    public void getUserByEmail() {

        User userByEmail = userService.getUserByEmail("Elena_Frau@epam.com");
        assertNotNull(userByEmail);
    }

   @Test
    public void save() {
       int countBefore = userService.getAll().size();
        User userTest = new User().setEmail("Gena_Sidorov@epam.com")
                .setBirthday(LocalDate.of(1990, 05, 10))
                .setFirstName("Gena").setLastName("Sidorov");
        userService.save(userTest);
        assertTrue(userService.getAll().size()==countBefore+1);
    }

    @Test
    public void remove() {

        int countBefore = userService.getAll().size();
        User userTest = new User().setFirstName("Peter").setLastName("Vasin")
                .setBirthday(LocalDate.of(1989, 05, 05))
                .setEmail("Peter_Vasin@epam.com");
        userTest.setId(2L);
        userService.remove(userTest);
        assertTrue(userService.getAll().size()==countBefore-1);
    }

   @Test
    public void getById() {
        String email = "Elena_Frau@epam.com";
        User userTest = userService.getById(1L);
        assertTrue(email.equals(userTest.getEmail()));
    }

    @Test
    public void getAll() {

        assertTrue(userService.getAll().size()==2);
    }
}