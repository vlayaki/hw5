package ru.otus.yakimov.hw5.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.yakimov.hw5.dao.BookDao;
import ru.otus.yakimov.hw5.domain.Book;
import ru.otus.yakimov.hw5.service.BookService;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Override
    public void add(Book book) {
        bookDao.add(book);
    }

    @Override
    public Book findById(String isbn) {
        return bookDao.findById(isbn);
    }
}
