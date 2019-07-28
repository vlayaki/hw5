package ru.otus.yakimov.hw5.dao;

import ru.otus.yakimov.hw5.domain.BookGenre;

import java.util.List;

public interface BookGenreDao {
    void add(List<BookGenre> bookGenreList);

    List<BookGenre> findByBookId(String bookId);

    void delete(String bookId);
}
