package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nullable;

public interface UserDao extends AbstractDao<User>{

    public @Nullable User getUserByEmail(@Nullable String email);
    public @Nullable User getById(@Nullable Long id);

}
