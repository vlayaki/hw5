package ru.otus.yakimov.hw5.dao;

import ru.otus.yakimov.hw5.domain.BookAuthor;

import java.util.List;

public interface BookAuthorDao {
    void add(List<BookAuthor> bookAuthorList);

    List<BookAuthor> findByBookId(String bookId);

    void delete(String isbn);
}
