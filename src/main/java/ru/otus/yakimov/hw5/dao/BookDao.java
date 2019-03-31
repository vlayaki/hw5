package ru.otus.yakimov.hw5.dao;

import ru.otus.yakimov.hw5.domain.Book;

import java.util.List;

public interface BookDao {
    Book findById(String id);

    List<Book> findAll();

    void add(Book book);

    void delete(String id);

}
