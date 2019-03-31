package ru.otus.yakimov.hw5.service;

import ru.otus.yakimov.hw5.domain.Book;

public interface BookService {
    void add(Book book);

    Book findById(String isbn);

    void delete(String isbn);
}
