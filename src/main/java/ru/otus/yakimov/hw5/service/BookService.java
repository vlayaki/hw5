package ru.otus.yakimov.hw5.service;

import ru.otus.yakimov.hw5.domain.Book;

import java.util.List;

public interface BookService {
    void add(Book book);

    Book findById(String isbn);

    List<Book> findBooks();

}
