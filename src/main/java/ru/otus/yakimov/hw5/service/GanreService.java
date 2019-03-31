package ru.otus.yakimov.hw5.service;

import ru.otus.yakimov.hw5.domain.Ganre;

public interface GanreService {
    void add(Ganre ganre);

    Ganre findById(long id);
}
