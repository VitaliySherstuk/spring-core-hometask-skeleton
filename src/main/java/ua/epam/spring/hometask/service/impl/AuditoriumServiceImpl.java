package ua.epam.spring.hometask.service.impl;

import ua.epam.spring.hometask.dao.AuditoriumDao;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.AuditoriumService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;


public class AuditoriumServiceImpl implements AuditoriumService {

    private AuditoriumDao auditoriumDao;

    public AuditoriumServiceImpl(AuditoriumDao auditoriumDao)
    {

        this.auditoriumDao = auditoriumDao;
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {

        return new HashSet<>(auditoriumDao.getAll());
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return auditoriumDao.getByName(name);
    }
}
