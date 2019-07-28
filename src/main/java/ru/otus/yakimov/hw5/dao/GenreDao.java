package ru.otus.yakimov.hw5.dao;

import ru.otus.yakimov.hw5.domain.Genre;

public interface GenreDao {
    void add(Genre genre);

    Genre findById(long id);
}
