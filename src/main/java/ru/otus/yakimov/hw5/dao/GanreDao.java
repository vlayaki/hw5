package ru.otus.yakimov.hw5.dao;

import ru.otus.yakimov.hw5.domain.Ganre;

public interface GanreDao {
    void add(Ganre ganre);

    Ganre findById(long id);
}
