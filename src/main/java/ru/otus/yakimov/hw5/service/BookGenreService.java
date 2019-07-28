package ru.otus.yakimov.hw5.service;

import ru.otus.yakimov.hw5.domain.BookGenre;

import java.util.List;

public interface BookGenreService {
    void add(List<BookGenre> bookGenreList);

    List<BookGenre> findByBookId(String bookId);

    void delete(String isbn);
}
