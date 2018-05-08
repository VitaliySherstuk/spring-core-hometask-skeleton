package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.User;

public interface UserDao extends AbstractDao<User>{

    public User getUserByEmail(String email);
    public User getById( Long id);

}
