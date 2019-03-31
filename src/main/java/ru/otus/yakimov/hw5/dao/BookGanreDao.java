package ru.otus.yakimov.hw5.dao;

import ru.otus.yakimov.hw5.domain.BookGanre;

import java.util.List;

public interface BookGanreDao {
    void add(List<BookGanre> bookGanreList);

    List<BookGanre> findByBookId(String bookId);

    void delete(String bookId);
}
