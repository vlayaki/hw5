package ru.otus.yakimov.hw5.service;

import ru.otus.yakimov.hw5.domain.BookGanre;

import java.util.List;

public interface BookGanreService {
    void add(List<BookGanre> bookGanreList);

    List<BookGanre> findByBookId(String bookId);

    void delete(String isbn);
}
