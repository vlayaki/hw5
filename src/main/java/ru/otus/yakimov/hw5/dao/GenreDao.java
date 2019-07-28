package ru.otus.yakimov.hw5.dao;

import ru.otus.yakimov.hw5.domain.Genre;

import java.util.Map;

public interface GenreDao {
    void add(Genre genre);

    Genre findById(long id);


    Map<Long, Genre> getGenreIdToGenreMap();
}
