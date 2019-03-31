package ru.otus.yakimov.hw5.dao;

import ru.otus.yakimov.hw5.domain.Author;

public interface AuthorDao {
    void add(Author author);

    Author findById(long id);

}
