package ru.otus.yakimov.hw5.service;

import ru.otus.yakimov.hw5.domain.Genre;

public interface GenreService {
    void add(Genre genre);

    Genre findById(long id);
}
