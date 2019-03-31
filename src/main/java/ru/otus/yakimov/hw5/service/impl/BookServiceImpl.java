package ru.otus.yakimov.hw5.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.yakimov.hw5.common.Utils;
import ru.otus.yakimov.hw5.dao.BookDao;
import ru.otus.yakimov.hw5.dao.BookGanreDao;
import ru.otus.yakimov.hw5.domain.Book;
import ru.otus.yakimov.hw5.domain.BookAuthor;
import ru.otus.yakimov.hw5.domain.BookGanre;
import ru.otus.yakimov.hw5.service.BookAuthorService;
import ru.otus.yakimov.hw5.service.BookGanreService;
import ru.otus.yakimov.hw5.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final BookAuthorService bookAuthorService;
    private final BookGanreService bookGanreService;

    @Override
    public void add(Book book) {
        bookDao.add(book);
        List<BookAuthor> bookAuthorList = Utils.fromBookToBookAuthor(book);
        bookAuthorService.add(bookAuthorList);
        List<BookGanre> bookGanreList = Utils.fromBookToBookGanre(book);
        bookGanreService.add(bookGanreList);
    }

    @Override
    public Book findById(String isbn) {
        return bookDao.findById(isbn);
    }

    @Override
    public void delete(String isbn) {
        bookAuthorService.delete(isbn);
        bookGanreService.delete(isbn);
        bookDao.delete(isbn);
    }
}
