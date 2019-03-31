package ru.otus.yakimov.hw5.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.yakimov.hw5.dao.BookAuthorDao;
import ru.otus.yakimov.hw5.domain.BookAuthor;
import ru.otus.yakimov.hw5.service.BookAuthorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookAuthorServiceImpl implements BookAuthorService {

    private final BookAuthorDao bookAuthorDao;

    @Override
    public void add(List<BookAuthor> bookAuthorList) {
        bookAuthorDao.add(bookAuthorList);
    }

    @Override
    public List<BookAuthor> findByBookId(String bookId) {
        return bookAuthorDao.findByBookId(bookId);
    }

    @Override
    public void delete(String bookId) {
        bookAuthorDao.delete(bookId);
    }
}
