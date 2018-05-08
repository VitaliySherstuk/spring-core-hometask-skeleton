package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.DomainObject;

import java.util.Collection;

public interface AbstractDao<T extends DomainObject> {

    public T add(T object);

    public void remove(T object);

    public Collection<T> getAll();
}
