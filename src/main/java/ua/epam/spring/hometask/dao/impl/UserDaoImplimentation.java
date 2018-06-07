/*
package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.User;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserDaoImplimentation implements UserDao {

    @Autowired
    private static Set<User> users = new HashSet<>();


    @Override
    public User add(User user) {
        users.add(user);
        return user;
    }

    @Override
    public void remove(User user) {

        users.remove(user);
    }

    @Override
    public User getById(Long id) {

        for(User user : users)
        {
            if(user.getId().equals(id))
               return user;
        }
        return null;
    }

    @Override
    public Collection getAll() {
        return users;
    }

    @Override
    public User getUserByEmail(String email) {

        for(User user : users)
        {
            if(user.getEmail().equals(email))
                return user;
        }
        return null;
    }
}
*/
