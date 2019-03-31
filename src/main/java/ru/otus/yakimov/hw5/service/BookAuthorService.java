package ru.otus.yakimov.hw5.service;

import ru.otus.yakimov.hw5.domain.BookAuthor;

import java.util.List;

public interface BookAuthorService {
    void add(List<BookAuthor> bookAuthor);

    List<BookAuthor> findByBookId(String bookId);

    void delete(String bookId);
}
