package ru.otus.yakimov.hw5.service;

import ru.otus.yakimov.hw5.domain.Author;

import java.util.Map;

public interface AuthorService {
    void add(Author author);

    Author findById(long id);

    Map<Long, Author> getIdToAuthorMap();
}
