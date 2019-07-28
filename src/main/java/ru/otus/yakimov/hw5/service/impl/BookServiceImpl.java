package ru.otus.yakimov.hw5.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.yakimov.hw5.common.Utils;
import ru.otus.yakimov.hw5.dao.BookDao;
import ru.otus.yakimov.hw5.domain.Book;
import ru.otus.yakimov.hw5.domain.BookAuthor;
import ru.otus.yakimov.hw5.domain.BookGenre;
import ru.otus.yakimov.hw5.service.BookAuthorService;
import ru.otus.yakimov.hw5.service.BookGenreService;
import ru.otus.yakimov.hw5.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final BookAuthorService bookAuthorService;
    private final BookGenreService bookGenreService;

    @Override
    public void add(Book book) {
        bookDao.add(book);
        List<BookAuthor> bookAuthorList = Utils.fromBookToBookAuthor(book);
        bookAuthorService.add(bookAuthorList);
        List<BookGenre> bookGenreList = Utils.fromBookToBookGenre(book);
        bookGenreService.add(bookGenreList);
    }

    @Override
    public Book findById(String isbn) {
        return bookDao.findById(isbn);
    }

    @Override
    public void delete(String isbn) {
        bookAuthorService.delete(isbn);
        bookGenreService.delete(isbn);
        bookDao.delete(isbn);
    }
}
