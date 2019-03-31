package ru.otus.yakimov.hw5.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.yakimov.hw5.dao.GanreDao;
import ru.otus.yakimov.hw5.domain.Ganre;
import ru.otus.yakimov.hw5.service.GanreService;

@Service
@RequiredArgsConstructor
public class GanreServiceImpl implements GanreService {

    private final GanreDao ganreDao;

    @Override
    public void add(Ganre ganre) {
        ganreDao.add(ganre);
    }

    @Override
    public Ganre findById(long id) {
        return ganreDao.findById(id);
    }
}
