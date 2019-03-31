package ru.otus.yakimov.hw5.service;

import ru.otus.yakimov.hw5.domain.Author;

public interface AuthorService {
    void add(Author author);

    Author findById(long id);
}
