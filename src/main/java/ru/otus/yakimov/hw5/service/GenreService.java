package ru.otus.yakimov.hw5.service;

import ru.otus.yakimov.hw5.domain.Genre;

import java.util.Map;
import java.util.Set;

public interface GenreService {
    void add(Genre genre);

    Genre findById(long id);

    Map<Long, Genre> getGenreIdToGenreMap();
}
